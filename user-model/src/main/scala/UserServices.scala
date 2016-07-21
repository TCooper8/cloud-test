package cloud.user

import codecraft.codegen.CmdGroupConsumer

trait IUserService extends CmdGroupConsumer {
  def post(cmd: AddUser): AddUserReply
  val methodRegistry = Map[String, Any => Any](
    "cmd.user.post" -> {
      any => post(any.asInstanceOf[AddUser])
    }
  )
}