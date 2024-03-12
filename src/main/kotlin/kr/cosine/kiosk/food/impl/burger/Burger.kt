package kr.cosine.kiosk.food.impl.burger

import kr.cosine.kiosk.food.Food

abstract class Burger(
    name: String,
    price: Int
) : Food(name, price)