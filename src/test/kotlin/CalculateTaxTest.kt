import org.junit.Assert.*
import org.junit.Test

class CalculateTaxTest {
    @Test
    fun calculateTax_Mastercard_TransactionLessThen300() {
        val userCard = "Mastercard"
        val transaction = 250

        val result = calculateTax(userCard = userCard, transaction = transaction)

        assertEquals(21, result)
    }

}