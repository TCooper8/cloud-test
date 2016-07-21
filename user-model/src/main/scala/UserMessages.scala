package cloud.user



final case class User(
  emailAddress: String
)

final case class AddUser(
  emailAddress: String
)

final case class AddUserReply(
  id: Option[String],
  code: Int,
  status: Option[String]
)

final case class GetUser(
  id: String
)

final case class GetUserReply(
  user: Option[User],
  code: Int,
  status: Option[String]
)