package org.validoc.shoppingCart

import org.mockito.Mockito
import org.scalatest.mock.MockitoSugar

class AddCommandSpec extends ShoppingCartSpec with ShoppingCartsFixture {

  import Mockito._


  "The Add Command" should "add the item to an empty basket" in {
    val CommandResponse(newBasket, message) = AddCommand(emptyBasket, apple)
    newBasket shouldBe List(apple)
    message shouldBe "Added Apple for £0.50"
  }

  it should "Add an item to the end of an existing basket" in {
    val CommandResponse(newBasket, message) = AddCommand(List(banana), apple)
    newBasket shouldBe List(banana, apple)
    message shouldBe "Added Apple for £0.50"
  }
}
