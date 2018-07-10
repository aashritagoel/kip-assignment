package dto

case class Book(
  id: Option[Int],
  name: String,
  publisher: String,
  rating: Int
)
