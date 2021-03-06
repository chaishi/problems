/*
 * the set [1,2,3,...,n] contains a total of n! unique permutations.
 * by listing and labelling all of the permutations in order, we get the following sequences
 * e.g. n=3
 * 123, k == 1
 * 132, k == 2
 * 213, k == 3
 * 231, k == 4
 * 312, k == 5
 * 321  k == 6
 *
 * Q: given n and k, return the kth permutation sequence. n is in [1...9] and k is in [1...n!]
 *
 * Note: normal solution to output all of the permutation will cause Time Limit Exceed exception. Correct solution to this problem is very
 * important which prunes useless branches(剪枝) from sum k. 
 * */
import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.lang.Math;


public class kthPermutation{
    /*
     * correct but inefficient
     * */
    public String getPermutation_1(int n, int k){
        int[] num = new int[n];
        int count = 1;

        for(int i = 0; i < n; ++i){ // in maximum, count is 9!, not exceed int length yet
            num[i] = i+1;
            count *= num[i];  
        }

        if(k > count)    return null;
        --k;    // k is 1-based

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < n; ++i){
            count /= (n-i);
            int selected = k / count;
            builder.append(num[selected]);  // selected is 0-based
            
            k = k % count;
            for(int j = selected + 1; j < n-i; ++j){    //reconstruct num[] as one integer is picked up already
                num[j-1] = num[j];
            }
        }
        return builder.toString();
    }

    /*
     * efficient solution, in time O(n)
     * */
    public String getPermutation_2(int n, int k){
        if(n == 0)    return "";

        StringBuilder res = new StringBuilder();

        List<Integer> num = new ArrayList<Integer>();

        for(int i = 0; i < n+1; i++){ // note [0...n] is pushed to num, where [0] = 0 is placeholder
            num.add(i);
        }

        for(int i = n-1; i > -1; i--){

            int factorial = nFactorial(i);

            /*
             * 1. Math.ceil() is critical important, it returns like 0.2 up to 1
             * 2. besides 1, with k is 1-based, index will skip [0] definitely
             * */
            int index = (int)Math.ceil(k / (double)factorial);

            res.append(num.get(index)); // res is populated from high digit

            num.remove(index);

            k %= factorial;
            if(k == 0)    k = factorial;
        }
        return res.toString();
    }

    /*
     * return n!
     * */
    public int nFactorial(int n){
        if(n == 0)    return 1;
        return n * nFactorial(n-1);
    }
}

/* unit test is in ../java_unittest/kthPermutation_junit */
