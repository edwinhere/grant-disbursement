# Government Grant Disbursement API
RESTful API that would help your team decide on groups of people who are eligible for various upcoming government grants.

# Run

You can run using Docker Hub Repository Images, or Docker Images you build locally or using Java Spring Boot Run (needs JDK 14 and Maven)

## Using Docker Hub Repository Image (Recommended)
```
docker run -p 8080:8080 -t edwinhere/grant-disbursement
```

## Using Docker Images Build Locally
```
docker build -t edwinhere/grant-disbursement .
docker run -p 8080:8080 -t edwinhere/grant-disbursement
```

## Using Java Spring Boot Run

**Prerequisites**: JDK 14 and Maven
```
mvn spring-boot:run
```

## Run Tests
```
mvn test
```
