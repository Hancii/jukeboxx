package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Song implements Idable {

    private int id;

    private String song;

    private String link;

    private Singer singer;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getSong() {return song;}

    public void setSong(String songs) {this.song = songs;}

    public String getLink() {return link;}

    public void setLink(String link) {this.link = link;}

    public Singer getSinger() {return singer;}

    public void setSinger(Singer singer) {this.singer = singer;}

    @Override
    public String toString() {
        return "nesto";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return id == song.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, song, link, singer);
    }
}
