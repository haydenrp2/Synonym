package com.vcu.ryanhayden.synonym;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Results extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        TextView tv = (TextView)findViewById(R.id.resultDisplay);
        tv.setText(getIntent().getStringExtra("resultString"));
    }
}
