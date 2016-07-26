package org.validoc.shoppingCart

case class CommandResponse(shoppingBasket: ShoppingBasket, message: String)

object Command {
  def cannotFindMessage(itemCode: Item) = s"Cannot find item [$itemCode]"

  def usage = "Legal commands: add, remove, list, items,  total"
}

sealed trait Command

sealed trait BasketCommand extends Command {
  def apply(basket: ShoppingBasket): CommandResponse
}

sealed trait ItemCommand extends Command {
  def apply(basket: ShoppingBasket, item: SaleableItem): CommandResponse
}


object ItemsCommand extends BasketCommand {
  def apply(basket: ShoppingBasket) = basket match {
    case Nil => CommandResponse(basket, "No items in basket")
    case _ => CommandResponse(basket, basket.map(_.toDisplay).mkString("Items:\n", "\n", ""))
  }
}

case class ListCommand(implicit itemFinder: ItemFinder) extends BasketCommand {
  def apply(basket: ShoppingBasket) =
    CommandResponse(
      basket,
      itemFinder.list.map { case (id, item) => s"$id: ${item.toDisplay}" }.mkString("\n"))
}


object TotalCommand extends BasketCommand {
  def apply(basket: ShoppingBasket): CommandResponse = {
    val total = basket.foldLeft(Money(0))((acc, item) => acc + item.cost)
    CommandResponse(basket, s"Total: $total")
  }
}


object AddCommand extends ItemCommand {
  def apply(basket: ShoppingBasket, item: SaleableItem) = CommandResponse(basket :+ item, s"Added ${item.item} for ${item.cost}")

}


object RemoveCommand extends ItemCommand {
  def apply(basket: ShoppingBasket, item: SaleableItem): CommandResponse =
    basket.indexWhere(_ == item) match {
      case -1 => CommandResponse(basket, s"Could not remove ${item.item} as none were in the basket")
      case index => CommandResponse(basket.take(index) ++ basket.drop(index + 1), s"Removed ${item.item}")
    }

}
