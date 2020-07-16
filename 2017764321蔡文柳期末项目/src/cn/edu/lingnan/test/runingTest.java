package cn.edu.lingnan.test;

import java.util.Scanner;
import java.util.Vector;

import cn.edu.lingnan.dao.FownerDAO;
import cn.edu.lingnan.dao.HouseDAO;
import cn.edu.lingnan.dto.Fowner;
import cn.edu.lingnan.dto.House;



	class Menu{
//		void SelectMenu(){
//			System.out.println("1.查询房子信息");
//		    System.out.println("2.查询房东信息");
//		    System.out.println("3.查询租客信息");
//		    System.out.println("4.返回上一步");
//		    System.out.println("输入操作序号：");
//		}
		void SelectHouseMenu(){
		    System.out.println("1.查看全部房子信息");
		    System.out.println("输入操作序号：");
		}
		void SelectFownerMenu(){
			System.out.println("1.按用户名和密码查找");
		    System.out.println("2.查看全部房东信息");
		    System.out.println("输入操作序号：");
		}
		
//		void SelectChooseMenu(){
//			System.out.println("1.查看全部房子表信息");
//		    System.out.println("2.按房东id查找");
//		    System.out.println("3.查看全部房东表信息");
//		    System.out.println("输入操作序号：");
//		}
		
//		void UpdateMenu(){
//			System.out.println("1.修改学生信息");
//		    System.out.println("2.修改社团信息");
//		    System.out.println("3.修改学生社团表");
//		    System.out.println("4.返回上一步");
//		    System.out.println("输入操作序号：");
//		}
		void UpdateHouseMenu(){
			System.out.println("1.增加房子信息");
		    System.out.println("2.删除房子信息");
		    System.out.println("输入操作序号：");
		}
		void UpdateFownerMenu(){
			System.out.println("1.增加房东信息");
		    System.out.println("2.删除房东信息");
		    System.out.println("3.更改房东信息");
		    System.out.println("输入操作序号：");
		}
		void UpdateChooseMenu(){
			System.out.println("1.增加房子房东表");
		    System.out.println("2.删除房子房东表");
		    System.out.println("3.更改房子房东表");
		    System.out.println("输入操作序号：");
		}
		void MainMenu(){
		    System.out.println("1.查看房子信息");
		    System.out.println("2.查看房东信息");
		    System.out.println("3.查看房子房东表");
		    System.out.println("4.更新房子信息");
		    System.out.println("5.更新房东信息");
		    System.out.println("6.更新房子房东表");
		    System.out.println("7.退出系统");
		    System.out.println("输入操作序号：");
		}
	}

	public class runingTest {
	    public static void main(String[] args) {
	    	Menu m = new Menu();
		    Scanner sc = new Scanner(System.in);
		    HouseDAO hd = new HouseDAO();
		    House h = new House();
		    FownerDAO fd = new FownerDAO();
		    Fowner f = new Fowner();
//		    ChooseDAO sd = new ChooseDAO();
//		    Choose s = new Choose();
		    System.out.println("/**********房屋租赁管理系统**********/");
		    m.MainMenu();
		    int a = sc.nextInt();
		    switch(a){
		    case 1:
		    	m.SelectHouseMenu();
		    	int b1 = sc.nextInt();
		    	switch(b1){
		    	case 1:
//		    		System.out.println("请输入学生姓名：");
//		    		String str1 = sc.next();
//		    		System.out.println("请输入密码：");
//		    		String str2 = sc.next();
//		    		hd.findHouseByNameAndPassword(str1, str2);
		  //  		System.out.println(pd.findPersonByNameAndPassword(str1, str2));
//		    		//按学生姓名和密码查找
//		    		break;
		    	case 2:
		    		Vector<House> v = new Vector<House>();
		        	v = hd.findAllHouse();
		        	for(House plist:v){
		        		System.out.println(plist.gethId()+" "+plist.getfId()+" "
		        	       +plist.getcId()+" "+plist.getDz()+" "+plist.getPri()+" "+plist.gethSta());
		        	}
		    		//查找全部
		    		break;
		    	}
		    	break;
		    case 2:
		    	m.SelectFownerMenu();
		    	int b2 = sc.nextInt();
		    	switch(b2){
		    	case 1:
		    		System.out.println("请输入房东姓名和密码：");
		    		String str1 = sc.next();
		    		String str2 = sc.next();
//出错		    		fd.findFownerByNameAndPassword(str1, str2);
		    		//按社团名称
		    		break;
		    	
		    	}
		    	break;
//		    case 3:
//		    	m.SelectChooseMenu();
//		    	int b3 = sc.nextInt();
//		    	switch(b3){
//		    	case 1:
//		    		System.out.println("请输入学生姓名：");
//		    		String str1 = sc.next();
//		    		sd.findChooseBypName(str1);
//		    		//按学生姓名
//		    		break;
//		    	case 2:
//		    		System.out.println("请输入学生id：");
//		    		String str2 = sc.next();
//		    		sd.findChooseBypId(str2);
//		    		//按学生id
//		    	case 3:
//		    		System.out.println("请输入社团名称：");
//		    		String str3 = sc.next();
//		    		sd.findChooseBycName(str3);
//		    		//按社团名称
//		    	case 4:
//		    		System.out.println("请输入社团id：");
//		    		String str4 = sc.next();
//		    		sd.findChoosecById(str4);
//		    		//按社团id
//		    	case 5:
//		    		Vector<Choose> v = new Vector<Choose>();
//		        	v = sd.findAllChoose();
//		        	for(Choose slist:v){
//		        		System.out.println(slist.getpId()+" "+slist.getpName()+" "+slist.getcId()+" "+slist.getcName());
//		        	}
//		    		//全部
//		    		break;
//		    	}
//		    	break;
		    case 3:
		    	m.UpdateHouseMenu();
		    	int b4 = sc.nextInt();
		    	switch(b4){
		    	case 1:
		        	System.out.println("请输入房子id：");
		        	String str1 = sc.next();
		        	System.out.println("请输入房东id：");
		        	String str2 = sc.next();
		        	System.out.println("请输入租客id（可为空）：");
		        	String str3 = sc.next();
		        	System.out.println("请输入房子地址：");
		        	String str4 = sc.next();
		        	System.out.println("请输入租金价格：");
		        	String str5 = sc.next();
		        	System.out.println("请输入房子状态：");
		        	int str6 = sc.nextInt();
		        	h.sethId(str1);
		        	h.setfId(str2);
		        	h.setcId(str3);
		        	h.setDz(str4);
		        	h.setPri(str5);
		        	h.sethSta(str6);
		        	hd.insertInfoToHouse(h);
		        	System.out.println("添加成功！");
		    		//增加
		    		break;
		    	case 2:
		    		System.out.println("请输入要删除的房子id：");
		    		String str7 = sc.next();
		        	hd.deleteHouse(str7);
		        	System.out.println("删除成功！");
		    		//删除
		    		break;
		    	case 3:
		    		System.out.println("请输入要修改的房子id：");
		    		String str9 = sc.next();
		        	h.sethId(str9);
		        	System.out.println("请输入修改后的房东id：");
		        	String str8 = sc.next();
		        	h.setfId(str8);
		        	hd.updateHouse(h);
		    	    //更新
		    		break;
		    	}
		    	break;
		    case 5:
		    	m.UpdateFownerMenu();
		    	int b5 = sc.nextInt();
		    	switch(b5){
		    	case 1:
		        	System.out.println("请输入房东名字：");
		        	String str1 = sc.next();
		        	System.out.println("请输入房东id：");
		        	String str2 = sc.next();
		        	f.setfName(str1);
		        	f.setfId(str2);
		        	fd.inserInfoToFowner(f);
		        	System.out.println("添加成功！");
		    		//增加
		    		break;
		    	case 2:
		    		System.out.println("请输入要删除的房东id：");
		    		String str3 = sc.next();
		        	fd.deleteFowner(str3);
		        	System.out.println("删除成功！");
		    		//删除
		    		break;
		    	case 3:
		    		System.out.println("请输入要修改的房东姓名：");
		    		String str4 = sc.next();
		        	f.setfId(str4);
		        	System.out.println("请输入修改后的房东id：");
		        	String str5 = sc.next();
		        	f.setfName(str5);
		        	fd.updataFowner(f);
		    	    //更新
		    		break;
		    	}
		    	break;
//		    case 6:
//		    	m.UpdateChooseMenu();
//		    	int b6 = sc.nextInt();
//		    	switch(b6){
//		    	case 1:
//		        	System.out.println("请输入学生id：");
//		        	String str1 = sc.next();
//		        	System.out.println("请输入学生姓名：");
//		        	String str2 = sc.next();
//		        	System.out.println("请输入社团id：");
//		        	String str3 = sc.next();
//		        	System.out.println("请输入社团名称：");
//		        	String str4 = sc.next();
//		        	s.setpId(str1);
//		        	s.setpName(str2);
//		        	s.setcId(str3);
//		        	s.setcName(str4);
//		        	sd.insertInfoToChoose(s);
//		        	System.out.println("添加成功！");
//		    		//增加
//		    		break;
//		    	case 2:
//		    		System.out.println("请输入要删除的学生id：");
//		    		String str5 = sc.next();
//		        	sd.deleteChoose(str5);
//		        	System.out.println("删除成功！");
//		    		//删除
//		    		break;
//		    	case 3:
//		    		System.out.println("请输入要修改的学生id：");
//		    		String str6 = sc.next();
//		        	s.setpId(str6);
//		        	System.out.println("请输入修改后的社团id：");
//		        	String str7 = sc.next();
//		        	s.setcId(str7);
//		        	System.out.println("请输入修改后的社团名称：");
//		        	String str8 = sc.next();
//		        	s.setcName(str8);
//		        	sd.updateChoose(s);
//		    	    //更新
//		    		break;
//		    	}
//		    	break;
		    case 7:
		    	System.out.println("已退出系统！");
		    	break;
		    }
		    if(sc!=null){
				sc.close();
		        sc = null;
		    }
	    }
	}



