package com.knoldus

import java.util.Properties

import org.apache.kafka.common.serialization._
import org.apache.kafka.streams._
import org.apache.kafka.streams.kstream.{KStream, Produced}
import serde._
import org.apache.kafka.streams.KeyValue
import org.apache.log4j.Logger
object MainApplication {
  def main(args: Array[String]): Unit = {
    val log: Logger = Logger.getLogger(this.getClass)
    val config = {
      val properties = new Properties()
      properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "stream-application")
      properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
      properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass)
      properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, classOf[EmployeeSerde])
      properties
    }

    val personSerializer = new CustomSerializer[Person]
    val personDeserializer = new CustomDeserializer[Person]
    val personSerde= Serdes.serdeFrom(personSerializer, personDeserializer)

    val builder = new StreamsBuilder()
    val sourceStream: KStream[String, Employee] = builder.stream("test-sqlite-jdbc-v1-employee")

    val personStream: KStream[String, Person] = sourceStream.map((key, employee) => {
      val prefix = employee.gender.toLowerCase match {
        case "male" => "Mr."
        case "female" => "Ms."
        case _ => ""
      }
      val person = Person(employee.id, s"$prefix ${employee.firstname} ${employee.lastname}")
      KeyValue.pair(key, person)
      })
    personStream.to( "person-topic", Produced.`with`(Serdes.String(), personSerde))

    val topology = builder.build
    sourceStream.print()
    val streams = new KafkaStreams(topology, config)
    streams.start()
  }
}