# Record Shop API

  Welcome to the Record Shop Api. Here you will be able to find any genre of music which you can think of!
  You will be able to search by your favorite Artist, Genre, Album, etc.
  
  This application is a RESTful Web API that is deployed on AWS Elasting Beanstalk.
  Additionaliy, this application is using cache via the library: 
      Caffeine Cache


## Technologies Used

    Java 21
    Spring Boot
    JPA / Hibernate
    PostgresSQL
    AWS
    Docker

## Requirements

  Install latest version of Java
  
  Install Java IDE
  
  Clone the project *https://github.com/dimadeloseros1/Record-Shop-API.git*
  
    run: mvn clean install
        
    run: mvn build
  
  
## Navigation

  Navigite to src/java/com.northcoders.RecordShopApi/RecordShopApiApplication
  
  Click on RUN

## API Instructions

  #### API URL: http://localhost:8080/api/v1/albums 
  
  ### Dummy Data
      {
        "stock": 4,
        "artist": "Rank 1",
        "releaseYear": 1997,
        "genre": "TRANCE",
        "albumName": "Airwave"
      }
## API Endpoints
  #### GET
      http://localhost:8080/api/v1/albums 
  
  #### POST
      http://localhost:8080/api/v1/albums 

  
  #### PUT
      http://localhost:8080/api/v1/albums/1 
      
  
  #### DELETE
      http://localhost:8080/api/v1/albums/1 


 ## Future Additions
  ### Security
  ### Enhaced Caching implementation for PUT & DELETE enpoints via CaffeineCaching library.
  
