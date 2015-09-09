package Entities;

import java.io.Serializable;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String usrname;
	private String password;
	private String cont;
    private String ipAddress;
	
	//Constructor
	public User(String usrname, String password, String cont, String ip){
		this.usrname = usrname;
		this.password = password;
		this.cont = cont;
        this.ipAddress = ip;
	}
	
	public String getUsrname() {
		return usrname;
	}
	protected void setUsrname(String usrname) {
		this.usrname = usrname;
	}
	public String getPassword() {
		return password;
	}
	protected void setPassword(String password) {
		this.password = password;
	}
	public String getCont() {
		return cont;
	}
	protected void setCont(String cont) {
		this.cont = cont;
	}
	
	public String getIP() {
		return this.ipAddress;
	}
	public void setIP(String ip) {
		this.ipAddress = ip;
	}
}
