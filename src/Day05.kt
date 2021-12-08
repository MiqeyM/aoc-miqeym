import jdk.jshell.Diag

class Point(val x: Int, val y: Int) {
}

class Line(val start: Point, val end: Point) {
    val furthestX = maxOf(start.x, end.x)
    val furthestY = maxOf(start.y, end.y)

}

class Diagram(val lines: List<Line>) {
    val crossingSet = mutableSetOf<Point>()


}

fun removeDiagonals(lines: List<Line>): List<Line> {
    return lines.filter { it.start.x == it.end.x || it.start.y == it.end.y }
}

fun getLineList(input: List<String>): List<Line> {
    val tempLineList = mutableListOf<Line>()
    val pattern = """(\d,\d)""".toRegex()
    input.forEach { line ->
        val matches = pattern.findAll(line)
        val list = matches.map { it.value }.toList()
        var p1Raw = list[0].split(",").map { it.toInt() }
        var p2Raw = list[1].split(",").map { it.toInt() }
        tempLineList.add(Line(Point(p1Raw[0], p1Raw[1]), Point(p2Raw[0], p2Raw[1])))
    }
    return tempLineList
}

fun setUpDiagram(lineList: List<Line>): Diagram {
    var maxX = 0
    var maxY = 0
    lineList.forEach { line ->
        maxX = maxOf(line.furthestX, maxX)
        maxY = maxOf(line.furthestY, maxY)
    }
    val diagramArray = Array<Array<Int>>(maxX){ setZero(maxY)}
    return Diagram(lineList)
}

fun setZero(size : Int):  Array<Int> {
    return Array<Int>(size){0}
}

fun main() {
    fun part1(input: List<String>): Int {
        val lines = removeDiagonals(getLineList(input))
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 1)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}