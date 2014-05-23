package com.dev.saurabh;

import java.util.Vector;

/**
 * Created by ssaurab on 2/2/14.
 */
public class LinkedList {

    public static void main(String [] args){
        Vector  tasks = new Vector<Integer>();
        tasks.add(1);
        tasks.add(17);
        tasks.add(5);
        tasks.add(7);
        tasks.add(8);
        tasks.add(9);

       B d = new B();
    }


  static class A{


      public A(){
          System.out.println("a1");
      }
  }


   static  class B extends  A{
        static {
            System.out.println("b1");
        }

       B(){
           System.out.println("b2");
       }

       {
           System.out.println("b3");
       }

    }

}
