package model;

import dao.AccountDAO;

public class LoginLogic {
	public boolean execute(Account account) {
		AccountDAO dao = new AccountDAO();
		Account result = dao.findByAccount(account);
		return result !=null;
	}
}
