package adrozhzha.mementoproducts.core.commands.product;

import adrozhzha.mementoproducts.commands.Command;
import adrozhzha.mementoproducts.core.domain.Product;

public abstract class UpdateProduct implements Command<Product> {

    protected final Product product;

    public UpdateProduct(Product product) {
        this.product = product;
    }
}
