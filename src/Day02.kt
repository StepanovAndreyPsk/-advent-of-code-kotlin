fun main() {
    fun part1(input: List<String>): Int {
        val redCnt = 12
        val greenCnt = 13
        val blueCnt = 14
        var result = 0

        gamesLoop@ for (index in 0..<input.size) {
            val gameId = index + 1
            val line = input[index]

            // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            val (_, game) = line.split(": ")
            val sets = game.split("; ")
            for (set in sets) {
                val colors = set.split(", ")
                for (pair in colors) {
                    val (numStr, color) = pair.split(' ')
                    val num = numStr.toInt()
                    if (color == "red" && num > redCnt || color == "green" && num > greenCnt || color == "blue" && num > blueCnt) {
                        continue@gamesLoop
                    }
                }
            }
            result += gameId
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        for (index in 0..<input.size) {
            val line = input[index]
            var reqRed = 0
            var reqGreen = 0
            var reqBlue = 0

            // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            val (_, game) = line.split(": ")
            val sets = game.split("; ")
            for (set in sets) {
                val colors = set.split(", ")
                for (pair in colors) {
                    val (numStr, color) = pair.split(' ')
                    val num = numStr.toInt()
                    when (color) {
                        "red" -> if (reqRed < num) { reqRed = num }
                        "green" -> if (reqGreen < num) { reqGreen = num }
                        "blue" -> if (reqBlue < num) { reqBlue = num }
                    }
                }
            }
            result += reqRed * reqGreen * reqBlue
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}