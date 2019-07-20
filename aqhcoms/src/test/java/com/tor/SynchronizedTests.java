package com.tor;

public class SynchronizedTests{
    /** 计数器 **/
    private static int count = 0;

    /**
     * synchronized 修饰一个代码块,被修饰的代码块称为同步语句块，作用范围是大括号包裹的代码，作用的对象是调用这个代码块的对象。
     */
    public static class SyncThread implements Runnable{
        public void run(){
            synchronized (this){
                for(int i = 0;i < 5;i++){
                    try {
                        System.out.println(Thread.currentThread().getName() + ":" + count++);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SyncThread syncThread = new SyncThread();
        Thread thread_1 = new Thread(syncThread, "syncThread_1");
        Thread thread_2 = new Thread(syncThread, "syncThread_2");
        thread_1.start();
        thread_2.start();
    }
}
