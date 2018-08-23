
object StudentExtractor {
  val StudentRegex = "Student\\((\\d+),(.*)\\)".r
  def unapply(str: String): Option[Student] = str match {
    case StudentRegex(age, name) => Some(Student(age.toInt, name))
    case _ => None
  }
}
