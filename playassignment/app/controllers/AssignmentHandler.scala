package controllers

import forms._
import javax.inject.Inject
import models.{AssignmentViewRepo, CacheRepo}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._


class AssignmentHandler @Inject()(controllerComponent: ControllerComponents,
                                  assignmentForm: AssignmentForm,
                                  assignmentViewRepo: AssignmentViewRepo,
                                  cacheRepo: CacheRepo
                                 ) extends AbstractController(controllerComponent) {

  def recordAssignment: Action[AnyContent] = Action.async { implicit request =>
    val session = request.session.get("login").getOrElse("")
    if(session == "yes" ) {
      val sessionUser = request.session.get("user").getOrElse("")

      val user = Await.result(cacheRepo.get(sessionUser).map { optionalUser =>
        optionalUser.fold {
          UserRepo(0, "first", Some("middle"), Some("last"), "default", 18, "default@example.com", "hello123", "Male")
        } { user => user
        }
      }, 10.seconds)
      assignmentForm.assignmentForm.bindFromRequest.fold(
        formWithError => {
          Future.successful(BadRequest(s"${formWithError.errors}"))
        },
        data => {
          assignmentViewRepo.get(data.title).flatMap { optionalAssignment =>
            optionalAssignment.fold {
              val dbPayload = AssignmentRepo(0, data.title, data.description)
              assignmentViewRepo.store(dbPayload).map { _ =>
                Ok(views.html.addassignment(user))
              }
            } { _ =>
              Future.successful(Ok("Assignment already exists. "))
            }
          }
        }
      )
    } else {
      Future.successful(NotFound("User is not logged in!"))
    }
  }

  def viewAssignment: Action[AnyContent] = Action.async { implicit request =>
    val session = request.session.get("login").getOrElse("")
    if(session == "yes" ){
      val sessionUser = request.session.get("user").getOrElse("")

      val user = Await.result(cacheRepo.get(sessionUser).map { optionalUser =>
        optionalUser.fold {
          UserRepo(0, "first", Some("middle"), Some("last"), "default", 18, "default@example.com", "hello123", "Male" )
        } { user => user
        }
      }, 10.seconds)

        assignmentViewRepo.getAllAssignment.map { data =>
          Ok(views.html.assignment(data, user))
        }
    }
    else{
      Future.successful(NotFound("User is not logged in!"))
    }

  }

  def deleteAssignment(id: Long): Action[AnyContent] = Action.async { implicit request =>
    assignmentViewRepo.delete(id)
    Future.successful(Redirect(routes.AssignmentHandler.viewAssignment()))
  }

}
