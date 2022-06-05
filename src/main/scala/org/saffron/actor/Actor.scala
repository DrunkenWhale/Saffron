package org.saffron.actor

import scala.annotation.targetName

trait Actor {

  def receive(message: Any): Unit

  def tell(message: Any): Unit

  def ask(message: Any): Any

  @targetName("askImpl")
  def ?(message: Any): Any = ask(message)

  @targetName("tellImpl")
  def !(message :Any):Unit = tell(message)

}
