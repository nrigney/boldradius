package org.rigney.boldradius
//For this exercise, produce an API and implementation for a service that accepts a collection of
//items the customer wishes to purchase (e.g. items and quantities), and produces the lowest
//possible price for that collection of items. The API is to be called by other applications in the
//same JVM, e.g. don’t worry about providing a REST or other remote interface to the API, just
//the actual method calls is fine.


import org.scalatest.Matchers
import org.scalatest.FlatSpec

class APISpec extends FlatSpec with Matchers {

  // The API is initialized with information that provides all the possible bundles and the catalog of
  // items and their prices.
  
  "API" should "be initialized with all possible bundles" in {
      info("Ok")
    }
  // Once it is initialized, many calls can be made at once to the API to
  // produce a total price for collections of items,
  it should "produce a total price for a collection of items" in {
    info("Ok")
  }
  // and it should be able to handle multiple simultaneous calls without errors (e.g. if I’m computing the price for one call, that should not
  // interfere with computing the price for another call).
  it should "be reentrant" in {
    info("Ok")
  }
}