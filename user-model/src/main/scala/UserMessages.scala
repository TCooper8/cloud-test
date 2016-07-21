package cloud.user



final case class AddUser(
  emailAddress: String
)

final case class AddUserReply(
  id: Option[String],
  code: Int,
  status: Option[String]
)