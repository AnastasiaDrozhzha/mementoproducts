package adrozhzha.mementoproducts.core.commands.product;

import adrozhzha.mementoproducts.commands.Command;
import adrozhzha.mementoproducts.core.domain.Product;
import adrozhzha.mementoproducts.core.domain.Property;

import java.util.Collections;
import java.util.List;

public abstract class BulkChangePropertyValues implements Command<Void> {

    protected final List<Product.Id> productIds;
    protected final List<PropertyValueChange> propertyValueChanges;

    public BulkChangePropertyValues(List<Product.Id> productIds,
                                    List<PropertyValueChange> propertyValueChanges) {
        this.productIds = Collections.unmodifiableList(productIds);
        this.propertyValueChanges = Collections.unmodifiableList(propertyValueChanges);
    }

    public abstract static class PropertyValueChange {

        protected final Property.Id propertyId;

        public PropertyValueChange(Property.Id propertyId) {
            this.propertyId = propertyId;
        }
    }

    public static class PropertyValueDelete extends PropertyValueChange {

        public PropertyValueDelete(Property.Id propertyId) {
            super(propertyId);
        }
    }

    public static class PropertyValueUpdate extends PropertyValueChange {

        private final String rawValue;

        public PropertyValueUpdate(Property.Id propertyId, String rawValue) {
            super(propertyId);
            this.rawValue = rawValue;
        }

        public String getRawValue() {
            return rawValue;
        }
    }
}
