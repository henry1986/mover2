package org.daiv.mover

import org.daiv.websocket.mh2.parse
import org.daiv.websocket.mh2.stringify
import kotlin.test.Test

class InitFieldTest {
    @Test
    fun test() {
        val field = InitField(
            MainComponents(5.0, 9.0, "..", "..", 5.0, 9.0, 6.0),
            StreetMap(mapOf(StreetCoordinate(5, 9) to StreetCurve(9, 7, Curve.DownToLeft)))
        )
        val string = InitField.serializer().stringify(field, moverModule)
        val parsed = InitField.serializer().parse(moverModule, string)
        println("par: $parsed")
    }
}