/*
 * given a matrix of mx x n elements(m rows, n columns), return all elements of the matrix in spiral order
 *
 * test case:
 * 1  2  3  4 
 * 5  6  7  8
 * 9 10 11 12
 * output in spiral order: 1,2,3,4,8,12,11,10,9,5,6,7
 * */
#include "../include/preliminary.h"

vector<int> spiralOrder(vector<vector<int> >& matrix){
    vector<int> res;
    int d = matrix.size();
    if(d==0)    return res;
    int w = matrix[0].size();
    if(w==0)    return res;
    
    int step[] = {1,-1,-1,1};
    int i=0, j=0, k=0;
    while(w>0 && d>0){
        if((k & 1) == 0){  // k == 0, 2
            if(k==0){
                for(int cnt=0; cnt<w; ++cnt, ++j){
                    res.push_back(matrix[i][j]);
                }
                --j;
            }else if(k==2){
                for(int cnt=0; cnt<w; ++cnt, --j){
                    res.push_back(matrix[i][j]);
                }
                ++j;
            }
            i += step[k];
            --d;
        }else{
            if(k==1){
                for(int cnt=0; cnt<d; ++cnt, ++i){
                    res.push_back(matrix[i][j]);
                }
                --i;
            }else if(k==3){
                for(int cnt=0; cnt<d; ++cnt, --i){
                    res.push_back(matrix[i][j]);
                }
                ++i;
            }
            j += step[k];
            --w;            
        }
        k = (k+1)%4;
    }
    return res;
}

void fillMatrix(vector<vector<int> >& matrix, int n){
    if(n<1)        return;
    for(int i=0; i<n; ++i){
        vector<int> vec;
        for(int j=0; j<n; ++j){
            vec.push_back(0);
        }
        matrix.push_back(vec);
    }
    return;
}

/*
 * Spiral Matrix II
 * */
vector<vector<int> > generateMatrix(int n){
    vector<vector<int> > matrix;
    if(n<1)        return matrix;
    int d = n, w = n;
    fillMatrix(matrix, n);
    
    int step[] = {1,-1,-1,1};
    int i=0, j=0, k=0, a=1;
    bool horizon = true;
    while(w>0 && d>0){
        if(horizon){
            if(k==0){
                for(int cnt=0; cnt<w; ++cnt, ++j){
                    matrix[i][j] = a++;
                }
                --j;
            }else if(k==2){
                for(int cnt=0; cnt<w; ++cnt, --j){
                    matrix[i][j] = a++;
                }
                ++j;
            }else{
                break;
            }
            i += step[k];
            --d;
        }else{
            if(k==1){
                for(int cnt=0; cnt<d; ++cnt, ++i){
                    matrix[i][j] = a++;
                }
                --i;
            }else if(k==3){
                for(int cnt=0; cnt<d; ++cnt, --i){
                    matrix[i][j] = a++;
                }
                ++i;
            }else{
                break;
            }
            j += step[k];
            --w;            
        }
        k = (k+1)%4;
        horizon = !horizon;
    }
    return matrix;
}

void displayVector(const vector<int>& nums){
    for(vector<int>::const_iterator iter = nums.begin(); iter != nums.end(); ++iter){
        cout << (*iter) << " ";
    }
    cout << endl;
    return;
}

void displayMatrix(const vector<vector<int> >& matrix){
    int n = matrix.size();
    for(int i=0; i<n; ++i){
        for(int j=0; j<n; ++j){
            printf("%d ", matrix[i][j]);
        }
        printf("\n");
    }
    return;
}

void test_01(){
    int arr0[] = {1,2,3,4};
    vector<int> vec0(arr0, arr0 + 4);
    int arr1[] = {5,6,7,8};
    vector<int> vec1(arr1, arr1 + 4);
    int arr2[] = {9,10,11,12};
    vector<int> vec2(arr2, arr2 + 4);
    int arr3[] = {13,14,15,16};
    vector<int> vec3(arr3, arr3 + 4);
    vector<vector<int> > matrix;
    matrix.push_back(vec0);
    matrix.push_back(vec1);
    matrix.push_back(vec2);
    matrix.push_back(vec3);
    displayVector(spiralOrder(matrix));
    return;
}

void test_02(){
    string str;
    while(1){
        printf("please input integer n:\n");
        if(getline(cin, str)==0 || str.empty())        break;
        int n = atoi(str.c_str());
        displayMatrix(generateMatrix(n));
    }
    return;
}

int main(int, char**){
    test_01();
    return 0;
}
