package org.saffron.actor

import scala.Conversion

final case class Receive(private[saffron] val res: Any) {}

object Receive {

  given wrapperToReceive: Conversion[Any, Receive] =
    Receive(_)

}