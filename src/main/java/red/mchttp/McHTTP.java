package red.McHTTP;

import org.bukkit.plugin.java.JavaPlugin;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

//public class McHTTP {
public final class McHTTP extends JavaPlugin {

String version = "1.0";

String pwrstate = "on";

String srvname = "red";

private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {
 
	    McHTTP http = new McHTTP();
 
	    System.out.println("GET Request Using HttpURLConnection");
            http.sendGet();
            System.out.println();
 
	}
 
	private void sendGet() throws Exception {
 
	    String username = "hitenpratap";
            StringBuilder url = new stringBuilder("https://twitter.com/search");
            stringBuilder.append("?q=");
            stringBuilder.append(URLEncoder.encode(username, "UTF-8"));
 
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
 
		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);
 
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + 
                       response.getStatusLine().getStatusCode());
 
		BufferedReader rd = new BufferedReader(
                       new InputStreamReader(response.getEntity().getContent()));
 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
 
		System.out.println(result.toString());
 
	}
}