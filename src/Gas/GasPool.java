package Gas;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class GasPool {
    private static ReadWriteLock lock = new ReentrantReadWriteLock();
    static float stock = 200f;//200
    float balance;
    boolean status=true;
    String indexGas;
    static int index=0;

    public GasPool() {
        this.balance = stock;
        index++;
        indexGas="GasPool"+index;
    }

    synchronized float request(float amount){
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

    float info(){
        return balance;
    }
}
