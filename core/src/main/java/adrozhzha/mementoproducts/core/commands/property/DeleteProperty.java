package adrozhzha.mementoproducts.core.commands.property;

import adrozhzha.mementoproducts.commands.Command;
import adrozhzha.mementoproducts.core.domain.Property;

public abstract class DeleteProperty implements Command<Void> {

    protected final Property.Id propertyId;

    public DeleteProperty(Property.Id propertyId) {
        this.propertyId = propertyId;
    }
}
