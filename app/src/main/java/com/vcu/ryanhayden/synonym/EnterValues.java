package com.vcu.ryanhayden.synonym;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EnterValues extends Activity {
    DatabaseManager db = new DatabaseManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_values);
    }

    public void onSubmit(View v){
        EditText firstInput = (EditText)findViewById(R.id.firstWord);
        String first = firstInput.getText().toString();
        EditText secondInput = (EditText)findViewById(R.id.secondWord);
        String second = secondInput.getText().toString();
        SynonymPair pair = new SynonymPair(first,second);
        db.insertSynonymPair(pair);
        Intent intent = new Intent(EnterValues.this, MainActivity.class);
        startActivity(intent);
    }
}
