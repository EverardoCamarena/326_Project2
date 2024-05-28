/**
 * Armando Sanchez & Everardo Camarena
 * DiningPhilosophers.java
 * This program starts the dining philosophers problem.
 */

public class DiningPhilosophers
{  
   public static void main(String args[])
   {
      int n =5; //number of philosophers
      DiningServer ds = new DiningServerImpl(n); //creates an instance and calls it ds
 
      for(int i =0; i<n; i++){ //creates thread for philopsher class
         //new Philosopher(i,ds)).run(); 
       new Thread(new Philosopher(i,ds)).start(); 
      }
   }
}
