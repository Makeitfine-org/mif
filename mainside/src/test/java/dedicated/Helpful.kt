/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package helpful

fun helpful() {
    println("Helpful fun")
}


fun helpfulOther() {
    println("HelpfulOther fun")
}

fun helpfulThird() {
    println("HelpfulOther third")
    helpfulFourth()
}

private fun helpfulFourth() {
    println("HelpfulOther third")
}