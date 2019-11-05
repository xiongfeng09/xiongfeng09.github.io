//package json
//import net.liftweb.json.Xml._
//import net.liftweb.json._
//
///**
// * Created by xiongfeng on 15/6/14.
// */
//object xmlToJson {
//  val xml = <xml>
//    <ToUserName>
//      <![CDATA[toUser]]>
//    </ToUserName>
//    <FromUserName>
//      <![CDATA[fromUser]]>
//    </FromUserName>
//    <CreateTime>1348831860</CreateTime>
//    <MsgType>
//      <![CDATA[text]]>
//    </MsgType>
//    <Content>
//      <![CDATA[this is a test]]>
//    </Content>
//    <MsgId>1234567890123456</MsgId>
//  </xml>
//
//
//  def convertFirstToLower(s: String) = s.headOption.map(_.toLower + s.drop(1)).getOrElse(s)
//  def run = {
//    val jValue = toJson(xml)
//    jValue.transform {
//      case JField(key, name) => JField(convertFirstToLower(key), name)
//    }
//  }
//}
//
//object test extends App {
//  println(xmlToJson.run)
//  println("abt".capitalize)
//
//
//  implicit val formats = net.liftweb.json.DefaultFormats
//  val f = Extraction.decompose(new A("xx", "yy"))
//  println("f" + f)
//}
//
//class A(val name: String, val age: String)
