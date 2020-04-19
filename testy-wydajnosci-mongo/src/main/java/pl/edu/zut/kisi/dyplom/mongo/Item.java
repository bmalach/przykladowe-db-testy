package pl.edu.zut.kisi.dyplom.mongo;

import org.springframework.data.annotation.Id;

public class Item {
    @Id
    public String id;
    private String name;

    public Item() {
        super();
    }

    public Item(String name) {
        super();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
