package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.function.Function;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.alibaba.fastjson.JSON;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {})
public class ZipkinServiceApplicationTests<E> {

  @Test
  public void contextLoads() {

    ArrayList<String> list = new ArrayList<String>(1);

    list.add("");

    list.set(0, "1");

    System.out.println(list);

  }

  @Test
  public void testTryCatchRunTimeException() {
    try {
      throw new RuntimeException("test");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }

  @Test
  public void testMethodWithCheck() {
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    String res = methodWithCheck(String::new, "11", "22");
    Object methodWithCheck = methodWithCheck(JSON::parse, "11", "22");
    System.out.println(res);
    System.out.println(methodWithCheck);
  }

  @Test
  public void testBitOption() {
    int option_1 = 1;
    int option_2 = 2;
    int option_3 = 4;
    int option_4 = 8;
    int option_5 = 16;
    int option = 20;
    System.out.println(option & option_1);
    System.out.println(option & option_2);
    System.out.println(option & option_3);
    System.out.println(option & option_4);
    System.out.println(option & option_5);
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
