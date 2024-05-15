**Sunrise/Sunset and NASA Satelite Image**

I wanted to learn and was pleasantly surpised to find it is extremely similar to C#. Just different libraries since we are not using the .NET Framework.


**Get Sunrise and Sunset for Location**

curl -X GET "http://localhost:8080/sunriseSunset?latitude=17.0&longitude=-88.0" -H "accept: application/json"
{"results":{"date":"2024-05-15","sunrise":"5:21:51 AM","sunset":"6:17:38 PM","first_light":"4:03:31 AM","last_light":"7:35:58 PM","dawn":"4:58:41 AM","dusk":"6:40:48 PM","solar_noon":"11:49:45 AM","golden_hour":"5:47:23 PM","day_length":"12:55:47","timezone":"America/Belize","utc_offset":-360},"status":"OK"}

**Download Satelilite Image for Location**

curl -o satellite_image.png "http://localhost:8080/satelliteImage?latitude=17.26545&longitude=-88.9946&date=2018-01-01"


**Docker** (stefonalfaro/sunrise-sunset-backend)

docker build -t sunrise-sunset-backend .
 
**SprintBoot**

mvn spring-boot:run
This is how we make the Controllers just like in .NET WebAPI


**Example**
![image](https://github.com/stefonalfaro/Java-Sunrise-Sunset-Backend-Service/assets/45152948/5011cc8c-1213-4371-934b-47d7b0561743)
