public class Task2 {
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        System.out.println(fizzBuzz.start());
    }
}

class FizzBuzz {
    private int maxNum;
    private int currentNum;
    private StringBuilder output;
    public FizzBuzz(int number) {
        if (number <= 0) return;
        this.maxNum = number;
        this.currentNum = 1;
        this.output = new StringBuilder();
    }

    private void fizz() {
        if (currentNum % 3 == 0 && currentNum % 5 != 0)
            output.append("fizz ");
    }

    private void buzz() {
        if (currentNum % 5 == 0 && currentNum % 3 != 0)
            output.append("buzz ");
    }

    private void fizzBuzz() {
        if (currentNum % 3 == 0 && currentNum % 5 == 0)
            output.append("fizzbuzz ");
    }

    private void number() {
        if (currentNum % 3 != 0 && currentNum % 5 != 0)
            output.append(currentNum + " ");
    }

    public String start() {
        // I haven't added any locks or synchronized keyword because there are already logic and only one thread at a time
        // will pass if condition and after all threads finish their job we increase current number by 1

        // There is other solutions I can add locks, but then thread will execute one after another as lock will be locked
        // and thread needs to wait before previous thread finish and there is no advantages of threads

        // And third solution is to divide array of numbers between 4 thread and then each thread will be
        // check designated part of an array, but its against homework description
        while (currentNum <= maxNum) {
            Thread a = new Thread() {
                @Override
                public void run() {
                    fizz();
                }
            };

            Thread b = new Thread() {
                @Override
                public void run() {
                    buzz();
                }
            };

            Thread c = new Thread() {
                @Override
                public void run() {
                    fizzBuzz();
                }
            };

            Thread d = new Thread() {
                @Override
                public void run() {
                    number();
                }
            };

            // start all threads at same time
            a.start();
            b.start();
            c.start();
            d.start();
            try {
                // wait while all threads finish job before increase current numebr
                a.join();
                b.join();
                c.join();
                d.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());;
            }
            currentNum++;
        }

        return output.toString();
    }
}