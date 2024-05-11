![image](https://github.com/stefonalfaro/Java-Sunrise-Sunset-Backend-Service/assets/45152948/a300fb7e-2ce0-4fe6-8358-aa20e2155299)

I wanted to learn and was pleasantly surpised to find it is extremely similar to C#. Just different libraries since we are not using the .NET Framework.

This Java code defines a simple backend service called `sunriseSunsetBackend`, which fetches sunrise and sunset times based on latitude and longitude. It makes HTTP GET requests and uses a 3rd party package Google's Gson library for JSON parsing.

1. **ApiResponse Class**: This class defines the structure to hold the response from the sunrise sunset API. It contains various fields like sunrise, sunset, and day length, among others.

2. **main Method**: This is the entry point of the program. It initializes with a latitude and longitude, makes an API request, and prints the sunrise and sunset times.

3. **makeApiRequest Method**: This method constructs a URL for the API request using the provided latitude and longitude, sends the request, and processes the response. It reads the response into a buffer, converts it to a string, and then deserializes it into an `ApiResponse` object using Gson.
