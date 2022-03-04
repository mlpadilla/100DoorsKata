package com.mlpadilla.a100doorskata

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify

class DoorsTransformationsKtTest: BehaviorSpec ({
    given("a list with closed doors") {
        val givenDoors = listOf(
            Door(state = DoorState.CLOSED),
            Door(state = DoorState.CLOSED)
        )
        `when`("invoking openAllDoorsTransformation") {
            val resultingDoors = openAllDoorsTransformation(givenDoors)
            then("all doors are open") {
                resultingDoors.size shouldBe 2
                resultingDoors.shouldForAll {
                    it.state shouldBe DoorState.OPEN
                }
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
            then("all doors are open") {
                resultingDoors.size shouldBe 2
                resultingDoors.shouldForAll {
                    it.state shouldBe DoorState.OPEN
                }
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
                resultingDoors.size shouldBe 4
                resultingDoors[1].state shouldBe DoorState.CLOSED
                resultingDoors[3].state shouldBe DoorState.CLOSED
            }
            then("odd doors remain unchanged") {
                resultingDoors.size shouldBe 4
                resultingDoors[0].state shouldBe givenDoors[0].state
                resultingDoors[2].state shouldBe givenDoors[2].state
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
                resultingDoors.size shouldBe 4
                resultingDoors[1].state shouldBe DoorState.CLOSED
                resultingDoors[3].state shouldBe DoorState.CLOSED
            }
            then("odd doors remain unchanged") {
                resultingDoors.size shouldBe 4
                resultingDoors[0].state shouldBe givenDoors[0].state
                resultingDoors[2].state shouldBe givenDoors[2].state
            }
        }
    }

    given("a list a number of doors that is a multiplier of 3 (6)") {
        val toggledThirdDoor = mockk<Door>()
        val toggledSixthDoor = mockk<Door>()
        mockkStatic("com.mlpadilla.a100doorskata.DoorKt")
        val givenDoors = listOf<Door>(
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true) {
                every { toggle() } returns toggledThirdDoor
            },
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true) {
                every { toggle() } returns toggledSixthDoor
            }
        )
        `when`("invoking toggleEveryThirdDoorTransformation") {
            val resultingDoors = toggleEveryThirdDoorTransformation(givenDoors)
            then("every third door is toggled") {
                verify(exactly = 1) { givenDoors[2].toggle() }
                verify(exactly = 1) { givenDoors[5].toggle() }
                resultingDoors[2] shouldBe toggledThirdDoor
                resultingDoors[5] shouldBe toggledSixthDoor
            }
            then("all other doors remain unchanged") {
                resultingDoors[0] shouldBe givenDoors[0]
                resultingDoors[1] shouldBe givenDoors[1]
                resultingDoors[3] shouldBe givenDoors[3]
                resultingDoors[4] shouldBe givenDoors[4]
            }
            then("the resulting list contains as many doors as the original list") {
                resultingDoors.size shouldBe givenDoors.size
            }
        }
    }
})
