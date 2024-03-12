package kr.cosine.kiosk.food.impl.drink

import kr.cosine.kiosk.food.Food

abstract class Drink(
    name: String,
    price: Int
) : Food(name, price)