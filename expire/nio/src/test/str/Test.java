/**
 * =========================================== 
 *          Copyright (C) 2018 Finanstar 
 *               All rights reserved
 *
 * 项 目 名： deri-web 
 * 文 件 名： Test.java 
 * 版本信息： V1.0.0 
 * 作 者： Daniel Pine 
 * 日 期： 2018年7月4日-下午3:59:57
 * 
 * ============================================
 */


package test.str;

import java.util.ArrayList;
import java.util.List;

/**
 * @描 述：
 * @author:Daniel Pine
 * @联系方式:danielpine@sina.com
 * @date :2018年7月4日-下午3:59:57
 */
public class Test {



  public static void main(String[] args) {
    String str = "0123456789abcdefghijklmnop哈哈";



    char[] charArray = str.toCharArray();


    for (int i = 0; i < charArray.length / 2; i++) {
      char tmp = charArray[i];
      charArray[i] = charArray[charArray.length - i - 1];
      charArray[charArray.length - i - 1] = tmp;
    }


    int sum;

    for (int i = 0; i < charArray.length / 2; i++) {
      sum = charArray[i] + charArray[charArray.length - i - 1];
      charArray[i] = (char) (sum - charArray[i]);
      charArray[charArray.length - i - 1] = (char) (sum - charArray[charArray.length - i - 1]);
    }

    String reverseStr = new String(charArray);
    System.out.println(reverseStr);
    System.out.println((char)('a' + 1));

    // a-z
    // 0-9
    // len 3

    int len = 3;

    List<Integer> cl = new ArrayList<>();


    /*for (int i = 'a'; i <= 'z'; i++) {
      cl.add(i);
    }
    for (int i = 'A'; i <= 'Z'; i++) {
      cl.add(i);
    }*/
    for (int i = '0'; i <= '1'; i++) {
      cl.add(i);
    }
   

    List<String> pwd = new ArrayList<>();

    System.out.println(cl);
    System.out.println(cl);
    System.out.println(cl);
    System.out.println(cl);
    for (Integer a : cl) {

      for (Integer b : cl) {

        for (Integer c : cl) {

          for (Integer d : cl) {
            pwd.add("" + (char) (int) a + (char) (int) b + (char) (int) c + (char) (int) d);
          }
        }
      }
    }

    System.out.println(pwd.size());



  }

}
