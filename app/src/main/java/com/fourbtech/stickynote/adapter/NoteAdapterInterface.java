package com.fourbtech.stickynote.adapter;

import com.fourbtech.stickynote.model.Note;

public interface NoteAdapterInterface {

    void onItemDelete(Note note);
    void onClickItem(Note note);

}
