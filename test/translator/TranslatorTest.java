package translator;

import org.junit.Assert;
import org.junit.Test;
import song.Note;
import song.Song;

import java.util.ArrayList;
import java.util.List;

public class TranslatorTest {

    @Test
    public void setSong(){
        List<Note> notes = new ArrayList<>();
        Translator translator = new Translator(new Song(notes, 160));

        Assert.assertArrayEquals(notes.toArray(), translator.getSong().getNotes().toArray());
    }
}
