package com.vcu.ryanhayden.synonym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    DatabaseManager db = new DatabaseManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v){
        if(v.getId() == R.id.findSynonymButton){
            EditText input = (EditText)findViewById(R.id.searchSynonym);
            String inputString = input.getText().toString();
            Intent intent = new Intent(MainActivity.this, Results.class);
            String result = db.searchByFirst(inputString);
            intent.putExtra("resultString",result);
            startActivity(intent);
        }
        else if(v.getId() == R.id.enterValuesButton){
            Intent intent = new Intent(MainActivity.this, EnterValues.class);
            startActivity(intent);
        }
    }
}
