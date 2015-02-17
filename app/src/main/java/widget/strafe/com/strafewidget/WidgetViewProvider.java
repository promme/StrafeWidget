/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package widget.strafe.com.strafewidget;

import java.util.ArrayList;
import java.util.List;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import widget.strafe.com.strafewidget.R;
import widget.strafe.com.strafewidget.StrafeWidgetProvider;

public class WidgetViewProvider extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StrafeRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}

class StrafeRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private static final int mCount = 10;
    private List<String> mWidgetItems = new ArrayList<String>();
    private Context mContext;
    private int mAppWidgetId;

    public StrafeRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    public void onCreate() {
        for (int i = 0; i < mCount; i++) {
            mWidgetItems.add("heaton");
        }
    }

    public void onDestroy() {
        mWidgetItems.clear();
    }

    public int getCount() {
        return mCount;
    }

    public RemoteViews getViewAt(int position) {

        RemoteViews rv = new RemoteViews(mContext.getPackageName(),
                R.layout.widget_row);
        rv.setTextViewText(R.id.matchinfo, "Heaton " + position);

        return rv;
    }

    public RemoteViews getLoadingView() {
        // You can create a custom loading view (for instance when getViewAt()
        // is slow.) If you
        // return null here, you will get the default loading view.
        return null;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public long getItemId(int position) {
        return position;
    }

    public boolean hasStableIds() {
        return true;
    }

    public void onDataSetChanged() {
        // This is triggered when you call AppWidgetManager
        // notifyAppWidgetViewDataChanged
        // on the collection view corresponding to this factory. You can do
        // heaving lifting in
        // here, synchronously. For example, if you need to process an image,
        // fetch something
        // from the network, etc., it is ok to do it here, synchronously. The
        // widget will remain
        // in its current state while work is being done here, so you don't need
        // to worry about
        // locking up the widget.
    }
}