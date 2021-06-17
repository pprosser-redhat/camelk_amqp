// camel-k: language=java

import org.apache.camel.builder.RouteBuilder;

public class WriteKafkaTopic extends RouteBuilder {
  @Override
  public void configure() throws Exception {

      // Write your routes here, for example:
      from("timer:java?period=1000")
        .routeId("sendToKafka")
        .setBody()
          .simple("Hello Camel K from ${routeId} at ${date:now:dd-MM-yyyy HH:mm:ss}")
        .log("Send Message ${body}")
        .to("kafka:test-topic?brokers=my-demo-cluster-kafka-bootstrap:9092");

  }
}
