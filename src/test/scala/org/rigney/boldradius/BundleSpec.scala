package org.rigney.boldradius

//For this exercise, produce an API and implementation for a service that accepts a collection of
//items the customer wishes to purchase (e.g. items and quantities), and produces the lowest
//possible price for that collection of items. The API is to be called by other applications in the
//same JVM, e.g. don’t worry about providing a REST or other remote interface to the API, just
//the actual method calls is fine.

import org.scalatest.Matchers
import org.scalatest.FlatSpec
import org.rigney.boldradius.models._
import org.rigney.boldradius.services._
import scala.BigDecimal

class BundleSpec extends FlatSpec with Matchers {

  import InitialState._

  val bogoTestFailureCart = Cart(unbundledItems = Seq(
    CartItem(testCatalog(1), 1)))
  val bogoTestSuccessCart = Cart(unbundledItems = Seq(
    CartItem(testCatalog(1), 2)))
  val cart3 = Cart(Seq(
    CartItem(testCatalog(1), 3), 
    CartItem(testCatalog(2), 3)))

  val emptyCart = new Cart()
  
  "A Bundle" should "calculate a discount amount properly" in {
    bundle1.discount shouldBe (BigDecimal(2))
  }

  it should "only apply to carts with the minimum number of an item in it" in {
    bundle1.appliesTo(bogoTestFailureCart) shouldBe (false)
    bundle1.appliesTo(cart3) shouldBe (true)
  }

  it should "apply properly to a cart" in {
    bundle1.calculateBundle(cart3) shouldEqual(new Cart(
        Seq(CartItem(testCatalog(1),1),CartItem(testCatalog(2),3)),
        Seq(BundledItem(testCatalog(1),2,BigDecimal(2.0)))))
  }
}