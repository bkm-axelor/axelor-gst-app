package com.axelor.gst.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.db.Query;
import com.axelor.gst.db.Product;

public class ProductRepository extends JpaRepository<Product> {

	public ProductRepository() {
		super(Product.class);
	}

	public Product findByCode(String code) {
		return Query.of(Product.class)
				.filter("self.code = :code")
				.bind("code", code)
				.fetchOne();
	}

	public Product findByName(String name) {
		return Query.of(Product.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

}

