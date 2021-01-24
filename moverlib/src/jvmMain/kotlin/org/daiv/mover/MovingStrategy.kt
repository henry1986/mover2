package org.daiv.mover

import mu.KotlinLogging


class MovingStrategy(override val carProxy: CarProxy) : MovingInterface {
    init {
        carProxy.resetVelocity(5)
    }

    /**
     * Aufgabe: Bringe das Fahrzeug an sein Ziel. Du hast dabei drei Funktionen zur Verfügung:
     *
     * [forward]
     * [right]
     * [left]
     *
     */
    override fun run() {
        forward()
        forward()
        forward()
        forward()
        forward()
        forward()
        forward()
        right()
        forward()
        forward()
        forward()
        forward()
        forward()
        done()
    }
}