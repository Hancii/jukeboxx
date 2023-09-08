package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Singer implements Idable {

    private int id;

    private String name;

    private Category category;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Category getCategory() {return category;}

    public void setCategory(Category category) {this.category = category;}

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                '}';
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
        return Objects.hash(id, name, category);
    }
}
