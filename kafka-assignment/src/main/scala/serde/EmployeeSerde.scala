package serde

import java.util

import com.knoldus.Employee
import org.apache.kafka.common.serialization.{Deserializer, Serde, Serializer}

class EmployeeSerde extends Serde[Employee] {

    private val employeeSerializer = new CustomSerializer[Employee]
    private val employeeDeserializer = new CustomDeserializer[Employee]

    def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {
      serializer.configure(configs, isKey)
      deserializer.configure(configs, isKey)
    }

    override def close(): Unit = {
      serializer.close()
      deserializer.close()
    }

    override def serializer: Serializer[Employee] = employeeSerializer

    override def deserializer: Deserializer[Employee] = employeeDeserializer
}
