To monitor Broker with Prometheus, switch on user workload monitoring and deploy the service monitor
To setup grafana....

1. create a service account - oc apply -f serviceaccount.yaml
2. create cluster role binding - oc apply -f clusterrolebinding.yaml
3. need to get serviceaccount token - oc serviceaccounts get-token grafana-serviceaccount -n brokerdemo
4. create a datasource that can access Thanos querier using the token from about.... paste the token above into the file datasources.yaml
5. create a configmap from the datasource.yaml - oc create configmap grafana-config --from-file=datasource.yaml -n brokerdemo
6. Deploy grafana application - oc apply -f grafana_application.yaml -n brokerdemo
7. expose service as a route - oc expose service grafana
8. Phils AMQ Broker-{random numbers}.json is a very simple grafana dashboard
With broker_everything deployed.

Go to one of the terminal windows for one of the running pods and send some messages

Test message migration

oc rsh phils-broker-ss-0
amq-broker/bin/artemis producer --url tcp://phils-broker-any-0-svc:61616 --user admin --password admin --destination test --message-count 5000
amq-broker/bin/artemis producer --url tcp://phils-broker-any-1-svc:61616 --user admin --password admin --destination test --message-count 5000

amq-broker/bin/artemis consumer --url tcp://phils-broker-any-0-svc:61616 --user admin --password admin --destination test::test --message-count 10000

Scale the cluster down to 1 broker
Check number of messages in Broker Console
