package com.yzd.netty.bytebuf;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/***
 *
 * @author : yanzhidong
 * @date : 2019/11/15 
 * @version : V1.0
 *
 */
public class AtomicUpdaterTest {

    public static void main(String[] args) {

        Person person = new Person();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(person.age++);
            });
            thread.start();
        }


        AtomicIntegerFieldUpdater<Person> atomicIntegerFieldUpdater=AtomicIntegerFieldUpdater
                .newUpdater(Person.class,"age");

        Person person2 = new Person();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(atomicIntegerFieldUpdater.getAndIncrement(person2));
            });
            thread.start();
        }



    }
}

class Person {
    volatile int age = 1;


}