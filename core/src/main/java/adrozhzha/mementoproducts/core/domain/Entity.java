package adrozhzha.mementoproducts.core.domain;

import java.util.Objects;

public abstract class Entity<Id> {

    public abstract Id getId();

    public abstract void setId(Id id);

    public boolean sameIdentityAs(Entity<Id> other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (this.getId() == null) {
            return false;
        }
        return Objects.equals(this.getId(), other.getId());
    }
}
