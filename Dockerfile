# FROM eclipse-temurin:17-jdk

# WORKDIR /app

# COPY target/*.jar app.jar

# EXPOSE 8081

# CMD ["java", "-jar", "app.jar"]









FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

COPY . .

RUN chmod +x ./mvnw && ./mvnw clean package -DskipTests

# RUN chmod +x mvnw

# RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
