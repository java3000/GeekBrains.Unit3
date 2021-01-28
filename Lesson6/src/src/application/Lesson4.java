package src.application;

import java.util.ArrayList;

//1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
// Используйте wait/notify/notifyAll.
//2. * На серверной стороне сетевого чата реализовать управление потоками через ExecutorService.
public class Lesson4 {

    public static void main(String[] args) {
        Printer p = new Printer();

        new Thread(() -> p.printX('A')).start();
        new Thread(() -> p.printX('B')).start();
        new Thread(() -> p.printX('C')).start();
    }

    public static class Printer {
        private final Object mon = new Object();
        private volatile char currentLetter = 'A';

        public void printX(char c) {
            ArrayList<Character> list = new ArrayList<>();
            list.add('A');
            list.add('B');
            list.add('C');

            synchronized (mon) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (currentLetter != c) {
                            mon.wait();
                        }
                        System.out.print(c);
                        currentLetter = list.get((list.indexOf(c) == 2) ? 0 : list.indexOf(c) + 1);
                        mon.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
