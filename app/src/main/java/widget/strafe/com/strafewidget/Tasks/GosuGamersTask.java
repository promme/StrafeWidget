package widget.strafe.com.strafewidget.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import widget.strafe.com.strafewidget.Objects.GosuGamersMatchObject;
import widget.strafe.com.strafewidget.Query.GosuGamersQuery;

/**
 * Created by olofberg on 15-02-17.
 */
public class GosuGamersTask extends AsyncTask<Void, Void, List<GosuGamersMatchObject>> {
    GosuGamersQuery gosuGamersQuery = new GosuGamersQuery();
    private ResponseCallback responseCallback;
    public GosuGamersTask(ResponseCallback responseCallback) {
        this.responseCallback = responseCallback;
    }

    public interface ResponseCallback {
        public void onGosuMatchesFetched(ArrayList<GosuGamersMatchObject> GosuGamersMatchObject);
    }

    @Override
    protected List<GosuGamersMatchObject> doInBackground(Void... params) {
        return gosuGamersQuery.fetchMatches();

    }

    @Override
    protected void onPostExecute(List<GosuGamersMatchObject> gosuGamersMatchObjects) {
        Log.d("gosuGamersMatchObjects", "" +  gosuGamersMatchObjects.size());
        ArrayList<GosuGamersMatchObject> temp = new ArrayList<>();
        temp = (ArrayList<GosuGamersMatchObject>) gosuGamersMatchObjects;
            responseCallback.onGosuMatchesFetched(temp);
        super.onPostExecute(gosuGamersMatchObjects);
    }
}
