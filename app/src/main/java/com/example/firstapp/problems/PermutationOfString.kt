package com.example.firstapp.problems

/**
 * Given a smaller string s ans a bigger string b, design an algorithm to find all
 * permutation the shorter string withing the longer one, print the location of
 * each permutation
 */

fun indexOfPermutations(smallS: String, bigS: String): Array<Int> {
    val hashMapSmallS = HashMap<Char, Int>()
    smallS.forEach { char ->
        var repeatsNumber = 1
        hashMapSmallS[char]?.let {
            repeatsNumber += it
        }
        hashMapSmallS.set(key = char, value = repeatsNumber)
    }
    val arrayListOfIndex = ArrayList<Int>()

    bigS.forEachIndexed() { index, currentChar ->
        if (index + smallS.length <= bigS.length) {
            if (hashMapSmallS[currentChar] != null) {
                validatePermutation(
                    hashMapSmallS,
                    bigS.substring(index, index + smallS.length)
                ).also {
                    if (it) {
                        arrayListOfIndex.add(index)
                    }
                }
            }
        } else {
            return@forEachIndexed
        }
    }
    return arrayListOfIndex.toTypedArray()
}

private fun validatePermutation(
    hashMapChar: HashMap<Char, Int>,
    substring: String
): Boolean {
    val substringHashMap = HashMap<Char, Int>()
    substring.forEach { currentChar ->
        hashMapChar[currentChar]?.let { currentChartRepeatsNumber ->
            var repeatsNumber = 1
            substringHashMap[currentChar]?.let {
                repeatsNumber += it
            }
            if (currentChartRepeatsNumber >= repeatsNumber) {
                substringHashMap.set(key = currentChar, value = repeatsNumber)
            } else {
                return false
            }

        } ?: return false
    }
    return true
}


fun indexOfPermutationsTest() {
    val smallS = "abbc"
    val bigS = "cbabadcbbabbcbabaabccbabc"
    println("Big: $bigS")
    println("Small: $smallS")
    indexOfPermutations(smallS = smallS, bigS = bigS).also { arrayOfIndex ->
        println("Permutations: ${arrayOfIndex.size}")
        println()
        arrayOfIndex.forEach { startIndex ->
            val substringPermutation = bigS.substring(startIndex, startIndex + smallS.length)
            println("($startIndex, ${startIndex + smallS.length}) : $substringPermutation")
        }
    }
}