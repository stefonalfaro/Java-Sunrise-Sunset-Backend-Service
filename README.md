# Sunrise/Sunset and NASA Satellite Image Application
Java application with the SpringBoot framework for REST HTTP APIs. Built into a Docker image and then deployed to Google Cloud Run. Final result is https://sunrise-sunset-backend-dvknejaaha-uc.a.run.app/sunriseSunset?latitude=17.0&longitude=-88.0


This application provides endpoints to retrieve sunrise and sunset times for a given location and to download satellite images from NASA for a specified location and date.

## Endpoints

### Get Sunrise and Sunset for Location

Retrieve sunrise and sunset times for a specific location by latitude and longitude.

#### Request

```sh
curl -X GET "http://localhost:8080/sunriseSunset?latitude=17.0&longitude=-88.0" -H "accept: application/json"
```

#### Response

```json
{
  "results": {
    "date": "2024-05-15",
    "sunrise": "5:21:51 AM",
    "sunset": "6:17:38 PM",
    "first_light": "4:03:31 AM",
    "last_light": "7:35:58 PM",
    "dawn": "4:58:41 AM",
    "dusk": "6:40:48 PM",
    "solar_noon": "11:49:45 AM",
    "golden_hour": "5:47:23 PM",
    "day_length": "12:55:47",
    "timezone": "America/Belize",
    "utc_offset": -360
  },
  "status": "OK"
}
```

### Download Satellite Image for Location

Download a satellite image from NASA for a specific location and date.

#### Request

```sh
curl -o satellite_image.png "http://localhost:8080/satelliteImage?latitude=17.26545&longitude=-88.9946&date=2018-01-01"
```

## Running the Application

### Docker

You can build and run the application using Docker. The Docker image is available at `stefonalfaro/sunrise-sunset-backend`.

#### Build the Docker Image

```sh
docker build -t sunrise-sunset-backend .
```

### Spring Boot

Run the application using Maven with Spring Boot.

```sh
mvn spring-boot:run
```

## Development Notes

This application demonstrates how similar Java and C# can be, particularly in the context of web development. The controllers in this Java application are constructed similarly to .NET WebAPI controllers, utilizing different libraries specific to Java.

---

### Example Satelite Image
![image](https://github.com/stefonalfaro/Java-Sunrise-Sunset-Backend-Service/assets/45152948/5011cc8c-1213-4371-934b-47d7b0561743)
