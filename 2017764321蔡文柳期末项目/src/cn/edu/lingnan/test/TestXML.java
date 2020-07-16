package cn.edu.lingnan.test;

import java.util.HashMap;

import cn.edu.lingnan.util.XmlParser;
import cn.edu.lingnan.util.XmlValidator;

public class TestXML {

	public static void main(String[] args) {
//		String xmlPath = "src//database.conf.xml";//�޷���ȡ�ļ�����Ϊ�ļ�����201701�ĸ�Ŀ¼�£�
//		String xsdPath ="src//database.conf.xsd";//������scr,��Ҫ��src//
		String xmlPath = "database.conf.xml";//��һ��·��
		String xsdPath ="database.conf.xsd";
		if(XmlValidator.validate(xmlPath, xsdPath)){
			HashMap<String,String> hm = XmlParser.parser(xmlPath);
	        System.out.println(hm.get("driver"));
	        System.out.println(hm.get("password"));
		}
	}

}
