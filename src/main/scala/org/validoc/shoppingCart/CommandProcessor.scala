package org.validoc.shoppingCart

class CommandProcessor(commands: Map[String, Command])(implicit itemFinder: ItemFinder) {

  def apply(command: String, basket: ShoppingBasket): CommandResponse = {
    val parts = command.split(" ").map(_.trim).filter(_.length > 0)
    if (parts.size > 0)
      processCommand(parts, basket)
    else
      CommandResponse(basket, Command.usage)
  }

  def processCommand(parts: Seq[String], basket: ShoppingBasket) =
    commands.get(parts(0)).fold(CommandResponse(basket, s"Command [${parts(0)}] not understood\n" + Command.usage))((command =>
      (command, parts.size)  match {
        case (basketCommand: BasketCommand, 1) => basketCommand(basket)
        case (basketCommand: BasketCommand, _) => CommandResponse(basket, "Cannot specify items with this command")
        case (itemCommand: ItemCommand, 2) => itemFinder(parts(1)).fold(CommandResponse(basket, Command.cannotFindMessage(parts(1))))(itemCommand(basket, _))
        case (itemCommand: ItemCommand, 1) => CommandResponse(basket, s"Must specify an item for command [${parts(0)}]")
        case (itemCommand: ItemCommand, _) => CommandResponse(basket, "Cannot specify more than one item with this command")
      })
    )
}

