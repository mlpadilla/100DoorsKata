package com.mlpadilla.a100doorskata

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.shouldBe

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
})
