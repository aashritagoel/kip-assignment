package com.knoldus


object URL{

  def apply(protocol:String,domain:String,path:String, params:Map[String,String]):String = {

    val paramsString = params.map(_.productIterator.mkString("=")).mkString("&")
    protocol + "://" + domain + path + "?" + paramsString

  }
  def parseUrlParameters(url: String) :Map[String, String]= url.split("&").map(v => {
    val m =  v.split("=", 2)
    m(0) -> m(1)
  }).toMap


  def check(s: String):Boolean ={
  val parts = s.split("/", 2)
  if(parts.length ==2) true
  else false
}

  def unapply(url:String):Option[(String , String, String,Map[String,String])] = {

    val newUrl = url.replace("://", " ").replace("?", " ")
    val parts = newUrl.split(" ")
    parts match {
      case Array(protocol, domainPath) => if (check(domainPath)) Some(protocol, domainPath.split("/", 2)(0),  "/"+domainPath.split("/", 2)(1), Map.empty) else Some(protocol, domainPath, "", Map.empty)
      case Array(protocol, domainPath, params) => if (check(domainPath)) Some(protocol, domainPath.split("/", 2)(0),  "/"+domainPath.split("/", 2)(1), parseUrlParameters(params)) else Some(protocol, domainPath, "", parseUrlParameters(params))
      case _ => None
    }
  }
  //  def unapply(url:String):Option[(String , String, String,Map[String,String])] = {
  //
  //    val parts = url.split("://")
  //    if (parts.length == 2) {
  //      val str = parts(1).split("/", 2)
  //      if (str.length == 2) {
  //        val params = str(1).split('?')
  //        if (params.length == 2) {
  //          val m = parseUrlParameters(params(1))
  //          Option(parts(0), str(0), "/" + params(0), m)
  //        }
  //        else Option(parts(0), str(0), "/"+params(0), Map.empty)
  //      }
  //      else Option(parts(0), str(0), "" , Map.empty)
  //    }
  //    else None
  //
  //  }
}

class URL {

  val url = URL("http", "aws.amazon.com", "/console/home", Map("state" -> "hash", "isauthcode" -> "true", "code" -> "112"))

  def extractUrl(str: String): String = {
    str match {
      case URL(protocol, domain, path, params) => s"protocol= $protocol \n domain=$domain \n path= $path \n params= ${params.toString}"
      case _ => s"Not valid"
    }
  }
}

//println(extractUrl(url))

