package adrozhzha.mementoproducts.core.commands.property;

import adrozhzha.mementoproducts.commands.Command;
import adrozhzha.mementoproducts.core.domain.Property;
import adrozhzha.mementoproducts.core.util.PageRequest;
import adrozhzha.mementoproducts.core.util.Specification;

public abstract class ListProperties implements Command<Property> {

    protected final Specification<Property> specification;
    protected final PageRequest pageRequest;

    public ListProperties(Specification<Property> specification, PageRequest pageRequest) {
        this.specification = specification;
        this.pageRequest = pageRequest;
    }
}
