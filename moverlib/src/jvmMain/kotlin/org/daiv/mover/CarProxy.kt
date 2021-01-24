package org.daiv.mover

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.KSerializer
import mu.KotlinLogging

interface CarProxyCom {
    fun <T : MoverCommunication> send(serializer: KSerializer<T>, t: T, response: () -> Unit)
}


class CarProxy(val carProxyCom: CarProxyCom, val streetMap: StreetMap) {
    val logger = KotlinLogging.logger {}
    private var carPosition = CarPosition(1, 1, CarDirection.East)
    private var velocity: Double = 0.01
    private fun send(goDirection: GoDirection) {
        val channel = Channel<Int>()
        val move = Move(carPosition, goDirection, velocity)
        logger.trace { "move: $move" }
        carProxyCom.send(Move.serializer(), move) {
            runBlocking {
                channel.send(5)
                logger.trace { "sended" }

            }
        }
        logger.trace { "start blocking" }
        runBlocking {
            channel.receive()
        }
        logger.trace { "ended blocking" }
        val prev = streetMap[carPosition]
        carPosition = carPosition.move(goDirection)
        val after = streetMap[carPosition]
        if (prev == null || after == null || !prev.isTransitionPossible(after)) {
            carProxyCom.send(MoverMessage.serializer(), MoverMessage("aua, auto hat sich verfahren ... jetz isses kaputt", "red")) {

            }
            throw RuntimeException("error")
        }
    }

    fun resetVelocity(velocity: Int) {
        this.velocity = velocity * 0.01
    }

    fun forward() {
        send(GoDirection.Forward)
    }

    fun done(){
        if(carPosition.x == 8 && carPosition.y == 7){
            carProxyCom.send(MoverMessage.serializer(), MoverMessage("Du hast es geschafft!!! Du hast Deine erste Aufgabe erfolgreich gemeistert! ", "blue")) {

            }
        } else {
            carProxyCom.send(MoverMessage.serializer(), MoverMessage("Das ist nicht das ziel ... $carPosition", "red")) {

            }
        }
    }

    fun right() {
        send(GoDirection.Right)
    }

    fun left() {
        send(GoDirection.Left)
    }

    fun isThisFieldForwardOnly(): Boolean {
        val street = streetMap[carPosition]!!
        if(street !is Street){
            return false
        }
        return street.streetConfig.isStraight
    }
}