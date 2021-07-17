FROM adoptopenjdk/openjdk11
COPY target/Desafio-Supera-0.0.1-SNAPSHOT.jar Desafio-Supera-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Desafio-Supera-0.0.1-SNAPSHOT.jar"]
