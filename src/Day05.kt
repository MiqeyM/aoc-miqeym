class Point(val x: Int, val y: Int) {
    override fun toString(): String {

        return "($x,$y)"
    }
}

class Line(val start: Point, val end: Point) {
    val furthestX = maxOf(start.x, end.x)
    val furthestY = maxOf(start.y, end.y)
    val isVertical = start.x == end.x

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

fun setUpDiagram(lineList: List<Line>): Array<Array<Int>> {
    var maxX = 0
    var maxY = 0
    lineList.forEach { line ->
        maxX = maxOf(line.furthestX, maxX)
        maxY = maxOf(line.furthestY, maxY)
    }
    val diagramArray = Array(maxX+1) { setZero(maxY+1) }
    var from = 0
    var to = 0
    lineList.forEach { line ->
        var i = 0
        if (line.isVertical) {
            from = minOf(line.start.y, line.end.y)
            to = maxOf(line.start.y, line.end.y)
            i = from
            while (i <= to) {
                diagramArray[line.start.x][i] += 1
                i++
            }


        } else {
            from = minOf(line.start.x, line.end.x)
            to = maxOf(line.start.x, line.end.x)
            i = from
            while (i <= to) {
                diagramArray[i][line.start.y] += 1
                i++
            }
        }
    }
    return diagramArray
}

fun setZero(size: Int): Array<Int> {
    return Array(size) { 0 }
}

fun printArray(array: Array<Array<Int>>) {
    array.forEach { row ->
        row.forEach { item -> print(".$item") }
        println(";\n")
    }
}

fun countOverlaps(diagram: Array<Array<Int>>): Int {
    var counter = 0
    diagram.forEach { line ->
        line.forEach { point -> if (point > 1) counter++ }
    }
    return counter
}


fun main() {
    fun part1(input: List<String>): Int {
        val lines = removeDiagonals(getLineList(input))
        val diagram = setUpDiagram(lines)
        //printArray(diagram)
        return countOverlaps(diagram)
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}