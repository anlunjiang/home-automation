# home-automation

This project aims to be a set of microservices aimed at home automation, or domotics. Microservice architecture is chosen
due to flexibility, language choice and modularity.

Microservices are initially aimed to be written in Java Spring Boot - mainly as a learning exercise. However each service 
can be rewritten in any other language (Python, Go) without much consequence

## Services

The automation system will be designed to be run on various devices across a home connected to a 
shared network. Each service will be containerised and run via Docker.

* Device-Registry
  * Service that keeps track of the different devices managed by the system and enables
  new devices to be registered, deleted or modified
* Controllers
  * Can house multiple controllers that can dictate actions to applicants around the home
  * This can be say temperature sensors, wifi plugs, HUE lights, etc, all will be housed under this
  microservice
  * Controllers will register themselves with the Device-Registry service and give info such
  as ip address, and devices that it controls
* Interface
  * Will talk to the device registry, get the state of those devices and then talk to the
  relevant controllers to control the devices


