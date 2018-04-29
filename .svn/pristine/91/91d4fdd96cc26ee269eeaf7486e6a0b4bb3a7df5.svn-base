package test.com.pg;

import java.io.IOException;
import java.net.ServerSocket;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class TestBase
{
	@SuppressWarnings({ "unused", "resource" })
	@Before
	public void setUp()
	{
		 //实际场景中，上下文通过web.xml配置自动初始化
	 ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:spring/*.xml");

	}

}
