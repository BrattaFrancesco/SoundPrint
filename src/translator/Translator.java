package translator;

import song.Song;

public class Translator {
    private Song song;

    public Translator(Song song) {
        this.song = song;
    }

    public Song getSong() {
        return song;
    }
}
