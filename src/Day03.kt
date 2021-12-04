fun main() {
    fun part1(input: List<String>): Int {
        var gammaRate = 0
        var epsRate = 0
        val lineLength = input.firstOrNull()?.length
        val reportSize = input.size
        if (lineLength != null) {
            val mostCommonTable = IntArray(lineLength)
            input.forEach {
                val currentItem = it
                var i = 0
                while (i < lineLength) {
                    mostCommonTable[i] += currentItem[i].digitToInt()
                    i++
                }
            }
            var j = 0
            while (j < lineLength) {
                mostCommonTable[j] = if (mostCommonTable[j] >= (reportSize / 2))
                    1
                else
                    0
                j++
            }

            gammaRate = mostCommonTable.joinToString().filter { it.isDigit() }.toInt(2)
            epsRate = mostCommonTable.map { (it + 1) % 2 }.joinToString().filter { it.isDigit() }.toInt(2)
            //println(epsRate)
        }
        return gammaRate * epsRate
    }

    fun findMostCommon(input: List<String>, index: Int): Int {
        var mostCommonNumber = 0
        if (input.isNotEmpty()) {
            if (index < input.first().length)
                input.forEach {
                    if (it[index].digitToInt() == 1)
                        mostCommonNumber++
                }
        }
        val threshold = input.size / 2.0
        return if (mostCommonNumber.toDouble() == threshold)
            -1
        else if (mostCommonNumber < threshold)
            0
        else
            1
    }

    fun filterList(input: MutableList<String>, index: Int, value: Int): MutableList<String> {
        input.removeIf { it[index].digitToInt() == value }
        return input
    }

    fun part2(input: List<String>): Int {
        if (input.isEmpty())
            return 0
        else {
            val lineLength = input.first().length
            val reportSize = input.size
            var finalOxygen = 0
            var finalCO2 = 0
            var tempOxygenTable = input.toMutableList()
            var tempCO2Table = input.toMutableList()

            var i = 0
            while (tempOxygenTable.size > 1 && i < lineLength) {
                when (findMostCommon(tempOxygenTable, i)) {
                    -1 -> filterList(tempOxygenTable, i, 0)
                    0 -> filterList(tempOxygenTable, i, 1)
                    1 -> filterList(tempOxygenTable, i, 0)
                }
                i++
            }
            finalOxygen = tempOxygenTable[0].toInt(2)

            i = 0

            while(tempCO2Table.size >1 && i <lineLength ){
                when (findMostCommon(tempCO2Table, i)) {
                    -1 -> filterList(tempCO2Table, i, 1)
                    0 -> filterList(tempCO2Table, i, 0)
                    1 -> filterList(tempCO2Table, i, 1)
                }
                i++
            }
            finalCO2 = tempCO2Table[0].toInt(2)



            /*
            if (lineLength != null) {
                val mostCommonTable = IntArray(lineLength)
                input.forEach {
                    val currentItem = it
                    var i = 0
                    while (i < lineLength) {
                        mostCommonTable[i] += currentItem[i].digitToInt()
                        i++
                    }
                }
                var j = 0
                var oxygenRating = input.toMutableList()
                var scrubberRating = input.toMutableList()

                while (j <= mostCommonTable.lastIndex && (oxygenRating.size + scrubberRating.size != 2)) {
                    //val currentMostCommon = mostCommonTable[j]
                    if (oxygenRating.size == 1)
                        finalOxygen = oxygenRating[0].toInt(2)
                    else {
                        when {
                            mostCommonTable[j] > (reportSize / 2) -> {
                                oxygenRating = oxygenRating.filter { it[j].digitToInt() == 1 }.toMutableList()
                                //scrubberRating = scrubberRating.filter { it[j].digitToInt() == 0 }.toMutableList()
                            }
                            mostCommonTable[j] < (reportSize / 2) -> {
                                oxygenRating = oxygenRating.filter { it[j].digitToInt() == 0 }.toMutableList()
                                //scrubberRating = scrubberRating.filter { it[j].digitToInt() == 1 }.toMutableList()
                            }
                            mostCommonTable[j] == reportSize / 2 -> {
                                oxygenRating = oxygenRating.filter { it[j].digitToInt() == 1 }.toMutableList()
                                //scrubberRating = scrubberRating.filter { it[j].digitToInt() == 0 }.toMutableList()
                            }
                        }
                    }
                    if (scrubberRating.size == 1)
                        finalCO2 = scrubberRating[0].toInt(2)
                    else {
                        if (mostCommonTable[j] > (reportSize / 2)) {
                            //oxygenRating = oxygenRating.filter { it[j].digitToInt() == 1 }.toMutableList()
                            scrubberRating = scrubberRating.filter { it[j].digitToInt() == 0 }.toMutableList()
                        } else if (mostCommonTable[j] < (reportSize / 2)) {
                            //oxygenRating = oxygenRating.filter { it[j].digitToInt() == 0 }.toMutableList()
                            scrubberRating = scrubberRating.filter { it[j].digitToInt() == 1 }.toMutableList()
                        } else {
                            //oxygenRating = oxygenRating.filter { it[j].digitToInt() == 1 }.toMutableList()
                            scrubberRating = scrubberRating.filter { it[j].digitToInt() == 0 }.toMutableList()
                        }
                    }
                    j++
                }*/


            return finalOxygen * finalCO2
        }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}