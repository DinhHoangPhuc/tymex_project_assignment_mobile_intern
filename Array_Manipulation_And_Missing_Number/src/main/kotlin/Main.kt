package org.example

fun main() {
    val listOfNumbers = listOf(3, 7, 1, 2, 6, 4)

    val missingNumberHelper = MissingNumberHelper()

    println("List of numbers: $listOfNumbers")

    val missingNumber = missingNumberHelper.findMissingNumber(listOfNumbers)

    println("Missing number: $missingNumber")
}