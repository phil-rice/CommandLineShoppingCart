package org.validoc.shoppingCart

import scala.io.Source


object ItemLoader {
  def apply(reference: String): ItemFinder = {
   val idToSalableItem =  Source.fromInputStream(getClass.getClassLoader.getResourceAsStream(reference), "UTF-8").getLines().drop(1).map {
      line =>
        val Array(id, name, price) = line.split(",").map(_.trim).filter(_.length > 0)
        val money = Money(price)
        (id -> SaleableItem(name, money))
    }.toMap
    ItemFinder(idToSalableItem)
  }

}
