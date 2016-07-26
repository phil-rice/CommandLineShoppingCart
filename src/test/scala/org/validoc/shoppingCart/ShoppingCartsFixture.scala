package org.validoc.shoppingCart

import org.scalatest.mock.MockitoSugar

trait ShoppingCartsFixture extends MockitoSugar {
  val apple = SaleableItem("Apple", Money(50))
  val banana = SaleableItem("Banana", Money(30))
  val crisps = SaleableItem("Crisps", Money(20))
  val donuts = SaleableItem("Donuts", Money(15))

  val nameToSaleableItem = Map("A" -> apple, "B" -> banana, "C" -> crisps, "D" -> donuts)

  def withItemFinder(fn: ItemFinder => Unit) = {
    val itemFinder = mock[ItemFinder]
    fn(itemFinder)
  }


  val emptyBasket: ShoppingBasket = List()

}
