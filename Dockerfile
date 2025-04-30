# Etapa 1: TailwindCSS build
FROM node:20-alpine AS node_builder

WORKDIR /frontend

# Copiar solo package.json y lock para aprovechar cache
COPY package.json package-lock.json ./
RUN npm ci --omit=dev

COPY src/main/resources/ ./src/main/resources/
RUN npm run build:tailwind


# Etapa 2: Build Java App
FROM gradle:8.13-jdk21-alpine AS java_builder

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts ./
COPY gradle gradle
RUN gradle build --no-daemon --dry-run || true

COPY . .

# Copiamos el output.css generado
COPY --from=node_builder /frontend/src/main/resources/static/css/styles.css src/main/resources/static/css/styles.css

RUN gradle build --no-daemon

# Etapa 3: Runtime - imagen final super ligera
FROM eclipse-temurin:21-jre-alpine-3.21

WORKDIR /app

COPY --from=java_builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]