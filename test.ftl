package com.ylzinfo.msw.app.xm.siapp.esb.request;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ${importStr};
import com.ylzinfo.msw.esb.support.domain.EsbRequest;
import com.ylzinfo.msw.esb.support.service.EsbServiceFactory;
import com.ylzinfo.msw.esb.support.service.QueryListService;

public class ${cName}Test {
	
	private static final String DQBH00 = "350500";
	ClassPathXmlApplicationContext context = null;
	EsbServiceFactory esf = null;
	
	@Before
	public void init() throws Exception{
		context = new ClassPathXmlApplicationContext("spring/applicationContext-esbClient.xml");
		esf = (EsbServiceFactory) context.getBean("esbServiceFactory");
	}
	
	<#list spList as map>
 <#list map?keys as itemKey>
    @Test
	public void test${itemKey?cap_first}() throws Exception{
		EsbRequest sr = ${cName}.${itemKey}(${map[itemKey]});
	    QueryListService ql = esf.getServiceWithRequest(sr, QueryListService.class);
		System.out.println(ql.getTotalCounts());
		System.out.println(ql.getQueryList());
	}
	
 </#list>
</#list> 
	

}
