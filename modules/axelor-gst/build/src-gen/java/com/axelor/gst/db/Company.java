package com.axelor.gst.db;

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
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.axelor.meta.db.MetaFile;
import com.google.common.base.MoreObjects;

@Entity
@Table(name = "GST_COMPANY", indexes = { @Index(columnList = "name"), @Index(columnList = "logo"), @Index(columnList = "address") })
public class Company extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GST_COMPANY_SEQ")
	@SequenceGenerator(name = "GST_COMPANY_SEQ", sequenceName = "GST_COMPANY_SEQ", allocationSize = 1)
	private Long id;

	@NotNull
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private MetaFile logo;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Contact> contactList;

	@Size(min = 15, max = 15)
	private String gstIn;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<BankAccount> bankDetails;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Address address;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public Company() {
	}

	public Company(String name) {
		this.name = name;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MetaFile getLogo() {
		return logo;
	}

	public void setLogo(MetaFile logo) {
		this.logo = logo;
	}

	public List<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}

	/**
	 * Add the given {@link Contact} item to the {@code contactList}.
	 *
	 * @param item
	 *            the item to add
	 */
	public void addContactListItem(Contact item) {
		if (getContactList() == null) {
			setContactList(new ArrayList<>());
		}
		getContactList().add(item);
	}

	/**
	 * Remove the given {@link Contact} item from the {@code contactList}.
	 *
	 * <p>
	 * It sets {@code item.null = null} to break the relationship.
	 * </p>
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeContactListItem(Contact item) {
		if (getContactList() == null) {
			return;
		}
		getContactList().remove(item);
	}

	/**
	 * Clear the {@code contactList} collection.
	 *
	 * <p>
	 * It sets {@code item.null = null} to break the relationship.
	 * </p>
	 */
	public void clearContactList() {
		if (getContactList() != null) {
			getContactList().clear();
		}
	}

	public String getGstIn() {
		return gstIn;
	}

	public void setGstIn(String gstIn) {
		this.gstIn = gstIn;
	}

	public List<BankAccount> getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(List<BankAccount> bankDetails) {
		this.bankDetails = bankDetails;
	}

	/**
	 * Add the given {@link BankAccount} item to the {@code bankDetails}.
	 *
	 * @param item
	 *            the item to add
	 */
	public void addBankDetail(BankAccount item) {
		if (getBankDetails() == null) {
			setBankDetails(new ArrayList<>());
		}
		getBankDetails().add(item);
	}

	/**
	 * Remove the given {@link BankAccount} item from the {@code bankDetails}.
	 *
	 * <p>
	 * It sets {@code item.null = null} to break the relationship.
	 * </p>
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeBankDetail(BankAccount item) {
		if (getBankDetails() == null) {
			return;
		}
		getBankDetails().remove(item);
	}

	/**
	 * Clear the {@code bankDetails} collection.
	 *
	 * <p>
	 * It sets {@code item.null = null} to break the relationship.
	 * </p>
	 */
	public void clearBankDetails() {
		if (getBankDetails() != null) {
			getBankDetails().clear();
		}
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
		if (!(obj instanceof Company)) return false;

		final Company other = (Company) obj;
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
			.add("name", getName())
			.add("gstIn", getGstIn())
			.omitNullValues()
			.toString();
	}
}
