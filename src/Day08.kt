fun main() {
    fun part1(input: List<String>): Int {
        val cleanedInput = mutableListOf<String>()
        input.forEach { cleanedInput.add(it.split("|")[1]) }
        val uniqueSegmentsRegex = """((\b[a-g]{2,4}\b)|(\b[a-g]{7}\b))""".toRegex()
        val uniqueCount = uniqueSegmentsRegex.findAll(cleanedInput.joinToString(" ")).count()
        return uniqueCount
    }

    fun part2(input: List<String>): Int {
        var outputSum = 0
        input.forEach { line ->
            val dataArray = line.split("|")
            val inputString = dataArray[0]
            val output = dataArray[1].split(" ").filter { it.length > 1 }
            val segmentScores = mutableMapOf<Char, Int>()
            inputString.forEach {
                if (it.isLetter()) {
                    val count = segmentScores.getOrPut(it) { 0 }
                    if (count != 0)
                        segmentScores[it] = count + 1
                    else
                        segmentScores[it] = 1
                }
            }
            val outputNumber = arrayListOf(0, 0, 0, 0)
            output.forEachIndexed { index, code ->
                var digitSum = 0
                code.forEach {
                    digitSum += segmentScores[it]!!
                }
                outputNumber[index] = when (digitSum) {
                    42 -> 0
                    17 -> 1
                    34 -> 2
                    39 -> 3
                    30 -> 4
                    37 -> 5
                    41 -> 6
                    25 -> 7
                    49 -> 8
                    45 -> 9
                    else -> throw error("bad input at $digitSum, with index: $index and code: $code")
                }
            }
            outputSum += outputNumber.joinToString("").toInt()
            outputNumber.replaceAll { it * 0 }
        }
        return outputSum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}