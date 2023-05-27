# Car Records  App

## Overview

This is the Grpc Service that handles the saving of booking records.

It registers itself on Consul.

```yml
spring:
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        instanceId: ${spring.application.name}:${${spring.application.instance_id}:${server.port}:${random.value}}
      config:
        enabled: false
```
This configuration in `application.yml` 
```instanceId: ${spring.application.name}:${${spring.application.instance_id}:${server.port}:${random.value}}``` 
is not used.

Custom ID is generated via `com.interview.carrecords.config.custom.ConsulAutoRegistration2#getCustomId()`

## Consul

Install and run [Consul](https://developer.hashicorp.com/consul/downloads).

Run Consul [locally](http://localhost:8500/ui/dc1/services). 

### Consul Services
![Services](docs/consul-services.png)

### Consul Instances
Custom ID is generated via `com.interview.carrecords.config.custom.ConsulAutoRegistration2#getCustomId()`
This configuration in `application.yml`
```instanceId: ${spring.application.name}:${${spring.application.instance_id}:${server.port}:${random.value}}```
is not used.
 
![Instances](docs/consul-instances.png)
