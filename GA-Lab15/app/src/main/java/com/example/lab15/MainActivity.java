package com.example.lab15;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Integer i;
    String[] from;
    int[] to;
    @SuppressLint("StaticFieldLeak")
    static ListView listView;

    EditText edit1;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = findViewById(R.id.editText);
        SharedPreferences save = getSharedPreferences("SAVE", 0);
        edit1.setText(save.getString("text", ""));

        from = new String[]{"Name"};
        to = new int[]{R.id.li_tv};
        Button btnadd = findViewById(R.id.add_button);
        final EditText editadd = findViewById(R.id.add_et);
        SQLiteDatabase db = openOrCreateDatabase("DBName", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MyTable5 (_id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR); ");
        Cursor cursor = db.rawQuery("SELECT * FROM Mytable5", null);
        i = cursor.getCount() + 1;
        if (cursor.getCount() > 0) {
            MyCursorAdapter scAdapter = new MyCursorAdapter(MainActivity.this, R.layout.list_item, cursor, from, to);
            listView = findViewById(R.id.list);
            listView.setAdapter(scAdapter);
        }
        db.close();
        btnadd.setOnClickListener(view -> {
            SQLiteDatabase btndb = openOrCreateDatabase("DBName", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor cursor2 = btndb.rawQuery("SELECT * FROM Mytable5", null);
            i = cursor2.getCount() + 1;
            for (int k = 1; k <= i; k++) {
                @SuppressLint("Recycle") Cursor cursor3 = btndb.rawQuery("SELECT * FROM Mytable5 WHERE _id=" + k + "", null);
                if (cursor3.getCount() == 0) {
                    i = k;
                    break;
                }
            }

            btndb.execSQL("INSERT INTO MyTable5 VALUES('" + i + "','" + editadd.getText().toString() + "');");
            Cursor curs = btndb.rawQuery("SELECT * FROM Mytable5", null);
            MyCursorAdapter scAdapter = new MyCursorAdapter(MainActivity.this, R.layout.list_item, curs, from, to);
            listView = findViewById(R.id.list);
            listView.setAdapter(scAdapter);
            btndb.close();
            Toast.makeText(findViewById(R.id.list).getContext(), "a row added to the table", Toast.LENGTH_LONG).show();
        });
    }

    @Override
    protected void onStop() {
        SharedPreferences save = getSharedPreferences("SAVE", 0);
        SharedPreferences.Editor editor = save.edit();
        editor.putString("text", edit1.getText().toString());
        editor.apply();
        super.onStop();
    }

    public static class MyCursorAdapter extends SimpleCursorAdapter {
        private final int layout_;
        String[] from;
        int[] to;
        ListView listView;
        EditText edit2;

        public MyCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
            super(context, layout, c, from, to);
            layout_ = layout;
        }

        @Override
        public void bindView(View view, Context _context, Cursor cursor) {
            @SuppressLint("Range") String data = cursor.getString(cursor.getColumnIndex("Name"));
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
            TextView text = view.findViewById(R.id.li_tv);
            text.setText(data);
            Button butdel = view.findViewById(R.id.li_delete);
            Button butedit = view.findViewById(R.id.li_edit);
            listView = MainActivity.listView;
            butdel.setOnClickListener(view1 -> {
                SQLiteDatabase db = _context.openOrCreateDatabase("DBName", MODE_PRIVATE, null);
                db.execSQL("DELETE FROM MyTable5 WHERE _id=" + id + "");
                Cursor cursor1 = db.rawQuery("SELECT * FROM Mytable5", null);
                from = new String[]{"Name"};
                to = new int[]{R.id.li_tv};
                MyCursorAdapter scAdapter = new MyCursorAdapter(_context, R.layout.list_item, cursor1, from, to);
                listView.setAdapter(scAdapter);
                db.close();
                Toast.makeText(_context, "row deleted from the db id = " + id, Toast.LENGTH_LONG).show();
            });
            butedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(_context);
                    dialog.setMessage("Enter new value:");
                    dialog.setTitle("Changing the item");
                    LayoutInflater inflater = new LayoutInflater(_context) {
                        @Override
                        public LayoutInflater cloneInContext(Context context) {
                            return null;
                        }
                    };
                    View dialogview = inflater.inflate(R.layout.dialog, null);
                    dialog.setView(dialogview);
                    edit2 = dialogview.findViewById(R.id.editTextCnahgeDBRecord);
                    edit2.setText(text.getText().toString());
                    dialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            SQLiteDatabase db = _context.openOrCreateDatabase("DBName", MODE_PRIVATE, null);
                            db.execSQL("UPDATE MyTable5 SET Name='" + edit2.getText().toString() + "' WHERE _id=" + id + "");
                            Cursor cursor = db.rawQuery("SELECT * FROM Mytable5", null);
                            from = new String[]{"Name"};
                            to = new int[]{R.id.li_tv};
                            MyCursorAdapter scAdapter = new MyCursorAdapter(_context, R.layout.list_item, cursor, from, to);
                            listView.setAdapter(scAdapter);
                            db.close();
                            Toast.makeText(_context, "row edited from the db row id=" + id, Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    });
                    dialog.setIcon(R.mipmap.ic_launcher_round);
                    AlertDialog alertDialog = dialog.create();
                    alertDialog.show();
                }
            });

        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(layout_, parent, false);
            return view;
        }
    }

}