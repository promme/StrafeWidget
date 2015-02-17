package widget.strafe.com.strafewidget.Query;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import widget.strafe.com.strafewidget.Objects.HLTVObject;

/**
 * Created by olofberg on 2014-09-01.
 */

public class HLTVFetcher {

    public interface onHltvMatchesFetched {
        void onHltvMatchesFetched(ArrayList<HLTVObject> matches);
    }

    private onHltvMatchesFetched mCallback;
    private Context mContext;
    private ArrayList<HLTVObject> mAllMatches = new ArrayList<HLTVObject>();


    private static final String FETCH_MATCHES_URL = "http://ajax.googleapis.com/ajax/services/feed/load?v=1.0&num=10&q=http://www.hltv.org/hltv.rss.php";


    public HLTVFetcher(Context context, onHltvMatchesFetched callback) {
        mContext = context;
        mCallback = callback;
    }


    public void fetchAllMatches() {

        HltvFeedTask loaderTask = new HltvFeedTask();
        loaderTask.execute();
    }

    class HltvFeedTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog mDialog;


        @Override
        protected void onPreExecute() {

            mDialog = new ProgressDialog(mContext);
            mDialog.setTitle("Loading Matches");
            mDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            HttpClient client = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet(FETCH_MATCHES_URL);

            try {
                Log.d("Statuscode", "1");
                HttpResponse response = client.execute(getRequest);
                Log.d("Statuscode", "2");
                StatusLine statusline = response.getStatusLine();
                int statusCode = statusline.getStatusCode();

                if (statusCode != 200) {
                    Toast.makeText(mContext, "no interwebs", Toast.LENGTH_LONG).show();

                    return null;
                }
                InputStream jsonStream = response.getEntity().getContent();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(jsonStream));

                StringBuilder builder = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                JSONObject jsonObject = new JSONObject(builder.toString());

                Log.d("Json data", jsonObject.toString());
                JSONArray matches = jsonObject.getJSONObject("responseData").getJSONObject("feed").getJSONArray("entries");


                Log.i("JSON matches", matches.toString());
                for (int i = 0; i < matches.length(); i++) {
                    mAllMatches.add(new HLTVObject(matches.getJSONObject(i)));
                    Log.e("****json obj", mAllMatches.get(i).getLink());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            mDialog.dismiss();
            mCallback.onHltvMatchesFetched(mAllMatches);


            super.onPostExecute(result);
        }
    }
}
