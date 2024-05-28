/**
 * Armando Sanchez & Everardo Camarena
 * Philosopher.java
 *
 * This class represents each philosopher thread.
 * Philosophers alternate between eating and thinking.
 *
 */
import java.util.Random;
public class Philosopher implements Runnable
{
 //private int num;
 public int num; //philosopher
 public DiningServer server; //gets data from diningserver

 public Philosopher(int num, DiningServer server) {
     this.num = num;
     this.server = server;
 }

@Override
 public void run() { //thread is started
     try {
         while (true) {
             think(); // displays random time from the function
             server.takeForks(num); // runs takeforks from server
             eat(); // displays random time from the function
             server.returnForks(num); // runs returnforks from server
         }
     } catch (InterruptedException ie) {
         Thread.currentThread().interrupt();
     }
 }

 private void think() throws InterruptedException { //random time for thinking
    int think = new Random() .nextInt(3000);
   
    System.out.println("Philosopher #" +num + " is thinking for " + think +"ms");
     Thread.sleep(think);
 }

 private void eat() throws InterruptedException { //random time for eating
    int eat =  new Random() .nextInt(3000);
   
    System.out.println("Philosopher #" +num + " is eating for " + eat +"ms");
     Thread.sleep(eat);
 }
}
