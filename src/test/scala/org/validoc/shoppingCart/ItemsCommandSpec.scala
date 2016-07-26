package org.validoc.shoppingCart

class ItemsCommandSpec extends ShoppingCartSpec with ShoppingCartsFixture {

  "The items command" should "return the message 'No Items av'" in {
    ItemsCommand.apply(emptyBasket) shouldBe CommandResponse(emptyBasket, "No items in basket")
  }

  it should "list an item in the basket if there is one" in {
    val startBasket = List(apple)
    val CommandResponse(newBasket, message) = ItemsCommand.apply(startBasket)
    newBasket shouldBe startBasket
    message shouldBe "Items:\nApple for £0.50"
  }

  it should "list all items in the basket if there are more than one" in {
    val startBasket = List(apple, banana)
    val CommandResponse(newBasket, message) = ItemsCommand.apply(startBasket)
    newBasket shouldBe startBasket
    message shouldBe "Items:\nApple for £0.50\nBanana for £0.30"
  }
}
