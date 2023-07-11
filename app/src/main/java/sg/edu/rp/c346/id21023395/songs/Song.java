package sg.edu.rp.c346.id21023395.songs;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(int id, String title, String singers, int year, int stars) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }
    public int getId() { return id; }

    public String getTitle() { return title; }

    public String getSingers() { return singers;}

    public int getYear() { return year;}

    public int getStars() { return stars;}
    public String setTitle(String t) { return this.title = t; }
    public String setSingers(String s) { return this.singers = s; }
    public int setYear(int y) { return this.year = y; }
    public int setStars(int s) { return this.stars = s; }

    @Override
    public String toString() {
        String starString = "";
        for (int i = 0; i < stars; i++) {
            starString += "*";
        }
        return String.format("%s\n%s - %d\n%s", title, singers, year, starString);
    }
}
