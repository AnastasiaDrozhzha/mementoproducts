package adrozhzha.mementoproducts.core.commands.product;

import adrozhzha.mementoproducts.commands.Command;
import adrozhzha.mementoproducts.core.domain.Product;
import adrozhzha.mementoproducts.core.util.PageRequest;
import adrozhzha.mementoproducts.core.util.Specification;

import java.util.List;

public abstract class ListProductsBaseInfo implements Command<List<Product.BaseInfo>> {

    protected final Specification<Product> specification;
    protected final PageRequest pageRequest;

    public ListProductsBaseInfo(Specification<Product> specification, PageRequest pageRequest) {
        this.specification = specification;
        this.pageRequest = pageRequest;
    }
}
