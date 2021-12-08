import java.lang.Exception

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
        var bingoIndex = bingoTables.indexOf(tempBingo)
        while (solutionNumber == -1) {
            val currentNumber = bingoNumbers[i]
            var wasNumberChecked = false
            var isSearchFinished = false
            var rowIndex = 0
            var itemIndex = 0
            //Iterate until number is found in THIS table or the end of THIS BINGO is reached
            while (!wasNumberChecked && !isSearchFinished) {
                if (tempBingo[rowIndex][itemIndex] == currentNumber) {
                    //Adding the 1 to current row result
                    bingoSolutions[bingoIndex][0][rowIndex] += 1
                    //Adding the 1 to current column result
                    bingoSolutions[bingoIndex][1][itemIndex] += 1
                    if (bingoSolutions[bingoIndex][1][itemIndex] == 5 || bingoSolutions[bingoIndex][0][rowIndex] == 5) {
                        solutionNumber = currentNumber
                    }
                    wasNumberChecked = true
                } else {
                    if (itemIndex == 4 && rowIndex < 4) {
                        itemIndex = 0
                        rowIndex++
                    } else if (itemIndex == 4 && rowIndex == 4)
                        isSearchFinished = true
                    else
                        itemIndex++
                }
            }
            if ((isSearchFinished || wasNumberChecked) && solutionNumber == -1 && bingoIndex != bingoTables.lastIndex)
                tempBingo = bingoTables[++bingoIndex]
            else if (solutionNumber != -1)
            //tempBingo = bingoTables[++bingoIndex]
            else if (bingoIndex == bingoTables.lastIndex) {
                i++
                tempBingo = bingoTables.first()
                bingoIndex = 0
            }

        }
        val winningBingo = tempBingo.toList()
        val numbersCrossed = bingoNumbers.take(i + 1)
        val a = winningBingo.flatten() - numbersCrossed.toSet()
        val sum = a.sum()


        return sum * solutionNumber
    }

    fun part2(input: List<String>): Int {
        val bingoNumbers = input[0].split(',').map { it.toInt() }
        val bingoData = input.drop(2)
        val bingoTables = createBingoArrays(bingoData).toMutableList()
        val rows = 5
        val columns = 5
        val tablesNumber = bingoTables.size
        bingoTables[1][1][1]
        val bingoSolutions = createEmptySolutionsTable(tablesNumber, columns, rows)
        var solutionNumber = -1
        var i = 0
        var tempBingo = bingoTables.first()
        var bingoIndex = bingoTables.indexOf(tempBingo)
        while (solutionNumber == -1) {
            val currentNumber = bingoNumbers[i]
            var wasNumberChecked = false
            var isIterationFinished = false
            var wasCurrentRemoved = false
            var rowIndex = 0
            var itemIndex = 0
            //Iterate until number is found in THIS table or the end of THIS BINGO is reached
            while (!wasNumberChecked && !isIterationFinished) {
                //The current bingo number was found
                if (tempBingo[rowIndex][itemIndex] == currentNumber) {
                    //Adding the 1 to current row result
                    bingoSolutions[bingoIndex][0][rowIndex] += 1
                    //Adding the 1 to current column result
                    bingoSolutions[bingoIndex][1][itemIndex] += 1
                    //The current number caused a BINGO!
                    if (bingoSolutions[bingoIndex][1][itemIndex] == 5 || bingoSolutions[bingoIndex][0][rowIndex] == 5) {
                        //Check if it is the last table
                        if (bingoTables.size > 1) {
                            bingoTables.remove(tempBingo)
                            bingoSolutions.removeAt(bingoIndex)
                            wasCurrentRemoved = true
                        } else {
                            solutionNumber = currentNumber
                        }

                    }
                    wasNumberChecked = true
                } else {
                    if (itemIndex == 4 && rowIndex < 4) {
                        itemIndex = 0
                        rowIndex++
                    } else if (itemIndex == 4 && rowIndex == 4)
                        isIterationFinished = true
                    else
                        itemIndex++
                }
            }
            //We are still looking for the last winning BINGO!
            if ((isIterationFinished || wasNumberChecked) && solutionNumber == -1 /*&& bingoIndex!=bingoTables.lastIndex*/) {
                if (wasCurrentRemoved) {
                    if (bingoIndex > bingoTables.lastIndex) {
                        tempBingo = bingoTables.first()
                        bingoIndex =0
                        i++
                    } else
                        tempBingo = bingoTables[bingoIndex]

                } else {
                    if (bingoIndex == bingoTables.lastIndex) {
                        i++
                        tempBingo = bingoTables.first()
                        bingoIndex = 0
                    } else {
                        try {
                            tempBingo = bingoTables[++bingoIndex]
                        } catch (e: Exception) {
                        }
                    }
                }
            }


        }
        val loosingBingo = tempBingo.toList()
        val numbersCrossed = bingoNumbers.take(i + 1)
        val a = loosingBingo.flatten() - numbersCrossed.toSet()
        val sum = a.sum()


        return sum * solutionNumber
    }


// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}