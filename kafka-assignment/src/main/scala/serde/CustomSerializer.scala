package serde

import java.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.apache.kafka.common.serialization.Serializer

import scala.reflect.ClassTag

class CustomSerializer[T >: Null : ClassTag] extends Serializer[T] {

  val mapper = new ObjectMapper
  mapper.registerModule(DefaultScalaModule)

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def serialize(topic: String, obj: T): Array[Byte] = {
    mapper.writeValueAsBytes(obj)
  }

  override def close(): Unit = {}
}
