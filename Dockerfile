FROM openjdk:17-oracle

COPY target/*.jar /opt/customer-svc.jar

EXPOSE 8080

ENTRYPOINT java -jar /opt/customer-svc.jar
