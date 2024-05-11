import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import com.google.gson.Gson; //This was a 3rd party package and I had to Configure > Convert to Maven Project and then modify the pom.xml to include it
import java.util.Properties;

public class sunriseSunsetBackend 
{
	//Config class. In the code we can use Config.getApiKey(); to return the value set in config.properties for nasa_api_key
	public static class Config 
	{
	    private static final String PROP_FILE = "/config.properties";

	    public static String getApiKey() 
	    {
	        Properties prop = new Properties();
	        try (InputStream inputStream = Config.class.getResourceAsStream(PROP_FILE)) 
	        {
	            if (inputStream == null) {
	                System.out.println("Sorry, unable to find config.properties");
	                return null;
	            }
	            
	            prop.load(inputStream);
	            
	            return prop.getProperty("nasa_api_key");
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	            return null;
	        }
	    }
	}
	
	//Sunrise Sunset API HTTP response
    public static class SunApiResponse 
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
     
	public static void main(String[] args) 
	{
		System.out.println("Starting Sunrise Sunset Backend v1.0.1");
		
        double latitude = 38.907192;
        double longitude = -77.036873;
        String date = "2018-01-01"; // For NASA Satelite
        
        //Make the Sunrise Sunset request
        SunApiResponse sunsetSunriseResponse = makeApiRequest(latitude, longitude);
        System.out.println("Sunrise: " + sunsetSunriseResponse.results.sunrise);
        System.out.println("Sunset: " + sunsetSunriseResponse.results.sunset);
        
        //Make the Satelite image request of Earth
        boolean success = downloadSateliteImage(latitude, longitude, date);
        if (success) {
        	String currentDirectory = System.getProperty("user.dir");
            System.out.println("NASA Landsat Satelite Image successfully downloaded to " + currentDirectory);
        } else {
            System.out.println("Failed to download the image.");
        }
	}
	
	//Service for Sunrise Sunset API
    public static SunApiResponse makeApiRequest(double latitude, double longitude) 
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
            SunApiResponse response = gson.fromJson(content.toString(), SunApiResponse.class);
            
            return response;
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }
    }
    
    //Service for NASA Landsat imagery
    public static boolean downloadSateliteImage(double latitude, double longitude, String date) 
    {
        try 
        {    
        	//Get the API key from a config file
        	String apiKey = Config.getApiKey();
            if (apiKey == null) {
                System.out.println("NASA API Key not set in environment variables.");
                return false;
            }
            
            //Build the URL object with your API key
            String urlStr = String.format("https://api.nasa.gov/planetary/earth/imagery?lon=%f&lat=%f&date=%s&dim=0.10&api_key=%s",
                longitude, latitude, date, apiKey
            );
            URL url = new URL(urlStr);

            //Open a connection to the URL and set up to read the image
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            //Read the image data
            InputStream in = new BufferedInputStream(con.getInputStream());
            FileOutputStream out = new FileOutputStream("downloaded_image.png");

            //Write the image data to a file
            byte[] buffer = new byte[1024];
            int count;
            while ((count = in.read(buffer)) != -1) {
                out.write(buffer, 0, count);
            }
            out.close();
            in.close();
            con.disconnect();

            return true;
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
    }
}
