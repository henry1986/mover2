package org.daiv.mover

import mu.KotlinLoggingLevel
import org.daiv.websocket.*

fun main() {
//    document.write("Hello, world!")
    LoggingSettings.setLogging(emptyList(), KotlinLoggingLevel.TRACE, true)
//    configureLogging()
    EBDataHandler(
        EBWebsocket(),
        moverModule,
        listOf(tranlaterWithEB(InitField.serializer(), context = moverModule) { event, eb ->
            println("create gui")
            val gui = Gui(event)
            println("gui created")
            eb.currentTranslaters = {
                listOf<Translater<out WSEvent>>(translater(Move.serializer(), moverModule) {
                    gui.moveCar(it) {
                        eb.send(MovementResponse.serializer(), MovementResponse(), MovementResponse.serializer()) {}
                    }
                }, translater(MoverMessage.serializer(), moverModule) {
                    gui.message(it)
                })
            }
        })
    )

}
