package com.example.sose1.plumnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateNoteActivity extends AppCompatActivity {

    DBHelper dbHelper;
    EditText noteCreateTitle, noteCreateContent;
    String title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note_activity);

        noteCreateTitle = findViewById(R.id.note_create_title);
        noteCreateContent = findViewById(R.id.note_create_content);

        dbHelper = new DBHelper(this);

        final Button createButton = findViewById(R.id.create_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty()){
                    Toast.makeText(getApplicationContext(), getString(R.string.fill_empty_fields), Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    createNote();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (isEmpty()) {
            Toast.makeText(getApplicationContext(), getString(R.string.fill_empty_fields), Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            createNote();
        }
    }

    public void emptyFields(){
        noteCreateTitle.setText("");
        noteCreateContent.setText("");
    }

    public boolean isEmpty(){
        getDataNote();

        if (title.equalsIgnoreCase("") || content.equalsIgnoreCase("")){
            return true;
        }
        else{
            return false;
        }
    }

    public void createNote(){
        getDataNote();
        Toast.makeText(getApplicationContext(), getString(R.string.info_create_note), Toast.LENGTH_SHORT).show();
        dbHelper.addNote(title, content);
        emptyFields();
        Intent intent = new Intent(CreateNoteActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void getDataNote(){
         title = noteCreateTitle.getText().toString();
         content = noteCreateContent.getText().toString();
    }
}








