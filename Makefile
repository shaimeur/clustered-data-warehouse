# Makefile for Spring Boot project

# Maven command
MVN = mvn

# Default target
all: compile

# Compile the project
compile:
	$(MVN) compile

# Run the Spring Boot application
run:
	$(MVN) spring-boot:run

# Run tests
test:
	$(MVN) test

# Package as a jar
build:
	$(MVN) clean package

# Clean build artifacts
clean:
	$(MVN) clean


# Build and run in one step
build-run: package
	java -jar target/*.jar

docker:
	docker compose up


.PHONY: all compile run test package clean build-run docker-build
