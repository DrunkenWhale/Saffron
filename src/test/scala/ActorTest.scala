import org.saffron.actor.{Actor, ActorSystem, Receive}
import org.saffron.actor.Receive.given
import concurrent.ExecutionContext.Implicits.global

@main def test1(): Unit = {

  case class TestMessage(name: String, age: Int, gender: Boolean)

  class TestActor extends Actor {
    override def receive(message: Any): Receive = message match {
      case TestMessage(name, age, gender) => TestMessage(name, age, gender)
    }
  }
  val actorSystem = ActorSystem.of[TestActor]("test")
  val actor = actorSystem.prop("114514", actor = new TestActor())
  (actor ? TestMessage("野兽先辈", 114514, true)).onComplete(println)
  Thread.sleep(114514)
}