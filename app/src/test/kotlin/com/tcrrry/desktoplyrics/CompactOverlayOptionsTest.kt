package com.tcrrry.desktoplyrics

import org.junit.Assert.*
import org.junit.Test

class CompactOverlayOptionsTest {
    @Test fun doubleRowsFitAtEverySupportedFontScale() {
        for (percent in 35..150) {
            val height = CompactOverlayOptions.minimumHeightDp(percent, CompactOverlayOptions.DOUBLE)
            val webHeight = 7 + (30 * 1.15 + 23 * 1.15) * percent / 100
            assertTrue("two rows clipped at $percent%", height >= webHeight)
            assertTrue("compact mode became expanded at $percent%", height <= 130)
            assertTrue(height >= CompactOverlayOptions.minimumHeightDp(percent, CompactOverlayOptions.SINGLE))
        }
    }

    @Test fun legacyPreferencesKeepAutomaticSizing() {
        assertEquals(CompactOverlayOptions.AUTO, CompactOverlayOptions.lineMode(null))
        assertEquals(CompactOverlayOptions.AUTO, CompactOverlayOptions.lineMode("unknown"))
        assertEquals(44, CompactOverlayOptions.minimumHeightDp(100, CompactOverlayOptions.AUTO))
        assertEquals(CompactOverlayOptions.TRANSLATION, CompactOverlayOptions.secondaryMode(null))
    }
}
