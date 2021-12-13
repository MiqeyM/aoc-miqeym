import kotlin.math.abs
import kotlin.math.roundToInt

fun main() {
    fun part1(input: List<String>): Int {
        val crabs = input.first().split(',').map { it.toInt() }.sorted()
        val median = if (crabs.size % 2 == 0) {
            ((crabs[crabs.size / 2] + crabs[crabs.size / 2 - 1]) / 2)
        } else {
            (crabs[crabs.size / 2])
        }

        var fuel = 0
        crabs.forEach { fuel += abs(median - it) }

        return fuel
    }

    fun part2(input: List<String>): Int {
        val crabs = input.first().split(',').map { it.toInt() }
        var average = crabs.average().roundToInt()
        var fuelTable = mutableListOf<Int>()
        for (i in -1..1) {
            var fuel = 0
            val variant = average + i
            crabs.forEach {
                val distance = abs(variant - it)
                fuel += distance * (1 + distance) / 2
            }
            fuelTable.add(fuel)
            println("Position:$variant, fuel: $fuel")
        }


        return fuelTable.minOf { it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)
    //println(part2(testInput))

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}