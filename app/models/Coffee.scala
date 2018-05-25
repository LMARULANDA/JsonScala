package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Coffee(name: String, price: Double, farm: Option[String])

object Coffee {

  /**
    * {
    * "nombre" : "PepitoPerez",
    * "precio": 123456,
    * "granja": "Alguna"
    * }
    */
  implicit val readsCoffee: Reads[Coffee] = (
    (JsPath \ "nombre").read[String] and
      (JsPath \ "precio").read[Double] and
      (JsPath \ "granja").readNullable[String]
    ).apply(Coffee.apply _)

  implicit val writesCoffee: Writes[Coffee] = (
    (JsPath \ "nombre").write[String] and
      (JsPath \ "precio").write[Double] and
      (JsPath \ "granja").writeNullable[String]
    ).apply(unlift(Coffee.unapply))

  implicit val formatCoffee: Format[Coffee] = (
    (JsPath \ "nombre").format[String] and
      (JsPath \ "precio").format[Double] and
      (JsPath \ "granja").formatNullable[String]
    ).apply(Coffee.apply _, Coffee.unapply(_).get)
  //implicit val cofffeFormat = Json.format[Coffee]
}
