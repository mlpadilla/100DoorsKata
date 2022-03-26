package com.mlpadilla.a100doorskata.ui

import com.mlpadilla.a100doorskata.A100DoorsInARow

fun A100DoorsInARow.toText(): String =
    doors.map { it.toChar() }
        .joinToString(separator = "")
