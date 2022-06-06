package org.saffron.actor

import scala.collection.mutable

private class ActorSystem(val name: String) {

  private val actorMap: mutable.HashMap[String, Actor] = new mutable.HashMap[String, Actor]()

  def prop[T <: Actor](path: String, actor: T): Actor = {
    actorMap.put(path, actor)
    actor.asInstanceOf[Actor]
  }

}


object ActorSystem {

  private val actorSystemMap: mutable.HashMap[String, ActorSystem] = new mutable.HashMap[String, ActorSystem]()

  def of[T <: Actor](name: String): ActorSystem = {
    val actorSystemOpt: Option[ActorSystem] = actorSystemMap.get(name)
    if (actorSystemOpt.isEmpty) {
      val actorSystem = new ActorSystem(name)
      actorSystemMap.put(actorSystem.name, actorSystem)
      actorSystem
    } else {
      actorSystemOpt.get
    }
  }


}