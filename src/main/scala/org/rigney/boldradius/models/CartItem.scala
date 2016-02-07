package org.rigney.boldradius.models

/**
 * SummableItems is a root trait for items that can be tallied.
 * def total:BigDecimal must represent a single total for all items in the class
 */
trait SummableItems {
  def total:BigDecimal
}

/** BundledItem is an item that exists in a Cart as part of a bundle.
 *  @param item The Catalog Item that is the base for this item.
 *  @param count How many Catalog Items there are in this item
 *  @param discount the discount that will be applied to the item by the bundle. 
 */
case class BundledItem(item:CatalogItem, count:Integer, discount: BigDecimal) extends SummableItems {
  def total:BigDecimal = item.price * BigDecimal(count) - discount
}

/** CartItem is an item that exists in a Cart as part of the user's unbundled items.
 *  @param item The Catalog Item that is the base for this item.
 *  @param count How many of the Catalog Item the user has selected.
 */
case class CartItem(item:CatalogItem, count:Integer) extends SummableItems {
    def total:BigDecimal = item.price * BigDecimal(count)

}