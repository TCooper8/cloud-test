package cloud.user

import play.api.libs.json._
import cloud.user._
import codecraft.codegen._
import scala.util.Try

object UserFormatters {
  implicit val AddUserFormat = Json.format[AddUser]
  implicit val AddUserReplyFormat = Json.format[AddUserReply]
}

object UserRoutingGroup {
  import UserFormatters._
  val groupRouting = GroupRouting(
    "cmd.user",
    "cmd.user.*",
    "cmd"
  )
  lazy val cmdInfo = List(
    codecraft.codegen.CmdRegistry(
      "cmd.user.post",
      (any: Any) => Try {
        val value = any.asInstanceOf[AddUser]
        Json.toJson(value).toString.getBytes
      },
      (any: Any) => Try {
        val value = any.asInstanceOf[AddUserReply]
        Json.toJson(value).toString.getBytes
      },
      (bytes: Array[Byte]) => Try {
        val json = Json.parse(new String(bytes))
        Json.fromJson[AddUser](json) match {
          case JsError(errors) => throw new Exception(errors mkString)
          case JsSuccess(any, _) => any.asInstanceOf[AddUser]
        }
      },
      (bytes: Array[Byte]) => Try {
        val json = Json.parse(new String(bytes))
        Json.fromJson[AddUserReply](json) match {
          case JsError(errors) => throw new Exception(errors mkString)
          case JsSuccess(any, _) => any.asInstanceOf[AddUserReply]
        }
      },
      groupRouting
    )
  )
}

object UserRoutingInfo {
  import UserFormatters._
  val eventInfo: List[EventRegistry] = List(

  )
}