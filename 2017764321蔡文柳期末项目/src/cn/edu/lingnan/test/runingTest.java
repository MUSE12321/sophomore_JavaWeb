package cn.edu.lingnan.test;

import java.util.Scanner;
import java.util.Vector;

import cn.edu.lingnan.dao.FownerDAO;
import cn.edu.lingnan.dao.HouseDAO;
import cn.edu.lingnan.dto.Fowner;
import cn.edu.lingnan.dto.House;



	class Menu{
//		void SelectMenu(){
//			System.out.println("1.��ѯ������Ϣ");
//		    System.out.println("2.��ѯ������Ϣ");
//		    System.out.println("3.��ѯ�����Ϣ");
//		    System.out.println("4.������һ��");
//		    System.out.println("���������ţ�");
//		}
		void SelectHouseMenu(){
		    System.out.println("1.�鿴ȫ��������Ϣ");
		    System.out.println("���������ţ�");
		}
		void SelectFownerMenu(){
			System.out.println("1.���û������������");
		    System.out.println("2.�鿴ȫ��������Ϣ");
		    System.out.println("���������ţ�");
		}
		
//		void SelectChooseMenu(){
//			System.out.println("1.�鿴ȫ�����ӱ���Ϣ");
//		    System.out.println("2.������id����");
//		    System.out.println("3.�鿴ȫ����������Ϣ");
//		    System.out.println("���������ţ�");
//		}
		
//		void UpdateMenu(){
//			System.out.println("1.�޸�ѧ����Ϣ");
//		    System.out.println("2.�޸�������Ϣ");
//		    System.out.println("3.�޸�ѧ�����ű�");
//		    System.out.println("4.������һ��");
//		    System.out.println("���������ţ�");
//		}
		void UpdateHouseMenu(){
			System.out.println("1.���ӷ�����Ϣ");
		    System.out.println("2.ɾ��������Ϣ");
		    System.out.println("���������ţ�");
		}
		void UpdateFownerMenu(){
			System.out.println("1.���ӷ�����Ϣ");
		    System.out.println("2.ɾ��������Ϣ");
		    System.out.println("3.���ķ�����Ϣ");
		    System.out.println("���������ţ�");
		}
		void UpdateChooseMenu(){
			System.out.println("1.���ӷ��ӷ�����");
		    System.out.println("2.ɾ�����ӷ�����");
		    System.out.println("3.���ķ��ӷ�����");
		    System.out.println("���������ţ�");
		}
		void MainMenu(){
		    System.out.println("1.�鿴������Ϣ");
		    System.out.println("2.�鿴������Ϣ");
		    System.out.println("3.�鿴���ӷ�����");
		    System.out.println("4.���·�����Ϣ");
		    System.out.println("5.���·�����Ϣ");
		    System.out.println("6.���·��ӷ�����");
		    System.out.println("7.�˳�ϵͳ");
		    System.out.println("���������ţ�");
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
		    System.out.println("/**********�������޹���ϵͳ**********/");
		    m.MainMenu();
		    int a = sc.nextInt();
		    switch(a){
		    case 1:
		    	m.SelectHouseMenu();
		    	int b1 = sc.nextInt();
		    	switch(b1){
		    	case 1:
//		    		System.out.println("������ѧ��������");
//		    		String str1 = sc.next();
//		    		System.out.println("���������룺");
//		    		String str2 = sc.next();
//		    		hd.findHouseByNameAndPassword(str1, str2);
		  //  		System.out.println(pd.findPersonByNameAndPassword(str1, str2));
//		    		//��ѧ���������������
//		    		break;
		    	case 2:
		    		Vector<House> v = new Vector<House>();
		        	v = hd.findAllHouse();
		        	for(House plist:v){
		        		System.out.println(plist.gethId()+" "+plist.getfId()+" "
		        	       +plist.getcId()+" "+plist.getDz()+" "+plist.getPri()+" "+plist.gethSta());
		        	}
		    		//����ȫ��
		    		break;
		    	}
		    	break;
		    case 2:
		    	m.SelectFownerMenu();
		    	int b2 = sc.nextInt();
		    	switch(b2){
		    	case 1:
		    		System.out.println("�����뷿�����������룺");
		    		String str1 = sc.next();
		    		String str2 = sc.next();
//����		    		fd.findFownerByNameAndPassword(str1, str2);
		    		//����������
		    		break;
		    	
		    	}
		    	break;
//		    case 3:
//		    	m.SelectChooseMenu();
//		    	int b3 = sc.nextInt();
//		    	switch(b3){
//		    	case 1:
//		    		System.out.println("������ѧ��������");
//		    		String str1 = sc.next();
//		    		sd.findChooseBypName(str1);
//		    		//��ѧ������
//		    		break;
//		    	case 2:
//		    		System.out.println("������ѧ��id��");
//		    		String str2 = sc.next();
//		    		sd.findChooseBypId(str2);
//		    		//��ѧ��id
//		    	case 3:
//		    		System.out.println("�������������ƣ�");
//		    		String str3 = sc.next();
//		    		sd.findChooseBycName(str3);
//		    		//����������
//		    	case 4:
//		    		System.out.println("����������id��");
//		    		String str4 = sc.next();
//		    		sd.findChoosecById(str4);
//		    		//������id
//		    	case 5:
//		    		Vector<Choose> v = new Vector<Choose>();
//		        	v = sd.findAllChoose();
//		        	for(Choose slist:v){
//		        		System.out.println(slist.getpId()+" "+slist.getpName()+" "+slist.getcId()+" "+slist.getcName());
//		        	}
//		    		//ȫ��
//		    		break;
//		    	}
//		    	break;
		    case 3:
		    	m.UpdateHouseMenu();
		    	int b4 = sc.nextInt();
		    	switch(b4){
		    	case 1:
		        	System.out.println("�����뷿��id��");
		        	String str1 = sc.next();
		        	System.out.println("�����뷿��id��");
		        	String str2 = sc.next();
		        	System.out.println("���������id����Ϊ�գ���");
		        	String str3 = sc.next();
		        	System.out.println("�����뷿�ӵ�ַ��");
		        	String str4 = sc.next();
		        	System.out.println("���������۸�");
		        	String str5 = sc.next();
		        	System.out.println("�����뷿��״̬��");
		        	int str6 = sc.nextInt();
		        	h.sethId(str1);
		        	h.setfId(str2);
		        	h.setcId(str3);
		        	h.setDz(str4);
		        	h.setPri(str5);
		        	h.sethSta(str6);
		        	hd.insertInfoToHouse(h);
		        	System.out.println("��ӳɹ���");
		    		//����
		    		break;
		    	case 2:
		    		System.out.println("������Ҫɾ���ķ���id��");
		    		String str7 = sc.next();
		        	hd.deleteHouse(str7);
		        	System.out.println("ɾ���ɹ���");
		    		//ɾ��
		    		break;
		    	case 3:
		    		System.out.println("������Ҫ�޸ĵķ���id��");
		    		String str9 = sc.next();
		        	h.sethId(str9);
		        	System.out.println("�������޸ĺ�ķ���id��");
		        	String str8 = sc.next();
		        	h.setfId(str8);
		        	hd.updateHouse(h);
		    	    //����
		    		break;
		    	}
		    	break;
		    case 5:
		    	m.UpdateFownerMenu();
		    	int b5 = sc.nextInt();
		    	switch(b5){
		    	case 1:
		        	System.out.println("�����뷿�����֣�");
		        	String str1 = sc.next();
		        	System.out.println("�����뷿��id��");
		        	String str2 = sc.next();
		        	f.setfName(str1);
		        	f.setfId(str2);
		        	fd.inserInfoToFowner(f);
		        	System.out.println("��ӳɹ���");
		    		//����
		    		break;
		    	case 2:
		    		System.out.println("������Ҫɾ���ķ���id��");
		    		String str3 = sc.next();
		        	fd.deleteFowner(str3);
		        	System.out.println("ɾ���ɹ���");
		    		//ɾ��
		    		break;
		    	case 3:
		    		System.out.println("������Ҫ�޸ĵķ���������");
		    		String str4 = sc.next();
		        	f.setfId(str4);
		        	System.out.println("�������޸ĺ�ķ���id��");
		        	String str5 = sc.next();
		        	f.setfName(str5);
		        	fd.updataFowner(f);
		    	    //����
		    		break;
		    	}
		    	break;
//		    case 6:
//		    	m.UpdateChooseMenu();
//		    	int b6 = sc.nextInt();
//		    	switch(b6){
//		    	case 1:
//		        	System.out.println("������ѧ��id��");
//		        	String str1 = sc.next();
//		        	System.out.println("������ѧ��������");
//		        	String str2 = sc.next();
//		        	System.out.println("����������id��");
//		        	String str3 = sc.next();
//		        	System.out.println("�������������ƣ�");
//		        	String str4 = sc.next();
//		        	s.setpId(str1);
//		        	s.setpName(str2);
//		        	s.setcId(str3);
//		        	s.setcName(str4);
//		        	sd.insertInfoToChoose(s);
//		        	System.out.println("��ӳɹ���");
//		    		//����
//		    		break;
//		    	case 2:
//		    		System.out.println("������Ҫɾ����ѧ��id��");
//		    		String str5 = sc.next();
//		        	sd.deleteChoose(str5);
//		        	System.out.println("ɾ���ɹ���");
//		    		//ɾ��
//		    		break;
//		    	case 3:
//		    		System.out.println("������Ҫ�޸ĵ�ѧ��id��");
//		    		String str6 = sc.next();
//		        	s.setpId(str6);
//		        	System.out.println("�������޸ĺ������id��");
//		        	String str7 = sc.next();
//		        	s.setcId(str7);
//		        	System.out.println("�������޸ĺ���������ƣ�");
//		        	String str8 = sc.next();
//		        	s.setcName(str8);
//		        	sd.updateChoose(s);
//		    	    //����
//		    		break;
//		    	}
//		    	break;
		    case 7:
		    	System.out.println("���˳�ϵͳ��");
		    	break;
		    }
		    if(sc!=null){
				sc.close();
		        sc = null;
		    }
	    }
	}



