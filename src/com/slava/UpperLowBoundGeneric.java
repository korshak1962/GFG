package com.slava;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UpperLowBoundGeneric {


    public static long totalExtends(List<? extends Number> list) {
        long count = 0;
        for (Number number : list)
            count += number.longValue();
        return count;
    }

    @Test
    public void testTotalUpper() {
        List<? extends Number> list = new ArrayList<>();
        //list.add(1);
        List<Integer> listInt = new ArrayList<>();
        listInt.add(1);
        List<Double> listDouble = new ArrayList<>();
        listDouble.add(1.1);

        System.out.println(totalExtends(listInt));
        System.out.println(totalExtends(listDouble));
    }


    public static long totalSuper(List<? super Number> list) {
        long count = 0;
        for (Object num : list){
            count += ((Number)num).longValue();
        System.out.println(num.getClass());}
        return count;
    }


    @Test
    public void testTotalSuper() {
        List<? super Number> list = new ArrayList<>();
        list.add(1);list.add(1.1);
        List<Integer> listInt = new ArrayList<>();
        listInt.add(1);
        List<Double> listDouble = new ArrayList<>();
        listDouble.add(1.1);

        System.out.println(totalSuper(list));
     //   System.out.println(totalSuper(listInt));
     //   System.out.println(totalSuper(listDouble));
    }

    @Test
    public void testTotalLow() {
        List<? super Number> list = new ArrayList<>();
        list.add(1);
        list.add(1.1);

        for (Object n : list) {
            System.out.println(n.getClass());
        }

    }

    class A {
        public String m1() {
            return "A";
        }
    }

    class B extends A {
        public String m2() {
            return "b";
        }
    }

    class C extends B {
        public String m3() {
            return "c";
        }
    }

    @Test
    public void testTotalLow1() {
        List<? super C> listC = new ArrayList<>();
        listC.add(new C());
        // listC.add(new B());
        List<? super A> listA = new ArrayList<>();
        listA.add(new C());
        listA.add(new B());
        // for (A n : listA) {
        //      System.out.println(n);
        //  }
        List<? super B> listB = new ArrayList<>();
        listB.add(new C());
        listB.add(new B());
        final A a = new A();
      //  listB.add(a);
        final Object o = new Object();
      //  listB.add(o);
        Iterator<? super B>  iter=listB.iterator();
        while (iter.hasNext()){System.out.println(((C)iter.next()).getClass());}
        //for (A n : (A)listB) {
         //   System.out.println(n.getClass());
       // }
    }

}
