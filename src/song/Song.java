package song;

import java.util.List;

public class Song {
    private List<Note> notes;
    private int tempo;

    public Song(List<Note> notes, int tempo) {
        this.notes = notes;
        this.tempo = tempo;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }


}

