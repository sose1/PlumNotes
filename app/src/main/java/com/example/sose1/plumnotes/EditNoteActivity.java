package com.example.sose1.plumnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;

public class EditNoteActivity extends AppCompatActivity {

    DBHelper dbHelper;
    EditText noteEditTitle, noteEditContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note_activity);


        noteEditTitle = findViewById(R.id.noteEditTitle);
        noteEditContent = findViewById(R.id.noteEditContent);
        

        dbHelper = new DBHelper(this);

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = noteEditTitle.getText().toString();
                String content = noteEditContent.getText().toString();

                if (title.equalsIgnoreCase("") || content.equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), getString(R.string.fill_empty_fields), Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(getApplicationContext(), getString(R.string.info_edit_note), Toast.LENGTH_SHORT).show();


                emptyFields();
                Intent intent = new Intent(EditNoteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void emptyFields() {
        noteEditTitle.setText("");
        noteEditContent.setText("");
    }
}
