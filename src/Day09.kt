fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            val values = line.split(" ").filter { it.isNotEmpty() }.map { it.toInt() }.toMutableList()
            val triangle: MutableList<MutableList<Int>> = mutableListOf()
            triangle.add(values)
            while(!triangle.last().all { it == 0 }) {
                val cur = triangle.last()
                val tmp = mutableListOf<Int>()
                for (i in 1..<cur.size) {
                    tmp.add(cur[i] - cur[i-1])
                }
                triangle.add(tmp)
            }
            triangle.last().add(0)
            for (i in triangle.size - 2 downTo 0) {
                triangle[i].add(triangle[i].last() + triangle[i + 1].last())
            }
            sum += triangle[0].last()
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            val values = line.split(" ").filter { it.isNotEmpty() }.map { it.toInt() }.toMutableList()
            val triangle: MutableList<MutableList<Int>> = mutableListOf()
            triangle.add(values)
            while(!triangle.last().all { it == 0 }) {
                val cur = triangle.last()
                val tmp = mutableListOf<Int>()
                for (i in 1..<cur.size) {
                    tmp.add(cur[i] - cur[i-1])
                }
                triangle.add(tmp)
            }
            var lastPredicted = 0
            for (i in triangle.size - 2 downTo 0) {
                val predictedVal = triangle[i][0] - lastPredicted
                lastPredicted = predictedVal
            }
            sum += lastPredicted
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 114)
    check(part2(testInput) == 2)

    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}
