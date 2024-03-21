package com.example.lab07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraListener;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CameraUpdateReason;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;

import com.example.lab07.databinding.ActivityMainBinding;
import com.yandex.runtime.image.ImageProvider;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements CameraListener {

    private MapView mapView;
    private static final Point startLocation = new Point(55.751957, 37.619493);
    private float zoomValue = 16.5f;
    private static final float ZOOM_BOUNDARY = 16.4f;

    private MapObjectCollection mapObjectCollection;
    private PlacemarkMapObject placemarkMapObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey(BuildConfig.API_KEY);
        MapKitFactory.initialize(this);

        com.example.lab07.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mapView = binding.map;
        moveToStartPosition();
        setMarkerFirstOpen();
        mapView.getMap().addCameraListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
        MapKitFactory.getInstance().onStart();
    }

    private void moveToStartPosition() {
        mapView.getMap().move(
                new CameraPosition(startLocation, zoomValue, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 5f),
                null
        );
    }

    private void setMarkerFirstOpen() {
        Bitmap marker = createBitmapFromVector(R.drawable.ic_pin_black_svg);
        mapObjectCollection = mapView.getMap().getMapObjects();
        if (marker != null) {
            placemarkMapObject = mapObjectCollection.addPlacemark(startLocation,
                    ImageProvider.fromBitmap(marker));
        } else return;
        placemarkMapObject.setOpacity(0.5f);
        placemarkMapObject.setText("Светличный Лев БПИ 225");
    }

    // Since yandex mapkit doesn't work with vector images we need to convert to bitmap
    private Bitmap createBitmapFromVector(int art) {
        Drawable drawable = ContextCompat.getDrawable(this, art);
        if (drawable == null)
            return null;
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888
        );
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    public void onCameraPositionChanged(@NonNull Map map, @NonNull CameraPosition cameraPosition, @NonNull CameraUpdateReason cameraUpdateReason, boolean b) {
        if (b) { // If camera finished moving
            if (cameraPosition.getZoom() >= ZOOM_BOUNDARY && zoomValue <= ZOOM_BOUNDARY) {
                placemarkMapObject.setIcon(
                        ImageProvider.fromBitmap(
                                Objects.requireNonNull(createBitmapFromVector(R.drawable.ic_pin_blue_svg))
                        )
                );
            } else if (cameraPosition.getZoom() <= ZOOM_BOUNDARY && zoomValue >= ZOOM_BOUNDARY) {
                placemarkMapObject.setIcon(
                        ImageProvider.fromBitmap(
                                Objects.requireNonNull(createBitmapFromVector(R.drawable.ic_pin_red_svg))
                        )
                );
            }
        }
        zoomValue = cameraPosition.getZoom();
    }
}