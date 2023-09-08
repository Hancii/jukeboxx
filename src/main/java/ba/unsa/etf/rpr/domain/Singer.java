package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Singer implements Idable{

    private int id;

    private String singer;

    private Category category;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getSinger() {return singer;}

    public void setSinger(String singer) {this.singer = singer;}

    public Category getCategory() {return category;}

    public void setCategory(Category category) {this.category = category;}

    @Override
    public String toString() {
        return "nesta";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Singer singer = (Singer) o;
        return id == singer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, singer, category);
    }
}
