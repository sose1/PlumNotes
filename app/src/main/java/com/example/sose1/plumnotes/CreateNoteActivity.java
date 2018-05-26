package com.example.sose1.plumnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class CreateNoteActivity extends AppCompatActivity{

    DBHelper dbHelper;
    EditText noteCreateTitle, noteCreateContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note_activity);

        noteCreateTitle = findViewById(R.id.noteCreateTitle);
        noteCreateContent = findViewById(R.id.noteCreateContent);


        dbHelper = new DBHelper(this);

        FloatingActionButton fab1 = findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = noteCreateTitle.getText().toString();
                String content = noteCreateContent.getText().toString();

                if (title.equalsIgnoreCase("") || content.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), getString(R.string.fill_empty_fields), Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(getApplicationContext(), getString(R.string.info_create_note), Toast.LENGTH_SHORT).show();
                dbHelper.addNote(title, content);
                emptyFields();
                Intent intent = new Intent(CreateNoteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    public void emptyFields(){
        noteCreateTitle.setText("");
        noteCreateContent.setText("");
    }
}
