package com.zonkesoft.headlinr

import kotlin.test.Test
import kotlin.test.assertTrue

class CommonGreetingTest {

    @Test
    fun testExample() {
        assertTrue(PlatformImplementation().greet().contains("Hello"), "Check 'Hello' is mentioned")
    }
}