fun main() {
    fun part1(input: List<String>): Int {
        var numericalValue: Int
        var counter = 0
        var threshold: Int
        if (input.isNotEmpty()) {
            numericalValue = input[0].toInt(10)
            counter = 0
            threshold = numericalValue
        } else
            return input.size
        var i = 0
        while (++i <= input.lastIndex) {
            numericalValue = input[i].toInt(10)
            if (numericalValue > threshold) {

                counter++
            }
            threshold = numericalValue

        }
        return counter

    }

    fun part2(input: List<String>): Int {
        val windowLength = 3
        val relativeWindowEnd = 2
        var windowSum: Int = 0
        var threshold: Int
        var counter = 0
        var valueSlidingOut = 0
        var valueSlidingIn = 0
        if (input.isNotEmpty()) {
            var i = 0
            valueSlidingOut = input[i].toInt(10)
           /* while (i < windowLength && i <= input.lastIndex) {
                windowSum += input[i].toInt(10)
            }
            i++*/
            threshold = windowSum
            while (i <= (input.lastIndex - 2)) {
                valueSlidingIn = input[i + relativeWindowEnd].toInt(10)
                windowSum = windowSum - valueSlidingOut + valueSlidingIn
                if (windowSum > threshold && i>0)
                    counter++
                threshold = windowSum
                valueSlidingOut = input[i].toInt(10)
                i++
            }

        }
        return counter
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    //check(part1(testInput) == 7)
    //check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
    println(part2(testInput))


}
