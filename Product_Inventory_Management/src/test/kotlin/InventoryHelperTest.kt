import org.example.InventoryHelper
import org.example.InventoryItem
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class InventoryHelperTest {

    private val inventoryHelper = InventoryHelper()
    private val inventory = listOf(
        InventoryItem("Laptop", 999.99, 5),
        InventoryItem("Smartphone", 499.9, 10),
        InventoryItem("Tablet", 299.99, 0),
        InventoryItem("Smartwatch", 199.99, 3)
    )

    @Test
    fun testCalculateTotalValueOfAllProducts() {
        val totalValue = inventoryHelper.calculateTotalValueOfAllProducts(inventory)
        assertEquals(999.99 * 5 + 499.9 * 10 + 299.99 * 0 + 199.99 * 3, totalValue)
    }

    @Test
    fun testFindMostExpensiveProduct() {
        val mostExpensiveProduct = inventoryHelper.findMostExpensiveProduct(inventory)
        assertEquals("Laptop", mostExpensiveProduct.name)
    }

    @Test
    fun testCheckIfProductIsInStock() {
        assertTrue(inventoryHelper.checkIfProductIsInStock(inventory, "Smartphone"))
        assertTrue(!inventoryHelper.checkIfProductIsInStock(inventory, "Tablet"))
    }

    @Test
    fun testSortProductsByPriceDescendend() {
        val sortedInventory = inventoryHelper.sortProductsByPriceDescendend(inventory)
        assertEquals("Laptop", sortedInventory[0].name)
        assertEquals("Smartwatch", sortedInventory[3].name)
    }

    @Test
    fun testSortProductsByPriceAscendend() {
        val sortedInventory = inventoryHelper.sortProductsByPriceAscendend(inventory)
        assertEquals("Smartwatch", sortedInventory[0].name)
        assertEquals("Laptop", sortedInventory[3].name)
    }

    @Test
    fun testSortProductsByQuantityDescendend() {
        val sortedInventory = inventoryHelper.sortProductsByQuantityDescendend(inventory)
        assertEquals("Smartphone", sortedInventory[0].name)
        assertEquals("Tablet", sortedInventory[3].name)
    }

    @Test
    fun testSortProductsByQuantityAscendend() {
        val sortedInventory = inventoryHelper.sortProductsByQuantityAscendend(inventory)
        assertEquals("Tablet", sortedInventory[0].name)
        assertEquals("Smartphone", sortedInventory[3].name)
    }
}