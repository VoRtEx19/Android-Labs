package com.example.lab05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab05.databinding.ActivityGridViewBinding;

import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends AppCompatActivity {

    private final List<Image> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.lab05.databinding.ActivityGridViewBinding grid_binding =
                ActivityGridViewBinding.inflate(getLayoutInflater());
        setContentView(grid_binding.getRoot());

        setData();

        final GridView gridView = grid_binding.grid;
        final CustomGridAdapter adapter = new CustomGridAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1, images);
        gridView.setAdapter(adapter);
    }

    private void setData() {
        String[] array = getResources().getStringArray(R.array.images);
        images.add(new Image(array[0], R.drawable.silly_cat_0));
        images.add(new Image(array[1], R.drawable.silly_cat_1));
        images.add(new Image(array[2], R.drawable.silly_cat_2));
        images.add(new Image(array[3], R.drawable.silly_cat_3));
        images.add(new Image(array[4], R.drawable.silly_cat_4));
        images.add(new Image(array[5], R.drawable.silly_cat_5));
        images.add(new Image(array[6], R.drawable.silly_cat_6));
        images.add(new Image(array[7], R.drawable.silly_cat_7));
        images.add(new Image(array[8], R.drawable.silly_cat_8));
        images.add(new Image(array[9], R.drawable.silly_cat_9));
    }

    private class CustomGridAdapter extends ArrayAdapter<Image> {

        public CustomGridAdapter(@NonNull Context context, int resource, @NonNull List<Image> objects) {
            super(context, resource, objects);
        }


        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder")
            View element = inflater.inflate(R.layout.list_item, parent, false);
            ImageView image = element.findViewById(R.id.list_item_image);
            TextView text = element.findViewById(R.id.list_item_text);
            image.setImageResource(images.get(position).getImageResource());
            text.setText(images.get(position).getName());
            return element;
        }
    }
}