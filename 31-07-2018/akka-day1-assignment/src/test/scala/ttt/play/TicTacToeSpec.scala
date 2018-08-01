import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import ttt.entites._

class TicTacToeSpec() extends TestKit(ActorSystem("MySpec")) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "An Game actor" should {

    "be able to change and return the state of tic tac toe game" in {
      val echo = system.actorOf(Props[Game])
      echo ! PlayStep(1, 1)
      expectMsgAllClassOf(classOf[TicTacToeMap])
    }

    "be able to send Tic Tac Toe Map and a GameOver when a condition is met" in {
      val echo = system.actorOf(Props[Game])
      echo ! PlayStep(1, 1)
      echo ! PlayStep(2, 2)
      echo ! PlayStep(5, 1)
      echo ! PlayStep(3, 2)
      echo ! PlayStep(9, 1)
      expectMsgAllClassOf(classOf[TicTacToeMap], classOf[TicTacToeMap], classOf[TicTacToeMap], classOf[TicTacToeMap])
      expectMsg(GameOver)

    }

    "be able to send PlaceAlreadyFilled Message when a PlayStep is sent on a non empty step" in {
      val echo = system.actorOf(Props[Game])
      echo ! PlayStep(1, 1)
      echo ! PlayStep(1, 2)
      expectMsgAllClassOf(classOf[TicTacToeMap], classOf[PlaceAlreadyFilled])
    }
  }
}