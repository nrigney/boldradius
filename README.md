

## Assumptions

+ Catalog items and Bundles are provided in implicit vals.  The requirements were unclear what an "initialization" would entail.  An implementation with initialization would just pass context objects around.
+ All calls are blocking since reentrancy was required, but not asynchronous operation.
+ All the operations for managing Bundles and Catalog items was assumed to be out of scope for this exercise.
+ No space or time requirements were given, so no real effort was made to optimize for space or time.

## Usage

This code was exercised through the unit and integration tests in src/test/scala.  There are 12 tests in 5 suites that cover the functionality described in the requirements.  ```sbt test``` will execute the tests.

```src/test/scala/InitalState.scala``` contains the definitions for the Catalog and Bundles.

## Design Overview

All artifacts have ScalaDoc.

The algorithm used to identify the cheapest cart (defined only as "lowest monetary value") is to take all the available packages, permute that list, and apply the resultant combinations one by one to the cart.  The resultant list of prices is sorted by cost, and the head is returned.

Note a minor optimization in employed -- the code only permutes the packages that are applicable to the cart, which would significantly cut down the search space for large carts or packages. 