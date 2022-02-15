package adrozhzha.mementoproducts.core.commands.product;

import adrozhzha.mementoproducts.commands.Command;
import adrozhzha.mementoproducts.core.domain.Product;

public abstract class DeleteProduct implements Command<Void> {

    protected final Product.Id productId;

    public DeleteProduct(Product.Id productId) {
        this.productId = productId;
    }
}
