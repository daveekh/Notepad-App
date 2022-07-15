package com.example.notepad;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    RecyclerView recyclerView;
    NotesListAdapter notesListAdapter;
    List<Notes> notes = new ArrayList<>();
    RoomDB database;
    FloatingActionButton fab_add;
    Notes selectedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_home);
        fab_add = findViewById(R.id.fab_add);

        database = RoomDB.getInstance(this);
        notes = database.dao().getAll();

        updateRecycler(notes);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivityForResult(intent, 101);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101) {
            if(resultCode == Activity.RESULT_OK) {
                Notes note = (Notes) data.getSerializableExtra("note");
                database.dao().insert(note);
                notes.clear();
                notes.addAll(database.dao().getAll());
                notesListAdapter.notifyDataSetChanged();
                //Toast.makeText(MainActivity.this, "Note added!", Toast.LENGTH_SHORT).show();

            }
        }

        else if(requestCode == 102) {
            if(resultCode == Activity.RESULT_OK) {
                Notes note = (Notes) data.getSerializableExtra("note");
                database.dao().update(note.getId(), note.getTitle(), note.getNotes());
                notes.clear();
                notes.addAll(database.dao().getAll());
                notesListAdapter.notifyDataSetChanged();
                //Toast.makeText(MainActivity.this, "Note edited!", Toast.LENGTH_SHORT).show();

            }
        }

    }

    private void updateRecycler(List<Notes> notes) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        notesListAdapter = new NotesListAdapter(MainActivity.this, notes, notesClickListener);
        recyclerView.setAdapter(notesListAdapter);
    }

    private final NotesClickListener notesClickListener = new NotesClickListener() {
        @Override
        public void onClick(Notes note) {
            Intent intent = new Intent(MainActivity.this, SelectNoteActivity.class);
            intent.putExtra("selected_note", note);
            startActivity(intent);
        }

        @Override
        public void onLongClick(Notes note, CardView cardView) {
            selectedNote = new Notes();
            selectedNote = note;
            showPopup(cardView);
        }
    };

    private void showPopup(CardView cardView) {
        PopupMenu popupMenu = new PopupMenu(this, cardView);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.edit: {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                intent.putExtra("old_note", selectedNote);
                startActivityForResult(intent, 102);
                return true;
            }
            case R.id.delete: {
                database.dao().delete(selectedNote);
                notes.remove(selectedNote);
                notesListAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Note deleted!", Toast.LENGTH_SHORT).show();
                return true;
            }
            default: return false;
        }
    }
}






























