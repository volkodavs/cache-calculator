FROM frolvlad/alpine-oraclejdk8:slim

ADD cache-calculator.jar app.jar
RUN sh -c 'touch /app.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
