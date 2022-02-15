package adrozhzha.mementoproducts.core.commands.product;

import adrozhzha.mementoproducts.commands.Command;
import adrozhzha.mementoproducts.core.domain.Product;

public abstract class FetchProduct implements Command<Product> {

    protected final Product.Id productId;

    public FetchProduct(Product.Id productId) {
        this.productId = productId;
    }
}
