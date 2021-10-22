/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package dedicated

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.stingion.makeitfine.data.model.Company
import helpful.helpful
import helpful.helpfulOther
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.springframework.context.annotation.Description
import kotlin.random.Random
import helpful.helpfulThird as third

@Description("Temp class for learning kotlin")
class KotlinLearnTest {

    companion object {
        val companies = listOf(
            Company(1, "company1", "Some desc 1"),
            Company(2, "company2", "Some desc 2"),
            Company(3, "company3", null)
        )
    }

    val prettyJson = { s: String? ->
        val gson = GsonBuilder().setPrettyPrinting().create()
        val je = JsonParser.parseString(s)
        gson.toJson(je)
    }

    val printlnPrettyJson: (String?) -> Unit = { s: String? -> println(prettyJson(s!!)) }

    @Test
    fun `check printlnPrettyJson`() {
        helpful()
        helpfulOther()
        third()

        assertDoesNotThrow { printlnPrettyJson("{\"a\":1234}") }
        assertThrows<NullPointerException> { printlnPrettyJson(null) }
    }

    @Test
    fun `list and map get and set`() {
        val list = mutableListOf<Int?>()

        assertThrows<IndexOutOfBoundsException> { list[0] = 3 }
        assertThrows<IndexOutOfBoundsException> { companies[3] }
        assertDoesNotThrow { list.add(1) }
        assertDoesNotThrow { list[0] = 7 }
        assertDoesNotThrow { companies[0] }

        val map = mutableMapOf<String, Double>()

        assertDoesNotThrow { map["1"] = 3.3 }
        assertDoesNotThrow { map["222"] }
        assertDoesNotThrow { map.put("2", 3.3) }
        assertDoesNotThrow { map.get("1") }

        assertTrue(map.size > 1)
        map["1"]?.let { e -> println("there is so key with value = $e") }
        map["null"]?.let { e -> println("there is so key with value = $e") }
    }

    @Test
    fun `check NPE`() {
        val company: Company? = null

        assertThrows<NullPointerException> { company!!.id }
        assertDoesNotThrow { company?.id }
        assertThrows<NullPointerException> { printlnPrettyJson(company?.name) }
    }

    @Test
    fun `infix fun`() {
        assertEquals(2, this showInf 2.2)
        assertNotEquals(2, this showInf 12.2)

        assertEquals(companies[1]?.id to "2", Pair(2, "2"))
        assertDoesNotThrow { assertEquals(companies[2]!!.id to "3", Pair(3, "3")) }

        assertDoesNotThrow { assertEquals(companies[2]!!.description to "4", Pair(null, "4")) }
        assertThrows<NullPointerException> { assertEquals(companies[2]!!.description!! to "4", Pair(null, "4")) }
    }

    private infix fun showInf(d: Double): Int {
        return d.toInt()
    }

    @Test
    fun `fun by default`() {
        assertDoesNotThrow { println(show(d = 2.2, sign = "!")) }
        assertDoesNotThrow { println(show(d = 2.2)) }
        assertDoesNotThrow { println(show(2, 3.2, "?")) }
        assertDoesNotThrow { show { println("abc") } }
        assertDoesNotThrow { showNext2("") { a -> println("=$a=") } }
    }

    fun show(other: Int = 1, d: Double, sign: String = "<>"): String {
        return "$sign$d$sign + i"
    }

    fun show(c: () -> Unit) = c.invoke()

    fun showNext(c: (a: String) -> Unit): Unit = c.invoke("111")

    fun showNext2(s: String = "!!!", c: (a: String) -> Unit): Unit = c.invoke(s)

    @Test
    fun namedParam() {
        manyParam(name = "Sir", surName = "Any", age = 5)
        manyParam(age = 5, name = "Sir")
        val defName: String = "Mo"
        manyParam(age = 5, name = defName)
        manyParam(age = 5, name = defName, p = *arrayOf("a1", "b1", "c1"))

        one(1, 2, 3, 4, 5)?.let { e -> println(e) }
    }

    fun one(vararg p: Int) = p.map { e -> e.toString() }.reduce { e1, e2 -> "$e1 $e2" }

    fun manyParam(name: String, surName: String = "Tomson", age: Int, vararg p: String) = println(
        "$name $surName $age ${
            java.util.Arrays
                .toString(p)
        }"
    )

    @Test
    fun `inside funs`() {
        assertDoesNotThrow { insideHave(1) }
        assertThrows<NullPointerException> { insideHave(null) }
    }

    fun insideHave(i: Int?) {
        var k1 = 1;
        val k2 = 1;
        fun inside(j: Int?) {
            k1 = 2
            println("$k1 $k2")
            i!!.toString()
        }
        inside(i)
        println(k1)
    }

    @Test
    fun `small check General types`() {
        assertDoesNotThrow {
            val s: ArrayList<S<String>>? = null
            val obj: List<S<Any>>? = s
            obj.toString()
        }

        assertDoesNotThrow {
            var s: MutableList<SI<Number>> = mutableListOf()
            s.add(SI<Int>() as SI<Number>)
            s[0].pr(2)
            s[0].pr(2.5)
        }

        `generic funs`(3, 3.5)
    }

    fun <T, K> `generic funs`(i: T, k: K) {
        println("$i <> $k")
    }

    class S<out T> {
    }

    class SI<in T> {
        fun pr(i: T) {
            println(i.toString())
        }
    }

    @Test
    fun `tailrec`() {
        assertEquals((120).toLong(), factorial(5))
    }

    tailrec fun factorial(n: Int): Long = if (n < 2) (1).toLong() else n * factorial(n - 1)

    @Test
    fun `inline funs`() {
        assertDoesNotThrow {
            var i = 15;
            do {
                val b = System.nanoTime();
                inline({ s -> println(s) })
                println(System.nanoTime() - b)
            } while (i-- > 0)
        }

        inline { s -> return }
        inline { s -> return }
        crossInline { println("okey") }
    }

    inline fun inline(f: (s: String) -> Unit) {
        f.invoke("abc")
    }

    inline fun crossInline(crossinline f: () -> Unit) {
        val inside = object : Runnable {
            override fun run() = f()
        }
        inside.run()
    }

    @Test
    fun `reified type`() {
        findParentOfType(String::class.java)
        findParentOfType<String>()
    }

    private inline fun <reified T> findParentOfType(): T? {
        !(null is T)
        return null;
    }

    fun <T> findParentOfType(clazz: Class<T>): T? {
        var p = this.javaClass
        while (p != null && !clazz.isInstance(p)) {
            return null
        }
        @Suppress("UNCHECKED_CAST")
        return p as T
    }

    var k: String?
        inline get() = "a"
        set(value) {
            k = "s"
        }

    @Test
    fun `extensions`() {
        val lI = mutableListOf(1, 2, 2)
        val lN = mutableListOf(1.3, 2, 2)
        val lA = mutableListOf(1.3, "Any", 2)

        lI.swap(0, 2)
        lN.swap(0, 2)
        lA.swap(2, 1)

        println(lI)
        println(lN)
        println(lA)
    }

    fun <T> MutableList<T>.swap(i1: Int, i2: Int) {
        val tmp = this[i1];
        this[i1] = this[i2]
        this[i2] = tmp
    }

    @Test
    fun `null type`() {
        var b: B? = null
        println(b.toString())
        b.one()

        b = B()
        println(b?.i)
    }

    open class A
    class B : A()

    fun B?.one() {
        println(this)
    }

    val B.i: Int
        get() = Random(5).nextInt()

    @Test
    fun `help object`() {
        assertDoesNotThrow {
            Newk.show()
            println(Newk.i)
        }
    }

    class Newk {
        companion object {
            internal fun show() {
                println("Say hello!!!")
            }
        }
    }

    val Newk.Companion.i: Int
        get() {
            return 333
        }

    @Test
    fun `lambdas`() {

        val a = { i: Int?, j: Double -> i!! * j }
        lam(a)

        val intPlus: Int.(Int) -> Int = Int::plus
        println(intPlus.invoke(1, 3))
        println(intPlus(1, 3))
        println(3.intPlus(1))

        val an: Double.(Double) -> Double = Double::plus
        mapOf(1 to 2, 2 to 3, 3 to 4).forEach { _, value -> println("$value!") }
        println(3.3.an(33.0))
    }

    fun lam(f: ttt) {
        println("f=${f.invoke(5, 3.3)}");
    }

}

typealias ttt = (Int?, Double) -> Double?
