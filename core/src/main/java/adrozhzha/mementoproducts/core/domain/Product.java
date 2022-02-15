package adrozhzha.mementoproducts.core.domain;

import java.io.Serializable;
import java.util.List;

public class Product extends Entity<Product.Id> {
    private Id id;
    private String name;
    private List<PropertyValue<?>> propertyValues;

    @Override
    public Id getId() {
        return id;
    }

    @Override
    public void setId(Id id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PropertyValue<?>> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(List<PropertyValue<?>> propertyValues) {
        this.propertyValues = propertyValues;
    }

    public interface Id extends Serializable {
    }

    public static class BaseInfo {
        private final Id id;
        private final String name;

        public BaseInfo(Id id, String name) {
            this.id = id;
            this.name = name;
        }

        public Id getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
