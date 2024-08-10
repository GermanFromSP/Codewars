package codewars.five_kyu

import java.util.*

/*
For a given chemical formula represented by a string, count the number of atoms of each element contained in the
molecule and return an object (associative array in PHP, Dictionary<string, int> in C#, Map<String,Integer> in Java).

For example:

var water = 'H2O';
parseMolecule(water); // return {H: 2, O: 1}

var magnesiumHydroxide = 'Mg(OH)2';
parseMolecule(magnesiumHydroxide); // return {Mg: 1, O: 2, H: 2}

var fremySalt = 'K4[ON(SO3)2]2';
parseMolecule(fremySalt); // return {K: 4, O: 14, N: 2, S: 4}
As you can see, some formulas have brackets in them. The index outside the brackets tells you that you have to multiply
count of each atom inside the bracket on this index. For example, in Fe(NO3)2 you have one iron atom, two nitrogen atoms
 and six oxygen atoms.

Note that brackets may be round, square or curly and can also be nested. Index after the braces is optional.
 */

fun getAtoms(formula: String): Map<String, Int> {
    val stack = Stack<MutableMap<String, Int>>()
    val bracketsStack = Stack<Char>()
    var currentMap = mutableMapOf<String, Int>()
    var i = 0

    while (i < formula.length) {
        when (val char = formula[i]) {
            '(', '[', '{' -> {
                stack.push(currentMap)
                bracketsStack.push(char)
                currentMap = mutableMapOf()
                i++
            }
            ')', ']', '}' -> {
                if (bracketsStack.isEmpty() || !isMatchingPair(bracketsStack.pop(), char)) {
                    throw IllegalArgumentException("Mismatched brackets in formula: $formula")
                }

                i++
                val start = i
                while (i < formula.length && formula[i].isDigit()) i++
                val multiplier = formula.substring(start, i).takeIf { it.isNotEmpty() }?.toInt() ?: 1

                val tempMap = currentMap
                currentMap = stack.pop()
                tempMap.forEach { (element, count) ->
                    currentMap[element] = currentMap.getOrDefault(element, 0) + count * multiplier
                }
            }
            else -> {
                if (!char.isLetter()) throw IllegalArgumentException("Invalid character in formula: $char")

                val start = i
                i++
                while (i < formula.length && formula[i].isLowerCase()) i++
                val element = formula.substring(start, i)

                if (element.isEmpty() || !element[0].isUpperCase()) {
                    throw IllegalArgumentException("Invalid element symbol in formula: $element")
                }

                val countStart = i
                while (i < formula.length && formula[i].isDigit()) i++
                val count = formula.substring(countStart, i).takeIf { it.isNotEmpty() }?.toInt() ?: 1
                currentMap[element] = currentMap.getOrDefault(element, 0) + count
            }
        }
    }

    if (stack.isNotEmpty() || bracketsStack.isNotEmpty()) {
        throw IllegalArgumentException("Mismatched parentheses in formula: $formula")
    }

    return currentMap
}


fun isMatchingPair(open: Char, close: Char): Boolean {
    return (open == '(' && close == ')') ||
            (open == '[' && close == ']') ||
            (open == '{' && close == '}')
}
