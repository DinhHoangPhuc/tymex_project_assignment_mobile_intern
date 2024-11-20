package org.example

class InventoryHelper {
    fun calculateTotalValueOfAllProducts(inventory: List<InventoryItem>): Double {
        return inventory.sumOf { calculateTotalPrice(it) }
    }

    fun findMostExpensiveProduct(inventory: List<InventoryItem>): InventoryItem {
        return inventory.maxByOrNull { it.price }!!
    }

    fun checkIfProductIsInStock(inventory: List<InventoryItem>, productName: String): Boolean {
        return inventory.any { it.name == productName && it.quantity > 0 }
    }

    fun sortProductsByPriceDescendend(inventory: List<InventoryItem>): List<InventoryItem> {
        return inventory.sortedByDescending { it.price }
    }

    fun sortProductsByPriceAscendend(inventory: List<InventoryItem>): List<InventoryItem> {
        return inventory.sortedBy { it.price }
    }

    fun sortProductsByQuantityDescendend(inventory: List<InventoryItem>): List<InventoryItem> {
        return inventory.sortedByDescending { it.quantity }
    }

    fun sortProductsByQuantityAscendend(inventory: List<InventoryItem>): List<InventoryItem> {
        return inventory.sortedBy { it.quantity }
    }

    private fun calculateTotalPrice(inventoryItem: InventoryItem): Double {
        return inventoryItem.price * inventoryItem.quantity
    }
}