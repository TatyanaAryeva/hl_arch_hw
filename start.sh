mvn clean package
docker build -t core:latest .
cd ./stand
docker-compose up -d
