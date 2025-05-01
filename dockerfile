FROM eclipse-temurin:17
WORKDIR /project
COPY . /project
RUN apt-get update && apt-get install -y ant && ant jar
ENTRYPOINT ["java","-cp","dist/aarithmetic.jar","arbitraryarithmetic.MyInfArith"]

