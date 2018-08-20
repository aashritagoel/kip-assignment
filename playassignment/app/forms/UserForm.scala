package forms

import play.api.data.Form
import play.api.data.Forms.{email, mapping, number, optional, text, list, nonEmptyText}

case class User(firstName: String,
                middleName: Option[String],
                lastName: Option[String],
                userName: String,
                age: Int,
                email: String,
                password: String,
                confirmPassword: String,
                gender: String,
                hobbies: List[String]
               )

class UserForm {
  val userForm = Form(
    mapping(
      "firstName" -> text,
      "middleName" -> optional(text),
      "lastName" -> optional(text),
      "username" -> text,
      "age" -> number,
      "email" -> email,
      "password" -> nonEmptyText,
      "confirmPassword" -> nonEmptyText,
      "gender" -> text.verifying("please select gender", _.nonEmpty),
      "hobbies" -> list(text)
    )(User.apply)(User.unapply)
  )
}
