package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public  class Category implements Idable{

    private int id;

    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category() {}

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getString() {
        return name;
    }

}
