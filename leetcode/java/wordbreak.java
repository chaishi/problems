/*
 * from oj.leetcode
 * problem I:
 * given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or
 * more dictionary words.
 * brute force is recursion, better solution is dynamic programming in time O(n^2)
 * test data:
 * s=leetcode, dict={lee, leet, tcod, code}, true
 * s=leetcode, dict={lee, tco, d, leet, cod}, false
 *
 * problem II:
 * given s and dict, add spaces in s to construct a sentence where each word is valid in dict. return all possible sentences
 * note: word in dict can be used repeatly
 * */

import java.io.*;
import java.util.*;

public class wordbreak{
    public boolean canBreak(String s, Set<String> dict){
        final int n = s.length();
        if(n == 0)    return true;

        boolean[] inDict = new boolean[n];
        for(int i = 0; i < n; ++i){
            inDict[i] = false;
        }

        for(int i = 0; i < n; ++i){
            if(!inDict[i] && dict.contains(s.substring(0, i+1))){ // str.substring(startIdx, endIdx)
                inDict[i] = true;
            }

            if(inDict[i]){
                for(int j = i+1; j < n; ++j){
                    if(!inDict[j] && dict.contains(s.substring(i+1, j+1))){
                        inDict[j] = true;
                    }
                }

                if(inDict[n-1])    return true;
            }
        }
        return inDict[n-1];
    }

    /*
     * efficient solution to problem 2.
     * 1. a traverse of O(n^2) is necessary and a List<List<int>> is used for the traverse result;
     * 2. utilize the structure to find all consecutive composition
     *
     * test data:
     * dict={a,aa,aaa,aaaa,aaaaa...}, str=aaaaaaaaaaaaaaab
     * dict={a,aa,aaa,aaaa,aaaaa...}, str=baaaaaaaaaaaaaaa
     * think about how to skip duplicate parts during preparement(fill the List<List<int>>)
     * 
     * why it can work to skip? because the Q asks to find "word break" instead of "word occurance",
     * any word occurance which cannot contribute to a complete breaking option does not need to cover
     * */
    public ArrayList<String> wordbreakall(String s, Set<String> dict){
        ArrayList<String> res = new ArrayList<String>();
        final int n = s.length();
        if(n == 0 || dict.isEmpty())    return res;

        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>(); // arr[s] = e means substr[s, e) in dict
        for(int i = 0; i < n; ++i){
            arr.add(i, new ArrayList<Integer>());            
        }

        for(int stop = n; stop > 0; --stop){
            if(stop < n && arr.get(stop).isEmpty()) // to skip the stop which can not jump to end as start
                continue;

            for(int start = stop - 1; start >= 0; --start){
                if(dict.contains(s.substring(start,stop))){
                    arr.get(start).add(stop);
                }
            }
        }

        Stack<String> stk = new Stack<String>();
        stk.push("");
        collect(arr, 0, s, stk, res); // construct result

        return res;
    }

    /*
     * construct result ArrayList<String>
     * */
    protected void collect(ArrayList<ArrayList<Integer>> arr, int start, String str,
            Stack<String> stk, ArrayList<String> res){
        stk.push(start == 0 ? "" : " ");
        for(int stop : arr.get(start)){
            stk.push(str.substring(start, stop));

            if(stop == str.length()){
                StringBuilder builder = new StringBuilder();
                for(String s : stk){
                    builder.append(s);
                }

                res.add(builder.toString());
            }else{ 
                collect(arr, stop, str, stk, res);
            }

            stk.pop(); // str pushed in this iteration
        }
        stk.pop(); // "" or " " which is pushed in beginning of the method
    }
}

/* unittest code is in ../java_unittest/wordbreak_junit/ */
