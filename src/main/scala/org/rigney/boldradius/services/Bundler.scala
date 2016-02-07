package org.rigney.boldradius.services

import org.rigney.boldradius.models.Cart
import org.rigney.boldradius.models.Bundle

/** An implementation of the BundleService trait.
 *  This is a _very_ simple service designed to function for the most common cases.
 */
object Bundler extends BundleService {
  
  /** calculateBundling given a bundle will calculate the resultant cart after applying this bundle.
   *  @param bundle The bundle to apply this operation to
   *  @param cart The cart to apply the bundling to.
   */
  def calculateBundling(bundle: Bundle, cart: Cart): Cart = {
    bundle.calculateBundle(cart)
  }

  /** calculateBestBundling a convenience function for calculateBundles to return the best bundling for a cart.
   *  @param cart The cart to find the best bundle for.
   *  
   *  Note this function can be extremely slow for large bundle/cart combinations. 
   */
  def calculateBestBundling(cart: Cart)(implicit bundles: Seq[Bundle]): Cart = calculateBundles(cart).sortBy(_.total).headOption.getOrElse(cart)

  /** calculateBundles find all the bundles that apply for a cart
   *  @param cart The cart to find the applicable bundles for
   *  @return A sequence of all the carts that might result from applying bundles to the cart
   *  
   *  Note: This function is O(bundles.count * cart.item_count).  It may be _very_ slow.
   */
  def calculateBundles(cart: Cart)(implicit bundles: Seq[Bundle]): Seq[Cart] = {
    // Get the permuted list of bundles that apply to this cart and 
    //Apply each one to exhaustively search for the best.  Note this may take
    //a Really Long Time
    //Note we always tack on the original cart to ensure we never get back an empty list.
    Seq(cart) ++ bundles.filter(_.appliesTo(cart)).permutations.map { currentBundle =>
      currentBundle.foldLeft(cart) { (cart, bundle) =>
        bundle.calculateBundle(cart)
      }
    }.toList
  }
}