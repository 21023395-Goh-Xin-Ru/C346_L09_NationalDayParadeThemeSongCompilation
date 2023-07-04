package sg.edu.rp.c346.id21023395.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class showSongs extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_songs);
        lv = findViewById(R.id.lv);
        Intent intentReceived = getIntent();

        DBHelper db2 = new DBHelper(showSongs.this);
        ArrayList<Song> al = db2.getTasks();
        db2.close();
        ArrayAdapter<String> aa = new ArrayAdapter(showSongs.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);
    }
}