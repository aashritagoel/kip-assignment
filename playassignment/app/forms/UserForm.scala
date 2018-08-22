package forms

import play.api.data.Form
import play.api.data.Forms.{email, mapping, number, optional, text, list}

case class Password(password: String,confirmPassword:String)
case class User(firstName: String,
                middleName: Option[String],
                lastName: Option[String],
                userName: String,
                age: Int,
                email: String,
                password: Password,
                gender: String,
                hobbies: List[String]
               )

case class UserRepo(id: Long,
                    firstName: String,
                    middleName: Option[String],
                    lastName: Option[String],
                    userName: String,
                    age: Int,
                    email: String,
                    password: String,
                    gender: String
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
      "passwordGroup"-> mapping(
        "password"-> text.verifying("password must not be empty",_.nonEmpty),
        "confirmPassword" -> text.verifying("confirm password must not be empty",_.nonEmpty)
      )(Password.apply)(Password.unapply)
        .verifying("password and confirm password must match",passwordGroup=>
          passwordGroup.password == passwordGroup.confirmPassword),
      "gender" -> text.verifying("please select gender", _.nonEmpty),
      "hobbies" -> list(text)
    )(User.apply)(User.unapply)
  )
}


