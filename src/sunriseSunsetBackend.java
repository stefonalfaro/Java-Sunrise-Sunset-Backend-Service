import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//This was a 3rd party package and I had to Configure > Convert to Maven Project and then modify the pom.xml to include it
import com.google.gson.Gson;

public class sunriseSunsetBackend {

	//Exact same as how I would do this in C#
    public static class ApiResponse 
    {
        public Results results;
        public String status;

        public static class Results 
        {
            public String date;
            public String sunrise;
            public String sunset;
            public String first_light;
            public String last_light;
            public String dawn;
            public String dusk;
            public String solar_noon;
            public String golden_hour;
            public String day_length;
            public String timezone;
            public int utc_offset;
        }
    }
    
    //Exact same as C#. This would be exposed to the user. Let's see how to expose HTTP endpoints? Maybe Docker too. 
	public static void main(String[] args) 
	{
		System.out.println("Starting Sunrise Sunset Backend v1.0.0");
		
        double latitude = 38.907192;
        double longitude = -77.036873;
        
        //How to handle this error?
        ApiResponse response = makeApiRequest(latitude, longitude);
        
        System.out.println("Sunrise: " + response.results.sunrise);
        System.out.println("Sunset: " + response.results.sunset);
	}
	
	//Backend Service
    public static ApiResponse makeApiRequest(double latitude, double longitude) 
    {
        try 
        {
        	//Build the URL object
            String urlStr = String.format("https://api.sunrisesunset.io/json?lat=%f&lng=%f", latitude, longitude);
            URL url = new URL(urlStr);
            
            //Build a HTTP request object and make the GET request
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            
            //Buffer the response. This is a little different than how I would do in C# but the final result in the same.
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();

            // Parse JSON response. This is similar to C# Newtonsoft.Json library
            Gson gson = new Gson();

            //This is important since regardless of how I read the JSON data (NoSQL database or HTTP API) I will need to turn it into a object
            ApiResponse response = gson.fromJson(content.toString(), ApiResponse.class);
            
            return response;
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }
    }

}
