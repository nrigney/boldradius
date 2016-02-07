package org.rigney.boldradius

import org.rigney.boldradius.models.CatalogItem
import org.rigney.boldradius.models.Bundle
import org.rigney.boldradius.models.BundleItem

/** InitialState is the base catalog and bundle set for testing */
object InitialState {
  //Test catalog items
  implicit val testCatalog:Seq[CatalogItem] = Seq(
      CatalogItem("item1",BigDecimal(1.0)),
      CatalogItem("item2",BigDecimal(2.0)),
      CatalogItem("item3",BigDecimal(3.0)),
      CatalogItem("item4",BigDecimal(4.0)),
      CatalogItem("item5",BigDecimal(5.0)),
      CatalogItem("item6",BigDecimal(6.0)),
      CatalogItem("item7",BigDecimal(7.0)),
      CatalogItem("item8",BigDecimal(8.0)))

      /* bundle1 is a BOGO offer -- discount the price of one testCatalog item if 2 are present */ 
      val bundle1 = Bundle(name = "BOGO1",
          items = Seq(BundleItem(testCatalog(1),testCatalog(1).price, 2))
          )
      val bundle2 = Bundle(name = "1OFF2",
          items = Seq(BundleItem(testCatalog(1),BigDecimal(0), 1),
                      BundleItem(testCatalog(2),BigDecimal(1), 2))
          )

      /* An implicit of bundles to be used in testing. */
      implicit val testBundles:Seq[Bundle] = Seq(bundle1, bundle2)
}