package sg.edu.rp.c346.id21023395.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText title, singers, year;
    RadioGroup rgStars;
    Button btnInsert, btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);
        singers = findViewById(R.id.singer);
        year = findViewById(R.id.year);
        rgStars = findViewById(R.id.rgStars);
        btnInsert = findViewById(R.id.button);
        btnShow = findViewById(R.id.button2);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Title = title.getText().toString();
                String Singers = singers.getText().toString();
                int Year = Integer.parseInt(year.getText().toString());
                int stars = getStars();
                // Create the DBHelper object, passing in the activity's Context
                DBHelper db = new DBHelper(MainActivity.this);
                // Insert a task
                db.insertSong(Title, Singers, Year, stars);
                db.close();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, showSongs.class);
                startActivity(i);
            }
        });
    }

    private int getStars() {
        int stars = 0;
        if(rgStars.getCheckedRadioButtonId() == R.id.oneStar){
            stars = 1;
        } else if(rgStars.getCheckedRadioButtonId() == R.id.twoStars){
            stars = 2;
        } else if(rgStars.getCheckedRadioButtonId() == R.id.threeStar) {
            stars = 3;
        } else if(rgStars.getCheckedRadioButtonId() == R.id.fourStars){
            stars = 4;
        } else if(rgStars.getCheckedRadioButtonId() == R.id.fiveStars) {
            stars = 5;
        }
        return stars;
    }
}