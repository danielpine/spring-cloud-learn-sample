package com.example.configservice;

import java.text.SimpleDateFormat;
import java.util.function.Function;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.alibaba.fastjson.JSON;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigServiceApplicationTests {

	@Test
	public void contextLoads() {
	}
	  @Test
	  public void testMethodWithCheck() {
	    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	    String res = methodWithCheck(String::new, "11", "22");
	    String methodWithCheck = methodWithCheck(JSON::toJSONString, "11{}", "22");
	    System.out.println(res);
	    System.out.println(methodWithCheck);
	  }



	  public <T, R> R methodWithCheck(Function<T, R> method, T t, String message) {
	    try {
	      return method.apply(t);
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	      e.printStackTrace();
	      throw new RuntimeException("test");
	    }
	  }
}
