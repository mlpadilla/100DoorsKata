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
            }
        }
        `when`("performing the second pass") {
            then("you only visit every second door and close them") {
                verifyOrder { doorsMock.pass(closeEverySecondDoorTransformation) }
                for (i in 2..99) {
                    verifyOrder { doorsMock.pass(any()) }
                }
            }
        }
        `when`("performing the third pass") {
            then("you visit every 3rd door toggling the door's state") {
                for (i in 0..1) {
                    verifyOrder { doorsMock.pass(any()) }
                }
                verifyOrder { doorsMock.pass(toggleEveryThirdDoorTransformation) }
                for (i in 3..99) {
                    verifyOrder { doorsMock.pass(any()) }
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
