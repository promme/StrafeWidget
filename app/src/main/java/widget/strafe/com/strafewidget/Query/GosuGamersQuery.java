package widget.strafe.com.strafewidget.Query;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import widget.strafe.com.strafewidget.Objects.GosuGamersMatchObject;

/**
 * Created by olofberg on 15-02-17.
 */
public class GosuGamersQuery {

    public List<GosuGamersMatchObject> fetchMatches() {
        Gson gson = new Gson();
        List<GosuGamersMatchObject> matches = new ArrayList<GosuGamersMatchObject>();
        HttpURLConnection connection = null;

        try {
            connection = openHttpURLConnection("http://www.counter-strike.net/jsfeed/gosumatches", 8000);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            Log.d("1","1");
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            JSONArray jsonArray = new JSONArray(builder.toString());
            Log.d("", connection.getInputStream().toString());

            for (int i = 0; i < jsonArray.length(); i++){

                matches.add(new GosuGamersMatchObject(jsonArray.getJSONObject(i)));
            }

            Log.d("getname",matches.get(0).getName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return matches;
    }

    public static HttpURLConnection openHttpURLConnection(String urlString, int timeout) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(timeout);
        connection.setReadTimeout(timeout);
        return connection;
    }
}
