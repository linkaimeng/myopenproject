package com.ylzinfo.msw.app.xm.siapp.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ylzinfo.msw.app.xm.siapp.esb.request.XMYiBaoEsbRequest;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TemplateUtil {
	public static void main(String[] args) {
		
		String filePath = TemplateUtil.class.getResource("").toString().substring(6);
		Configuration cfg = new Configuration();
		try {
			cfg.setDirectoryForTemplateLoading(new File(filePath));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			Template temp = cfg.getTemplate("test.ftl","utf-8");
			/*  创建数据模型  */
			Map root = new HashMap();
			
			Class<?> c = XMYiBaoEsbRequest.class;
			String importStr = c.getName();
			String cName = c.getSimpleName();
			Method[] methodArray = c.getDeclaredMethods();
			List<Map<String,String>> spList = new ArrayList<Map<String,String>>();
			for(Method m :methodArray){
				
				if(m.getParameterTypes().length>1){
					
					Map<String,String> spMap = new HashMap<String,String>();
					//TODO
					StringBuffer strSp = new StringBuffer();
					strSp.append("DQBH00");
					for(int i=0;i<m.getParameterTypes().length-1;i++){
						strSp.append(",\"\"");
					}
					spMap.put(m.getName(), strSp.toString());
					spList.add(spMap);
				}
			}
			root.put("importStr", importStr);
			root.put("cName", cName);
			root.put("spList", spList);
			
			/*  将模板和数据模型合并  */
			 FileOutputStream fos = new FileOutputStream("./"+cName+"Test.java");     
			 Writer out = new OutputStreamWriter(fos, "UTF-8");     

			temp.process(root, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
}
