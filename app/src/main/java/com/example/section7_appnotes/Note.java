package com.example.section7_appnotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

public class Note extends AppCompatActivity {

    EditText titleEditText, contentEditText;

    public void onBackPressed() {
        try {
            titleEditText = findViewById(R.id.titleEditText);
            Intent intent = getIntent();
            intent.putExtra("Title",titleEditText.getText().toString());
            setResult(RESULT_OK,intent);
            finish();
        } catch(Exception e){
            e.printStackTrace();
        }
        super.onBackPressed();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);
    }
}
