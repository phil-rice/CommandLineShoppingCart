package org.validoc.shoppingCart

class SaleableItemSpec extends ShoppingCartSpec with ShoppingCartsFixture {

  "A saleable item" should "have a decent toDisplay" in {
    apple.toDisplay shouldBe "Apple for £0.50"
  }


}
