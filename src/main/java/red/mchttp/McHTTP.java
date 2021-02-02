package red.McHTTP;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import org.bukkit.plugin.java.JavaPlugin;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class McHTTP extends JavaPlugin {

String version = "1.0";

String USER_AGENT = "McHTTP/" + version;

//send request when server is started
@Override
    public void onEnable() {
		//Config Registration
        getConfig().options().copyDefaults();
        saveDefaultConfig();
	//power actions
			String pwrstate = "on";
			sendGETRequestv2(getConfig().getString("endpoint") + "?state=" + pwrstate + "&name=" + getConfig().getString("name"));
}
//send request when server is powered off
@Override
    public void onDisable() {
		saveConfig();
		//power actions
			String pwrstate = "off";
			sendGETRequestv2(getConfig().getString("endpoint") + "?state=" + pwrstate + "&name=" + getConfig().getString("name"));
}
    public void sendGETRequestv2(final String url) {

        //Build client
        try (final CloseableHttpClient httpClient = HttpClientBuilder.create().setUserAgent(USER_AGENT).build()) {

            final HttpGet request = new HttpGet();
            request.setURI(new URL(url).toURI());

            final HttpResponse response = httpClient.execute(request);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            int responseCode = response.getStatusLine().getStatusCode();

            String bufferReaderLine;
            final StringBuilder stringBuilder = new StringBuilder();
            while ((bufferReaderLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(bufferReaderLine);
            }

            bufferedReader.close();
            //System.out.print(response);

            System.out.println("[McHTTP] Request completed successfully!");
        } catch (final IOException ioE) {
            ioE.printStackTrace();
        } catch (final URISyntaxException e) {
            e.printStackTrace();
        }
    }
}