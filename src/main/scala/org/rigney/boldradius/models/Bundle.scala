package org.rigney.boldradius.models

/**
 * BundleItem a cart item inside a bundle.
 * @param item The Catalog Item this bundling applies to
 * @param discount The discount that will be applied to the item
 * @param numberRequired The number of this item that must be present for this bundle to apply.
 *
 * Note this BundleItem is used as a _template_.  It will not actually be present in a cart. 
 */
case class BundleItem(item: CatalogItem, discount: BigDecimal, numberRequired: Integer)

/** Bundle is a collection of BundleItems for later bundling.
 *  @param name The name of the Bundle
 *  @param items The items that must be present for the bundle to apply
 */
case class Bundle(name: String, items: Seq[BundleItem]) {
  /** The discount that will be applied by this bundling.
   *  Intended for displaying bundles.
   */
  def discount: BigDecimal = items.map(_.discount).sum
  
  /** appliesTo determines if a bundle can be applied to the unbundled items in a cart.
   *  @param cart The cart to attempt to apply this bundle to.
   *  
   *  This is intended to improve performance when applying bundles.
   */
  def appliesTo(cart: Cart): Boolean = items.forall { item: BundleItem =>
    cart.unbundledItems.exists { i =>
      i.item.equals(item.item) && (item.numberRequired <= i.count)
    }
  }
  
  /** Calculate the resultant cart for this bundle
   *  @param cart The cart we're going to apply this bundle to.
   *  @return The cart that results from this bundling.
   */
  def calculateBundle(cart: Cart): Cart = {
    val newunbundled = cart.unbundledItems.map { cartItem =>
      val newCount = items.find(_.item.equals(cartItem.item))
          .flatMap { i => Some(cartItem.count - i.numberRequired)}.getOrElse(cartItem.count.toInt)
      cartItem.copy(count = newCount)
    }.dropWhile(_.count == 0)

    val newBundled = cart.bundledItems ++ items.map { item =>
      BundledItem(item.item, item.numberRequired,item.discount)
    }.dropWhile(_.count == 0)
    
    new Cart(newunbundled, newBundled)
  }
}