package mate.academy.springboot.datajpa.repository.specification.product;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductFromSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "from";
    private static final String FIELD_NAME = "price";

    @Override
    public Specification<Product> getSpecification(String[] params) {
        int from = params[0].equals("") ? 0 : Integer.parseInt(params[0]);
        return (root, query, cb) -> cb.ge(root.get(FIELD_NAME), from);
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
