fun main() {
    fun part1(input: List<String>): Int {
        var result = 1
        val (_, timeValues) = input[0].split("Time:")
        val timeList = timeValues.split(" ").filter { it.isNotEmpty() }.map{ it.toInt() }
        val (_, distanceValues) = input[1].split("Distance:")
        val distanceList = distanceValues.split(" ").filter { it.isNotEmpty() }.map { it.toInt() }

        for (raceIndex in 0..<timeList.size) {
            var optionsNum = 0
            for (holdTime in 1..<timeList[raceIndex]) {
                val moveTime = timeList[raceIndex] - holdTime
                val speed = holdTime
                val distance = speed * moveTime
                if (distance > distanceList[raceIndex]) {
                    optionsNum++
                }
            }
            result *= optionsNum
        }
        return result
    }

    fun part2(input: List<String>): Long {
        var result = 0.toLong()
        val (_, timeValues) = input[0].split("Time:")
        val time = timeValues.replace(" ", "").toLong()
        val (_, distanceValues) = input[1].split("Distance:")
        val distance = distanceValues.replace(" ", "").toLong()
        for (holdTime in 1..<time) {
            val moveTime = time - holdTime
            val speed = holdTime
            val dist = speed * moveTime
            if (dist > distance) {
                result++
            }
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 288)
    check(part2(testInput) == 71503.toLong())

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
