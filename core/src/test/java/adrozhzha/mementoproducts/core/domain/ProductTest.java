package adrozhzha.mementoproducts.core.domain;

public class ProductTest extends EntityTest<Product, Product.Id> {

    @Override
    protected Product createDomainEntity() {
        return new Product();
    }

    @Override
    protected Product.Id createEntityId() {
        return new ProductId();
    }

    private class ProductId implements Product.Id {

    }
}