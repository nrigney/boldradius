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

class BundleServiceSpec extends FlatSpec with Matchers {

  import InitialState._

  val bogoTestFailureCart = Cart(unbundledItems = Seq(
    CartItem(testCatalog(1), 1)))
  val bogoTestSuccessCart = Cart(unbundledItems = Seq(
    CartItem(testCatalog(1), 2)))
  val cart3 = Cart(Seq(new CartItem(testCatalog(1), 3), new CartItem(testCatalog(2), 3)))

  val emptyCart = new Cart()
  
  "The Bundle Service" should "work even for empty carts" in {
    Bundler.calculateBestBundling(emptyCart) shouldBe a [Cart]  
    Bundler.calculateBestBundling(emptyCart) shouldBe emptyCart  
  }

  //... a service that accepts a collection of
  //items the customer wishes to purchase (e.g. items and quantities), and produces the lowest
  //possible price for that collection of items
  it should "accept a collection of items and return a cart" in {
    Bundler.calculateBundling(bundle2, cart3) shouldBe a[Cart]
  }

  it should "return a list of carts to find the best" in {
    Bundler.calculateBundles(cart3) shouldBe a [Seq[_]]
  }
  it should "return the cheapest cart" in {
    assert(Bundler.calculateBestBundling(cart3).total == BigDecimal(12))
  }
}