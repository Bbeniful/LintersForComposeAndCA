package com.example.simpleapp.data


class FakeWrongObject {

    fun aMethod() {
        val objectName = ""
        println("I cannot be called directly in the presentation layer $objectName")
    }
}