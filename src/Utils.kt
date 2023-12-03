package utils

import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.reflect.KFunction1
import kotlin.test.assertEquals

abstract class Day {

  abstract val parts: List<KFunction1<List<String>, Any>>

  fun assert(value: Any) {
    val method = Thread.currentThread().stackTrace[2].methodName
    val str = method.removePrefix("part")
    val part = parts[str.takeWhile { it.isDigit() }.toInt() - 1]
    val data = readData(str.dropWhile { it.isDigit() }.lowercase())
    assertEquals(value.toString(), part(data).toString())
  }

  private fun readData(name: String): List<String> {
    val day = this::class.simpleName!!.lowercase()
    return Path("src/$day/data/$name.txt").readLines()
  }
}

fun String.md5() =
  BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun Any?.println() = println(this)
