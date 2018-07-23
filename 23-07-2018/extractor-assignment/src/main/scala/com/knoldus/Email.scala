package com.knoldus

class Email {

  def regexEmail(str: String) :Option[(String, String)] =
  {
    val email = """^([A-Z0-9a-z._%+-]+)@([A-Z0-9a-z._%+-]+[.]+[A-Z0-9a-z._%+-]+)$""".r
    str match {
      case email(user, domain) => Some(user, domain)
      case _ => None
    }

  }
}
