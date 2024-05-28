/**
 * Armando Sanchez & Everardo Camarena
 * DiningServer.java
 *
 * This class contains the methods called by the  philosophers.
 *
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DiningServerImpl  implements DiningServer
{  
	public Lock[] lock; //aray that allows access to pick up and return
    public Condition[] conditions; //array that allows access once a condition is met
	public int num; //variable use as identifying the philosophers number

    public DiningServerImpl(int n) { //constrcutor that takes n as a argument
		num = n; //sets the number of philosphers to 5
		lock = new Lock[n]; //creates locks for every fork
    	conditions = new Condition[n]; //creates conditions for every fork

    //initializes each lock with the conditions
    for (int i = 0; i < n; i++) {
        lock[i] = new ReentrantLock(); 
        conditions[i] = lock[i].newCondition();
    }
	}
	
	 // called by a philosopher when they wish to eat
     @Override
	 public void takeForks(int philNumber) {
        int Fork = philNumber; 
        int Forks = (philNumber + 1) % lock.length; 
        
        // checks to see if they can pick up both forks and procceds to lock 
        if (philNumber % 2 == 0) {
            lock[Fork].lock();
            lock[Forks].lock();
        } else {
            lock[Forks].lock();
            lock[Fork].lock();
        }
    
        // signals that the forks are available for 
        conditions[Fork].signal();
        conditions[Forks].signal();
        //prints what forks they have
        System.out.println("Philosopher #" + philNumber + " has forks " + Fork + " and " + Forks);
    }
    @Override
    // called by a philosopher when they are finished eating
    public void returnForks(int philNumber) {
        int Fork = philNumber;
        int Forks = (philNumber + 1) % num;
          //prints what forks they returned
        System.out.println("Philosopher #" + philNumber + " returned forks " + Fork + " and " + Forks);
        //releases the locks 
        lock[Fork].unlock();
        lock[Forks].unlock();
       
	}
}