import java.io.{ByteArrayOutputStream, ObjectOutputStream}
import java.util

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Serializer

class StudentSerializer extends Serializer[Student] {

  val mapper = new ObjectMapper()
  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}


  override def serialize(topic: String, value: Student): Array[Byte] = {

      val byteOut = new ByteArrayOutputStream()
      val objOut = new ObjectOutputStream(byteOut)
      objOut.writeObject(value)
      objOut.close()
      byteOut.close()
      byteOut.toByteArray

  }

  override def close(): Unit = {}
}
