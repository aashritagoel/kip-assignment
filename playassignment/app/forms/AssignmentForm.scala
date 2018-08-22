package forms
import play.api.data.Form
import play.api.data.Forms.{mapping, text}

case class Assignment(title: String, description: String)
case class AssignmentRepo(id: Long, title: String, description: String)


class AssignmentForm {
  val assignmentForm = Form(
    mapping(
      "title" -> text,
      "description" -> text
    )(Assignment.apply)(Assignment.unapply)
  )
}


