# Event Sourcing and CQRS Demo Application
This is a demo application for event sourcing and CQRS built with spring boot.

## Getting Started

### Prerequisites
- Java 11
- Maven 3.3.9
- Docker

### Running
- Run `mvn clean install` to build the application
- Run the following to start the event store: 
  docker run -d \
  --name axonserver \
  -p 8024:8024 \
  -p 8124:8124 \
  axoniq/axonserver
- Start both query and command services from the main classes
- et voila!

### Endpoints
http://localhost:8080/house
{
"price": "270000",
"type": "apartment"
}

http://localhost:8080/house/sell/<aggregateid>
{
"price": "460000",
"type": "house"
}

http://localhost:8081/house/<aggregateid>