# Data Extraction Library
This project is a library which can be called from a CLI project or wrapped in a Microservice.
It is based on Spring Boot and used as a test for Embedit.

The Data Extraction library uses REST template, Spring Cache / Hazelcast backend and implements finding users and their posts by user id. The dataset is  http://jsonplaceholder.typicode.com

## Caching
By default, the cache is 5 min ttl and max. 50 MB size.
This setting can be adjusted before Spring is initialized by calling `HazelcastConfiguration.setCacheSize()` and `HazelcastConfiguration.setCacheTtl()`.
      
## Testing
Spring Test, JUnit and Mockito are used for unit testing.