package com.axelor.gst.web;

import java.math.BigDecimal;
import java.util.List;

import com.axelor.gst.db.Product;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;

public class ProductController {

	public void getGst(ActionRequest request, ActionResponse response) {

		Product product = request.getContext().asType(Product.class);

		BigDecimal gstRate = product.getCategory().getGstRate();
		response.setValue("gstRate", gstRate);
	}
	public void createReport(ActionRequest request, ActionResponse response) {

		@SuppressWarnings("unchecked")
		List<Integer> productIdsList = (List<Integer>) request.getContext().get("_ids");
		List<String> idsl = Lists.transform(productIdsList, Functions.toStringFunction());
		String productIds = String.join(",", idsl);
		System.err.println(productIds);
		request.getContext().put("productIds", productIds);
	}
	
}
