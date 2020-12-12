package Gas;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class GasPool {
    private static ReadWriteLock lock = new ReentrantReadWriteLock();
    private static float stock = 200f;//200
    private float balance;
    private boolean status=true;
    private String indexGas;
    private static int index=0;

    public GasPool() {
        this.balance = stock;
        index++;
        indexGas="GasPool"+index;
    }

    protected float request(float amount){
        status=false;
        lock.writeLock().lock();
        try {
            Thread.sleep(5000);//5000
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        float f=0;
        if(balance-amount<0){
            status=true;
            lock.writeLock().unlock();
            return f;
        } else if(balance-amount>=0&&balance>=0){
            balance-=amount;
            f=amount;
        }
        status=true;
        System.out.println(this.indexGas+": "+balance+"/"+stock);
        System.out.println("______________________________________________________");
        lock.writeLock().unlock();
        return f;
    }

    public static float getStock() {
        return stock;
    }

    public static void setStock(float stock) {
        GasPool.stock = stock;
    }

    public synchronized float getBalance() {
        return balance;
    }

    public boolean isStatus() {
        return status;
    }

    public String getIndexGas() {
        return indexGas;
    }

    public static int getIndex() {
        return index;
    }
}
