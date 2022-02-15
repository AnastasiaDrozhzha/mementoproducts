package adrozhzha.mementoproducts.core.domain;

public class PropertyValue<T> {

    private Property.Id propertyId;
    private T value;

    public PropertyValue(Property.Id propertyId, T value) {
        this.propertyId = propertyId;
        this.value = value;
    }

    public Property.Id getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Property.Id propertyId) {
        this.propertyId = propertyId;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
