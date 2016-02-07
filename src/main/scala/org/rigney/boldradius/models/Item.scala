package org.rigney.boldradius.models

import scala.math.BigDecimal

trait Item {
  def price: BigDecimal
  def id: String
}

case class BaseItem(val id:String, val price:BigDecimal) extends Item {
  
}

case class BundlesItem(val base:BaseItem, val discount : BigDecimal) extends Item {
  def price: BigDecimal = base.price - discount
  def id:String = base.id
}
