package tp4.tres;

public class SynchronizedObjectCounter {
    private int c = 0;
    private Object lock;

    public void increment(){
    synchronized (lock) {c++;} // Este elemento debe ser casteado a Integer
   
    }

        public void decrement() {
            synchronized (this) {
                c--;
            }
        }

        public int value() {
            return c;
        }

}
