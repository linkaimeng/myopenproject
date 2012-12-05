package com.ylzinfo.msw.app.xm.siapp.util;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class XmlPrase {
	private static final Logger LOGGER = Logger.getLogger(XmlPrase.class);
	public static String praseXmlString(String filePath,String dmlb00,String dmlbmc){
		try {
			SAXReader reader = new SAXReader();
		 Document doc = reader.read(new File(filePath));

			
//			Document doc = DocumentHelper.parseText(xmlString);
			Element root = doc.getRootElement();
			List<Element> children = root.elements();
			StringBuffer sb = new StringBuffer();
			for(Element e:children){
				String code =  e.attributeValue("bh0000");
				String name = e.attributeValue("mc0000");
				sb.append("<insert tableName=\"SYS_CODE\">\n");
				sb.append("<column name=\"DMLB00\" value=\""+dmlb00+"\" />\n");
				sb.append("<column name=\"DMLBMC\" value=\""+dmlbmc+"\" />\n");
				sb.append("<column name=\"DMZ000\" value=\""+code+"\"/>\n");
				sb.append("<column name=\"DMZMC0\" value=\""+name+"\"/>\n");
				sb.append("<column name=\"SFKWH0\" value=\"1\"/>\n</insert>\n");
			}
			
		} catch (DocumentException e) {
			LOGGER.error(e,e);
		}
		
		return null;
	}

	/**
	 * 第一个参数是需要转换的xml文件路径，第二个参数是DMLB00值，第三个是DMLBMC值
	 * 测试的test.xml文件内容是(注意需要加这个xml标签头)
	 * <?xml version="1.0" encoding="gbk"?>
	 * <code dmlb00="yyjgdm">
	 *    <list bh0000="6"  mc0000="中山医院总部"     py0000="979992"  wb0000="784789"/> 
	 *    <list bh0000="7"  mc0000="金榜分部"         py0000="5232"    wb0000="784789"/> 
	 *    <list bh0000="9"  mc0000="湖里分部"         py0000="4532"    wb0000="784799"/> 
	 * </code>
	 * 
	 * @param args
	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		praseXmlString("D:\\test.xml","ZYGZ00","择业工种(厦门)");
//	}

}
