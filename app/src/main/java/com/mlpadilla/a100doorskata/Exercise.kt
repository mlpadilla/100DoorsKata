package com.mlpadilla.a100doorskata

class Exercise(
    doors: A100DoorsInARow
) {
    init {
        doors.pass(openAllDoorsTransformation)
        for (i in 1..99) {
            doors.pass(identityDoorsTransformation)
        }
    }
}

private val identityDoorsTransformation: DoorsTransformation = { it }
