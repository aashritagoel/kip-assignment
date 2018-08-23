import java.io.{ByteArrayInputStream, ObjectInputStream}
import java.util

import org.apache.kafka.common.serialization.Deserializer

class StudentDeserializer extends Deserializer[Student]{

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def deserialize(topic: String, data: Array[Byte]): Student = {
    val byteIn = new ByteArrayInputStream(data)
    val objIn = new ObjectInputStream(byteIn)
    val obj = objIn.readObject().asInstanceOf[Student]
    byteIn.close()
    objIn.close()
    obj
    /*val dataInString = data.map(_.toChar).mkString
    val student: Option[Student] = StudentExtractor.unapply(dataInString)
    student.getOrElse(Student(-1, ""))*/
  }

  override def close(): Unit = {}

}