package org.rigney.boldradius.services

import org.rigney.boldradius.models.Cart
import org.rigney.boldradius.models.Bundle

trait BundleService {
  //Calculate the price for the items in a cart for this bundle.
  def calculateBundling(bundle:Bundle, cart:Cart):Cart
  //Calculate the best price for a cart
  def calculateBestBundling(cart:Cart)(implicit bundles:Seq[Bundle]):Cart
  //Calculate all bundles that apply for this cart
  def calculateBundles(cart:Cart)(implicit bundles:Seq[Bundle]):Seq[Cart]
}