package forms

import play.api.data.Form
import play.api.data.Forms.{email, list, mapping, number, optional, text}

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
      "firstName" ->  text.verifying("First Name must not be empty",_.nonEmpty),
      "middleName" -> optional(text),
      "lastName" -> optional(text),
      "username" ->  text.verifying("Username must not be empty",_.nonEmpty),
      "age" -> number.verifying("Age must be between 18 to 75",age => age >= 18 && age <=75),
      "email" -> email.verifying("password must not be empty",_.nonEmpty),
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


