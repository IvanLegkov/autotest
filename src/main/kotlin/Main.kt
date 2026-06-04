import kotlin.math.max

const val taxPercent4VisaMir = 0.0075
const val taxMin4VisaMir = 35
const val taxPercent4MastercardMaestro = 0.006
const val taxFixed4MastercardMaestro = 20

var dayLimitUsed4Mastercard = 0
var monthLimitUsed4Mastercard = 0
var dayLimitUsed4Maestro = 0
var monthLimitUsed4Maestro = 0
var dayLimitUsed4Visa = 0
var monthLimitUsed4Visa = 0
var dayLimitUsed4Mir = 0
var monthLimitUsed4Mir = 0
var monthLimitUsed4VKPay = 0

const val onetimeLimit4VKPay = 15_000
const val monthLimit4VKPay = 40_000
const val dayLimitExceptVKPay = 150_000
const val monthLimitExceptVKPay = 600_000
const val minTransfer4MastercardMaestro2NotPayTax = 300
const val maxTotalMonthTransferLimit4MastercardMaestro2NotPayTax = 75_000

fun main() {
    val userCard = "Mastercard"
    val transaction = 10_000

    println(calculateTax(userCard, transaction))
}

fun calculateTax(userCard: String, transaction: Int): String {
    when (userCard) {
        "Mastercard", "Maestro" ->
            when {
                transaction + determineMonthLimitUsed(userCard) > monthLimitExceptVKPay -> {
                    return ("Перевод невозможен, т.к. превысит месячный лимит ($monthLimitExceptVKPay руб.) на " +
                            "${transaction + determineMonthLimitUsed(userCard) - monthLimitExceptVKPay} руб.")
                    }
                transaction + determineDayLimitUsed(userCard) > dayLimitExceptVKPay -> {
                    return ("Перевод невозможен, т.к. превысит дневной лимит ($dayLimitExceptVKPay руб.) на " +
                            "${transaction + determineDayLimitUsed(userCard) - dayLimitExceptVKPay} руб.")
                    }
                transaction < minTransfer4MastercardMaestro2NotPayTax ||
                        maxTotalMonthTransferLimit4MastercardMaestro2NotPayTax <=
                        determineMonthLimitUsed(userCard) -> {
                            val tax: Int = (transaction * taxPercent4MastercardMaestro +
                                    taxFixed4MastercardMaestro).toInt()
                            addMonthAndDayLimitUsed(userCard, transaction)
                            return ("Комиссия за перевод составила $tax руб.")
                        }
                transaction + determineMonthLimitUsed(userCard) >
                        maxTotalMonthTransferLimit4MastercardMaestro2NotPayTax -> {
                            val tax: Int = ((transaction + determineMonthLimitUsed(userCard) -
                                    maxTotalMonthTransferLimit4MastercardMaestro2NotPayTax) *
                                    taxPercent4MastercardMaestro + taxFixed4MastercardMaestro).toInt()
                            addMonthAndDayLimitUsed(userCard, transaction)
                            return ("Комиссия за перевод составила $tax руб.")
                        }
                else -> return ("Комиссия не взимается")
            }
        "Visa", "Mir" ->
            when {
                transaction + determineMonthLimitUsed(userCard) > monthLimitExceptVKPay -> {
                    return ("Перевод невозможен, т.к. превысит месячный лимит ($monthLimitExceptVKPay руб.) на " +
                            "${transaction + determineMonthLimitUsed(userCard) - monthLimitExceptVKPay} руб.")
                }
                transaction + determineDayLimitUsed(userCard) > dayLimitExceptVKPay -> {
                    return ("Перевод невозможен, т.к. превысит дневной лимит ($dayLimitExceptVKPay руб.) на " +
                            "${transaction + determineDayLimitUsed(userCard) - dayLimitExceptVKPay} руб.")
                }
                else -> {
                    val tax: Int = max((transaction * taxPercent4VisaMir).toInt(), taxMin4VisaMir)
                    addMonthAndDayLimitUsed(userCard, transaction)
                    return ("Комиссия за перевод составила $tax руб.")
                }
            }
        "VKPay" ->
            when {
                transaction > onetimeLimit4VKPay -> {
                    return ("Перевод невозможен, т.к. превышает единоразовый лимит в $onetimeLimit4VKPay руб.")
                }
                transaction + determineMonthLimitUsed(userCard) > monthLimit4VKPay -> {
                    return ("Перевод невозможен, т.к. превысит месячный лимит ($monthLimit4VKPay руб.) на " +
                            "${transaction + monthLimitUsed4VKPay - monthLimit4VKPay} руб.")
                }
                else -> {
                    addMonthAndDayLimitUsed(userCard, transaction)
                    return ("Комиссия не взимается")
                }
            }
        else -> return ("Недопустимая карта")
    }
}

fun determineDayLimitUsed(userCard: String): Int {
    return when (userCard) {
        "Mastercard" -> dayLimitUsed4Mastercard
        "Maestro" -> dayLimitUsed4Maestro
        "Visa" -> dayLimitUsed4Visa
        else -> dayLimitUsed4Mir
    }
}

fun determineMonthLimitUsed(userCard: String): Int {
    return when (userCard) {
        "Mastercard" -> monthLimitUsed4Mastercard
        "Maestro" -> monthLimitUsed4Maestro
        "Visa" -> monthLimitUsed4Visa
        "Mir" -> monthLimitUsed4Mir
        else -> monthLimitUsed4VKPay
    }
}

fun addMonthAndDayLimitUsed(userCard: String, transaction: Int) {
    when (userCard) {
        "Mastercard" -> {
            monthLimitUsed4Visa += transaction
            dayLimitUsed4Visa += transaction
        }
        "Maestro" -> {
            monthLimitUsed4Mir += transaction
            dayLimitUsed4Mir += transaction
        }
        "Visa" -> {
            monthLimitUsed4Visa += transaction
            dayLimitUsed4Visa += transaction
        }
        "Mir" -> {
            monthLimitUsed4Mir += transaction
            dayLimitUsed4Mir += transaction
        }
        "VKPay" -> {
            monthLimitUsed4VKPay += transaction
        }
    }
}