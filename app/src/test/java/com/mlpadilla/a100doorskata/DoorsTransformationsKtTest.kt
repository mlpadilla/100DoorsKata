package com.mlpadilla.a100doorskata

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import io.mockk.unmockkStatic

class DoorsTransformationsKtTest: BehaviorSpec ({
    given("a list with closed doors") {
        val givenDoors = listOf(
            Door(state = DoorState.CLOSED),
            Door(state = DoorState.CLOSED)
        )
        `when`("invoking openAllDoorsTransformation") {
            val resultingDoors = openAllDoorsTransformation(givenDoors)
            then("all doors are open") {
                resultingDoors.shouldForAll {
                    it.state shouldBe DoorState.OPEN
                }
            }
            then("the resulting list contains as many doors as the original list") {
                resultingDoors.size shouldBe 2
            }
        }
    }

    given("a list with open doors") {
        val givenDoors = listOf(
            Door(state = DoorState.OPEN),
            Door(state = DoorState.OPEN)
        )
        `when`("invoking openAllDoorsTransformation") {
            val resultingDoors = openAllDoorsTransformation(givenDoors)
            then("all doors are open") { resultingDoors.size shouldBe 2
                resultingDoors.shouldForAll {
                    it.state shouldBe DoorState.OPEN
                }
            }
            then("the resulting list contains as many doors as the original list") {
                resultingDoors.size shouldBe 2
            }
        }
    }

    given("a list with many open doors") {
        val givenDoors = listOf(
            Door(state = DoorState.OPEN),
            Door(state = DoorState.OPEN),
            Door(state = DoorState.OPEN),
            Door(state = DoorState.OPEN),
        )
        `when`("invoking closeEverySecondDoorTransformation") {
            val resultingDoors = closeEverySecondDoorTransformation(givenDoors)
            then("even doors are closed") {
                resultingDoors[1].state shouldBe DoorState.CLOSED
                resultingDoors[3].state shouldBe DoorState.CLOSED
            }
            then("odd doors remain unchanged") {
                resultingDoors[0].state shouldBe givenDoors[0].state
                resultingDoors[2].state shouldBe givenDoors[2].state
            }
            then("the resulting list contains as many doors as the original list") {
                resultingDoors.size shouldBe 4
            }
        }
    }

    given("a list with many closed doors") {
        val givenDoors = listOf(
            Door(state = DoorState.CLOSED),
            Door(state = DoorState.CLOSED),
            Door(state = DoorState.CLOSED),
            Door(state = DoorState.CLOSED),
        )
        `when`("invoking closeEverySecondDoorTransformation") {
            val resultingDoors = closeEverySecondDoorTransformation(givenDoors)
            then("even doors are closed") {
                resultingDoors[1].state shouldBe DoorState.CLOSED
                resultingDoors[3].state shouldBe DoorState.CLOSED
            }
            then("odd doors remain unchanged") {
                resultingDoors[0].state shouldBe givenDoors[0].state
                resultingDoors[2].state shouldBe givenDoors[2].state
            }
            then("the resulting list contains as many doors as the original list") {
                resultingDoors.size shouldBe 4
            }
        }
    }

    given("a list with a number of doors that is a multiplier of x (list = 8, x = 4)") {
        val toggledXDoor = mockk<Door>()
        val toggled2XDoor = mockk<Door>()
        mockkStatic("com.mlpadilla.a100doorskata.DoorKt")
        val givenDoors = listOf<Door>(
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true) {
                every { toggle() } returns toggledXDoor
            },
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true) {
                every { toggle() } returns toggled2XDoor
            }
        )
        `when`("invoking toggleEveryXDoorTransformation (x = 4)") {
            val resultingDoors = toggleEveryXDoorTransformation(x = 4)(givenDoors)
            then("every x door is toggled (4, 8)") {
                verify(exactly = 1) { givenDoors[3].toggle() }
                verify(exactly = 1) { givenDoors[7].toggle() }
                resultingDoors[3] shouldBe toggledXDoor
                resultingDoors[7] shouldBe toggled2XDoor
            }
            then("all other doors remain unchanged") {
                resultingDoors[0] shouldBe givenDoors[0]
                resultingDoors[1] shouldBe givenDoors[1]
                resultingDoors[2] shouldBe givenDoors[2]
                resultingDoors[4] shouldBe givenDoors[4]
                resultingDoors[5] shouldBe givenDoors[5]
                resultingDoors[6] shouldBe givenDoors[6]
            }
            then("the resulting list contains as many doors as the original list") {
                resultingDoors.size shouldBe givenDoors.size
            }
        }
        unmockkStatic("com.mlpadilla.a100doorskata.DoorKt")
    }

    given("a list of doors (2 doors)") {
        val toggledXDoor = mockk<Door>()
        mockkStatic("com.mlpadilla.a100doorskata.DoorKt")
        val givenDoors = listOf<Door>(
            mockk(relaxed = true),
            mockk(relaxed = true) {
                every { toggle() } returns toggledXDoor
            },
        )
        `when`("invoking toggleEveryXDoorTransformation with x = size of the list (x = 2)") {
            val resultingDoors = toggleEveryXDoorTransformation(x = 2)(givenDoors)
            then("only the last door is toggled") {
                verify(exactly = 1) { givenDoors[1].toggle() }
                resultingDoors[1] shouldBe toggledXDoor
            }
            then("all other doors remain unchanged") {
                resultingDoors[0] shouldBe givenDoors[0]
            }
            then("the resulting list contains as many doors as the original list") {
                resultingDoors.size shouldBe givenDoors.size
            }
        }
        unmockkStatic("com.mlpadilla.a100doorskata.DoorKt")
    }
})
