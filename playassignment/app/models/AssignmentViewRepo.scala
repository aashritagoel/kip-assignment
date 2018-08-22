package models

import forms.AssignmentRepo
import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape

import scala.concurrent.Future


class AssignmentViewRepo @Inject()(override protected val dbConfigProvider: DatabaseConfigProvider) extends Implementation with AssignmentTable

trait Implementation {
  self: AssignmentTable =>

  import profile.api._

  def store(assignmentRepo: AssignmentRepo): Future[Long] = {
    db.run {
      assignmentQuery returning assignmentQuery.map(_.id) += assignmentRepo
    }
  }

  def getAllAssignment: Future[Seq[AssignmentRepo]] = {
    db.run {
      assignmentQuery.result
    }
  }

  def get(title: String): Future[Option[AssignmentRepo]] = {
    db.run {
      assignmentQuery.filter(_.title.toLowerCase === title.toLowerCase).result.headOption
    }
  }

  def delete(id: Long): Future[Int] = {
    val requiredRow = assignmentQuery.filter(_.id === id)
    db.run {
      requiredRow.delete
    }
  }
}

trait AssignmentTable extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  val assignmentQuery: TableQuery[Assignment] = TableQuery[Assignment]

  private[models] class Assignment(tag: Tag) extends Table[AssignmentRepo](tag, "assignments") {
    //noinspection ScalaStyle

    def id: Rep[Long] = column[Long]("Id", O.PrimaryKey, O.AutoInc)

    def title: Rep[String] = column[String]("Title")

    def description: Rep[String] = column[String]("Description")


    def * : ProvenShape[AssignmentRepo] = (id, title, description) <> (AssignmentRepo.tupled, AssignmentRepo.unapply)

  }

}