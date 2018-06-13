package com.example.sose1.plumnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditNoteActivity extends AppCompatActivity {

    DBHelper dbHelper;
    EditText noteEditTitle, noteEditContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note_activity);

        noteEditTitle = findViewById(R.id.note_edit_title);
        noteEditContent = findViewById(R.id.note_edit_content);

        Intent intent = getIntent();
        final int ID = intent.getIntExtra("ID",0);
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");

        noteEditTitle.setText(title);
        noteEditContent.setText(content);

        dbHelper = new DBHelper(this);

        Button editButton = (Button) findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = noteEditTitle.getText().toString();
                String content = noteEditContent.getText().toString();

                if (title.equalsIgnoreCase("") || content.equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), getString(R.string.fill_empty_fields), Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(getApplicationContext(), getString(R.string.info_edit_note), Toast.LENGTH_SHORT).show();
                dbHelper.updateNote(ID, title, content);

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
