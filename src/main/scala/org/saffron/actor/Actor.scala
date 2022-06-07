package org.saffron.actor

import scala.annotation.targetName
import scala.collection.mutable
import scala.concurrent.Future

trait Actor {

  private[saffron] val mailbox: mutable.Queue[Any] = mutable.Queue[Any]()

  def receive(message: Any): Unit

  def tell(message: Any)(implicit actor: Actor): Unit = {
    this.mailbox.addOne(ActorMessage(message = message, sender = actor))
  }

  def ask(message: Any)(implicit actor:Actor): Future[Any]

  @targetName("askImpl")
  def ?(message: Any): Future[Any] = ask(message)

  @targetName("tellImpl")
  def !(message: Any): Unit = tell(message)

}
