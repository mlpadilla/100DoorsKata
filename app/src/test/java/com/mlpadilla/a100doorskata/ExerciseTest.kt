package com.mlpadilla.a100doorskata

import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify

class ExerciseTest: BehaviorSpec({
    given("this exercise") {
        val doorsMock = mockk<A100DoorsInARow>(relaxed = true)
        val exercise = Exercise(doors = doorsMock)
        then("there are 100 passes through the doors") {
            verify(exactly = 100) {
                doorsMock.pass()
            }
        }
    }
})
