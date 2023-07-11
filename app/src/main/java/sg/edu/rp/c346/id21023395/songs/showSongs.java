package sg.edu.rp.c346.id21023395.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class showSongs extends AppCompatActivity {

    ListView lv;
    Button btnShow5Stars;
    ArrayList<Song>al;
    ArrayAdapter<String>aa;
    Spinner spn;
    ArrayList<String> yearList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_songs);
        btnShow5Stars = findViewById(R.id.show5Stars);
        spn = findViewById(R.id.spinner);
        lv = findViewById(R.id.lv);
        getIntent();

        DBHelper db2 = new DBHelper(showSongs.this);
        al = db2.getSongs();
        db2.close();
        aa = new ArrayAdapter(showSongs.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        yearList = new ArrayList<>();
        DBHelper db = new DBHelper(showSongs.this);
        yearList = db.getYears(); // Method in DBHelper class to retrieve the unique years
        db.close();
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yearList);
        spn.setAdapter(yearAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song data = al.get(position);
                Intent i = new Intent(showSongs.this,
                        ThirdActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        btnShow5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(showSongs.this);
                al.clear();
                int songs5Stars = 5;
                al.addAll(dbh.getSongsFilter(songs5Stars));
                aa.notifyDataSetChanged();
            }
        });

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedYear = parent.getItemAtPosition(position).toString();
                int year = Integer.parseInt(selectedYear);
                DBHelper dbh = new DBHelper(showSongs.this);
                al.clear();
                al.addAll(dbh.getYearsFilter(year));
                aa.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(showSongs.this);
        al.clear();
        al.addAll(dbh.getSongs());
        aa.notifyDataSetChanged();
    }
}