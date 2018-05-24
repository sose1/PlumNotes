package com.example.sose1.plumnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class CreateNoteActivity extends AppCompatActivity{

    DBHelper dbHelper;
    EditText noteCreateTitle, noteCreateContent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note_activity);

        noteCreateTitle = findViewById(R.id.create_note_title);
        noteCreateContent = findViewById(R.id.create_note_content);

        dbHelper = new DBHelper(this);

        FloatingActionButton fab1 = findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = noteCreateTitle.getText().toString();
                String content = noteCreateContent.getText().toString();

                //sprawdzanie czy pola są puste
                if (name.equalsIgnoreCase("") || content.equalsIgnoreCase("")){
                    Snackbar.make(view, getString(R.string.fill_empty_fields), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }

                //funkcja z klasy do zarządzania Bazami danych
                dbHelper.addNote(name, content);

                //czyszczenie pól
                emptyFields();

                Intent intent = new Intent(CreateNoteActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void emptyFields(){
        noteCreateTitle.setText("");
        noteCreateContent.setText("");
    }
}