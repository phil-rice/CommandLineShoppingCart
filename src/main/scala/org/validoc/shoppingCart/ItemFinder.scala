package org.validoc.shoppingCart

case class ItemFinder(nameToSaleableItem: Map[Item, SaleableItem]) {
  def apply(item: Item): Option[SaleableItem] = nameToSaleableItem.get(item)

  def list= nameToSaleableItem.toList.sortBy(_._1)
}
