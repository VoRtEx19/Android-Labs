package com.example.lab08;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class MyWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyWidgetItemFactory(getApplicationContext(), intent);
    }

    static class MyWidgetItemFactory implements RemoteViewsFactory {
        private final Context context;
        private final int appWidgetId;
        private final String[] exampleData = {"one", "two", "three", "four", "five", "six", "seven"};

        MyWidgetItemFactory(Context context, Intent intent) {
            this.context = context;
            this.appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }


        @Override
        public void onCreate() {
            // connect to datasource
        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onDestroy() {
            // close data source
        }

        @Override
        public int getCount() {
            return exampleData.length;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_item);
            views.setTextViewText(R.id.widget_text_item, exampleData[position]);
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
