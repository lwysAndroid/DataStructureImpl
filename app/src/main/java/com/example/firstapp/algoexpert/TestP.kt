package com.example.firstapp.algoexpert

class SomeClass(fName: String, fAge: Int) {
    private val firstName :String
    private val age :Int

    private lateinit var firstNameTest: String
    private val ageTest: Int by lazy { getAges() }

    init {
        firstName = fName
        age = fAge
        println(firstName)
        println(age)
    }

    fun setValues() {
        firstNameTest = ""
//        age = 1
    }

    private fun getAges() = 5

}