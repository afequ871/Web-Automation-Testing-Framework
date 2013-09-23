package gov.va.sep.automatedtesting.template;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoginTemplate {	
	
	private String loginHeaderLbl;	
	private String loginUserName;	
	private String loginPassword;	
	
	
	public LoginTemplate(Properties propFile){
		loginHeaderLbl = propFile.getProperty("loginHeaderLbl");
		loginUserName = propFile.getProperty("loginUserName");
		loginPassword = propFile.getProperty("loginPassword");
		
		
		
	}
	
	public String getLoginHeaderLbl() {
		return loginHeaderLbl;
	}

	public void setLoginHeaderLbl(String loginHeaderLbl) {
		this.loginHeaderLbl = loginHeaderLbl;
	}

	public String getLoginUserName() {
		return loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	
	public Properties getProperties() {
		Properties prop = new Properties();
		InputStream in = getClass().getResourceAsStream("suite.properties");
		try {
			prop.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return prop;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
