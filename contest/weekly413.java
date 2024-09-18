package contest;

import java.util.*;

public class weekly413 {


    public static void main(String[] args) {

        List<List<Integer>> grid2 = Arrays.asList(
                Arrays.asList(8, 7, 6),
                Arrays.asList(8, 3, 2)
        );

        System.out.println(maxScore(grid2));
    }


    public static boolean checkTwoChessboards(String coordinate1, String coordinate2) {


        int num1=(coordinate1.charAt(0)-'a')+1;
        int num=(coordinate1.charAt(1)-'1');
        //System.out.println(num1 +  " " + num);
        int num2=(coordinate2.charAt(0)-'a')+1;
        int num3=(coordinate2.charAt(1)-'1');
// System.out.println(num2 +  " " + num3);
        return (num1+num)%2 == (num2+num3)%2;


    }


/*
Input: queries = [[1,2],[3,4],[2,3],[-3,0]], k = 2

Output: [-1,7,5,3]
 */

    public static int[] resultsArray(int[][] queries, int k) {

          int[] result = new int[queries.length];


          PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->(

                  (Math.abs(b[1])+Math.abs(b[0])) -(Math.abs(a[1])+Math.abs(a[0]))
                  ));
          int index=0;
          for (int i = 0; i < queries.length; i++) {





              pq.add(new int[]{queries[i][0], queries[i][1]});


              while(pq.size() > k) {
                //  result[index++] = Math.abs(pq.peek()[0])+Math.abs(pq.peek()[1]);
                  pq.poll();
              }

              if(pq.size()==k)
                  result[index++] = Math.abs(pq.peek()[0])+Math.abs(pq.peek()[1]);
              else
                  result[index++] =-1;

          }

          return result;
    }




    //upsolve  karna hai 3-4


        public static int maxScore(List<List<Integer>> grid) {



            // Sort each row in descending order
            for (List<Integer> row : grid) {
                Collections.sort(row,(a,b)->b-a);

            }

            // Initialize the DP table
            // int[] dp = new int[grid.get(0).size()];
            Set<Integer> usedValues = new HashSet<>();
            int maxScore = 0;
            for (List<Integer> row : grid) {

                for(int value:row)
                {
                    if (!usedValues.contains(value)) {
                        maxScore += value;
                        usedValues.add(value);
                        break; // Move to the next row after selecting a value
                    }
                }
            }


            return maxScore;



        }

}
