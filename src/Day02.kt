fun main() {
    fun part1(input: List<String>): Int {

        var deltaDepth = 0
        var deltaHorizontal = 0
        //var currentMove
        input.forEach { move ->
            val currentMove = move.split(" ")
            if (currentMove.lastIndex == 1) {
                val magnitude = currentMove[1].toInt(10)
                when (currentMove[0]) {
                    "forward" -> deltaHorizontal += magnitude
                    "down" -> deltaDepth += magnitude
                    "up" -> deltaDepth -= magnitude
                    else -> {}
                }
            }

        }
        return deltaHorizontal * deltaDepth
    }

    fun part2(input: List<String>): Int {
        var deltaDepth = 0
        var deltaHorizontal = 0
        var aim = 0
        input.forEach { move ->
            val currentMove = move.split(" ")
            if (currentMove.lastIndex == 1) {
                val magnitude = currentMove[1].toInt(10)
                when (currentMove[0]) {
                    "forward" -> {
                        deltaHorizontal += magnitude
                        deltaDepth += aim*magnitude
                    }
                    "down" -> aim += magnitude
                    "up" -> aim -= magnitude
                    else -> {}
                }
            }

        }
        return deltaHorizontal * deltaDepth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}