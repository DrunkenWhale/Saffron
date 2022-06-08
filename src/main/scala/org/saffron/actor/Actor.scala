package org.saffron.actor

import scala.annotation.targetName
import scala.collection.mutable
import scala.concurrent.Future
import concurrent.ExecutionContext.Implicits.global

trait Actor {

  private implicit val _actor: Actor = this

  //  private[saffron] val mailbox: mutable.Queue[Any] = mutable.Queue[Any]()

  def receive(message: Any): Receive

  def tell(message: Any)(implicit actor: Actor): Unit = {
    actor.receive(message)
  }

  def ask(message: Any)(implicit actor: Actor): Future[Any] = {
    Future {
      actor.receive(message)
    }
  }

  @targetName("askImpl")
  def ?(message: Any)(implicit actor: Actor): Future[Any] = ask(message)(actor)

  @targetName("tellImpl")
  def !(message: Any)(implicit actor: Actor): Unit = tell(message)(actor)

}


object Actor {
  implicit val emptySender: Actor = x => x
}