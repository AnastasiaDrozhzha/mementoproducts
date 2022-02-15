package adrozhzha.mementoproducts.core.commands.property;

import adrozhzha.mementoproducts.commands.Command;
import adrozhzha.mementoproducts.core.domain.Property;

public abstract class UpdateProperty implements Command<Property> {

    protected final Property property;

    public UpdateProperty(Property property) {
        this.property = property;
    }
}
