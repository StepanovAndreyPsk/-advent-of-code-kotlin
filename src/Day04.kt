import java.lang.IllegalStateException

fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        for (card in input) {
            // Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            val winningNumbers = mutableSetOf<Int>()
            val (_, numbers) = card.split(": ")
            val (left, right) = numbers.split(" | ")
            val winningNumbersList = left.split(" ").filter { it.isNotEmpty() } .map { it.toInt() }
            val currentNumbersList = right.split(" ").filter { it.isNotEmpty() } .map { it.toInt() }
            winningNumbers.addAll(winningNumbersList)
            var score = 0
            for (number in currentNumbersList) {
                if (winningNumbers.contains(number)) {
                    if (score == 0) {
                        score += 1
                    } else {
                        score *= 2
                    }
                }
            }
            result += score
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val numsOfCopies = MutableList<Int>(input.size) { 1 }
        for (cardIndex in 0..<input.size) {
            // Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            val winningNumbers = mutableSetOf<Int>()
            val (_, numbers) = input[cardIndex].split(": ")
            val (left, right) = numbers.split(" | ")
            val winningNumbersList = left.split(" ").filter { it.isNotEmpty() } .map { it.toInt() }
            val currentNumbersList = right.split(" ").filter { it.isNotEmpty() } .map { it.toInt() }
            winningNumbers.addAll(winningNumbersList)
            var matches = 0
            for (number in currentNumbersList) {
                if (winningNumbers.contains(number)) {
                    matches++
                }
            }
            for (i in 1..matches) {
                if (cardIndex + i >= input.size)
                    break
                numsOfCopies[cardIndex + i] += numsOfCopies[cardIndex]
            }

        }
        return numsOfCopies.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
