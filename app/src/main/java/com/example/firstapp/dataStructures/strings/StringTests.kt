package com.example.firstapp.dataStructures.strings

import kotlin.system.measureTimeMillis

fun main() {
    val repeat = 1000
    val charArray = CharArray(repeat * abcd.length)
    val strBuilder = StringBuilder()
    (0 until 100).forEach {
        strBuilder.append(abcd)
    }
    strBuilder.toCharArray(charArray)

    val timeFill =measureTimeMillis { fillString(charArray) }
    println("timeFill $timeFill")

    val timeBuild =measureTimeMillis { buildString(charArray) }
    println("timeBuild $timeBuild")
}
/* O(xn^2) where x is the length of the word in this case
as it is a char the length is 1  and n is the length of the array,
the huge curve is due to for each element in the  array  all of it is being copying to a new array
 */
fun fillString(charArray: CharArray): String {
    var startString = ""
    charArray.forEach {
        startString += it
    }
    return startString
}
/* O(n) it isn't being copying from one array to another
*/
fun buildString(charArray: CharArray): String {
    val stringBuilder = StringBuilder()
    charArray.forEach {
        stringBuilder.append(it)
    }
    return stringBuilder.toString()
}

val abcd = "abcdefghijklmnopqrstuvwxyz"