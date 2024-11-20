package org.example

class MissingNumberHelper {
    fun findMissingNumber(numbers: List<Int>): Int {
        val n = numbers.size + 1
        val sum = numbers.sum()
        val expectedSum = n * (n + 1) / 2
        return expectedSum - sum
    }
}