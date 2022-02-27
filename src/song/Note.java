package song;

public class Note {
    private String name;
    private double duration;

    public Note(String name, double d) {
        this.name = name;
        this.duration = d;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
}