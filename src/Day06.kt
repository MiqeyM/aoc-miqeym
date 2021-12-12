class Fish(var timer: Int = 8) {
    fun simulateDay(): Boolean {
        timer--
        return if (timer < 0) {
            timer = 6
            true
        } else
            false
    }

    override fun toString(): String {
        return "$timer"
    }
}

class FishSchool(startData: List<Int>) {
    val fishies = mutableListOf<Fish>()
    init {
        startData.forEach {
            fishies.add(Fish(it))
        }
    }

    //var fishCount = fishies.size
    fun simulate(daysPassed: Int) : Int{
        var i = 0
        while (i < daysPassed) {
            var newFish = 0
            fishies.forEach {
                if (it.simulateDay())
                    newFish++
            }
            while (newFish--!=0)
                fishies.add(Fish())
            i++
        }
        return fishies.size
    }
}

fun main() {
    fun part1(input: List<String>, days : Int): Int {
        val fishSchool = FishSchool(input.first().split(',').map { it.toInt() })
        return fishSchool.simulate(days)
    }

    fun part2(input: List<String>, days : Int): Long {
        val fishList = mutableListOf<Long>(0,0,0,0,0,0,0,0,0)
        input.first().split(',').map { it.toInt() }.forEach {
            fishList[it]++
        }
        var i = 0
        while (i<days)
        {
            val newFishies =  fishList[0]
            fishList.removeAt(0)
            fishList.add(8,newFishies)
            fishList[6]+=newFishies
            i++

        }
        return fishList.sum()
        //val fishSchool = FishSchool(input.first().split(',').map { it.toInt() })
        //return fishSchool.simulate(days)

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput,18) == 26)
    check(part1(testInput,80) == 5934)
    check(part2(testInput,18) == 26L)
    check(part2(testInput,80) == 5934L)
    check(part2(testInput,256) == 26984457539)

    val input = readInput("Day06")
    println("ans1: ${part1(input,80)}")
    println(part2(input,256))
}