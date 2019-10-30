package com.example.section7_appnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    boolean firstOpen;
    ListView notesListView;
    final ArrayList<String> noteTitle = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    protected String title = "";


    protected void newNote() {
        try {
            Intent intent = new Intent(this, Note.class);
            startActivityForResult(intent, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.addNote) {
            newNote();
            return true;
        }
        return false;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 1) {
                if (resultCode == RESULT_OK) {
                    title = data.getStringExtra("Title");
                    noteTitle.add(title);
                    notesListView.setAdapter(arrayAdapter);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstOpen = true;
        notesListView = findViewById(R.id.notesListView);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noteTitle);
        notesListView.setAdapter(arrayAdapter);
        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });
    }
}