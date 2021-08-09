package com.unhiredcoder.cipheynotes.fragments.fragmentNotes.util

import java.util.*

fun String.genKey(): String {
    return "${this}_${String().randomStringKey()}"
}

fun String.decodeKey(len: Int): String {
    return this.drop(len + 1)
}

fun String.randomStringKey(): String {
    val len = 6
    val base = "ABCDEFGHKLMNOPQRSTWXYZabcdefghjkmnpqrstwxyz"
    val max = base.length - 1
    var encoded = ""
    val random = Random()
    while (encoded.length < len + 1) {
        encoded += base[random.nextInt(max)]
    }
    return encoded
}