import java.lang.IllegalStateException
import kotlin.math.max
import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        for (line in input) {
            val digit1 = line.first { it.isDigit() }
            val digit2 = line.last { it.isDigit() }
            val calibrationVal = digit1.toString().toInt() * 10 + digit2.toString().toInt()
            result += calibrationVal
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        val digitsList = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        for (line in input) {
            var digit1 = -1
            var digit2 = -1
            var digit1Index = -1
            var digit2Index = line.length
            line.indexOfFirst { it.isDigit() }.also { if (it != -1) {
                digit1 = line[it].digitToInt();
                digit1Index = it
            } }
            line.indexOfLast { it.isDigit() }.also { if (it != -1) {
                digit2 = line[it].digitToInt()
                digit2Index = it
            } }

            for (index in 0..<digitsList.size) {
                line.indexOf(digitsList[index]).let {
                    if (it != -1 && (digit1Index == -1 || it < digit1Index)) {
                        digit1 = index + 1
                        digit1Index = it
                    }
                }
                line.lastIndexOf(digitsList[index]).let {
                    if (it != -1 && (digit2Index == line.length || it > digit2Index)) {
                        digit2 = index + 1
                        digit2Index = it
                    }
                }
            }
            if (digit1 == -1 || digit2 == -1) {
                throw IllegalStateException()
            }

            result += digit1 * 10 + digit2
        }

        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 142)
//    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
