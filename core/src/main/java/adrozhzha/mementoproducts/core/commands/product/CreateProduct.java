package adrozhzha.mementoproducts.core.commands.product;

import adrozhzha.mementoproducts.commands.Command;
import adrozhzha.mementoproducts.core.domain.Product;

public abstract class CreateProduct implements Command<Product> {

    protected final Product productTemplate;

    public CreateProduct(Product productTemplate) {
        this.productTemplate = productTemplate;
    }
}
