package cn.edu.lingnan.test;

import java.util.HashMap;

import cn.edu.lingnan.util.XmlParser;
import cn.edu.lingnan.util.XmlValidator;

public class TestXML {

	public static void main(String[] args) {
//		String xmlPath = "src//database.conf.xml";//无法读取文件是因为文件不在201701的根目录下，
//		String xsdPath ="src//database.conf.xsd";//而是在scr,故要加src//
		String xmlPath = "database.conf.xml";//配一个路径
		String xsdPath ="database.conf.xsd";
		if(XmlValidator.validate(xmlPath, xsdPath)){
			HashMap<String,String> hm = XmlParser.parser(xmlPath);
	        System.out.println(hm.get("driver"));
	        System.out.println(hm.get("password"));
		}
	}

}
