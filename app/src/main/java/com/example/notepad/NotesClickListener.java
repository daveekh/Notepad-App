package com.example.notepad;

import androidx.cardview.widget.CardView;

public interface NotesClickListener {

    void onClick(Notes note);
    void onLongClick(Notes note, CardView cardView);


}
