FROM openjdk:11
COPY . /usr/src/todo
WORKDIR /usr/src/todo
RUN ./gradlew clean build -x test
CMD ["java","-jar","build/libs/awards-0.1-all.jar"]