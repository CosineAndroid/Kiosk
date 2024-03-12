package kr.cosine.kiosk

import kr.cosine.kiosk.food.Food

class Buyer {

    var money = 1000

    val cart = mutableListOf<Food>()

    val bought = mutableListOf<Food>()
}