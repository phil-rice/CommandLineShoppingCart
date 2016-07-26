package org.validoc.shoppingCart

import org.scalatest.{FlatSpec, Matchers}

class MoneySpec extends FlatSpec with Matchers {

  "Money" should "have an easy creation method" in {
    Money(1) shouldBe new Money(1)
    Money(10) shouldBe new Money(10)
  }

  it should "have agood tostring for +ve numbers" in {
    Money(0).toString shouldBe "£0.00"
    Money(1).toString shouldBe "£0.01"
    Money(103).toString shouldBe "£1.03"
    Money(1000).toString shouldBe "£10.00"
  }

  it should "have agood tostring for -ve numbers" in {
    Money(-1).toString shouldBe "(£0.01)"
    Money(-103).toString shouldBe "(£1.03)"
    Money(-1000).toString shouldBe "(£10.00)"
  }

  it should "have a negated method that returns a money with 'minus the amount'" in {
    Money(0).negated shouldBe Money(0)
    Money(1).negated shouldBe Money(-1)
    Money(103).negated shouldBe Money(-103)
    Money(1000).negated shouldBe Money(-1000)
    Money(-1).negated shouldBe Money(1)
    Money(-103).negated shouldBe Money(103)
    Money(-1000).negated shouldBe Money(1000)
  }

  it should "allow + between two moneys" in {
    Money(0) + Money(1) shouldBe Money(1)
    Money(-3) + Money(1) shouldBe Money(-2)
  }

  it should "allow - between two moneys" in {
    Money(0) - Money(1) shouldBe Money( - 1)
    Money(-3) - Money(1) shouldBe Money(-4)
    Money(3) - Money(2) shouldBe Money(1)
  }

  it should "allow * n" in {
    Money(10) * 0 shouldBe Money(0)
    Money(10) * 3 shouldBe Money(30)
  }

  it should "produce money from a string representation" in {
    Money("£1.23") shouldBe Money(123)
    Money("£999.99") shouldBe Money(99999)
    Money("£80") shouldBe Money(8000)
  }
}
