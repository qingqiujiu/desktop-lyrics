package com.tcrrry.desktoplyrics

import android.content.Context
import androidx.core.content.ContextCompat

/** Resource-backed palette for settings screens built in Kotlin. */
internal val Context.textPrimary: Int get() = ContextCompat.getColor(this, R.color.text_primary)
internal val Context.textSecondary: Int get() = ContextCompat.getColor(this, R.color.text_secondary)
internal val Context.textHint: Int get() = ContextCompat.getColor(this, R.color.text_hint)
internal val Context.accentColor: Int get() = ContextCompat.getColor(this, R.color.accent)
internal val Context.surfaceColor: Int get() = ContextCompat.getColor(this, R.color.surface)
