package com.example.lab05;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab05.databinding.ActivityListViewBinding;

public class ListViewActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.lab05.databinding.ActivityListViewBinding list_binding =
                ActivityListViewBinding.inflate(getLayoutInflater());
        setContentView(list_binding.getRoot());

        setListAdapter(
                new MyListAdapter(this,
                        android.R.layout.simple_list_item_1,
                        R.id.list_item_text,
                        getResources().getStringArray(R.array.images)));
    }

    public class MyListAdapter extends ArrayAdapter<String> {
        public MyListAdapter(Context context, int resource,
                             int textViewResourceId, String[] string) {
            super(context, resource, textViewResourceId, string);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder")
            View row = inflater.inflate(R.layout.list_item, parent, false);

            String[] items = getResources().getStringArray(R.array.images);
            ImageView image = row.findViewById(R.id.list_item_image);
            TextView text = row.findViewById(R.id.list_item_text);
            text.setText(items[position]);

            switch (position) {
                case 0:
                    image.setImageResource(R.drawable.silly_cat_0);
                    break;
                case 1:
                    image.setImageResource(R.drawable.silly_cat_1);
                    break;
                case 2:
                    image.setImageResource(R.drawable.silly_cat_2);
                    break;
                case 3:
                    image.setImageResource(R.drawable.silly_cat_3);
                    break;
                case 4:
                    image.setImageResource(R.drawable.silly_cat_4);
                    break;
                case 5:
                    image.setImageResource(R.drawable.silly_cat_5);
                    break;
                case 6:
                    image.setImageResource(R.drawable.silly_cat_6);
                    break;
                case 7:
                    image.setImageResource(R.drawable.silly_cat_7);
                    break;
                case 8:
                    image.setImageResource(R.drawable.silly_cat_8);
                    break;
                case 9:
                    image.setImageResource(R.drawable.silly_cat_9);
                    break;
            }
            return row;
        }
    }
}