class Point(val x: Int, val y: Int) {
    override fun toString(): String {

        return "($x,$y)"
    }
}

class Line(val start: Point, val end: Point) {
    val isDiagonal: Boolean = (start.x != end.x && start.y != end.y)
    val furthestX = maxOf(start.x, end.x)
    val furthestY = maxOf(start.y, end.y)
    val isVertical = start.x == end.x

}

fun getLeftMostPoint(line: Line): Array<Point> {
    val a = arrayOf(line.start, line.end)
    a.sortBy { it.x }
    return a

}

fun isAscending(leftmost: Point, rightMost: Point): Boolean {
    return leftmost.y > rightMost.y
}

fun removeDiagonals(lines: List<Line>): List<Line> {
    return lines.filter { it.start.x == it.end.x || it.start.y == it.end.y }
}

fun getLineList(input: List<String>): List<Line> {
    val tempLineList = mutableListOf<Line>()
    val pattern = """(\d{1,3},\d{1,3})""".toRegex()
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
    val diagramArray = Array(maxX + 1) { setZero(maxY + 1) }
    var from = 0
    var to = 0
    var leftMost: Point
    var rightMost: Point
    lineList.forEach { line ->
        var i = 0
        if (line.isVertical && !line.isDiagonal) {
            from = minOf(line.start.y, line.end.y)
            to = line.furthestY
            i = from
            while (i <= to) {
                diagramArray[i][line.start.x] += 1
                i++
            }


        } else if (line.isDiagonal) {
            val sortedPoints = getLeftMostPoint(line)
            leftMost = sortedPoints[0]
            rightMost = sortedPoints[1]
            val k: Int = if (isAscending(leftMost, rightMost)) -1 else 1
            i = 0
            while (leftMost.x + i <= rightMost.x ) {
                diagramArray[leftMost.y + (k*i)][leftMost.x + i] += 1
                i++
            }
        } else {
            from = minOf(line.start.x, line.end.x)
            to = line.furthestX
            i = from
            while (i <= to) {
                diagramArray[line.start.y][i] += 1
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
        row.forEach { item -> print("   $item") }
        println(";\n")
    }
    println("----------")
}

fun countOverlaps(diagram: Array<Array<Int>>): Int {
    var counter = 0
    diagram.forEach { line ->
        line.forEach { point -> if (point > 1) counter++ }
    }
    //printArray(diagram)
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
        val lines = getLineList(input)
        val diagram = setUpDiagram(lines)
        //printArray(diagram)
        return countOverlaps(diagram)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}