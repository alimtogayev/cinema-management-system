class NumbersThread extends Thread {
    private int from;
    private int to;

    @Override
    public void run() {
        // implement the method
        for (int i = from; i <= to; i++) {
            System.out.println(i);
        }

    }
    public NumbersThread(int from, int to) {
        // implement the constructor
        this.from = from;
        this.to = to;
    }

    // you should override some method here
}