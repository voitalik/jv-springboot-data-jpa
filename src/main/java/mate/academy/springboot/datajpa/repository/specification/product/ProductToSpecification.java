package mate.academy.springboot.datajpa.repository.specification.product;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductToSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "to";
    private static final String FIELD_NAME = "price";

    @Override
    public Specification<Product> getSpecification(String[] params) {
        int to = params[0].equals("") ? Integer.MAX_VALUE : Integer.parseInt(params[0]);
        return (root, query, cb) -> cb.le(root.get(FIELD_NAME), to);
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
