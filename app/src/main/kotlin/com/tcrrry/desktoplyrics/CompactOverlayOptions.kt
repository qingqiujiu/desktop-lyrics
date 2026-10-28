package com.tcrrry.desktoplyrics

import kotlin.math.ceil
import kotlin.math.roundToInt

/** Shared by the settings screen and the overlay so two rows always fit. */
object CompactOverlayOptions {
    const val AUTO = "auto"
    const val SINGLE = "single"
    const val DOUBLE = "double"
    const val TRANSLATION = "translation"
    const val NEXT_LINE = "next"

    fun lineMode(value: String?): String = when (value) {
        SINGLE, DOUBLE -> value
        else -> AUTO
    }

    fun secondaryMode(value: String?): String = if (value == NEXT_LINE) NEXT_LINE else TRANSLATION

    fun minimumHeightDp(percent: Int, mode: String): Int {
        val scale = percent.coerceIn(35, 150) / 100f
        return if (lineMode(mode) == DOUBLE) {
            // Match WebView's original + secondary line heights, with rounding room.
            ceil(10 + (30 * 1.15 + 23 * 1.15) * scale).toInt()
        } else {
            (9.5f + 34.5f * scale).roundToInt().coerceIn(32, 64)
        }
    }
}
