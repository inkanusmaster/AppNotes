package com.example.section7_appnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

    ListView notesListView;
    ArrayList<String> noteTitleArrayList = new ArrayList<>();
    ArrayList<String> noteContentArrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    SharedPreferences titleSharedPreferences;
    SharedPreferences contentSharedPreferences;


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
        String currentTitle, currentContent;

        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 1) {
                if (resultCode == RESULT_OK) {
                    currentTitle = data.getStringExtra("Title");
                    currentContent = data.getStringExtra("Content");
                    noteTitleArrayList.add(currentTitle);
                    noteContentArrayList.add(currentContent);
//                    titleSharedPreferences.edit().putString(String.valueOf(noteTitleArrayList.indexOf(currentTitle)), currentTitle).apply();
//                    contentSharedPreferences.edit().putString(String.valueOf(noteContentArrayList.indexOf(currentContent)), currentContent).apply();

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
        titleSharedPreferences = this.getSharedPreferences("com.example.section7_appnotes", MODE_PRIVATE);
        contentSharedPreferences = this.getSharedPreferences("com.example.section7_appnotes", MODE_PRIVATE);
        notesListView = findViewById(R.id.notesListView);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noteTitleArrayList);
        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String title = titleSharedPreferences.getString(String.valueOf(i), "");
                String content = contentSharedPreferences.getString(String.valueOf(i), "");
                System.out.println("TITLE\n" + title + "\n");
                System.out.println("CONTENT\n" + content + "\n");

            }
        });
    }
}