package adrozhzha.mementoproducts.core.commands.property;

import adrozhzha.mementoproducts.commands.Command;
import adrozhzha.mementoproducts.core.domain.Property;

public abstract class CreateProperty implements Command<Property> {

    protected final Property propertyTemplate;

    public CreateProperty(Property propertyTemplate) {
        this.propertyTemplate = propertyTemplate;
    }
}
