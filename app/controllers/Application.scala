package controllers

import json.JSON
import play.api.Logger
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, AnyContent, Controller}
import play.modules.reactivemongo.MongoController

import scala.concurrent.Future

object Application extends Controller with MongoController with JSON {

  /**
   * Root
   * Returns all api links
   * @return Action[AnyContent]
   */
  def root: Action[AnyContent] = Action.async {
    val response: JsValue =
      Json.obj("invitation_url" -> "/invitation",
        "documentation_url" -> "https://github.com/KasonChan/evojam/blob/master/README.md")
    Logger.info(response.toString)
    Future.successful(Ok(prettify(response)).as("application/json; charset=utf-8"))
  }

}