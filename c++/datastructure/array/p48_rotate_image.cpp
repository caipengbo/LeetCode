//
// Created by Myth on 11/26/2020.
//
#include <iostream>
#include <vector>

using namespace std;

class P48 {
public:
    void rotate(vector<vector<int>> &matrix) {
        int n = matrix.size();
        // 矩阵转置
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                swap(matrix[i][j], matrix[j][i]);
            }
        }
        // 矩阵左右对称 沿中心对称轴
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n / 2; j++) {
                swap(matrix[i][j], matrix[i][n - 1 - j]);
            }
        }
    }
};

int main() {
    P48 p48;
    vector<vector<int>> matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    p48.rotate(matrix);
    int n = matrix.size();
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            cout << matrix[i][j] << " ";
        }
        cout << endl;
    }
}