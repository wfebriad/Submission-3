package id.web.wfebriadi.submissionkamus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView word, translate;

    public static String WORD = "extra_word";
    public static String TRANSLATE = "extra_translate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        word = (TextView)findViewById(R.id.word);
        translate = (TextView)findViewById(R.id.translate);

        String word1 = getIntent().getStringExtra(WORD);
        String translate1 = getIntent().getStringExtra(TRANSLATE);

        word.setText(word1);
        translate.setText(translate1);

    }
}
