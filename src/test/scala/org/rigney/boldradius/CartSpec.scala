package org.rigney.boldradius

import scala.BigDecimal

import org.rigney.boldradius.models._
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class CartSpec extends FlatSpec with Matchers {

  import InitialState._
  
  val catalogItem1 = new CatalogItem("item1",BigDecimal(5.0))
  val singleCartItem = new CartItem(catalogItem1,1)
  
  "A Cart" should "calculate cart items properly" in {
    val testCart = new Cart(Seq(singleCartItem, singleCartItem))
    
    assert(testCart.total == BigDecimal(10.0)) 
        
  }
    
}