package com.mlpadilla.a100doorskata

import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import io.mockk.verifyOrder
import io.mockk.unmockkStatic

class ExerciseTest: BehaviorSpec({
    given("this exercise") {
        val doorsMock = mockk<A100DoorsInARow>(relaxed = true)
        mockkStatic(::toggleEveryXDoorTransformation)
        val openAllDoorsTransformationMock = mockk<(List<Door>) -> List<Door>>(relaxed = true)
        val closeEverySecondDoorTransformationMock = mockk<(List<Door>) -> List<Door>>(relaxed = true)
        val toggleEveryThirdDoorTransformationMock = mockk<(List<Door>) -> List<Door>>(relaxed = true)
        val toggleEveryXDoorTransformationMock = mockk<(List<Door>) -> List<Door>>(relaxed = true)
        every { openAllDoorsTransformation } returns openAllDoorsTransformationMock
        every { closeEverySecondDoorTransformation } returns closeEverySecondDoorTransformationMock
        every { toggleEveryThirdDoorTransformation } returns toggleEveryThirdDoorTransformationMock
        every { toggleEveryXDoorTransformation(any()) } returns toggleEveryXDoorTransformationMock

        Exercise(doors = doorsMock)
        `when`("performing the first pass") {
            then("every door is opened") {
                verifyOrder { doorsMock.pass(openAllDoorsTransformationMock) }
            }
        }
        `when`("performing the second pass") {
            then("you only visit every second door and close them") {
                verifyOrder { doorsMock.pass(closeEverySecondDoorTransformationMock) }
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
                verifyOrder { doorsMock.pass(toggleEveryThirdDoorTransformationMock) }
                for (i in 3..99) {
                    verifyOrder { doorsMock.pass(any()) }
                }
            }
        }
        `when`("performing the rest of passes") {
            then("continue until you complete the 100th pass only visiting the 100th door") {
                for (i in 0..2) {
                    verifyOrder { doorsMock.pass(any()) }
                }
                for (i in 3..99) {
                    verifyOrder { toggleEveryXDoorTransformation(x = i + 1) }
                    verifyOrder { doorsMock.pass(toggleEveryXDoorTransformationMock) }
                }
            }
        }
        then("there are 100 passes through the doors") {
            verify(exactly = 100) {
                doorsMock.pass(any())
            }
        }
        unmockkStatic(::toggleEveryXDoorTransformation)
    }
})
