package com.example.calculator;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity{
    // Deklarerar flyttal och strängar för att använda längre ner.
    Float nr1, nr2, result;
    String one, two, res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClick(View view) {
        // Söker fram de två textfälten med talen i.
        EditText edittext1 = findViewById(R.id.nr1);
        EditText edittext2 = findViewById(R.id.nr2);
        EditText edittext3 = findViewById(R.id.display);

        // Switch-sats där den går in i en av 5 följade, addition, subtraktion, multiplikation, division eller rensa.
        switch (view.getId()) {

            case R.id.clear:
                // Tömmer alla fält.
                ((EditText) findViewById(R.id.nr1)).setText("");
                ((EditText) findViewById(R.id.nr2)).setText("");
                ((EditText) findViewById(R.id.result)).setText("");
                ((EditText) findViewById(R.id.display)).setText("");
                break;

            case R.id.addition:
                // Adderar

                    one = edittext1.getText().toString();
                    two = edittext2.getText().toString();

                if(one.length() == 0 || two.length() == 0){
                    Toast.makeText(getApplicationContext(),"You have to insert a number in both field one and two.",Toast.LENGTH_SHORT).show();
                    break;
                }
                else {
                    nr1 = Float.parseFloat(one);
                    nr2 = Float.parseFloat(two);
                    result = nr1 + nr2;
                    res = Float.toString(result);
                    ((EditText) findViewById(R.id.result)).setText(res);
                    edittext3.setText(nr1 + "+" +nr2 +"=" +res);
                    break;
                }
            case R.id.subtraction:
                // Subtraherar
                one = edittext1.getText().toString();
                two = edittext2.getText().toString();

                if(one.length() == 0 || two.length() == 0){
                    Toast.makeText(getApplicationContext(),"You have to insert a number in both field one and two.",Toast.LENGTH_SHORT).show();
                    break;
                }
                else {
                    nr1 = Float.parseFloat(one);
                    nr2 = Float.parseFloat(two);
                    result = nr1 - nr2;
                    res = Float.toString(result);
                    ((EditText) findViewById(R.id.result)).setText(res);
                    edittext3.setText(nr1 + "-" +nr2 +"=" +res);
                    break;
                }

            case R.id.multiplication:
                // Multiplicerar
                one = edittext1.getText().toString();
                two = edittext2.getText().toString();

                if(one.length() == 0 || two.length() == 0){
                    Toast.makeText(getApplicationContext(),"You have to insert a number in both field one and two.",Toast.LENGTH_SHORT).show();
                    break;
                }
                else {
                    nr1 = Float.parseFloat(one);
                    nr2 = Float.parseFloat(two);
                    result = nr1 * nr2;
                    res = Float.toString(result);
                    ((EditText) findViewById(R.id.result)).setText(res);
                    edittext3.setText(nr1 + "*" +nr2 +"=" +res);
                    break;
                }

            case R.id.division:
                // Dividerar
                one = edittext1.getText().toString();
                two = edittext2.getText().toString();

                if(one.length() == 0 || two.length() == 0){
                    Toast.makeText(getApplicationContext(),"You have to insert a number in both field one and two.",Toast.LENGTH_SHORT).show();
                    break;
                }
                else {
                    nr1 = Float.parseFloat(one);
                    nr2 = Float.parseFloat(two);
                    result = nr1 / nr2;
                    res = Float.toString(result);
                    ((EditText) findViewById(R.id.result)).setText(res);
                    edittext3.setText(nr1 + "/" +nr2 +"=" +res);
                    break;
                }
        }
    }
}
