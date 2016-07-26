package org.validoc.shoppingCart

import org.scalatest.{FlatSpec, Matchers}


class ItemFinderSpec extends FlatSpec with Matchers with ShoppingCartsFixture {
  val itemFinder = new ItemFinder(nameToSaleableItem)

  "An item finder" should "find the item specified by its name" in {
    itemFinder("A") shouldBe Some(apple)
    itemFinder("B") shouldBe Some(banana)
    itemFinder("C") shouldBe Some(crisps)
    itemFinder("D") shouldBe Some(donuts)
  }

  it should "return None if the item wasn't found " in {
    itemFinder("not legal") shouldBe None
  }
  it should "return the sorted list of keys mapped to saleable items" in {
    itemFinder.list shouldBe List(("A", apple), ("B", banana), ("C", crisps), ("D", donuts))
  }

}
