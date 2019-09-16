package com.example.helloworldkau;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Toast.makeText(getApplicationContext(),"Hello World!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
