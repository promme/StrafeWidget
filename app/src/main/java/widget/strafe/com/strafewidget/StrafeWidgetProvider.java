package widget.strafe.com.strafewidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import widget.strafe.com.strafewidget.Objects.GosuGamersMatchObject;
import widget.strafe.com.strafewidget.Tasks.GosuGamersTask;

/**
 * Created by olofberg on 15-02-11.
 */
public class StrafeWidgetProvider extends AppWidgetProvider {

    public static final String EXTRA_ITEM = "widget.strafe.com.strafewidget.EXTRA_ITEM";
    public static final String TOAST_ACTION = "widget.strafe.com.strafewidget.TOAST_ACTION";

    private GosuGamersTask gosuGamersTask;
    private GosuGamersTask.ResponseCallback responseCallback;
    private ArrayList<GosuGamersMatchObject> gosuGamersMatchObjectsArraylist;

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager mgr = AppWidgetManager.getInstance(context);
        if (intent.getAction().equals(TOAST_ACTION)) {
            int appWidgetId = intent.getIntExtra(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            int viewIndex = intent.getIntExtra(EXTRA_ITEM, 0);
            Toast.makeText(context, "Touched view " + viewIndex,
                    Toast.LENGTH_SHORT).show();
        }
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d("Update", "Update Started");
        Toast.makeText(context, "Strafe widget updated", Toast.LENGTH_LONG).show();

        responseCallback = new GosuGamersTask.ResponseCallback() {
            @Override
            public void onGosuMatchesFetched(ArrayList<GosuGamersMatchObject> gosuGamersMatchObjects) {

                gosuGamersMatchObjectsArraylist = gosuGamersMatchObjects;
                Log.d("onGosuMatchesFetched", "Success " + gosuGamersMatchObjects.size());
                updateRemoteViews(context, appWidgetManager, appWidgetIds, gosuGamersMatchObjects);

            }
        };
        gosuGamersTask = new GosuGamersTask(responseCallback);
        gosuGamersTask.execute();



    }
    public void updateRemoteViews(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds, ArrayList<GosuGamersMatchObject> gosuGamersMatchObjects){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Bundle arraylistBundle = new Bundle();
        arraylistBundle.putSerializable("arraylistBundle", gosuGamersMatchObjects);
        for (int i = 0; i < appWidgetIds.length; i++) {

            Intent intent = new Intent(context, WidgetViewProvider.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    appWidgetIds[i]);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            intent.putExtra("arraylistBundle", arraylistBundle);
            RemoteViews rv = new RemoteViews(context.getPackageName(),
                    R.layout.widget_layout);
            rv.setRemoteAdapter(appWidgetIds[i], R.id.match_list_view, intent);



            rv.setTextViewText(R.id.widget_textview, "Strafe \nUpdated:" + dateFormat.format(date));
            Log.d("Update", "Update Finished");
            appWidgetManager.updateAppWidget(appWidgetIds[i], rv);
        }
    }

}
