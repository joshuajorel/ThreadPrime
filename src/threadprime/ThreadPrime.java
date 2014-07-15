/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package threadprime;

import java.util.Scanner;

/**
 *
 * @author JoshuaJorel
 */
public class ThreadPrime extends Thread {

    /**
     * @param args the command line arguments
     */
    
    int threadNumber;
    static int size;
    static int search;
    static boolean found;
    
    public ThreadPrime(int x){
        threadNumber = x;
    }
    
    public void run(){
        int x = 0;
        long startTime = System.currentTimeMillis();
        long endTime;
        long totalTime;
        
        while(!found && x<search/2/size){
            if(x+(search/2/size)*(threadNumber-1) != 0 && x+(search/2/size)*(threadNumber-1) != 1){
                if(search % (x+(search/2/size)*(threadNumber-1)) == 0 && search != 2){
                found = true;
                endTime = System.currentTimeMillis();
                totalTime = endTime - startTime;
                System.out.println("Number "+search+" is not prime with execution time "+totalTime+"ms");
                }else{
                   x++;
                }
            }else{
                x++;
            }
           
        }
        
        if(!found){
            endTime = System.currentTimeMillis();
            totalTime = endTime - startTime;
            System.out.println("Number is prime or this thread ("+threadNumber+") with execution time "+totalTime+"ms was unable to search for a prime factor");
        }
    }

    
    public static void main(String[] args) {
        // TODO code application logic here
        ThreadPrime[] threads;
        Scanner reader = new Scanner(System.in);
        
        //ask for number of threads and create array of classes
        System.out.print("Enter number of threads: ");
        ThreadPrime.size = reader.nextInt();
        threads = new ThreadPrime[ThreadPrime.size];
        
        //ask for number to be searched
        System.out.print("Search for: ");
        ThreadPrime.search = reader.nextInt();
        
        //initialize thread classes
        for(int x=0;x<ThreadPrime.size;x++){
            threads[x] = new ThreadPrime(x+1);
        }
        
        //start threads
        for(int x=0;x<ThreadPrime.size;x++){
            threads[x].start();
        }
    }
}
