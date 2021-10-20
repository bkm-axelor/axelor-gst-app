package com.axelor.gst.web;

import java.math.BigDecimal;

import com.axelor.gst.db.Product;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public class ProductController {

	public void getGst(ActionRequest request, ActionResponse response) {

		Product product = request.getContext().asType(Product.class);

		BigDecimal gstRate = product.getCategory().getGstRate();
		response.setValue("gstRate", gstRate);
	}
	
}
