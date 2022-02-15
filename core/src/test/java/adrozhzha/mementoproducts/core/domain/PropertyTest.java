package adrozhzha.mementoproducts.core.domain;

public class PropertyTest extends EntityTest<Property, Property.Id> {

    @Override
    protected Property createDomainEntity() {
        return new Property();
    }

    @Override
    protected Property.Id createEntityId() {
        return new PropertyId();
    }

    private static class PropertyId implements Property.Id {

    }
}