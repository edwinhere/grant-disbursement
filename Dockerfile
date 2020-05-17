# © 2020 Edwin Jose Palathinkal
FROM openjdk:14-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-XX:TieredStopAtLevel=1","-cp","app:app/lib/*","com.gmail.edwinhere.grantdisbursement.GrantDisbursementApplication"]