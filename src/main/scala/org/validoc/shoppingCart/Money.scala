package org.validoc.shoppingCart

object Money {
  def apply(s: String): Money = {
    val withoutPrefix = s.dropWhile(ch => !Character.isDigit(ch))
    val index = withoutPrefix.indexOf('.')
    val moneyAsInt = index match {
      case -1 => withoutPrefix.toInt * 100
      case i => (withoutPrefix.take(i) ++ withoutPrefix.drop(i + 1)).toInt
    }
    Money(moneyAsInt)
  }
}

case class Money(amount: Int) {
  override def toString = {
    val rawAmount = f"£${Math.abs(amount) / 100.0}%.2f"
    if (amount >= 0) rawAmount else s"($rawAmount)"
  }

  def negated = Money(-amount)

  def +(other: Money) = Money(amount + other.amount)

  def -(other: Money) = Money(amount - other.amount)

  def *(n: Int) = Money(amount * n)
}

