# Build 
FROM gradle:8.3-jdk17 AS builder
WORKDIR /app
COPY . .

ARG DB_USER
ARG DB_PASSWORD
ARG DB_URL

ENV DB_USER=${DB_USER}
ENV DB_PASSWORD=${DB_PASSWORD}
ENV DB_URL=${DB_URL}

RUN gradle build --no-daemon

# Run
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
