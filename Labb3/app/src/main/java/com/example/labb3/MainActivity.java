package com.example.labb3;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.Toast;



public class MainActivity extends Activity {
    ArrayAdapter aa;
    ArrayList<String> list = new ArrayList<String>();


    // Returnerar strängen från textfältet så att MyAsynctask-klassen kan ta hand om den.
    public String getArtist(){
        EditText cac = findViewById(R.id.TextView1);
        String artistName = cac.getText().toString()
                .replace(' ', '+');
        return artistName;
    }

    // Hämtar arraylisten från MyAsyncTask och lägger in i listvyn.
    public void setArtists() {
        MyAsyncTask ma = new MyAsyncTask();
        list = ma.getList();

        aa = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_list_item_1, MainActivity.this.list);
        ListView lw = findViewById(R.id.listView1);
        lw.setAdapter(aa);
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                EditText input = findViewById(R.id.TextView1);

                // Startar en Async-task när jag klickar på knappen ifall textfältet är ifyllt, annars toast.

                if(input.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(), "You have to insert a name!", Toast.LENGTH_LONG).show();



                }
                else{
                    MyAsyncTask backgroundTask = new MyAsyncTask(
                            MainActivity.this);
                    backgroundTask.execute();
                }
            }

        });

    }
}