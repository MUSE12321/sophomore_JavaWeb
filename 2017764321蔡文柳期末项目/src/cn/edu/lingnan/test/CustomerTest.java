package cn.edu.lingnan.test;

import cn.edu.lingnan.dao.CustomerDAO;
import cn.edu.lingnan.dto.Customer;

public class CustomerTest {
	public static void main(String[] args) {
		Customer c = new Customer();
		CustomerDAO cd = new CustomerDAO();
//		cd.delete("c01");
		c.setcId("c06");
		c.setcName("c06");
		c.setcPassword("c06");
		c.setcSuper(2);
		c.setcPn("6");
	System.out.println(cd.insert(c));
		
	}
	

}
