package com.example.sose1.plumnotes.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sose1.plumnotes.database.DBHelper;
import com.example.sose1.plumnotes.R;

import java.util.Random;

public class CreateNoteActivity extends AppCompatActivity {

    DBHelper dbHelper;
    EditText noteCreateTitle, noteCreateContent;
    String title, content;
    int color;

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

        if (!isEmpty()) {
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
        color = getRandomColor();
        Toast.makeText(getApplicationContext(), getString(R.string.info_create_note), Toast.LENGTH_SHORT).show();
        dbHelper.addNote(title, content, color);
        emptyFields();
        Intent intent = new Intent(CreateNoteActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void getDataNote(){
        title = noteCreateTitle.getText().toString();
        content = noteCreateContent.getText().toString();
    }

    public int getRandomColor(){
        Random rnd = new Random();

        int r = rnd.nextInt(128) + 117; // 128 ... 255
        int g = rnd.nextInt(128) + 117; // 128 ... 255
        int b = rnd.nextInt(128) + 117; // 128 ... 255

        int clr = Color.rgb(r, g, b);
        return clr;
    }
}








