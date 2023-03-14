package com.example.firstapp.upskills

class NullablesExamples(name: String, code: String) {

    private val variableCreatedInTheConstructor: String

    init {
        variableCreatedInTheConstructor = name + code
    }

    private lateinit var lateInitIsVarAndNotNullable: String
    fun setNameOnce(firstName: String) {
        if (this::lateInitIsVarAndNotNullable.isInitialized) {
            return
        } else {
            lateInitIsVarAndNotNullable = firstName
        }
    }

    private val noNullableVal: String = "s"
    private val noNullableVal2: String? = "s"
    private var nullableVar: String? = "s"

    fun someMethod() {
        val oneString: String = noNullableVal2 ?: nullableVar ?: lateInitIsVarAndNotNullable
        val twoString: String = noNullableVal2 ?: nullableVar ?: noNullableVal

        nullableVar?.let { noNullableNow ->

            if (noNullableNow == null) {

            }
        }
    }

    private val aLazyExample: String by lazy { generateString() }
    private val anotherLazyExample: String? by lazy { generateString() }

//    private val aLazyExample2: String by lazy { generateNullableString() }
    private val anotherLazyExample2: String? by lazy { generateNullableString() }


    private fun generateString(): String {
        return ""
    }

    private fun generateNullableString(): String? {
        return ""
    }

}