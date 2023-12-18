import kotlin.math.max
import kotlin.math.min

fun main() {
    val mapsNames = listOf(
        "seed-to-soil map:",
        "soil-to-fertilizer map:",
        "fertilizer-to-water map:",
        "water-to-light map:",
        "light-to-temperature map:",
        "temperature-to-humidity map:",
        "humidity-to-location map:"
    )

    fun getSeedsList(input: List<String>): MutableList<Long> {
        val (_, seeds) = input[0].split(": ")
        return seeds.split(" ").filter { it.isNotEmpty() }.map{ it.toLong() }.toMutableList()
    }

    fun getListOfMaps(input: List<String>): MutableList<List<List<Long>>> {
        fun findEndIndex(startIndex: Int): Int {
            val subListIndex = input.subList(startIndex, input.size).indexOf("")
            return if (subListIndex != -1) startIndex + subListIndex else input.size
        }
        fun convertToMap(startIndex: Int, endIndex: Int): List<List<Long>> {
            return input.subList(startIndex, endIndex).map { it -> it.split(" ").filter { it.isNotEmpty() }.map { it.toLong()} }
        }


        val listOfMaps: MutableList<List<List<Long>>> = mutableListOf()

        for (mapName in mapsNames) {
            val startIndex = input.indexOf(mapName) + 1
            val endIndex = findEndIndex(startIndex)
            val map = convertToMap(startIndex, endIndex)
            listOfMaps.add(map)
//            println(map)
        }
        return listOfMaps
    }

    fun part1(input: List<String>): Long {
        val listOfMaps = getListOfMaps(input)
        val seedsList = getSeedsList(input)

        for (map in listOfMaps) {
            for (seedIndex in 0..<seedsList.size) {
                val id = seedsList[seedIndex]
                for (triple in map) {
                    val (destStartIndex, sourceStartIndex, length) = triple
                    if (id >= sourceStartIndex && id < sourceStartIndex + length) {
                        seedsList[seedIndex] = destStartIndex + (id - sourceStartIndex)
                    }
                }
            }
        }
        return seedsList.min()
    }

    fun part2(input: List<String>): Int {
        val listOfMaps = getListOfMaps(input)
        var seedsList = getSeedsList(input)

        for (map in listOfMaps) {
            for (indexInSeedsList in seedsList.indices step 2) {
                val startId = seedsList[indexInSeedsList]
                val seedsSegmentLength = seedsList[indexInSeedsList + 1]
                val tmpList = mutableListOf<Long>()

                for (triple in map) {
                    val (destStartIndex, sourceStartIndex, length) = triple
                    if (startId + length - 1 >= sourceStartIndex && startId < sourceStartIndex + length) {
                        if (startId < sourceStartIndex) {
                            tmpList.add(startId)
                            tmpList.add(sourceStartIndex - startId)
                        }
                        var start = max(startId, sourceStartIndex)
                        start = destStartIndex + (start - sourceStartIndex)
                        tmpList.add(start)
                        val end = min(startId + seedsSegmentLength, sourceStartIndex + length)
                        tmpList.add(end - start)
                        if (startId + seedsSegmentLength > sourceStartIndex + length) {
                            tmpList.add(sourceStartIndex + length)
                            tmpList.add(startId + seedsSegmentLength - (sourceStartIndex + length))
                        }
                        seedsList = tmpList
                    }

                }
                print(seedsList)
            }
        }
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
//    check(part1(testInput) == 35.toLong())
    part2(testInput)
//    check(part2(testInput) == 30)

    val input = readInput("Day05")
    part1(input).println()
//    part2(input).println()
}
