package com.example.lab05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.lab05.databinding.ActivityRecyclerViewBinding;

public class RecyclerViewActivity extends AppCompatActivity {

    private final ArrayList<Image> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.lab05.databinding.ActivityRecyclerViewBinding recycler_binding =
                ActivityRecyclerViewBinding.inflate(getLayoutInflater());
        setContentView(recycler_binding.getRoot());
        setData();
        RecyclerView recyclerView = recycler_binding.recycler;
        CustomRecyclerAdapter adapter = new CustomRecyclerAdapter(this, images);
        recyclerView.setAdapter(adapter);
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

    public static class CustomRecyclerAdapter extends
            RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {
        private final LayoutInflater inflater;
        private final List<Image> images;

        public CustomRecyclerAdapter(Context context, List<Image> images) {
            this.images = images;
            this.inflater = LayoutInflater.from(context);
        }

        public void onBindViewHolder(CustomRecyclerAdapter.ViewHolder holder, int position) {
            Image image = images.get(position);
            holder.text.setText(image.getName());
            holder.image.setImageResource(image.getImageResource());
        }

        @NonNull
        @Override
        public CustomRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                   int viewType) {
            View view = inflater.inflate(R.layout.list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public int getItemCount() {
            return images.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final ImageView image;
            public final TextView text;

            public ViewHolder(View view) {
                super(view);
                image = view.findViewById(R.id.list_item_image);
                text = view.findViewById(R.id.list_item_text);
            }
        }
    }
}