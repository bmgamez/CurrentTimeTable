package de.bmgamez.backend;

public class Lesson {

    private int period;
    private String subject;
    private String room;
    private String Text;

    public Lesson(int period, String subject, String room, String Text) {
        this.period = period;
        this.subject = subject;
        this.room = room;
        this.Text = Text;
    }

    public int getPeriod() {
        return period;
    }

    public String getSubject() {
        return subject;
    }

    public String getRoom() {
        return room;
    }

    public String getText() {
        return Text;
    }
}
