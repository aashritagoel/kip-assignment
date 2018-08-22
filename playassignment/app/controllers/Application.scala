package controllers

import forms._
import javax.inject.Inject
import models.CacheRepo
import play.api.mvc._
import scala.concurrent.duration._


import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

class Application @Inject()(controllerComponent: ControllerComponents,
                            userForm: UserForm,
                            cacheRepo: CacheRepo,
                            loginForm: LoginForm,
                            resetForm: ResetForm) extends AbstractController(controllerComponent) {

  def index: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok(views.html.welcome()))
  }

  def signUp: Action[AnyContent] = Action.async { implicit request =>
    val session = request.session.get("login").getOrElse("")
    if (session == "yes") {
      Future.successful(NotFound("User is already signed In !"))
    } else {
      Future.successful(Ok(views.html.signup(userForm.userForm)))
    }
  }

  def resetView: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok(views.html.reset(resetForm.resetForm)))
  }

  def addAssignment: Action[AnyContent] = Action.async { implicit request =>
    val session = request.session.get("login").getOrElse("")
    if (session == "yes") {
      val sessionUser = request.session.get("user").getOrElse("")

      val user = Await.result(cacheRepo.get(sessionUser).map { optionalUser =>
        optionalUser.fold {
          UserRepo(0, "first", Some("middle"), Some("last"), "default", 18, "default@example.com", "hello123", "Male")
        } { user => user
        }
      }, 10.seconds)
      if (user.userName == "admin") {
        Future.successful(Ok(views.html.addassignment(user)))
      }
      else {
        Future.successful(NotFound("Normal user can't access this page!"))
      }
    }
    else {
      Future.successful(NotFound("User is not logged in!"))
    }
  }

  def viewUsers: Action[AnyContent] = Action.async { implicit request =>
    val session = request.session.get("login").getOrElse("")
    if (session == "yes") {
      val sessionUser = request.session.get("user").getOrElse("")

      val user = Await.result(cacheRepo.get(sessionUser).map { optionalUser =>
        optionalUser.fold {
          UserRepo(0, "first", Some("middle"), Some("last"), "default", 18, "default@example.com", "hello123", "Male")
        } { user => user
        }
      }, 10.seconds)
      if (user.userName == "admin") {
        cacheRepo.getAllUsers.map { data =>
          Ok(views.html.viewusers(data, user))
        }
      }
      else {
        Future.successful(NotFound("Normal user can't access this page!"))
      }
    }
    else {
      Future.successful(NotFound("User is not logged in!"))
    }

  }

  def profile: Action[AnyContent] = Action.async { implicit request =>
    val session = request.session.get("login").getOrElse("")
    if (session == "yes") {
      val sessionUser = request.session.get("user").getOrElse("")

      val user = Await.result(cacheRepo.get(sessionUser).map { optionalUser =>
        optionalUser.fold {
          UserRepo(0, "first", Some("middle"), Some("last"), "default", 18, "default@example.com", "hello123", "Male")
        } { user => user
        }
      }, 10.seconds)
      Future.successful(Ok(views.html.profileview(userForm.userForm, user)))
    }
    else {
      Future.successful(NotFound("User is not logged in!"))
    }
  }

  def register: Action[AnyContent] = Action.async { implicit request =>
    userForm.userForm.bindFromRequest.fold(
      formWithError => {
        Future.successful(Ok(views.html.signup(formWithError)))
      },
      data => {
        cacheRepo.get(data.email).flatMap { optionalUser =>
          optionalUser.fold {
            val dbPayload = UserRepo(0, data.firstName, data.middleName, data.lastName, data.userName, data.age, data.email, data.password.password, data.gender)
            cacheRepo.store(dbPayload).map { _ =>
              Redirect(routes.Application.success()).flashing(
                "success" -> "User has been registered.")
            }
          } { _ =>
            Future.successful(Ok("User already exists."))
          }
        }
      }
    )
  }

  def success: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok(views.html.success()))
  }

  def getUser(email: String): Action[AnyContent] = Action.async { implicit request =>
    cacheRepo.get(email).map { optionalUser =>
      optionalUser.fold {
        NotFound("User doesn't exist!")
      } { user =>
        Ok(s"${user.email}, ${user.gender}, ${user.age}")
      }
    }
  }

  def login: Action[AnyContent] = Action.async { implicit request =>
    val session = request.session.get("login").getOrElse("")
    if (session == "yes") {
      Future.successful(NotFound("User is already signed In !"))
    } else {
      Future.successful(Ok(views.html.login(loginForm.loginForm)))
    }
  }

  def logout: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Redirect(routes.Application.index()).withNewSession)
  }

  def authenticate: Action[AnyContent] = Action.async { implicit request =>
    loginForm.loginForm.bindFromRequest.fold(
      formWithError => {
        Future.successful(Ok(views.html.login(formWithError)))
      },
      data => {
        cacheRepo.get(data._1).map { optionalUser =>
          optionalUser.fold {
            NotFound("User doesn't exist!")
          } { user => {
            if (user.password == data._2) {
              //Ok(views.html.profileview(userForm.userForm, user))
              Redirect(routes.Application.profile()).withSession("login" -> "yes", "user" -> user.email)
            }
            else {
              BadRequest(s"Username or Password is incorrect!")
            }
          }
          }
        }
      }
    )
  }

  def update(email: String): Action[AnyContent] = Action.async { implicit request =>
    userForm.userForm.bindFromRequest.fold(
      formWithError => {
        println(formWithError)
        cacheRepo.get(email).map { optionalUser =>
          optionalUser.fold {
            BadRequest("Not found!")
          } { user =>
            Ok(views.html.profileview(formWithError, user))
          }
        }
      },
      data => {
        cacheRepo.get(email).flatMap { optionalUser =>
          optionalUser.fold {
            Future.successful(NotFound("User doesn't exist!"))
          } { user =>
            val dbPayload = UserRepo(user.id,
                                      data.firstName,
                                      data.middleName,
                                      data.lastName,
                                      data.userName,
                                      data.age,
                                      data.email,
                                      data.password.password,
                                      data.gender
                                    )
            cacheRepo.update(email, dbPayload).map { _ =>
              Redirect(routes.Application.success()).flashing(
                "success" -> "User has been updated successfully.")
            }
          }
        }
      }
    )
  }

  def resetPassword: Action[AnyContent] = Action.async { implicit request =>
    resetForm.resetForm.bindFromRequest.fold(
      formWithError => {
        Future.successful(Ok(views.html.reset(formWithError)))
      },
      resetParameters => {
        cacheRepo.get(resetParameters.email).flatMap { optionalUser =>
          optionalUser.fold {
            Future.successful(NotFound("User doesn't exist!"))
          } { user =>
            cacheRepo.updatePassword(resetParameters.email, resetParameters.password.password).map { _ =>
              Redirect(routes.Application.success()).flashing(
                "success" -> "Password has been reset.")
            }
          }
        }
      }
    )
  }

}
