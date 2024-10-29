#include <stdio.h>
#include <string>
#include <queue>
using namespace std;

int main()
{

    // 767. Reorganize String

    class compare
    {

    public:
        bool operator()(pair<char, int> &a, pair<char, int> &b)
        {

            return b.second > a.second;
        }
    };
    string reorganizeString(string s)
    {

        unordered_map<char, int> map;

        for (char ch : s)
        {
            map[ch]++;
        }

        priority_queue<pair<char, int>, vector<pair<char, int>>, compare> pq;

        for (auto &ch : map)
        {
            pq.push({ch.first, ch.second});
        }

        string ans = "";
        char prev = '-1';
        while (pq.size() != 0)
        {

            pair<char, int> temp = pq.top();
            pq.pop();
            ans += temp.first;
            map[temp.first]--;

            if (prev != '-1' && map[prev] > 0)
                pq.push({prev, map[prev]});

            prev = temp.first;
        }

        if (ans.size() != s.size())
        {
            return "";
        }
        return ans;
    }

    // 378. Kth Smallest Element in a Sorted Matrix
/*
Time Complexity Analysis:

1. Initial Heap Construction:
- We push the first element of each row (a total of n elements) into the min-heap.
- This step takes O(n log n) time, where n is the number of rows in the matrix.

2. Heap Operations:
- We perform k-1 operations where, in each operation, we pop the smallest element from the heap and push the next element from the same row if it exists.
- Each pop and push operation takes O(log n) time.
- Thus, the heap operations contribute O(k log n) time.

Overall Time Complexity:
- The total time complexity is O((n + k) log n).
- For large k (close to n^2), the time complexity can be approximated as O(k log n), but the exact complexity remains O((n + k) log n).

Space Complexity:
- The space complexity is O(n) due to the storage of elements in the min-heap.
*/

    int kthSmallest(vector<vector<int>> & matrix, int k)
    {

        // min heap
        int m = matrix[0].size();
        priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> pq;

        for (int i = 0; i < matrix.size(); i++)
        {
            pq.push({matrix[i][0], i, 0});
        }

        while (k-- > 1)
        {
            vector<int> top = pq.top();
            pq.pop();

            int i = top[1];
            int j = top[2];
            j++;
            if (j < m)
            {
                pq.push({matrix[i][j], i, j});
            }
        }

        return pq.top()[0];
    }
    return 0;
}