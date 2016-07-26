package org.validoc.shoppingCart

import org.mockito.Mockito

class ListCommandSpec extends ShoppingCartSpec with ShoppingCartsFixture {

  import Mockito._

  "The ListCommand" should "list the items available for purchase" in {
    implicit val itemFinder = mock[ItemFinder]
    val list = List(("1", apple), ("2", banana))
    when(itemFinder.list) thenReturn list
    val startBasket = List(apple)
    val CommandResponse(newBasket, message) = ListCommand().apply(startBasket)
    newBasket shouldBe startBasket
    message shouldBe "1: Apple for £0.50\n2: Banana for £0.30"
  }
}
