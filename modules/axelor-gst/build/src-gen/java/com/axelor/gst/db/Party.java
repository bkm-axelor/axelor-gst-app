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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Table(name = "GST_PARTY", indexes = { @Index(columnList = "name") })
public class Party extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GST_PARTY_SEQ")
	@SequenceGenerator(name = "GST_PARTY_SEQ", sequenceName = "GST_PARTY_SEQ", allocationSize = 1)
	private Long id;

	@Widget(readonly = true)
	private String reference;

	@NotNull
	private String name;

	@Widget(selection = "gst.party.type")
	@NotNull
	private String type = "Company";

	private Boolean customer = Boolean.FALSE;

	private Boolean supplier = Boolean.FALSE;

	@Size(min = 15, max = 15)
	private String gstin;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "party", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Contact> contactList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "party", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addresstList;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public Party() {
	}

	public Party(String name) {
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

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getCustomer() {
		return customer == null ? Boolean.FALSE : customer;
	}

	public void setCustomer(Boolean customer) {
		this.customer = customer;
	}

	public Boolean getSupplier() {
		return supplier == null ? Boolean.FALSE : supplier;
	}

	public void setSupplier(Boolean supplier) {
		this.supplier = supplier;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
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
	 * <p>
	 * It sets {@code item.party = this} to ensure the proper relationship.
	 * </p>
	 *
	 * @param item
	 *            the item to add
	 */
	public void addContactListItem(Contact item) {
		if (getContactList() == null) {
			setContactList(new ArrayList<>());
		}
		getContactList().add(item);
		item.setParty(this);
	}

	/**
	 * Remove the given {@link Contact} item from the {@code contactList}.
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
	 * If you have to query {@link Contact} records in same transaction, make
	 * sure to call {@link javax.persistence.EntityManager#flush() } to avoid
	 * unexpected errors.
	 * </p>
	 */
	public void clearContactList() {
		if (getContactList() != null) {
			getContactList().clear();
		}
	}

	public List<Address> getAddresstList() {
		return addresstList;
	}

	public void setAddresstList(List<Address> addresstList) {
		this.addresstList = addresstList;
	}

	/**
	 * Add the given {@link Address} item to the {@code addresstList}.
	 *
	 * <p>
	 * It sets {@code item.party = this} to ensure the proper relationship.
	 * </p>
	 *
	 * @param item
	 *            the item to add
	 */
	public void addAddresstListItem(Address item) {
		if (getAddresstList() == null) {
			setAddresstList(new ArrayList<>());
		}
		getAddresstList().add(item);
		item.setParty(this);
	}

	/**
	 * Remove the given {@link Address} item from the {@code addresstList}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeAddresstListItem(Address item) {
		if (getAddresstList() == null) {
			return;
		}
		getAddresstList().remove(item);
	}

	/**
	 * Clear the {@code addresstList} collection.
	 *
	 * <p>
	 * If you have to query {@link Address} records in same transaction, make
	 * sure to call {@link javax.persistence.EntityManager#flush() } to avoid
	 * unexpected errors.
	 * </p>
	 */
	public void clearAddresstList() {
		if (getAddresstList() != null) {
			getAddresstList().clear();
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
		if (!(obj instanceof Party)) return false;

		final Party other = (Party) obj;
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
			.add("reference", getReference())
			.add("name", getName())
			.add("type", getType())
			.add("customer", getCustomer())
			.add("supplier", getSupplier())
			.add("gstin", getGstin())
			.omitNullValues()
			.toString();
	}
}
