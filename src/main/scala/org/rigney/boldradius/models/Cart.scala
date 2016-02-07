package org.rigney.boldradius.models

/* Cart is a basic shim to hold out shopping cart.
 * @param unbundledItems items that haven't been used in a bundle yet.
 * @param bundledItems items that belong to a bundle
 */
case class Cart(unbundledItems:Seq[CartItem] = Nil, bundledItems:Seq[BundledItem] = Nil) {
  def total: BigDecimal = (unbundledItems ++ bundledItems).map(_.total).sum
  override def toString:String = s"""unbundled:${unbundledItems.mkString(",")}:bundled:${bundledItems.mkString(",")}:total:${total}"""
}