// camel-k: language=java

import org.apache.camel.builder.RouteBuilder;

public class ReadKafkaTopic extends RouteBuilder {
  @Override
  public void configure() throws Exception {

      // Write your routes here, for example:
      from("kafka:test-topic?brokers=my-demo-cluster-kafka-bootstrap:9092&groupId=reader&autoCommitEnable=true&autoOffsetReset=earliest")
        .routeId("readKafkaMessage")
        .setBody().simple("Received: ${body}")
        .to("log:info");

  }
}
