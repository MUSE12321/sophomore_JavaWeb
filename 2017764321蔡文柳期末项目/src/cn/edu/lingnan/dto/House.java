package cn.edu.lingnan.dto;

public class House {
	
	private String hId;
	private String fId;
	private String fName;
	private String cId;
	private String dz;
	private String Pri;
	private int hSta;
	private String fPn;
	
	public String gethId() {
		return hId;
	}
	public void sethId(String hId) {
		this.hId = hId;
	}
	public String getfId() {
		return fId;
	}
	public void setfId(String fId) {
		this.fId = fId;
	}
	public String getPri() {
		return Pri;
	}
	public void setPri(String pri) {
		this.Pri = pri;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public int gethSta() {
		return hSta;
	}
	public void sethSta(int hSta) {
		this.hSta = hSta;
	}
	public String getfPn() {
		return fPn;
	}
	public void setfPn(String fPn) {
		this.fPn = fPn;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getDz() {
		return dz;
	}
	public void setDz(String dz) {
		this.dz = dz;
	}
	
	
}
