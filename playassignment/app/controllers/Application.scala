package controllers

import akka.Done
import forms.{LoginForm, ResetForm, User, UserForm}
import javax.inject.Inject
import models.CacheRepo
import play.api.mvc._

import scala.concurrent.Future
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
    Future.successful(Ok(views.html.signup()))
  }

  def register: Action[AnyContent] = Action.async { implicit request =>
    userForm.userForm.bindFromRequest.fold(
      formWithError => {
        Future.successful(BadRequest(s"${formWithError.errors}"))
      },
      data => {
        cacheRepo.get(data.email).flatMap { optionalUser =>
          optionalUser.fold {
            cacheRepo.store(data).map {
              case Done => Redirect(routes.Application.success())
              case _ => InternalServerError("Something went wrong!")
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
        Ok(s"${user.email}, ${user.hobbies}, ${user.gender}, ${user.age}")
      }
    }
  }

  def login: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok(views.html.login()))
  }

  def profile: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok(views.html.profile()))
  }
  def assignment: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok(views.html.assignment()))
  }


  def authenticate: Action[AnyContent] = Action.async { implicit request =>
    loginForm.loginForm.bindFromRequest.fold(
      formWithError => {
        Future.successful(BadRequest(s"${formWithError.errors}"))
      },
      user => {
        cacheRepo.get(user._1).map { optionalUser =>
          optionalUser.fold {
            NotFound("User doesn't exist!")
          } { u => {
            if (u.password == user._2) {
              Redirect(routes.Application.profile)
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

}
