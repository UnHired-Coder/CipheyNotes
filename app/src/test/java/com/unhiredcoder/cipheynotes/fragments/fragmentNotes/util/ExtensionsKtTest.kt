package com.unhiredcoder.cipheynotes.fragments.fragmentNotes.util
import junit.framework.TestCase.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ExtensionsKtTest {

    private val deviceId: String = "fake_device_id"

    @Test
    fun testGenKey() {
        assertFalse(deviceId.genKey() == deviceId)
    }

    @Test
    fun testDecodeKey() {
        val key = "${deviceId}_SUCCESS"
        print(key.decodeKey(deviceId.length))
        assertTrue(key.decodeKey(deviceId.length) == "SUCCESS")
    }

    @Test
    fun testRandomStringKey() {
        val uniques: MutableSet<String> = mutableSetOf()
        val len = 200
        for (i in 1..len)
            uniques.add(String().randomStringKey())
        assertTrue(uniques.size == len)
    }
}