package kr.cosine.kiosk

import kr.cosine.kiosk.food.Food
import kr.cosine.kiosk.food.impl.burger.impl.BulgogiBurger
import kr.cosine.kiosk.food.impl.burger.impl.CheeseBurger
import kr.cosine.kiosk.food.impl.drink.impl.Coke
import kr.cosine.kiosk.food.impl.drink.impl.Sprite
import kr.cosine.kiosk.food.impl.snack.impl.Ace
import kr.cosine.kiosk.food.impl.snack.impl.HomerunBall

private val rootCategories = mapOf(
    "버거" to listOf(BulgogiBurger(), CheeseBurger()),
    "음료수" to listOf(Coke(), Sprite()),
    "과자" to listOf(Ace(), HomerunBall()),
).toList()

private var money = 1000

private val cart = mutableListOf<Food>()

private val bought = mutableListOf<Food>()

fun main(args: Array<out String>) {
    main()
}

private fun main() {
    println("1. 메뉴판")
    println("2. 장바구니")
    println("3. 구매한 품목")
    println("")
    println("이동할 곳의 번호를 입력해주세요.")
    mainAction()
}

private fun mainAction() {
    val index = getIndex() + 1
    when (index) {
        1 -> menu()
        2 -> cart()
        3 -> bought()
        else -> {
            println("존재하지 않는 번호입니다.")
            mainAction()
        }
    }
}

private fun menu() {
    val categoryIndex = getCategoryIndex()
    if (categoryIndex + 1 == 0) {
        main()
        return
    }
    menu(categoryIndex)
}

private fun cart() {
    println("[ 장바구니 ] (보유 중인 돈: $money)")
    var totalPrice = 0
    cart.forEach { food ->
        val price = food.price
        totalPrice += price
        println("${food.name} (${price}원)")
    }
    println("-> 총 ${totalPrice}원이 필요합니다.")
    println("")
    println("0. 뒤로가기")
    println("1. 구매하기")
    println("2. 초기화")
    println("실행할 번호를 입력해주세요.")
    cartAction()
}

private fun cartAction() {
    val index = getIndex() + 1
    when (index) {
        0 -> main()

        1 -> {
            if (cart.isEmpty()) {
                println("장바구니가 비어있습니다.")
                cartAction()
                return
            }
            val price = cart.sumOf { it.price }
            if (money < price) {
                println("보유 중인 돈이 부족합니다.")
                cartAction()
                return
            }
            money -= price
            println("구매를 완료하였습니다! (남은 돈: $money)")
            println("")
            bought.addAll(cart)
            cart.clear()
            main()
        }

        2 -> {
            if (cart.isEmpty()) {
                println("장바구니가 비어있습니다.")
                cartAction()
                return
            }
            cart.clear()
            println("장바구니를 초기화하였습니다.")
            cart()
        }

        else -> {
            println("존재하지 않는 번호입니다.")
            cartAction()
        }
    }
}

private fun bought() {
    println("[ 구매한 품목 ]")
    var expenses = 0
    bought.forEach { food ->
        val price = food.price
        expenses += price
        println("${food.name} (${price}원)")
    }
    println("-> 총 ${expenses}원 지출하였습니다.")
    println("")
    println("0. 뒤로가기")
    boughtAction()
}

private fun boughtAction() {
    val index = getIndex() + 1
    when (index) {
        0 -> main()
        else -> {
            println("존재하지 않는 번호입니다.")
            boughtAction()
        }
    }
}

private fun getCategoryIndex(): Int {
    println("[ 메뉴판 ]")
    println("0. 뒤로가기")
    rootCategories.forEachIndexed { index, (categoryName, _) ->
        println("${index + 1}. $categoryName")
    }
    println("")
    println("카테고리 번호를 입력해주세요.")
    return getIndex()
}


private fun menu(categoryIndex: Int) {
    val category = rootCategories[categoryIndex]
    println("")
    println("[ ${category.first} 메뉴판 ] (보유 중인 돈: $money)")
    println("0. 뒤로가기")
    val foods = category.second
    foods.forEachIndexed { index, food ->
        println("${index + 1}. ${food.name} (${food.price}원)")
    }
    putMenu(foods, categoryIndex)
}

private fun putMenu(foods: List<Food>, categoryIndex: Int) {
    val food = getMenu(foods)
    if (food == null) {
        menu()
        return
    }
    cart.add(food)
    println("${food.name}을(를) 장바구니에 담았습니다.")
    putMenu(foods, categoryIndex)
}

private fun getMenu(foods: List<Food>): Food? {
    println("")
    println("장바구니에 담을 메뉴의 번호를 입력해주세요.")
    val food: Food
    while (true) {
        val inputFoodIndex = getIndex()
        if (inputFoodIndex + 1 == 0) {
            return null
        }
        val foodByIndex = foods.getOrNull(inputFoodIndex)
        if (foodByIndex == null) {
            println("존재하지 않는 번호입니다.")
        } else {
            food = foodByIndex
            break
        }
    }
    return food
}

private fun getIndex(): Int {
    val index: Int
    while (true) {
        val inputIndex = readln().toIntOrNull()
        if (inputIndex == null) {
            println("숫자만 입력할 수 있습니다.")
        } else {
            index = inputIndex - 1
            break
        }
    }
    return index
}