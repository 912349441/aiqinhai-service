public class DemoLock {
    private static String obj1 = "obj1";
    private static String obj2 = "obj2";

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Lock1 running");
                synchronized (DemoLock.obj1){
                    try {
                        System.out.println("Lock1 get obj1");
                        Thread.sleep(3000);
                        synchronized (DemoLock.obj2){
                            System.out.println("Lock1 get obj2");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Lock2 running");
                synchronized (DemoLock.obj2){
                    try {
                        System.out.println("Lock2 get obj2");
                        Thread.sleep(3000);
                        synchronized (DemoLock.obj1){
                            System.out.println("Lock2 get obj1");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}