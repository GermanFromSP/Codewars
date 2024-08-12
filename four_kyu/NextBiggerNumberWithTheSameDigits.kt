package codewars.four_kyu

/*
Create a function that takes a positive integer and returns the next bigger number that can be formed by rearranging
its digits. For example:

  12 ==> 21
 513 ==> 531
2017 ==> 2071
If the digits can't be rearranged to form a bigger number, return -1 (or nil in Swift, None in Rust):

  9 ==> -1
111 ==> -1
531 ==> -1
 */

fun nextBiggerNumber(n: Long): Long {
    val digits = n.toString().toCharArray()
    val length = digits.size

    var i = length - 2
    while (i >= 0 && digits[i] >= digits[i + 1]) {
        i--
    }

    if (i == -1) return -1

    var j = length - 1
    while (digits[j] <= digits[i]) {
        j--
    }

    digits[i] = digits[j].also { digits[j] = digits[i] }

    val rightPart = digits.slice(i + 1 until length).reversed()
    val resultDigits = (digits.slice(0 until i + 1) + rightPart).joinToString("")

    return resultDigits.toLong()
}