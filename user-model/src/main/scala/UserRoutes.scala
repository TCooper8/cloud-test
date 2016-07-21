package cloud.user

import play.api.libs.json._
import cloud.user._
import codecraft.codegen._
import scala.util.Try

object UserFormatters {
  implicit val UserFormat = Json.format[User]
  implicit val AddUserFormat = Json.format[AddUser]
  implicit val AddUserReplyFormat = Json.format[AddUserReply]
  implicit val GetUserFormat = Json.format[GetUser]
  implicit val GetUserReplyFormat = Json.format[GetUserReply]
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
      "cmd.user.get",
      (any: Any) => Try {
        val value = any.asInstanceOf[GetUser]
        Json.toJson(value).toString.getBytes
      },
      (any: Any) => Try {
        val value = any.asInstanceOf[GetUserReply]
        Json.toJson(value).toString.getBytes
      },
      (bytes: Array[Byte]) => Try {
        val json = Json.parse(new String(bytes))
        Json.fromJson[GetUser](json) match {
          case JsError(errors) => throw new Exception(errors mkString)
          case JsSuccess(any, _) => any.asInstanceOf[GetUser]
        }
      },
      (bytes: Array[Byte]) => Try {
        val json = Json.parse(new String(bytes))
        Json.fromJson[GetUserReply](json) match {
          case JsError(errors) => throw new Exception(errors mkString)
          case JsSuccess(any, _) => any.asInstanceOf[GetUserReply]
        }
      },
      groupRouting
    ),
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