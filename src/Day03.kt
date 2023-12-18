fun main() {
    fun part1(input: List<String>): Int {
        fun isValid(i: Int, j: Int): Boolean {
            return i >= 0 && j >= 0 && i < input.size && j < input[0].length
        }

        var result = 0
        val dx = listOf(-1, 0, 1)
        val dy = listOf(-1, 0, 1)

        for (i in 0..<input.size) {
            var start = -1
            var partNumber = false
            for (j in 0..<input[i].length) {
                val curChar = input[i][j]
                if (curChar.isDigit()) {
                    if (start == -1) {
                        start = j
                    }
                    if (!partNumber) {
                        for (x_ in dx) {
                            for (y_ in dy) {
                                if (isValid(i + x_, j + y_)) {
                                    val c = input[i + x_][j + y_]
                                    if (!c.isDigit() && c != '.') {
                                        partNumber = true
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (partNumber) {
                        result += input[i].subSequence(start, j).toString().toInt()
                        partNumber = false
                    }
                    start = -1
                }
            }
            if (partNumber) {
                result += input[i].subSequence(start, input[0].length).toString().toInt()
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        fun isValid(i: Int, j: Int): Boolean {
            return i >= 0 && j >= 0 && i < input.size && j < input[0].length
        }

        val stars: MutableMap<Pair<Int, Int>, ArrayList<Int>> = HashMap()

        var result = 0
        val dx = listOf(-1, 0, 1)
        val dy = listOf(-1, 0, 1)

        for (i in 0..<input.size) {
            var start = -1
            var adjacentStarsList = mutableListOf<Pair<Int, Int>>()
            var partNumber = false
            for (j in 0..<input[i].length) {
                val curChar = input[i][j]
                if (curChar.isDigit()) {
                    if (start == -1) {
                        start = j
                    }

                    for (x_ in dx) {
                        for (y_ in dy) {
                            if (isValid(i + x_, j + y_)) {
                                val c = input[i + x_][j + y_]
                                if (!c.isDigit() && c != '.') {
                                    partNumber = true
                                    if (c == '*') {
                                        val starCoordinates = Pair(i + x_, j + y_)
                                        if (!adjacentStarsList.contains(starCoordinates)) {
                                            adjacentStarsList.add(starCoordinates)
                                        }
                                    }
                                }
                            }
                        }
                    }

                } else {
                    if (partNumber) {
                        val number = input[i].subSequence(start, j).toString().toInt()
                        partNumber = false
                        for (star in adjacentStarsList) {
                            if (stars.containsKey(star)) {
                                stars[star]!!.add(number)
                            } else {
                                stars[star] = arrayListOf()
                                stars[star]!!.add(number)
                            }
                        }
                    }
                    adjacentStarsList = mutableListOf()
                    start = -1
                }
            }
            if (partNumber) {
                val number = input[i].subSequence(start, input[0].length).toString().toInt()
                for (star in adjacentStarsList) {
                    if (stars.containsKey(star)) {
                        stars[star]!!.add(number)
                    } else {
                        stars[star] = arrayListOf()
                        stars[star]!!.add(number)
                    }
                }
            }
        }

        for (star in stars) {
            if (star.value.size == 2) {
                result += star.value[0] * star.value[1]
            }
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 4361)
    check(part2(testInput) == 467835)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}