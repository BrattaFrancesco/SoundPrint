package song;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SongTest {

    @Test
    public void createSong(){
        List<Note> notes =  new ArrayList<>();
        notes.add(new Note("A", 0.25));
        notes.add(new Note("B", 0.5));
        notes.add(new Note("C", 1));
        notes.add(new Note("D", 2));
        notes.add(new Note("E", 4));

        Song song = new Song(notes, 0);
        Assert.assertArrayEquals(notes.toArray(), song.getNotes().toArray());
    }

    @Test
    public void setTempoSong(){
        List<Note> notes =  new ArrayList<>();

        Song song = new Song(notes, 160);
        Assert.assertEquals(160, song.getTempo());
    }
}
