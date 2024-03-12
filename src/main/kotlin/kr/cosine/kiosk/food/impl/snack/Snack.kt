package kr.cosine.kiosk.food.impl.snack

import kr.cosine.kiosk.food.Food

abstract class Snack(
    name: String,
    price: Int
) : Food(name, price)