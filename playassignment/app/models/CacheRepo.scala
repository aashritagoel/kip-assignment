package models

import forms.UserRepo
import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape

import scala.concurrent.Future


class CacheRepo @Inject()(override protected val dbConfigProvider: DatabaseConfigProvider) extends Impl with UserTable

trait Impl {
  self: UserTable =>

  import profile.api._

  def store(userRepo: UserRepo): Future[Long] = {
    db.run {
      userProfileQuery returning userProfileQuery.map(_.id) += userRepo
    }
  }

  def get(email: String): Future[Option[UserRepo]] = {
    db.run {
      userProfileQuery.filter(_.email.toLowerCase === email.toLowerCase).result.headOption
    }
  }

  def getAllUsers: Future[Seq[UserRepo]] = {
    db.run {
      userProfileQuery.result
    }
  }

  def update(email: String, userRepo: UserRepo): Future[Int] = {
    db.run {
      val q = for {user <- userProfileQuery if user.email === email} yield user
      q.update(userRepo)
    }
  }

  def updatePassword(email: String, password: String): Future[Int] = {
    db.run {
      val q = for {user <- userProfileQuery if user.email === email} yield user.password
      q.update(password)
    }
  }
}

trait UserTable extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  val userProfileQuery: TableQuery[UserProfile] = TableQuery[UserProfile]

  private[models] class UserProfile(tag: Tag) extends Table[UserRepo](tag, "userprofile") {
    //noinspection ScalaStyle

    def id: Rep[Long] = column[Long]("Id", O.PrimaryKey, O.AutoInc)

    def firstName: Rep[String] = column[String]("firstName")

    def middleName: Rep[Option[String]] = column[Option[String]]("middleName")

    def lastName: Rep[Option[String]] = column[Option[String]]("lastName")

    def userName: Rep[String] = column[String]("username")

    def age: Rep[Int] = column[Int]("Age")

    def email: Rep[String] = column[String]("Email")

    def password: Rep[String] = column[String]("Password")


    def gender: Rep[String] = column[String]("Gender")


    def * : ProvenShape[UserRepo] = (id, firstName, middleName, lastName, userName, age, email, password, gender) <> (UserRepo.tupled, UserRepo.unapply)

  }

}