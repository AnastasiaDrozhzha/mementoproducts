package adrozhzha.mementoproducts.core.commands.property;

import adrozhzha.mementoproducts.commands.Command;
import adrozhzha.mementoproducts.core.domain.Property;

public abstract class FetchProperty implements Command<Property> {

    protected final Property.Id propertyId;

    public FetchProperty(Property.Id propertyId) {
        this.propertyId = propertyId;
    }
}
