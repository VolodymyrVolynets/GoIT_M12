public class Task1 {
    public static void main(String[] args) {
        long timeFromStartApp = System.currentTimeMillis();
        new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(1000);
                    System.out.println((System.currentTimeMillis() - timeFromStartApp) / 1000 + " секунд");
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());;
                }
            }
        }).start();

        new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(5000);
                    System.out.println("Минуло 5 секунд");
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());;
                }
            }
        }).start();
    }
}
