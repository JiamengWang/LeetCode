package com.company;

public class brainTestSolution {
    /**generating a list of primes using sieve of  eratosthenes
     * */

    public void run(int max) {
        boolean[] output = sieveOfEratosthenes(max);
        for (int i = 0; i < output.length;i ++) {
            if (output[i]) System.out.println(i);
        }
    }

    public boolean[] sieveOfEratosthenes (int max) {
        boolean[] flag = new boolean[max + 1];

        /*initial flag, set all elements to true except 0 and 1*/
        for (int i = 2; i < flag.length; i++) {
            flag[i] = true;
        }

        int prime = 2;
        while (prime <= Math.sqrt(max)) {

             crossOff(flag,prime);

             prime = nextPrime(flag,prime);
        }


        return flag;
    }

    private void crossOff(boolean[] flag, int prime) {
        for (int i = prime * prime;i < flag.length; i+= prime) {
            flag[i] = false;
        }
    }

    private int nextPrime (boolean[] flag, int prime) {
        int next =  prime + 1;
        while (next < flag.length && !flag[next]) {
            next++;
        }
        return next;
    }
}
