import java.util.Properties

import org.apache.kafka.clients.producer._

object StudentDataProducer extends App {

  val  props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")

  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "StudentSerializer")


  val TOPIC="student"

  val producer = new KafkaProducer[String, Student](props)
  val student = Student(100, "AashritaGoel")
  val record = new ProducerRecord[String, Student](TOPIC, "key",student)
  producer.send(record)

  producer.close()
}
