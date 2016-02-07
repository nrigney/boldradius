package org.rigney.boldradius.models

/** CatalogItem is a simplistic representation of a user-selectable item.
 *  @param description The ID/description of the item.
 *  @param price The price of the item.
 *  
 *  Note that since this is an (over) simplification of a shopping cart, no real business logic has been applied:
 *  descriptions can be empty, prices can be negative or 0.
 */
case class CatalogItem(description:String, price:BigDecimal)
