package org.example

data class InventoryItem(
    val name: String,
    val price: Double,
    val quantity: Int,
) {
    override fun toString(): String {
        return "Name: $name, Price: $price, Quantity: $quantity"
    }
}
