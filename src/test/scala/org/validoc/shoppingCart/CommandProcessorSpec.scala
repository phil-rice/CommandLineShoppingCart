package org.validoc.shoppingCart

import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar

class CommandProcessorSpec extends ShoppingCartSpec with ShoppingCartsFixture {
  val startBasket = List(apple)
  val someResponse = CommandResponse(List(banana), "someMessage")

  val itemFinder = mock[ItemFinder]

  "The Command Processor" should "execute the basket command implied by the command on the basket" in {
    withItemFinder { implicit itemFinder =>
      val command1 = mock[BasketCommand]
      val command2 = mock[Command]
      when(command1.apply(startBasket)) thenReturn someResponse

      val processor = new CommandProcessor(Map("one" -> command1, "two" -> command2))

      processor("one", startBasket) shouldBe someResponse
    }
  }

  it should "execute an item comamnd implied by the command on the item" in {
    withItemFinder { implicit itemFinder =>
      val command1 = mock[ItemCommand]
      val command2 = mock[Command]

      when(itemFinder.apply("A")) thenReturn Some(apple)
      when(command1.apply(startBasket, apple)) thenReturn someResponse

      val processor = new CommandProcessor(Map("one" -> command1, "two" -> command2))

      processor("one A", startBasket) shouldBe someResponse
    }
  }

  it should "should report that an item is needed if an item command is specified without an item" in {
    withItemFinder { implicit itemFinder =>
      val command1 = mock[ItemCommand]
      val command2 = mock[Command]

      val processor = new CommandProcessor(Map("one" -> command1, "two" -> command2))

      processor("one", startBasket) shouldBe CommandResponse(startBasket, "Must specify an item for command [one]")
    }
  }
  it should "return a help message if an unknown command was given" in {
    withItemFinder { implicit itemFinder =>
      val command1 = mock[Command]
      val command2 = mock[Command]
      val processor = new CommandProcessor(Map("one" -> command1, "two" -> command2))

      processor("Q", startBasket) shouldBe CommandResponse(startBasket, "Command [Q] not understood\n" + Command.usage)
    }
  }


  it should "return instructions if there is no command given" in {
    withItemFinder { implicit itemFinder =>
      val command1 = mock[Command]
      val command2 = mock[Command]
      val processor = new CommandProcessor(Map("one" -> command1, "two" -> command2))

      processor("  ", startBasket) shouldBe CommandResponse(startBasket, Command.usage)
    }
  }

  it should "return instructions if too many command parts are given for an item command " in {
    withItemFinder { implicit itemFinder =>
      val command1 = mock[ItemCommand]
      val command2 = mock[Command]
      val processor = new CommandProcessor(Map("one" -> command1, "two" -> command2))

      processor("one a b c", startBasket) shouldBe CommandResponse(startBasket, "Cannot specify more than one item with this command")
    }
  }

  it should "return instructions if too many command parts are given for a basket command " in {
    withItemFinder { implicit itemFinder =>
      val command1 = mock[BasketCommand]
      val command2 = mock[Command]
      val processor = new CommandProcessor(Map[String, Command]("one" -> command1, "two" -> command2))

      processor("one a b c", startBasket) shouldBe CommandResponse(startBasket, "Cannot specify items with this command")
    }
  }
}
