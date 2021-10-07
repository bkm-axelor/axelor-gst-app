package com.axelor.gst.db;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Table(name = "GST_INVOICE_LINE", indexes = { @Index(columnList = "product") })
public class InvoiceLine extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GST_INVOICE_LINE_SEQ")
	@SequenceGenerator(name = "GST_INVOICE_LINE_SEQ", sequenceName = "GST_INVOICE_LINE_SEQ", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Product product;

	@NotNull
	private String hsbn;

	private String item;

	private Integer quantity = 0;

	private BigDecimal price = new BigDecimal("1");

	@Widget(readonly = true)
	private BigDecimal netAmount = BigDecimal.ZERO;

	@Widget(readonly = true)
	private BigDecimal gstRate = BigDecimal.ZERO;

	@Widget(readonly = true)
	private BigDecimal igst = BigDecimal.ZERO;

	@Widget(readonly = true)
	private BigDecimal sgst = BigDecimal.ZERO;

	@Widget(readonly = true)
	private BigDecimal cgst = BigDecimal.ZERO;

	@Widget(readonly = true)
	private BigDecimal grossAmount = BigDecimal.ZERO;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public InvoiceLine() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getHsbn() {
		return hsbn;
	}

	public void setHsbn(String hsbn) {
		this.hsbn = hsbn;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Integer getQuantity() {
		return quantity == null ? 0 : quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price == null ? BigDecimal.ZERO : price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getNetAmount() {
		return netAmount == null ? BigDecimal.ZERO : netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public BigDecimal getGstRate() {
		return gstRate == null ? BigDecimal.ZERO : gstRate;
	}

	public void setGstRate(BigDecimal gstRate) {
		this.gstRate = gstRate;
	}

	public BigDecimal getIgst() {
		return igst == null ? BigDecimal.ZERO : igst;
	}

	public void setIgst(BigDecimal igst) {
		this.igst = igst;
	}

	public BigDecimal getSgst() {
		return sgst == null ? BigDecimal.ZERO : sgst;
	}

	public void setSgst(BigDecimal sgst) {
		this.sgst = sgst;
	}

	public BigDecimal getCgst() {
		return cgst == null ? BigDecimal.ZERO : cgst;
	}

	public void setCgst(BigDecimal cgst) {
		this.cgst = cgst;
	}

	public BigDecimal getGrossAmount() {
		return grossAmount == null ? BigDecimal.ZERO : grossAmount;
	}

	public void setGrossAmount(BigDecimal grossAmount) {
		this.grossAmount = grossAmount;
	}

	public String getAttrs() {
		return attrs;
	}

	public void setAttrs(String attrs) {
		this.attrs = attrs;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!(obj instanceof InvoiceLine)) return false;

		final InvoiceLine other = (InvoiceLine) obj;
		if (this.getId() != null || other.getId() != null) {
			return Objects.equals(this.getId(), other.getId());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("id", getId())
			.add("hsbn", getHsbn())
			.add("item", getItem())
			.add("quantity", getQuantity())
			.add("price", getPrice())
			.add("netAmount", getNetAmount())
			.add("gstRate", getGstRate())
			.add("igst", getIgst())
			.add("sgst", getSgst())
			.add("cgst", getCgst())
			.add("grossAmount", getGrossAmount())
			.omitNullValues()
			.toString();
	}
}
