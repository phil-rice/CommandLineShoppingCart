package org.validoc.shoppingCart

import org.mockito.Mockito._

class RemoveCommandSpec extends ShoppingCartSpec with ShoppingCartsFixture {

  "The Remove Command" should "remove an item if there is one in the basket" in {
    val CommandResponse(newBasket, message) = RemoveCommand(List(apple, banana), apple)
    newBasket shouldBe List(banana)
    message shouldBe "Removed Apple"
  }

  it should "only remove one item if there are multiple in the basket" in {
    val CommandResponse(newBasket, message) = RemoveCommand(List(apple, apple, banana), apple)
    newBasket shouldBe List(apple, banana)
    message shouldBe "Removed Apple"
  }

  it should "say cannot find item if the item isn't in the basket" in {
    val startBasket = List(banana)
    val CommandResponse(newBasket, message) = RemoveCommand(startBasket, apple)
    newBasket shouldBe startBasket
    message shouldBe "Could not remove Apple as none were in the basket"
  }
}
