package adrozhzha.mementoproducts.core.domain;

import java.io.Serializable;

public class User extends Entity<User.Id> {

    private Id id;
    private String uid;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public interface Id extends Serializable {

    }
}
