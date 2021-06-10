# ReadWriteAMQP

## create the application.properties

```
quarkus.qpid-jms.url=mybroker-amqp-0-svc.broker.cluster.local:9092
```

## Deploy application.properties as config map to OpenShift 

````
oc create configmap amqptest --from-file=application.properties
````

## Deploy the Camel K integration

The Integration has 2 routes in it.

* On a timer, the Integration will send a message to AMQ Broker using AMQP
* The Integration will receive the message sent to the messahe broker

````
kamel run ReadWriteAMQP.java
````