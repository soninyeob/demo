

docker-compose up -d
docker-compose ps
docker-compose down

docker images
docker rmi {image-id}

docker-compose exec kafka-1 kafka-topics --create --topic test-hello --bootstrap-server kafka-1:9092 --replication-factor 3 --partitions 2
docker-compose exec kafka-1 kafka-topics --delete --topic test-hello --bootstrap-server kafka-1:9092
docker-compose exec kafka-1 kafka-topics --list --bootstrap-server kafka-1:9092

curl "localhost:8080/hello?message=123"

echo 'export DOCKER_CLI_HINTS=false' >> ~/.zshrc