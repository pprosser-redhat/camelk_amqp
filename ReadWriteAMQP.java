// camel-k: language=java configmap=amqptest

import org.apache.camel.builder.RouteBuilder;

public class ReadWriteAMQP extends RouteBuilder {
  @Override
  public void configure() throws Exception {

      // Send message to AMQ Broker every one second
      from("timer:java?period=1000")
        .routeId("send")
        .setBody()
          .simple("Hello Camel K from ${routeId} at ${date:now:dd-MM-yyyy HH:mm:ss}")
        .log("${body}")
        .to("amqp:queue:phil-test");

      // Receive messages from AMQ Broker
      from("amqp:queue:phil-test")
        .routeId("Receive")
      .log("Receieve Message: ${body}");
      }

}
