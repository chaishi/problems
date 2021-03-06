/*
 * find the contiguous subarray within an array(containing at least one number) which has the largest sum.
 * Furthermore, if you have figured out O(n) solution, try coding another using divide-and-conquer approach.
 *
 * test case:
 * [-1], -1
 * [-2, -3, -4, -1, -2], -1    //as subsrray has one element at least, test case of all negative elements needs careful process
 * [-1, 4, -2, 3, 1, -2], 6
 * [2, 2, -4, 1, 2, -1], 4
 * */
#include "../include/preliminary.h"

/*
 * linear scan in O(n)
 * */
int maxSubArray_01(int A[], int n){   
    int sum = 0, msm = 0, t = n-1, mx = A[n-1];
    for(int i=n-1; i>=0; --i){
        mx = max(mx, A[i]);    //save the max element of all
        if(A[i] <= 0){
            if(t == i){
                t = i-1;
                continue;
            }else if(A[i] + sum <= 0){
                sum = 0;
                t = i-1;
                continue;
            }else{
                sum += A[i];
            }
        }else{
            sum += A[i];
            msm = max(msm, sum);
        }
    }
    return mx<0 ? mx : msm;   //msm is 0 at least, so return value depends on mx
}

/*
 * from discussion on oj.leetcode
 * */
int maxSubArray_02(int A[], int n){
    int mSum = A[0];   //max sum
    int cSum = max(A[0], 0);      //current sum
    for(int i=1; i<n; ++i){
        cSum += A[i];
        mSum = max(mSum, cSum);
        cSum = max(cSum, 0);
    }
    return mSum;
}

/*
 * from discussion on oj.leetcode
 * */
int maxSubArray_03(int A[], int n){
    int best = A[0];    //max sum
    int curr = A[0];    //current sum
    for(int i=1; i<n; ++i){
        curr = max(curr, 0) + A[i];
        best = max(curr, best);
    }
    return best;
}

/*
 * divide-and-conquer, worse than linear solution, in time O(nlgn)
 * */
int split(int A[], int n){  //return max sum among the range of A[]
    if(n==1)    return A[0];
    int l = n >> 1;
    int lmx = split(A, l);
    int rmx = split(A+l, n-l);
    int lhalf=A[l-1], rhalf=A[l];   //initial value of lhalf and rhalf is critical important to avoid returning 0 when negative
    int tmp = lhalf;
    for(int i=l-2; i>=0; --i){    //leftward from middle line
        tmp += A[i];
        lhalf = max(lhalf, tmp);
    }
    tmp = rhalf;
    for(int i=l+1; i<n; ++i){
        tmp += A[i];
        rhalf = max(rhalf, tmp);
    }
    return max(max(lmx, rmx), lhalf + rhalf);
}

int maxSubArray_04(int A[], int n){
    if(n==1)    return A[0];
    return split(A, n);
}

void test_01(){
    string str;
    while(1){
        printf("please input integer array:\n");
        if(getline(cin, str)==0 || str.empty())        break;
        int *arr = new int[str.size()]();
        int n = splitStr2IntArray(str, arr);
        printf("solution1: result is %d\n", maxSubArray_01(arr, n));
        printf("solution2: result is %d\n", maxSubArray_02(arr, n));
        printf("solution3: result is %d\n", maxSubArray_03(arr, n));
        printf("solution3: result is %d\n", maxSubArray_04(arr, n));
        delete[] arr;
        arr=0;
    }
}

int main(int, char**){
    test_01();
    return 0;
}
