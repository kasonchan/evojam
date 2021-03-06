import json.JSON
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.Results._
import play.api.mvc.{RequestHeader, Result}
import play.api.{Application, GlobalSettings, Logger}

import scala.concurrent.Future

/**
 * Created by ka-son on 9/25/15.
 */
object Global extends GlobalSettings with JSON {

  override def onStart(app: Application) {
    Logger.info("Application has started")
  }

  override def onStop(app: Application) {
    Logger.info("Application shutdown...")
  }

  /**
   * On handler not found
   * @param request RequestHeader
   * @return Future[Result]
   */
  override def onHandlerNotFound(request: RequestHeader): Future[Result] = {
    val response: JsValue = Json.obj("messages" -> Json.arr("Not found"))
    Logger.info(response.toString())
    Future.successful(NotFound(prettify(response)).as("application/json; charset=utf-8"))
  }

  /**
   * On bad request
   * @param request RequestHeader
   * @param error String
   * @return Future[Result]
   */
  override def onBadRequest(request: RequestHeader, error: String): Future[Result] = {
    val response: JsValue = Json.obj("messages" -> Json.arr((error)))
    Logger.info(response.toString())
    Future.successful(BadRequest(prettify(response)).as("application/json; charset=utf-8"))
  }

  /**
   * On error
   * @param request RequestHeader
   * @param ex Throwable
   * @return Future[Result]
   */
  override def onError(request: RequestHeader, ex: Throwable): Future[Result] = {
    val response: JsValue = Json.obj("messages" -> Json.arr(ex.getMessage.toString()))
    Logger.error(ex.getMessage.toString())
    Future.successful(InternalServerError(prettify(response)).as("application/json; charset=utf-8"))
  }

}
