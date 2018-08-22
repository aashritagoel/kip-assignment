package controllers

import forms._
import javax.inject.Inject
import models.AssignmentViewRepo
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AssignmentHandler @Inject()(controllerComponent: ControllerComponents,
                                  assignmentForm: AssignmentForm,
                                  assignmentViewRepo: AssignmentViewRepo
                                 ) extends AbstractController(controllerComponent) {

  def recordAssignment: Action[AnyContent] = Action.async { implicit request =>
    assignmentForm.assignmentForm.bindFromRequest.fold(
      formWithError => {
        Future.successful(BadRequest(s"${formWithError.errors}"))
      },
      data => {
        assignmentViewRepo.get(data.title).flatMap { optionalAssignment =>
          optionalAssignment.fold {
            val dbPayload = AssignmentRepo(0, data.title, data.description)
            assignmentViewRepo.store(dbPayload).map { _ =>
              Ok(views.html.addassignment())
            }
          } { _ =>
            Future.successful(Ok("Assignment already exists. "))
          }
        }
      }
    )
  }

  def viewAssignment: Action[AnyContent] = Action.async { implicit request =>
    assignmentViewRepo.getAllAssignment.map { data =>
      Ok(views.html.assignment(data))
    }
  }

  def deleteAssignment(id: Long): Action[AnyContent] = Action.async { implicit request =>
    assignmentViewRepo.delete(id)
    Future.successful(Redirect(routes.AssignmentHandler.viewAssignment()))
  }

}
