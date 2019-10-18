package com.example.labb3;

import android.os.AsyncTask;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.net.URL;
import java.util.ArrayList;



public class MyAsyncTask extends AsyncTask<Void, Void, Void> {
    static ArrayList<String> arraylist = new ArrayList<String>();
    public MainActivity mainActivity;



    public MyAsyncTask(MainActivity mainActivity) {

        this.mainActivity = mainActivity;

    }

    public MyAsyncTask() {

    }
    @Override
    protected Void doInBackground(Void... params) {
        synchronized (this) {
            try {
                // tömmer arraylistan.
                arraylist.clear();
                // Hämtar artistnamet som jag ska söka på.
                String artistName = mainActivity.getArtist();

                URL url;
                try {
                    // Hämtar från api:t. Sätter limiten på sökningar till 5 och autokorrekt är på.
                    url = new URL("http://ws.audioscrobbler.com/2.0/?method=artist.getsimilar&artist=" + artistName + "&api_key=04375e59e78548186e8cb85f11808920&limit=5&autocorrect=1");
                    XmlPullParserFactory parserCreator = XmlPullParserFactory
                            .newInstance();
                    XmlPullParser parser = parserCreator.newPullParser();
                    parser.setInput(url.openStream(), null);
                    int parserEvent = parser.getEventType();
                    String tagName;
                    while (parserEvent != XmlPullParser.END_DOCUMENT) {
                        switch (parserEvent) {
                            case XmlPullParser.START_TAG:
                                tagName = parser.getName();

                                // Om tagname i XML:en är = name så lägger vi till det i arraylistan.
                                if (tagName.equals("name")) {
                                    arraylist.add("Artist: "+ parser.nextText());
                                }
                                break;
                        }
                        parserEvent = parser.next();
                    }

                    // Undantagshantering
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        // Kör metoden setArtists när tasken är "klar".
        mainActivity.setArtists();
        super.onPostExecute(result);
    }

    // Metod för att kunna returnera listan.
    public ArrayList<String> getList() {
        return arraylist;
    }

}
