package org.validoc.shoppingCart

class TotalCommandSpec extends ShoppingCartSpec with ShoppingCartsFixture {

  "The TotalCommand" should "display $0.00 if the basket is empty" in {
    val CommandResponse(newBasket, message) = TotalCommand.apply(emptyBasket)
    newBasket shouldBe emptyBasket
    message shouldBe "Total: £0.00"
  }

  it should "display the value of the items in the shopping basket" in {
    val startBasket = List(apple, banana)
    val CommandResponse(newBasket, message) = TotalCommand.apply(startBasket)
    newBasket shouldBe startBasket
    message shouldBe "Total: £0.80"

  }

}
