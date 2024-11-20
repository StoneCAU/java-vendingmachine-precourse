package vendingmachine.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Products {
    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public Optional<Product> getProductByName(String name) {
        return products.stream()
                .filter(product -> product.getName()
                        .equals(name)).findFirst();
    }
}
