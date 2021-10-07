package com.axelor.gst.db;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Table(name = "GST_INVOICE", indexes = { @Index(columnList = "company"), @Index(columnList = "party"), @Index(columnList = "party_contact"), @Index(columnList = "invoice_address"), @Index(columnList = "shipping_address") })
public class Invoice extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GST_INVOICE_SEQ")
	@SequenceGenerator(name = "GST_INVOICE_SEQ", sequenceName = "GST_INVOICE_SEQ", allocationSize = 1)
	private Long id;

	@Widget(readonly = true, selection = "gst.invoice.status")
	private String status = "Draft";

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Company company;

	@Widget(readonly = true)
	private String reference;

	private LocalDateTime invoiceDate;

	private Integer priority = 0;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Party party;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Contact partyContact;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Address invoiceAddress;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Address shippingAddress;

	private Boolean invoiceAddressShipping = Boolean.TRUE;

	@Widget(readonly = true)
	private BigDecimal netAmount = BigDecimal.ZERO;

	@Widget(readonly = true)
	private BigDecimal netIGST = BigDecimal.ZERO;

	@Widget(readonly = true)
	private BigDecimal netCSGT = BigDecimal.ZERO;

	@Widget(readonly = true)
	private BigDecimal netSGST = BigDecimal.ZERO;

	@Widget(readonly = true)
	private BigDecimal grossAmount = BigDecimal.ZERO;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<InvoiceLine> invoiceItems;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public Invoice() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public LocalDateTime getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(LocalDateTime invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Integer getPriority() {
		return priority == null ? 0 : priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public Contact getPartyContact() {
		return partyContact;
	}

	public void setPartyContact(Contact partyContact) {
		this.partyContact = partyContact;
	}

	public Address getInvoiceAddress() {
		return invoiceAddress;
	}

	public void setInvoiceAddress(Address invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Boolean getInvoiceAddressShipping() {
		return invoiceAddressShipping == null ? Boolean.FALSE : invoiceAddressShipping;
	}

	public void setInvoiceAddressShipping(Boolean invoiceAddressShipping) {
		this.invoiceAddressShipping = invoiceAddressShipping;
	}

	public BigDecimal getNetAmount() {
		return netAmount == null ? BigDecimal.ZERO : netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public BigDecimal getNetIGST() {
		return netIGST == null ? BigDecimal.ZERO : netIGST;
	}

	public void setNetIGST(BigDecimal netIGST) {
		this.netIGST = netIGST;
	}

	public BigDecimal getNetCSGT() {
		return netCSGT == null ? BigDecimal.ZERO : netCSGT;
	}

	public void setNetCSGT(BigDecimal netCSGT) {
		this.netCSGT = netCSGT;
	}

	public BigDecimal getNetSGST() {
		return netSGST == null ? BigDecimal.ZERO : netSGST;
	}

	public void setNetSGST(BigDecimal netSGST) {
		this.netSGST = netSGST;
	}

	public BigDecimal getGrossAmount() {
		return grossAmount == null ? BigDecimal.ZERO : grossAmount;
	}

	public void setGrossAmount(BigDecimal grossAmount) {
		this.grossAmount = grossAmount;
	}

	public List<InvoiceLine> getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(List<InvoiceLine> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	/**
	 * Add the given {@link InvoiceLine} item to the {@code invoiceItems}.
	 *
	 * @param item
	 *            the item to add
	 */
	public void addInvoiceItem(InvoiceLine item) {
		if (getInvoiceItems() == null) {
			setInvoiceItems(new ArrayList<>());
		}
		getInvoiceItems().add(item);
	}

	/**
	 * Remove the given {@link InvoiceLine} item from the {@code invoiceItems}.
	 *
	 * <p>
	 * It sets {@code item.null = null} to break the relationship.
	 * </p>
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeInvoiceItem(InvoiceLine item) {
		if (getInvoiceItems() == null) {
			return;
		}
		getInvoiceItems().remove(item);
	}

	/**
	 * Clear the {@code invoiceItems} collection.
	 *
	 * <p>
	 * It sets {@code item.null = null} to break the relationship.
	 * </p>
	 */
	public void clearInvoiceItems() {
		if (getInvoiceItems() != null) {
			getInvoiceItems().clear();
		}
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
		if (!(obj instanceof Invoice)) return false;

		final Invoice other = (Invoice) obj;
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
			.add("status", getStatus())
			.add("reference", getReference())
			.add("invoiceDate", getInvoiceDate())
			.add("priority", getPriority())
			.add("invoiceAddressShipping", getInvoiceAddressShipping())
			.add("netAmount", getNetAmount())
			.add("netIGST", getNetIGST())
			.add("netCSGT", getNetCSGT())
			.add("netSGST", getNetSGST())
			.add("grossAmount", getGrossAmount())
			.omitNullValues()
			.toString();
	}
}
