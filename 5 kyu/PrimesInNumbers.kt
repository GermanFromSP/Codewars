package ru.german.android.expertcourseunscrambleword.result

/*
Given a positive number n > 1 find the prime factor decomposition of n. The result will be a string
with the following form :

 "(p1**n1)(p2**n2)...(pk**nk)"
with the p(i) in increasing order and n(i) empty if n(i) is 1.

Example: n = 86240 should return "(2**5)(5)(7**2)(11)"
 */

object PrimeDecomp {
    fun factors(l: Int): String {
        var num = l
        val factors = mutableMapOf<Int, Int>()

        var count = 0
        while (num % 2 == 0) {
            count++
            num /= 2
        }
        if (count > 0) factors[2] = count

        var i = 3
        while (i * i <= num) {
            count = 0
            while (num % i == 0) {
                count++
                num /= i
            }
            if (count > 0) factors[i] = count
            i += 2
        }

        if (num > 2) factors[num] = 1

        return factors.entries.joinToString("") { (prime, exp) ->
            if (exp == 1) "($prime)" else "($prime**$exp)"
        }
    }
}