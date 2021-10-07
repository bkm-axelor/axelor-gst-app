package com.axelor.gst.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.db.Query;
import com.axelor.gst.db.ProductCategory;

public class ProductCategoryRepository extends JpaRepository<ProductCategory> {

	public ProductCategoryRepository() {
		super(ProductCategory.class);
	}

	public ProductCategory findByCode(String code) {
		return Query.of(ProductCategory.class)
				.filter("self.code = :code")
				.bind("code", code)
				.fetchOne();
	}

	public ProductCategory findByName(String name) {
		return Query.of(ProductCategory.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

}

