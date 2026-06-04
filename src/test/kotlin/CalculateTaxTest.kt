import org.junit.Assert.*
import org.junit.Test

class CalculateTaxTest {
    @Test
    fun calculateTax_Mastercard_ExceedMonthLimit() {
        val userCard = "Mastercard"
        val transaction = 50_000
        monthLimitUsed4Mastercard = 575_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Перевод невозможен, т.к. превысит месячный лимит (600000 руб.) на " +
                "25000 руб.", result)
    }

    @Test
    fun calculateTax_Mastercard_ExceedDayLimit() {
        val userCard = "Mastercard"
        val transaction = 100_000
        dayLimitUsed4Mastercard = 90_000
        monthLimitUsed4Mastercard = 90_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Перевод невозможен, т.к. превысит дневной лимит (150000 руб.) на " +
                "40000 руб.", result)
    }

    @Test
    fun calculateTax_Mastercard_NoTaxMonthLimitAlreadyExceeded() {
        val userCard = "Mastercard"
        val transaction = 50_000
        monthLimitUsed4Mastercard = 80_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Комиссия за перевод составила 320 руб.", result)
    }

    @Test
    fun calculateTax_Mastercard_TransactionLessThan300() {
        val userCard = "Mastercard"
        val transaction = 250

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Комиссия за перевод составила 21 руб.", result)
    }

    @Test
    fun calculateTax_Mastercard_TransactionExceedsNoTaxMonthLimit() {
        val userCard = "Mastercard"
        val transaction = 20_000
        monthLimitUsed4Mastercard = 60_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Комиссия за перевод составила 50 руб.", result)
    }

    @Test
    fun calculateTax_Mastercard_NoTax() {
        val userCard = "Mastercard"
        val transaction = 20_000
        monthLimitUsed4Mastercard = 40_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Комиссия не взимается", result)
    }

    @Test
    fun calculateTax_Maestro_ExceedMonthLimit() {
        val userCard = "Maestro"
        val transaction = 50_000
        monthLimitUsed4Maestro = 575_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Перевод невозможен, т.к. превысит месячный лимит (600000 руб.) на " +
                "25000 руб.", result)
    }

    @Test
    fun calculateTax_Maestro_ExceedDayLimit() {
        val userCard = "Maestro"
        val transaction = 100_000
        dayLimitUsed4Maestro = 90_000
        monthLimitUsed4Maestro = 90_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Перевод невозможен, т.к. превысит дневной лимит (150000 руб.) на " +
                "40000 руб.", result)
    }

    @Test
    fun calculateTax_Maestro_NoTaxMonthLimitAlreadyExceeded() {
        val userCard = "Maestro"
        val transaction = 50_000
        monthLimitUsed4Maestro = 80_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Комиссия за перевод составила 320 руб.", result)
    }

    @Test
    fun calculateTax_Maestro_TransactionLessThan300() {
        val userCard = "Maestro"
        val transaction = 250

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Комиссия за перевод составила 21 руб.", result)
    }

    @Test
    fun calculateTax_Maestro_TransactionExceedsNoTaxMonthLimit() {
        val userCard = "Maestro"
        val transaction = 20_000
        monthLimitUsed4Maestro = 60_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Комиссия за перевод составила 50 руб.", result)
    }

    @Test
    fun calculateTax_Maestro_NoTax() {
        val userCard = "Maestro"
        val transaction = 20_000
        monthLimitUsed4Maestro = 40_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Комиссия не взимается", result)
    }

    @Test
    fun calculateTax_Visa_ExceedMonthLimit() {
        val userCard = "Visa"
        val transaction = 60_000
        monthLimitUsed4Visa = 550_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Перевод невозможен, т.к. превысит месячный лимит (600000 руб.) на " +
                "10000 руб.", result)
    }

    @Test
    fun calculateTax_Visa_ExceedDayLimit() {
        val userCard = "Visa"
        val transaction = 100_000
        dayLimitUsed4Visa = 60_000
        monthLimitUsed4Visa = 60_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Перевод невозможен, т.к. превысит дневной лимит (150000 руб.) на " +
                "10000 руб.", result)
    }

    @Test
    fun calculateTax_Visa_MinimalTaxApplied() {
        val userCard = "Visa"
        val transaction = 1_000
        dayLimitUsed4Visa = 45_000
        monthLimitUsed4Visa = 45_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Комиссия за перевод составила 35 руб.", result)
    }

    @Test
    fun calculateTax_Visa_PercentTaxApplied() {
        val userCard = "Visa"
        val transaction = 15_000
        dayLimitUsed4Visa = 45_000
        monthLimitUsed4Visa = 45_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Комиссия за перевод составила 112 руб.", result)
    }

    @Test
    fun calculateTax_Mir_ExceedMonthLimit() {
        val userCard = "Mir"
        val transaction = 48_000
        monthLimitUsed4Mir = 565_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Перевод невозможен, т.к. превысит месячный лимит (600000 руб.) на " +
                "13000 руб.", result)
    }

    @Test
    fun calculateTax_Mir_ExceedDayLimit() {
        val userCard = "Mir"
        val transaction = 70_000
        dayLimitUsed4Mir = 95_000
        monthLimitUsed4Mir = 95_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Перевод невозможен, т.к. превысит дневной лимит (150000 руб.) на " +
                "15000 руб.", result)
    }

    @Test
    fun calculateTax_Mir_MinimalTaxApplied() {
        val userCard = "Mir"
        val transaction = 2_500
        dayLimitUsed4Mir = 5_000
        monthLimitUsed4Mir = 5_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Комиссия за перевод составила 35 руб.", result)
    }

    @Test
    fun calculateTax_Mir_PercentTaxApplied() {
        val userCard = "Mir"
        val transaction = 18_000
        dayLimitUsed4Mir = 45_000
        monthLimitUsed4Mir = 45_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Комиссия за перевод составила 135 руб.", result)
    }

    @Test
    fun calculateTax_VKPay_ExceedOnetimeLimit() {
        val userCard = "VKPay"
        val transaction = 18_000
        monthLimitUsed4VKPay = 10_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Перевод невозможен, т.к. превышает единоразовый лимит в 15000 руб.", result)
    }

    @Test
    fun calculateTax_VKPay_ExceedMonthLimit() {
        val userCard = "VKPay"
        val transaction = 9_000
        monthLimitUsed4VKPay = 35_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Перевод невозможен, т.к. превысит месячный лимит (40000 руб.) на " +
                "4000 руб.", result)
    }

    @Test
    fun calculateTax_VKPay_NoTax() {
        val userCard = "VKPay"
        val transaction = 6_000
        monthLimitUsed4VKPay = 10_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Комиссия не взимается", result)
    }

    @Test
    fun calculateTax_UnsupportedCard() {
        val userCard = "UnionPay"
        val transaction = 6_000

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals("Недопустимая карта", result)
    }
}