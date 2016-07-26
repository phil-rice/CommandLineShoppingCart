package org.validoc.shoppingCart

case class SaleableItem(item: String, cost: Money ){
  def toDisplay =s"$item for $cost"
}
