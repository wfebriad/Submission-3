package id.web.wfebriadi.submissionkamus;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import id.web.wfebriadi.submissionkamus.database.KamusHelper;
import id.web.wfebriadi.submissionkamus.model.EnglishModel;
import id.web.wfebriadi.submissionkamus.model.IndonesiaModel;

public class Preload extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload);

        new LoadData().execute();
    }

    private class LoadData extends AsyncTask<Void, Integer, Void> {
        final String TAG = LoadData.class.getSimpleName();
        KamusHelper kamusHelper;
        AppPreference appPreference;

        protected void onPreExecute(){
            kamusHelper = new KamusHelper(Preload.this);
            appPreference = new AppPreference(Preload.this);
        }
        @Override
        protected Void doInBackground(Void... voids) {

            Boolean firstRun = appPreference.getFirstRun();
            if (firstRun) {
                ArrayList<EnglishModel> englishModels = preLoadRawEnglish();
                ArrayList<IndonesiaModel> indonesiaModels = preLoadRawIndonesia();

                kamusHelper.open();
                kamusHelper.beginTransaction();
                Log.d(TAG, "MULAI");

                try {
                    for (EnglishModel englishModel : englishModels) {
                        kamusHelper.insertTransactionEnglish(englishModel);
                    }
                    kamusHelper.setTransactionSuccessEnglish();
                    Log.d(TAG, "insertTransactionEnglish : ini");;
                } catch (Exception e){
                    e.printStackTrace();
                }

                try {
                    for (IndonesiaModel indonesiaModel : indonesiaModels){
                        kamusHelper.insertTransactionIndonesia(indonesiaModel);
                    }
                    kamusHelper.setTransactionSuccessIndonesia();
                    Log.d(TAG, "insertTransactionIndonesia : ini");
                } catch (Exception e){
                    e.printStackTrace();
                }
                Log.d(TAG, "Selesai");
                kamusHelper.endTransaction();
                kamusHelper.close();

                appPreference.setFirstRun(false);
            } else {
                try {
                    synchronized (this){
                        this.wait(2000);
                    }
                } catch (Exception e){

                }
            }
            return null;
        }
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent i = new Intent(Preload.this, MainActivity.class);
            startActivity(i);
            finish();
        }


    }
    public ArrayList<EnglishModel> preLoadRawEnglish(){
        ArrayList<EnglishModel> englishModels = new ArrayList<>();
        String line;
        BufferedReader reader;

        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(R.raw.english_indonesia);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                EnglishModel englishModel;
                englishModel = new EnglishModel(splitstr[0], splitstr[1]);
                englishModels.add(englishModel);
                count++;
            } while (line != null);
        } catch (Exception e){
            e.printStackTrace();
        }
        return englishModels;
    }
    public ArrayList<IndonesiaModel> preLoadRawIndonesia(){
        ArrayList<IndonesiaModel> indonesiaModels = new ArrayList<>();
        String line;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(R.raw.indonesia_english);
            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;

            do {
                line = reader.readLine();
                String[] aplitstr = line.split("\t");

                IndonesiaModel indonesiaModel;
                indonesiaModel = new IndonesiaModel(aplitstr[0], aplitstr[1]);
                indonesiaModels.add(indonesiaModel);
                count++;
            } while (line != null);
        } catch (Exception e){
            e.printStackTrace();
        }
        return  indonesiaModels;
    }
}
