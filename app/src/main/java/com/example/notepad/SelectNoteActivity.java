package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SelectNoteActivity extends AppCompatActivity {

    TextView textView_selectTitle;
    TextView textView_selectDate;
    TextView textView_selectNote;
    Notes note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_note);

        textView_selectTitle = findViewById(R.id.textView_selectTitle);
        textView_selectDate = findViewById(R.id.textView_selectDate);
        textView_selectNote = findViewById(R.id.textView_selectNote);

        note = new Notes();
        try {
            note = (Notes) getIntent().getSerializableExtra("selected_note");
            textView_selectTitle.setText(note.getTitle());
            textView_selectDate.setText(note.getDate());
            textView_selectNote.setText(note.getNotes());
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}