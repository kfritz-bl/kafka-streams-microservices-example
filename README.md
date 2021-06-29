# Spring Kafka Streams using Spring Cloud Streams End to End example

## Kafka Setup in Docker
- See the following link to setup the Kafka environment in Docker.
  - https://docs.confluent.io/platform/current/quickstart/cos-docker-quickstart.html
    
### Step 1: Download and Start Confluent Platform Using Docker
1. Download or copy the contents of the Confluent Community all-in-one Docker Compose file, for example:
```shell
curl --silent --output docker-compose.yml \
https://raw.githubusercontent.com/confluentinc/cp-all-in-one/6.2.0-post/cp-all-in-one-community/docker-compose.yml
```
2. Start Confluent Platform specifying the -d option to run in detached mode:
```shell
docker-compose up -d
```
The above command starts Confluent Platform with separate containers for all Confluent Platform components. Your output should resemble the following:
```shell
Creating network "cp-all-in-one-community_default" with the default driver
Creating zookeeper ... done
Creating broker    ... done
Creating schema-registry ... done
Creating rest-proxy      ... done
Creating connect         ... done
Creating ksql-datagen    ... done
Creating ksqldb-server   ... done
Creating ksqldb-cli      ... done
```
3. Verify that the services are up and running:
```shell
docker-compose ps
```
You should see the following:
```shell
     Name                    Command               State                Ports
------------------------------------------------------------------------------------------
broker            /etc/confluent/docker/run        Up      0.0.0.0:29092->29092/tcp,
0.0.0.0:9092->9092/tcp
connect           /etc/confluent/docker/run        Up      0.0.0.0:8083->8083/tcp,
9092/tcp
ksqldb-cli        ksql http://localhost:8088       Up
ksql-datagen      bash -c echo Waiting for K ...   Up
ksqldb-server     /etc/confluent/docker/run        Up      0.0.0.0:8088->8088/tcp
rest-proxy        /etc/confluent/docker/run        Up      0.0.0.0:8082->8082/tcp
schema-registry   /etc/confluent/docker/run        Up      0.0.0.0:8081->8081/tcp
zookeeper         /etc/confluent/docker/run        Up      0.0.0.0:2181->2181/tcp,
2888/tcp, 3888/tcp
```
If the state is not Up, rerun the docker-compose up -d command.

### Step 2: Create Kafka Topics
In this step, you create Kafka topics using the Kafka CLI.

1. Create a topic named web-domains:
```shell
docker-compose exec broker kafka-topics \
--create \
--bootstrap-server localhost:9092 \
--replication-factor 1 \
--partitions 1 \
--topic web-domains
```

2. Create a topic named active.web-domains:
```shell
docker-compose exec broker kafka-topics \
--create \
--bootstrap-server localhost:9092 \
--replication-factor 1 \
--partitions 1 \
--topic active.web-domains
```

## Endpoint
- http://localhost:8080/domain/lookup/facebook - to pull all facebook related web domain names
- http://localhost:8080/domain - to view a JSON object containing all related web domain names

## Microservices
- 'domain-crawler' - uses Spring Kafka
- 'domain-processor' - uses Spring Cloud Stream with Kafka Streams binder
- 'domain-service' - uses Spring Cloud Stream with Kafka Streams binder

## Architecture
![architecture](architecture.png)