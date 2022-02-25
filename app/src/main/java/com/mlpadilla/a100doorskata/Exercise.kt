package com.mlpadilla.a100doorskata

class Exercise(
    doors: A100DoorsInARow
) {
    init {
        for (i in 0..99) {
            doors.pass()
        }
    }
}
