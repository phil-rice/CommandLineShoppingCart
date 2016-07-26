package org.validoc.shoppingCart

class ItemLoaderSpec extends ShoppingCartSpec {

  "An ItemLoader" should "create an ItemFinder from the specified file" in {

    ItemLoader("TestItems.csv") shouldBe ItemFinder(Map(
      "1" -> SaleableItem("Short Sleeve Jumper", Money(999)),
      "2" -> SaleableItem("Shoulder Bag", Money(999)),
      "3" -> SaleableItem("Skinny Jeans", Money(4500)),
      "4" -> SaleableItem("Leather Jeans", Money(8000)),
      "5" -> SaleableItem("Leather Jacket", Money(19999)),
      "6" -> SaleableItem("Wool Socks", Money(2050)),
      "7" -> SaleableItem("Piqué Polo shirt", Money(5055))
    ))
  }

}
