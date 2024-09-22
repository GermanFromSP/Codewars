package ru.german.android.expertcourseunscrambleword.game

/*
When working with color values it can sometimes be useful to extract the individual red, green, and
blue (RGB) component values for a color. Implement a function that meets these requirements:

Accepts a case-insensitive hexadecimal color string as its parameter (ex. "#FF9933" or "#ff9933")
Returns a Map<String, int> with the structure {r: 255, g: 153, b: 51} where r, g, and b range from
0 through 255
Note: your implementation does not need to support the shorthand form of hexadecimal notation (ie
 "#FFF")

Example
"#FF9933" --> {r: 255, g: 153, b: 51}
 */

fun hexStringToRGB(hexString: String): RGB {
    val cleanHex = hexString.removePrefix("#").uppercase()

    val r = cleanHex.substring(0, 2).toInt(16)
    val g = cleanHex.substring(2, 4).toInt(16)
    val b = cleanHex.substring(4, 6).toInt(16)

    return RGB(r, g, b)
}

data class RGB(val r: Int, val g: Int, val b: Int)
