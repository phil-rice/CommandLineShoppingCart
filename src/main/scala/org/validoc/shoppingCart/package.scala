package org.validoc

package object shoppingCart {

  /* I'm just making it so that I can easily identify my Items if I change my mind about how to represent them (very likely) */
  type Item = String

  type ShoppingBasket = List[SaleableItem]
}
