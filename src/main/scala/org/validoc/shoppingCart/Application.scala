package org.validoc.shoppingCart

import scala.io.StdIn

object Application extends App {

  println("************************************")
  println("* Welcome to the Net-A-Porter Shop *")
  println("************************************")
  println("Enter \"Q\" to Quit")
  println("Enter \"add <ProductId>\" to add to basket")
  println("Enter \"remove <ProductId>\" to remove from basket")
  println("Enter \"items\" to see what is in the basket")
  println("Enter \"list\" to show a list of products in the inventory")
  println("Enter \"total\" to show the total price of the basket")

  val apple = SaleableItem("Apple", Money(50))
  val banana = SaleableItem("Banana", Money(30))
  val crisps = SaleableItem("Crisps", Money(20))
  val donuts = SaleableItem("Donuts", Money(15))

  val nameToSaleableItem = Map("A" -> apple, "B" -> banana, "C" -> crisps, "D" -> donuts)


 implicit  val itemFinder = ItemLoader("items.csv")

  val commandProcessor = new CommandProcessor(Map(
    "add" -> AddCommand,
    "remove" -> RemoveCommand,
    "list" -> ListCommand(),
    "items" -> ItemsCommand,
    "total" -> TotalCommand
  ))
  var basket: ShoppingBasket = List()
  while (true) {
    val command = StdIn.readLine()
    println(s"Command: $command")
    if (command == "Q") {
      println("Thanks for shopping at Net-a-Porter!")
      System.exit(0)
    }
    val CommandResponse(newBasket, message) = commandProcessor(command, basket)
    basket = newBasket
    println(message)
  }
}
