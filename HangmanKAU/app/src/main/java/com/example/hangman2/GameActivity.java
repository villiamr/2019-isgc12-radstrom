package com.example.hangman2;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.*;
import android.content.Context;
import java.util.ArrayList;
import java.util.*;
import android.widget.ImageView;
import android.widget.EditText;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;



public class GameActivity extends Activity {
    public EditText editText;
    String randomWord;
    int wordLength;
    String guess;
    int picCounter = 0;
    double counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        editText = this.findViewById(R.id.guess);
        randomizeString();
        setHint();

        // Lyssnare för att kolla om något händer i edittexten.
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                wrongOrRightLetter();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void randomizeString(){

        try(InputStream inputStream = getResources().openRawResource(R.raw.database_file))
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Skapar en sträng som är null för att kolla mot while-loopen om vi nått slutet på filen.
            String line = null;

            // En array för att hålla reda på orden från databasen.
            ArrayList<String> array = new ArrayList<>();

            // While-loop för att lägga in alla ord från databasen.
            while ((line = reader.readLine()) != null) {
                array.add(line);
            }
            Random rand = new Random();
            // Slumpar ett tal mellan 0 och arrayens storlek.
            int randomIndex = rand.nextInt(array.size());
            randomWord = (array.get(randomIndex));
            wordLength = randomWord.length();
        }

        // Undantagshantering, ifall inte textfilen hittas.
        catch (IOException ex)
        {
            Toast.makeText(getApplicationContext(),"No database found.." ,Toast.LENGTH_SHORT).show();
        }


    }

    public void setHint(){
        // Tom sträng som ska tilldelas antal "_" för varje antal bokstav som finns i ordet.
        String hintString = "";
        TextView h = findViewById(R.id.hint);
        //Loop som går igenom längeen på det slumpade ordet och skriver ut hur många bokstäver det är med "X".
        for(int i = 0; i < wordLength; i++){
            hintString = hintString + "*";
            h.setText(hintString);
        }

    }

    public void getChars(){
        // Diverse deklarationer
        TextView guessedLettersID = findViewById(R.id.guessedLetters);
        String guessedLetters = guessedLettersID.getText().toString();
        EditText letter = findViewById(R.id.guess);
        // gör om till en sträng
        String guess = letter.getText().toString();
        // lägger in varje bokstav per inmatning för att sedan skriva ut till skärmen.
        guessedLetters = guessedLetters + " " + guess;
        guessedLettersID.setText(guessedLetters);
    }

    public void changeHint(){

        // Hämtar ut diverse element som jag behöver ta ifrån.
        EditText letter = findViewById(R.id.guess);
        guess = letter.getText().toString();
        char tempChar = guess.charAt(0);
        TextView h = findViewById(R.id.hint);
        // Tar "hinten" och gör om till en sträng, för att sedan göra om till en array.
        String hintString = h.getText().toString();
        char[] stringToHintArray = hintString.toCharArray();

        // Kollar så att det inte är tomt i fältet.
        if(guess.length() == 1) {
            // Skapar upp en ny array med orden i.
            char[] stringToCharArray = randomWord.toCharArray();
            // Kör en loop igenom varje bokstav i det slumpade ordet.
            for (int i = 0; i < stringToCharArray.length; i++) {
                // Om den gissade bokstaven är samma som det som ligger i arrayen så läggs
                // det in i hintarrayen. När loopen kört klart så görs det om till en sträng
                // och skrivs ut som hint.
                if(tempChar == stringToCharArray[i]){
                    stringToHintArray[i] = tempChar;
                }
            }
            String finalString = new String(stringToHintArray);
            h.setText(finalString);
        }

    }

    public void isWin(){
        // Jag adderar 0.5 istället för 1 på denna counter, eftersom att lyssnaren även registrerar
        // när fältet töms. Kom gärna med feedback på hur jag hade kunnat göra detta annorlunda
        // för jag har behövt ta lite omvägar pga av det, så det kanske inte blev snyggaste lösningen. :D
        counter = counter + 0.5;
        TextView h = findViewById(R.id.hint);
        String win = h.getText().toString();

        if(counter <= 7 && !win.contains("*")){

            Intent intentWin = new Intent(GameActivity.this, WinActivity.class);
            startActivity(intentWin);

        }else if(counter == 7 && win.contains("*")){

            Intent intentLoose = new Intent(GameActivity.this, LooseActivity.class);
            startActivity(intentLoose);

        }
    }

    // Gömmer androids tangentbord efter varje inmatning.
    public void hideSoftKeyBoard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public void wrongOrRightLetter(){
        EditText letter = findViewById(R.id.guess);
        ImageView img = findViewById(R.id.stage0);
        String guess = letter.getText().toString();
        hideSoftKeyBoard();
        TextView guessedLettersID = findViewById(R.id.guessedLetters);
        String guessedLetters = guessedLettersID.getText().toString();

        // En check så att man inte kan mata in samma bokstav flera gånger.
        if(guessedLetters.contains(guess) && editText.length() == 1){
            editText.setText("");
            counter = counter -1;
            Toast.makeText(getApplicationContext(),"You have already used that letter." ,Toast.LENGTH_SHORT).show();
        }else{
        // Diverse deklarationer


        // Om inmatningen finns med i slumpade ordet och textlängden är 1 så går vi in här
        // och kallar på två funktioner för att sedan tömma edittexten.
        if(randomWord.contains(guess) && editText.length() == 1){
            // tar bort 1 från countern så att personen har 7 felaktiga försök på sig.
            counter = counter -1;
            Toast.makeText(getApplicationContext(),"Good job, right letter!" ,Toast.LENGTH_SHORT).show();
            getChars();
            changeHint();
            editText.setText("");

        }
        // Om inte inmatningen matchar med slumpade ordet och längden är 1 på edittexten
        // så går vi in här och ändrar sourcen på den bild som används beroende på steg.
        else if(!randomWord.contains(guess) && editText.length() == 1) {
            picCounter = picCounter + 1;
            getChars();
            editText.getText().clear();
            Toast.makeText(getApplicationContext(), "Wrong letter, try again.", Toast.LENGTH_SHORT).show();
            if (picCounter == 1) {
                img.setImageResource(R.drawable.stage1);
            }
            if (picCounter == 2) {
                img.setImageResource(R.drawable.stage2);
            }
            if (picCounter == 3) {
                img.setImageResource(R.drawable.stage3);
            }
            if (picCounter == 4) {
                img.setImageResource(R.drawable.stage4);
            }
            if (picCounter == 5) {
                img.setImageResource(R.drawable.stage5);
            }
            if (picCounter == 6) {
                img.setImageResource(R.drawable.stage6);
            }
            if (picCounter == 7) {
                img.setImageResource(R.drawable.stage7);
            }
        }

        }

        // Kör denna funktion varje gång för att kolla om vinst / förlust.
        isWin();
    }
}
