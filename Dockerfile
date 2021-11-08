FROM ubuntu:latest

MAINTAINER Khulan "onagraa@email.com"

ENV version=aws-db-usage
ENV dbuser=postgres
ENV dbpass=password321
           #jdbc:driver://hostname:port/dbName
ENV jdbcurl=jdbc:postgresql://pmadatabaseaws.ck2gytu0tf3w.us-east-1.rds.amazonaws.com:5432/postgres

RUN apt-get update && apt-get install -y openjdk-8-jdk

WORKDIR /usr/local/bin/

ADD target/pma-app.jar .

ENTRYPOINT ["java", "-jar", "pma-app.jar"]
