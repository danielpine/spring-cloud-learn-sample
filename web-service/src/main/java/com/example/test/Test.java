/**
 * ===========================================
 *
 * Copyright (C) 2019 Codehelper
 *
 * All rights reserved
 *
 * 项 目 名： codehelper-be
 *
 * 文 件 名： Test.java
 *
 * 版本信息： V1.0.0
 *
 * 作 者： Daniel Pine
 *
 * 日 期： 2019年5月20日-下午6:56:53
 * 
 * ============================================
 */


package com.example.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @description ：
 * @author :Daniel Pine
 * @email :danielpine@sina.com
 * @date :2019年5月20日-下午6:56:53
 */
public class Test {
  public static void main(String[] args) throws UnknownHostException {
    InetAddress[] allByName = InetAddress.getAllByName("m.im.jh");
    for (InetAddress inetAddress : allByName) {
      System.out.println(inetAddress.getHostAddress());
    }
  }
}
