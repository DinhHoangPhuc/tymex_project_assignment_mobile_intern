package org.example

fun main() {
    val inventory = listOf(
        InventoryItem("Laptop", 999.99, 5),
        InventoryItem("Smartphone", 499.9, 10),
        InventoryItem("Tablet", 299.99, 0),
        InventoryItem("Smartwatch", 199.99, 3),z
    )

    val inventoryHelper = InventoryHelper()

    println("Product list:")
    inventory.forEach { println(it) }
    println("Total value of all products: ${inventoryHelper.calculateTotalValueOfAllProducts(inventory)}")
    println("Most expensive product: ${inventoryHelper.findMostExpensiveProduct(inventory)}")
    println("Is 'Headphones' in stock? ${inventoryHelper.checkIfProductIsInStock(inventory, "Headphones")}")

    println("Products sorted by price (desc):")
    inventoryHelper.sortProductsByPriceDescendend(inventory).forEach { println(it) }

    println("Products sorted by price (asc):")
    inventoryHelper.sortProductsByPriceAscendend(inventory).forEach { println(it) }

    println("Products sorted by quantity (desc):")
    inventoryHelper.sortProductsByQuantityDescendend(inventory).forEach { println(it) }

    println("Products sorted by quantity (asc):")
    inventoryHelper.sortProductsByQuantityAscendend(inventory).forEach { println(it) }
}