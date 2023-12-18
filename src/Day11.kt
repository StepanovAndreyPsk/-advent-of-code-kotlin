import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Int {
        val emptyRows = mutableSetOf<Int>()
        val emptyCols = mutableSetOf<Int>()
        val galaxiesX = mutableListOf<Int>()
        val galaxiesY = mutableListOf<Int>()
        var result = 0
        for (i in 0..<input.size) {
            var empty = true
            for (j in 0..<input[0].length) {
                if (input[i][j] != '.') {
                    empty = false
                }
                if (input[i][j] == '#') {
                    galaxiesX.add(i)
                    galaxiesY.add(j)
                }
            }
            if (empty) {
                emptyRows.add(i)
            }
        }
        for (i in 0..<input[0].length) { // cols
            var empty = true
            for (j in 0..<input.size) { // rows
                if (input[j][i] != '.') {
                    empty = false
                }
            }
            if (empty) {
                emptyCols.add(i)
            }
        }
        for (i in 0..<galaxiesX.size) {
            for (j in i..<galaxiesX.size) {
                var dist = abs(galaxiesX[i] - galaxiesX[j]) + abs(galaxiesY[i] - galaxiesY[j])
                for (rowInd in emptyRows) {
                    if (rowInd > min(galaxiesX[i], galaxiesX[j]) && rowInd < max(galaxiesX[i], galaxiesX[j])) {
                        dist++
                    }
                }

                for (colInd in emptyCols) {
                    if (colInd > min(galaxiesY[i], galaxiesY[j]) && colInd < max(galaxiesY[i], galaxiesY[j])) {
                        dist++
                    }
                }
                result += dist
            }

        }
        return result
    }

    fun part2(input: List<String>): Long {
        val emptyRows = mutableSetOf<Int>()
        val emptyCols = mutableSetOf<Int>()
        val galaxiesX = mutableListOf<Int>()
        val galaxiesY = mutableListOf<Int>()
        var result = 0.toLong()
        for (i in 0..<input.size) {
            var empty = true
            for (j in 0..<input[0].length) {
                if (input[i][j] != '.') {
                    empty = false
                }
                if (input[i][j] == '#') {
                    galaxiesX.add(i)
                    galaxiesY.add(j)
                }
            }
            if (empty) {
                emptyRows.add(i)
            }
        }
        for (i in 0..<input[0].length) { // cols
            var empty = true
            for (j in 0..<input.size) { // rows
                if (input[j][i] != '.') {
                    empty = false
                }
            }
            if (empty) {
                emptyCols.add(i)
            }
        }
        for (i in 0..<galaxiesX.size) {
            for (j in i..<galaxiesX.size) {
                var dist = abs(galaxiesX[i] - galaxiesX[j]) + abs(galaxiesY[i] - galaxiesY[j]).toLong()
                for (rowInd in emptyRows) {
                    if (rowInd > min(galaxiesX[i], galaxiesX[j]) && rowInd < max(galaxiesX[i], galaxiesX[j])) {
                        dist += 999999
                    }
                }

                for (colInd in emptyCols) {
                    if (colInd > min(galaxiesY[i], galaxiesY[j]) && colInd < max(galaxiesY[i], galaxiesY[j])) {
                        dist += 999999
                    }
                }
                result += dist
            }

        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")
    println(part1(testInput))
    check(part1(testInput) == 374)
    println(part2(testInput))

    val input = readInput("Day11")
    part1(input).println()
    part2(input).println()
}
