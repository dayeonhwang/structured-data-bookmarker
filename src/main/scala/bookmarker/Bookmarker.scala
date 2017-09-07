package bookmarker

import org.scalajs.dom
import org.scalajs.dom.html
import org.scalajs.dom.html.{Html, Image}
import org.scalajs.jquery._

import scala.scalajs.js
import scala.scalajs.js.annotation._

@JSImport("jquery", JSImport.Namespace)
@js.native
object jquery extends JQueryStatic

@js.native
trait JQueryUI extends JQuery {

  def dialog(dict:js.Object): Unit = js.native
  def dialog(s: String): Unit = js.native
}

object JQueryUI {
  dialog
  implicit def toQueryUI(jquery: JQuery): JQueryUI = jquery.asInstanceOf[JQueryUI]
}

@JSImport("jquery-ui/ui/widgets/dialog", JSImport.Namespace)
@js.native
object dialog extends js.Object

@JSExportTopLevel("foobar")
object Bookmarker {

  def alert(text: String): Unit = {
    dom.window.alert(text)
  }

  def extractMetaProperty(target: String) : String = {
    val elem = dom.document.querySelector(s"""meta[property="$target"]""")
    elem match {
      case elem: dom.Element => elem.getAttribute("content").toString
      case _ => "element not found"
    }
  }

  @JSExport
  def main(): Unit = {
    import JQueryUI._

    val ogTitle = extractMetaProperty("og:title")
    val ogDescription = extractMetaProperty("og:description")
    val ogUrl = extractMetaProperty("og:url")
    val ogImg = extractMetaProperty("og:image")

    lazy val content =
      s"""
        |<div id="dialog-content" title="Meta properties">
        | <div>
        |   <h3 id="title">og:title</h3>
        |   <p>${ogTitle}</p>
        | </div>
        | <div>
        |   <h3 id="description">og:description</h3>
        |   <p>${ogDescription}</p>
        | </div>
        | <div>
        |   <h3 id="url">og:url</h3>
        |   <p>${ogUrl}</p>
        | </div>
        | <div>
        |   <h3 id="img">og:image</h3>
        |   <img src=${ogImg} style="width: 70%; height: 70%;">
        | </div>
        |</div>
      """.stripMargin

    lazy val node = jquery(content)

    lazy val dialogOptions = js.Dynamic.literal(
      "autoOpen" -> true,
      "buttons" -> js.Array(
        js.Dynamic.literal(
          "text" -> "Close",
          "click" -> { () =>
            node.dialog("close") })
      ),
      "closeOnEscape" -> true,
      "resizable" -> true,
      "draggable" -> true,
      "height" -> "auto",
      "minWidth" -> 550,
      "modal" -> true
    )

    node.dialog(dialogOptions)

  }

}