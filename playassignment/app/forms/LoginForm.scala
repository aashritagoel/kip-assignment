package forms

import play.api.data.Form
import play.api.data.Forms.{mapping, text, tuple}

class LoginForm {
  val loginForm = Form(
    tuple("email" -> text, "password" -> text)

  )

}

case class Reset(email: String, password: Password)

class ResetForm {
  val resetForm = Form(
    mapping(
      "email" -> text,
      "passwordGroup"-> mapping(
        "password"-> text.verifying("password must not be empty",_.nonEmpty),
        "confirmPassword" -> text.verifying("confirm password must not be empty",_.nonEmpty)
      )(Password.apply)(Password.unapply)
        .verifying("password and confirm password must match",passwordGroup=>
          passwordGroup.password == passwordGroup.confirmPassword)
    )(Reset.apply)(Reset.unapply)
  )

}
