package logic.model.dao;

import java.sql.SQLException;
import java.util.List;

import logic.bean.*;

public interface AccountDAO {
	
	public int createAccount(AccountBean accountBean) throws SQLException;
	
	public int removeAccount(AccountBean accountBean) throws SQLException;
	
	public int getNumberToken(AccountBean accountBean) throws SQLException;
	
	public int updateNumberToken(AccountBean accountBean) throws SQLException;
	
	public List<AccountBean> getAllAccounts() throws SQLException;
	
	public AccountBean getAccount(String cf) throws SQLException;
	
}
