package recursionAndBackTracking;


import java.util.ArrayList;

/**
 *  given coins [2,3,5,7]
 *
 *  1)permutation (Infinite coins)
 *  2)permutation (single coin)
 *  3)combination (infinite coins)
 *  4) combination (single coin)
 *
 */
public class permutationAndCombination {

    /**
     * Calculates the number of permutations where infinite supply of given coins
     * can sum up to a specified target value.
     *
     * @param arr The list of coin denominations available.
     * @param ans The current permutation of coin values.
     * @param target The remaining target value to be achieved.
     * @return The count of different permutations that sum up to the target.
     */
    public static int permutationInfiniteCoin(ArrayList<Integer> arr,String ans, int target) {


        if(target==0)
        {
            System.out.println(ans);
            return 1;
        }



        int count=0;

        for(int i=0;i<arr.size();i++)
        {
            int temp=arr.get(i);
            if(target>=temp)
             count+=permutationInfiniteCoin(arr,ans+""+temp,target-temp);
        }

         return count;
    }

    public static int permutationSingleCoin(ArrayList<Integer> arr,String ans, int target) {


        if(target==0)
        {
            System.out.println(ans);
            return 1;
        }



        int count=0;

        for(int i=0;i<arr.size();i++)
        {
            int temp=arr.get(i);
            if(target>=temp && temp>0) {
                arr.set(i,-temp);
                count += permutationSingleCoin(arr, ans + "" + temp, target - temp);
                arr.set(i,temp);
            }
        }

        return count;
    }



    public static int combinationInfiniteCoin(ArrayList<Integer> arr,int index,String ans, int target)
    {
       if(target==0)
       {
           System.out.println(ans);
           return 1;
       }


        int count=0;


        for(int i=index;i<arr.size();i++)
        {
            int ele=arr.get(i);
            if(target>=ele)
                count+=combinationInfiniteCoin(arr,i,ans+""+ele,target-ele);
        }

        return count;
    }

    public static int combinationSingleCoin(ArrayList<Integer> arr,int index,String ans, int target)
    {
        if(target==0)
        {
            System.out.println(ans);
            return 1;
        }


        int count=0;


        for(int i=index;i<arr.size();i++)
        {
            int ele=arr.get(i);
            if(target>=ele)
                count+=combinationSingleCoin(arr,i+1,ans+""+ele,target-ele);
        }

        return count;
    }

    /**
     *
     * same thing using subsequence pattern
     * 1)permutation (Infinite coins)
     * 2)permutation (single coin)
     * 3)combination (infinite coins)
     * 4) combination (single coin)
     *
     */

     public static int combinationInfiniteCoinsSubsequence(ArrayList<Integer>arr, int idx,String ans, int target)
     {

          if(idx>=arr.size() || target==0)
          {
              if(target==0)
              {
                  System.out.println(ans);
                  return 1;
              }

              return 0;
          }


         int count=0;
         int ele=arr.get(idx);

         //include as combination again , current index can again we used so pass idx
         if(target-ele>=0)
             count+=combinationInfiniteCoinsSubsequence(arr,idx,ans+ele,target-ele);

         //exculde
         count+=combinationInfiniteCoinsSubsequence(arr,idx+1,ans,target);

         return count;
     }


    public static int combinationSingleCoinSubsequence(ArrayList<Integer>arr, int idx,String ans, int target)
    {

        if(idx>=arr.size() || target==0)
        {
            if(target==0)
            {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }


        int count=0;
        int ele=arr.get(idx);

        //include as combination again , current index cannot we again used so idx+1
        if(target-ele>=0)
            count+=combinationSingleCoinSubsequence(arr,idx+1,ans+ele,target-ele);

        //exculde
        count+=combinationSingleCoinSubsequence(arr,idx+1,ans,target);

        return count;
    }

    public static int permutaionInfiniteCoinsSubsequence(ArrayList<Integer>arr, int idx,String ans, int target)
    {

        if(idx>=arr.size() || target==0)
        {
            if(target==0)
            {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }


        int count=0;
        int ele=arr.get(idx);

        //include in permutaion again , stsrt form 0 as permutaion
        if(target-ele>=0)
            count+=permutaionInfiniteCoinsSubsequence(arr,0,ans+ele,target-ele);

        //exculde
        count+=permutaionInfiniteCoinsSubsequence(arr,idx+1,ans,target);

        return count;
    }

    public static int permutaionSingleCoinSubsequence(ArrayList<Integer>arr, int idx,String ans, int target)
    {

        if(idx==arr.size() || target==0)
        {
            if(target==0)
            {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }


        int count=0;
        int ele=arr.get(idx);

        //include in permutaion again , start form 0 as permutation
        if(target-ele>=0 && ele>0) {
             arr.set(idx,-ele);
            count += permutaionSingleCoinSubsequence(arr, 0, ans + ele, target - ele);
            arr.set(idx,ele);
        }
        //exculde
        count+=permutaionSingleCoinSubsequence(arr,idx+1,ans,target);

        return count;
    }


    public static void main(String[] args) {

        ArrayList<Integer> arr=new ArrayList<>();
        arr.add(2);
        arr.add(3);
        arr.add(5);
        arr.add(7);
        int target=10;

        int count=permutationInfiniteCoin(arr,"",target);
        System.out.println("Permuation of infinite coin is "+count);

         count=permutaionInfiniteCoinsSubsequence(arr,0,"",target);
        System.out.println("Permuation of infinite coin subsequnece is "+count);

         count=permutationSingleCoin(arr,"",target);
        System.out.println("Permuation of single coin is "+count);

        count=permutaionSingleCoinSubsequence(arr,0,"",target);
        System.out.println("Permuation of single coin  subsequence is "+count);


        count=combinationInfiniteCoin(arr,0,"",target);
        System.out.println("Combination of infinite coin is "+count);

        count=combinationInfiniteCoin(arr,0,"",target);
        System.out.println("Combination of infinite coin subsequence is "+count);

        count=combinationSingleCoin(arr,0,"",target);
        System.out.println("Combination of single coin is "+count);

        count=combinationSingleCoin(arr,0,"",target);
        System.out.println("Combination of single coin  subsequence is "+count);
    }
}
