package cn.edu.lingnan.test;

import cn.edu.lingnan.dao.FownerDAO;
import cn.edu.lingnan.dto.Fowner;

public class text {
	public static void main(String[] args) {
		Fowner f = new Fowner();
		FownerDAO fn = new FownerDAO();
//		f.setfId("f05");
//		f.setfName("aaa");
//		f.setfPassword("aaa");
//		f.setfSuper(2);
//		f.setfPn(66666);
//		System.out.println(fn.inserInfoToFowner(f));
  //É¾³ý      fn.deleteFowner("f01");
		f.setfName("aaa");
		fn.updataFowner(f);
	}
		

}
