package com.example.practice;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Capabilities {

  public static void main(String[] args) {
    List<Integer> values = Arrays.asList(1, 2, 3, 5, 6, 8, 6, 4, 5, 6);
    values.forEach(System.out::println);
    values.forEach(new Consumer<Integer>() {
      @Override
      public void accept(Integer value) {
        System.out.println(value);
      }
    });
    values.forEach((Integer v) -> System.out.println(v));
    values.forEach((v) -> System.out.println(v));
    System.out.println(values.stream().map(e -> e * 2).reduce((c, e) -> c + e).orElse(0));
    System.out.println(sum(values, e -> true));
    System.out.println(sum(values, e -> e % 2 == 0));
    System.out.println(sum(values, e -> e % 2 != 0));
    System.out.println("==========================================");
    System.out.println(values.stream().filter(e -> e > 3).filter(Java8Capabilities::isEven)
        .map(Java8Capabilities::map).findFirst().orElse(0));
    System.out.println("==========================================");
    List<Integer> list =
        Stream.of(1, 2, 3, 4).map(Java8Capabilities::map).collect(Collectors.toList());
    System.out.println(list);
  }

  public static int sum(Collection<Integer> nums, Predicate<Integer> selector) {
    return nums.stream().filter(selector).reduce(0, (a, b) -> (a + b));
  }

  public static int i = 0;

  public static int map(Integer e) {
    System.out.println(i++);
    return e % 2 == 0 ? e + 3 : e * 3;
  }

  public static boolean isEven(Integer e) {
    System.out.println(i++);
    return e % 2 == 0;
  }
}
