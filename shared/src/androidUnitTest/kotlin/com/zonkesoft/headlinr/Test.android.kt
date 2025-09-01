package com.zonkesoft.headlinr

import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", PlatformImplementation().greet().contains("Android"))
    }
}