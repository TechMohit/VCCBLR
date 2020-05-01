package grossary.cyron.com.grossaryvccblrrelesed.about;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


import grossary.cyron.com.grossaryvccblrrelesed.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView txt=findViewById(R.id.txt);
        txt.setVisibility(View.VISIBLE);
    }
}
