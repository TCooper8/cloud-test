package vanner.user

import akka.actor.ActorSystem
import codecraft.platform._
import codecraft.platform.amqp._
import scala.concurrent.duration._
import cloud.user._
import scala.util.{Try, Success, Failure}

case class UserService(cloud: ICloud) extends IUserService {
  def post(cmd: AddUser): AddUserReply = {
    AddUserReply(None, 404, Some("Not implemented"))
  }

  def onError(exn: Throwable) {
    println(s"$exn")
  }
}

object Main {
  val routingInfo = RoutingInfo(
    List(
      UserRoutingGroup.cmdInfo
    ).flatten.map(reg => (reg.key, reg)).toMap,
    List(
      UserRoutingInfo.eventInfo
    ).flatten map { eventInfo => (eventInfo.key -> eventInfo) } toMap,
    Map(
      UserRoutingGroup.groupRouting.queueName -> UserRoutingGroup.groupRouting
    )
  )

  def main(argv: Array[String]) {
    val system = ActorSystem("service")
    import system.dispatcher

    val cloud = amqp.AmqpCloud(
      system,
      "amqp://cloud-test:test@192.168.99.100:5672/cloud-test",
      routingInfo
    )

    val service = UserService(cloud)

    cloud subscribeCmd ("cmd.user", service, 5 seconds) onComplete {
      case Failure(e) => throw e
      case Success(_) =>
        println("Subscribed")
    }
  }
}
