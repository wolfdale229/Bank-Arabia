FROM openjdk:8-alpine

COPY target/uberjar/bank_arabia.jar /bank_arabia/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/bank_arabia/app.jar"]
