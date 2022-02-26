package com.mlpadilla.a100doorskata

import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder

class ExerciseTest: BehaviorSpec({
    given("this exercise") {
        val doorsMock = mockk<A100DoorsInARow>(relaxed = true)
        Exercise(doors = doorsMock)
        `when`("performing the first pass") {
            then("every door is opened") {
                verifyOrder { doorsMock.pass(openAllDoorsTransformation) }
                for (i in 1..99) {
                    verifyOrder { doorsMock.pass(match { it != openAllDoorsTransformation }) }
                }
            }
        }
        then("there are 100 passes through the doors") {
            verify(exactly = 100) {
                doorsMock.pass(any())
            }
        }
    }
})
