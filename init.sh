#bin/bash
#compile code
./mvnw spotless:apply
./mvnw clean install
docker-compose up --build