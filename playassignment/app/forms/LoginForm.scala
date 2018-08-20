package forms

import controllers.routes
import play.api.data.Form
import play.api.data.Forms.{list, mapping, optional, text, tuple}

class LoginForm {
  val loginForm = Form(
    tuple("email" -> text, "password" -> text)

  )

}

class ResetForm {
  val resetForm = Form(
    tuple("email" -> text, "password" -> text)

  )

}
