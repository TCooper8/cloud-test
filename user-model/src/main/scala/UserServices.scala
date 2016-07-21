package cloud.user

import codecraft.codegen.CmdGroupConsumer

trait IUserService extends CmdGroupConsumer {
  def get(cmd: GetUser): GetUserReply
  def post(cmd: AddUser): AddUserReply
  val methodRegistry = Map[String, Any => Any](
    "cmd.user.get" -> {
      any => get(any.asInstanceOf[GetUser])
    },
    "cmd.user.post" -> {
      any => post(any.asInstanceOf[AddUser])
    }
  )
}