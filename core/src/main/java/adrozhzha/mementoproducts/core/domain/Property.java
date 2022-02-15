package adrozhzha.mementoproducts.core.domain;

import java.io.Serializable;

public class Property extends Entity<Property.Id> {
    private Id id;
    private String name;
    private String type;

    @Override
    public Id getId() {
        return id;
    }

    @Override
    public void setId(Id id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public interface Id extends Serializable {
    }
}
