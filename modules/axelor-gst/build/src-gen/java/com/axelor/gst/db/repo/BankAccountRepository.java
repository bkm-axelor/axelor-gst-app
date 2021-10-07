package com.axelor.gst.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.gst.db.BankAccount;

public class BankAccountRepository extends JpaRepository<BankAccount> {

	public BankAccountRepository() {
		super(BankAccount.class);
	}

}

