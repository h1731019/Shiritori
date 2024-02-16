package model;

import dao.AccountDAO;

public class NewAccountLogic {
	public boolean execute(Account account) {
		AccountDAO dao = new AccountDAO();
		dao.newAccount(account);
		Account result = dao.findByAccount(account);
		return result !=null;
	}

}
