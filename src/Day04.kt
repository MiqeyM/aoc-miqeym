fun main() {
    fun createBingoArrays(inputData: List<String>): List<List<List<Int>>> {
        val currentLine = inputData.first()
        val tempTable = mutableListOf<List<Int>>()
        val tempRow = mutableListOf<Int>()
        val tempBingos = mutableListOf(tempTable)
        inputData.forEach { line ->
            if (line.isEmpty()) {
                tempBingos.add(tempTable)
                println(tempTable)
                tempTable.clear()
            } else {
                //Todo: poprawić usuwanie spacji
                val numbers = line.split("  ", " ").map { it.toInt() }
                numbers.forEach { tempRow.add(it) }
                tempTable.add(numbers)

            }

        }

        //println(numbers)

        println(tempTable)
        println(tempBingos)
        return tempBingos
    }

    fun part1(input: List<String>): Int {
        val bingoNumbers = input[0].split(',').map { it.toInt() }
        val bingoData = input.drop(2)
        createBingoArrays(bingoData)
        val bingoTables = listOf<Array<Int>>()
        //val bingoSolutions =
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }


// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 1)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}