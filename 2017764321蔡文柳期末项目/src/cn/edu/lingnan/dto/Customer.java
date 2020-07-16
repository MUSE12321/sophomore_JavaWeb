package cn.edu.lingnan.dto;

public class Customer {
	private String cId;
	private String cName;
	private String cPassword;
	private int    cSuper;
	private String cPn;
	
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcPassword() {
		return cPassword;
	}
	public void setcPassword(String cPassword) {
		this.cPassword = cPassword;
	}
	public int getcSuper() {
		return cSuper;
	}
	public void setcSuper(int cSuper) {
		this.cSuper = cSuper;
	}
	public String getcPn() {
		return cPn;
	}
	public void setcPn(String cPn) {
		this.cPn = cPn;
	}
	
}
