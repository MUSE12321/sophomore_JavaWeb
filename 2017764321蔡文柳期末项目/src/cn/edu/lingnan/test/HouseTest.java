package cn.edu.lingnan.test;

import cn.edu.lingnan.dao.HouseDAO;

import cn.edu.lingnan.dto.House;

public class HouseTest {
	public static void main(String[] args) {
		HouseDAO hd = new HouseDAO();
		House h = new House();
		h.sethId("hA06");
		h.setfId("f04");
		h.setfName("уехЩ");
		h.setcId("c04");
		h.setDz("linganna");
		h.setPri("5000");
		h.sethSta(5);
		h.setfPn("666666");
		
	//  HouseDAO hd = new HouseDAO();
//		hd.deleteHouse("h01");
		
		System.out.println(hd.insertInfoToHouse(h));
//		System.out.println(hd.updateHouse(h));
		
	}
	

}
