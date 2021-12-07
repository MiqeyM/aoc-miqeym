fun main() {
    fun createBingoArrays(inputData: List<String>): List<List<List<Int>>> {
        var tempTable = mutableListOf<List<Int>>()
        val tempBingos = mutableListOf<List<List<Int>>>()
        inputData.forEach { line ->
            if (line.isEmpty()) {
                tempBingos.add(tempTable.toMutableList())
                tempTable = mutableListOf()
            } else {
                val numRegEx = """(\D{1,2})""".toRegex()
                val numbersString = line.trim().split(numRegEx)
                val numbersInt = numbersString.map { it.toInt() }
                tempTable.add(numbersInt.toMutableList())
            }
        }
        tempBingos.add(tempTable.toMutableList())
        //println(tempBingos)
        return tempBingos
    }

    fun createEmptySolutionsTable(tables: Int, rows: Int, columns: Int): MutableList<MutableList<MutableList<Int>>> {
        val bingoSolutionsTable = mutableListOf<MutableList<MutableList<Int>>>()
        for (i in (0 until tables)) {
            var singleBingoSolution = mutableListOf<MutableList<Int>>()

            var solutionColumn = mutableListOf<Int>()
            for (i in (0 until rows)) {
                solutionColumn.add(0)
            }
            singleBingoSolution.add(solutionColumn.toMutableList())
            solutionColumn.clear()
            for (i in (0 until columns)) {
                solutionColumn.add(0)
            }
            singleBingoSolution.add(solutionColumn.toMutableList())
            bingoSolutionsTable.add(singleBingoSolution.toMutableList())
        }

        //println(bingoSolutionsTable)
        return bingoSolutionsTable
    }

    fun part1(input: List<String>): Int {
        val bingoNumbers = input[0].split(',').map { it.toInt() }
        val bingoData = input.drop(2)
        val bingoTables = createBingoArrays(bingoData)
        val rows = 5
        val columns = 5
        val tablesNumber = bingoTables.size
        bingoTables[1][1][1]
        val bingoSolutions = createEmptySolutionsTable(tablesNumber, columns, rows)
        var solutionNumber = -1
        var i = 0
        var tempBingo = bingoTables.first()
        while (solutionNumber == -1) {
            val currentNumber = bingoNumbers[i]
            //TODO: zmienić to na przerywalnego while
            bingoTables.forEachIndexed { index, it ->
                var wasNumberChecked = false
                var rowIndex = 0
                var itemIndex = 0
                //Iterate until number is found in THIS table
                while (!wasNumberChecked) {
                    if (it[rowIndex][itemIndex] == currentNumber) {
                        //Adding the 1 to current row result
                        bingoSolutions[index][0][rowIndex] += 1
                        //Adding the 1 to current column result
                        bingoSolutions[index][1][itemIndex] += 1
                        if (bingoSolutions[index][1][itemIndex] == 5 || bingoSolutions[index][0][rowIndex] == 5) {
                            solutionNumber = currentNumber
                        }
                        wasNumberChecked = true
                    } else {
                        if (itemIndex == 4 && rowIndex < 4) {
                            itemIndex = 0
                            rowIndex++
                        } else if (itemIndex == 4 && rowIndex == 4)
                            throw error("Ktos tu nie znalazł numerka tak jak miał!")
                        else
                        itemIndex++
                    }
                }
                if(solutionNumber!=-1)
                    break
            }

        }
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