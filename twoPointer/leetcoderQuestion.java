package twoPointer;

import javax.print.DocFlavor;
import java.sql.SQLOutput;
import java.util.*;

public class leetcoderQuestion {

    //125. Valid Palindrome

    public boolean isPalindrome(String s) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                char ch = s.charAt(i);
                /* ASCII Conversion for Uppercase to Lowercase:
                    1. 'A' to 'Z' have ASCII values 65 to 90
                     2. 'a' to 'z' have ASCII values 97 to 122
                    3. Difference between uppercase and lowercase is 32 ('a' - 'A' = 32)
                        4. To manually convert: (char)(uppercase + 32)
                      Example: 'A' + 32 = 'a' (65 + 32 = 97)
            Just remember: Uppercase + 32 = Lowercase
*/


                char ch1 = (char) (ch + ('a' - 'A'));
                sb.append(ch1);
            } else if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                char ch = s.charAt(i);
                sb.append(ch);
            } else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                char ch = s.charAt(i);
                sb.append(ch);
            }
        }

        String str = sb.toString();
        System.out.println(str);
        int i = 0;
        int j = str.length() - 1;


        while (i < j) {
            char ch = str.charAt(i);
            char ch1 = str.charAt(j);

            if (ch != ch1)
                return false;

            i++;
            j--;
        }

        return true;
    }


    //680. Valid Palindrome II

    public boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                // If mismatch found, try skipping either the left character or the right
                // character
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
            }
            i++;
            j--;
        }
        return true; // If no mismatch found, it's already a palindrome
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }


    //1984. Minimum Difference Between Highest and Lowest of K Scores

    public static int minimumDifference(int[] nums, int k) {

        if(k==0)
            return 0;

        Arrays.sort(nums);

        int minDiff=Integer.MAX_VALUE;

          for(int i=0;i<=nums.length-k;i++)
          {
              minDiff = Math.min(minDiff, nums[i+k-1]-nums[i]);
          }


          return minDiff;

    }

    //1768. Merge Strings Alternately

    public String mergeAlternately(String word1, String word2) {

          int i=0;
          int j=0;
          StringBuilder sb=new StringBuilder();
          int k=0;
          while(i<word1.length()&&j<word2.length()){

              if(k%2==0)
              {
                  sb.append(word1.charAt(i));
                  i++;
                  k++;
              }
              else{
                  sb.append(word2.charAt(j));
                  j++;
                  k++;
              }
          }

          while(i<word1.length()){
              sb.append(word1.charAt(i++));
          }

          while(j<word2.length()){
              sb.append(word2.charAt(j++));
          }

          return sb.toString();
    }

    //344. Reverse String
    public void reverseString(char[] s) {

          int i=0;
          int j=s.length-1;

           while(i<j){
               char temp=s[i];
               s[i]=s[j];
               s[j]=temp;
               i++;
               j--;
           }
    }

    //88. Merge Sorted Array
    public void merge(int[] nums1, int m, int[] nums2, int n) {

         int i=m-1;
         int j=n-1;
         int k=m+n-1;
         while(i>=0 && j>=0){
             if(nums1[i]<nums2[j]){
                 nums1[k--]=nums2[j--];
             }
             else{
                 nums1[k--]=nums1[i--];
             }
         }

         while(j>=0){
             nums1[k--]=nums2[j--];
         }
    }

    //283. Move Zeroes
    public static  void moveZeroes(int[] nums) {
       int i=0;
       int j=0;
       for(int l=0;l<nums.length;l++){
           if(nums[l]!=0){
               j=l;
               break;
           }
       }

       while(i<nums.length){
           if(nums[i]==0){
               nums[i]=nums[j];
               nums[j]=0;
                 while(j<nums.length && nums[j]!=0)
                 {
                     j++;
                 }
           }
           i++;
       }
    }


    //557. Reverse Words in a String III

    public String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            StringBuilder reverseWord = new StringBuilder(word);
            sb.append(reverseWord.reverse()).append(" ");
        }


        return sb.toString().trim();
    }


    //844. Backspace String Compare

    //not use StringBuilder as stack
    public static boolean backspaceCompareUsingSpace(String s, String t) {
        return buildFinalString(s).equals(buildFinalString(t));
    }

    private static String buildFinalString(String str) {
        LinkedList<Character> stack = new LinkedList<>();

        for (char ch : str.toCharArray()) {
            if (ch == '#') {
                if (!stack.isEmpty()) {
                    stack.removeLast(); // Remove the last character if there's a backspace
                }
            } else {
                stack.addLast(ch); // Add character to the end of the list
            }
        }

        // Convert LinkedList to a String
        StringBuilder result = new StringBuilder();
        for (char ch : stack) {
            result.append(ch);
        }

        return result.toString();
    }

    //O(1)
    public boolean backspaceCompare(String s, String t) {
       return afterRemoval(s).equals(afterRemoval(t));
    }

    public String afterRemoval(String s)
    {
        StringBuilder sb=new StringBuilder(s);
        int i=s.length()-1;
        int countOfBackSpace=0;

        while(i>=0)
        {
            if(sb.charAt(i)=='#')
            {
                countOfBackSpace++;
                sb.deleteCharAt(i);
            }
            else{

                if(countOfBackSpace>0)
                {
                     sb.deleteCharAt(i);
                     countOfBackSpace--;
                }
            }

            i--;
        }

        return sb.toString();
    }
//1662. Check If Two String Arrays are Equivalent
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {

        StringBuilder sb=new StringBuilder();

        for(String s:word1)
        {
            sb.append(s);
        }

        StringBuilder sb1=new StringBuilder();


        for(String s:word2)
        {
            sb1.append(s);
        }

        return sb.toString().equals(sb1.toString());

    }
 //80. Remove Duplicates from Sorted Array II
    public int removeDuplicates(int[] nums) {

        int j=0;
        int n=nums.length;
        for(int i=0;i<n;i++)
        {

            if(j<2 || nums[i]!=nums[j-2])
            {
                nums[j]=nums[i];
                j++;
            }
        }

        return j;

    }

    //167. Two Sum II - Input Array Is Sorted
    //O(n)
    /*
         Follow up ?
         can we use binary Search?
     */


    public int[] twoSum(int[] numbers, int target) {

        // int [] ans=new int[2];

        int i=0;
        int j=numbers.length-1;

        while(i<j)
        {
            if(numbers[i]+numbers[j]==target)
            {
                return new int[]{i+1,j+1};
            }
            else if(numbers[i]+numbers[j]>target)
                j--;
            else
                i++;
        }

        return new int[2];
    }

 //15. 3Sum

    //use LinkedList in these kind of sceneario
 public void twoSum(int[] numbers, int target,LinkedList<LinkedList<Integer>>ans,int i) {

     // int [] ans=new int[2];



     int j=numbers.length-1;

     HashSet<String> map=new HashSet<>();

     while(i<j)
     {
         if(numbers[i]+numbers[j]==target)
         {
             StringBuilder sb=new StringBuilder();
             sb.append(Integer.toString(numbers[i]));
             sb.append(Integer.toString(numbers[j]));

             String s=sb.toString();

             if(map.contains(s)==false){

                 LinkedList<Integer>ans1=new LinkedList<>();
                 ans1.addLast(numbers[i]);
                 ans1.addLast(numbers[j]);
                 ans.addLast(ans1);
                 map.add(s);
             }
             i++;
             j--;

         }
         else if(numbers[i]+numbers[j]>target)
             j--;
         else
             i++;
     }


 }
    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        int n=nums.length;

        List<List<Integer>> ans=new LinkedList<>();

        int prev=nums[0];
        for(int i=0;i<n;i++)
        {

            if(i==0 || prev!=nums[i])
            {
                LinkedList<LinkedList<Integer>> ans1=new LinkedList<>();
                twoSum(nums,-nums[i],ans1,i+1);

                for(LinkedList<Integer>l:ans1)
                {
                    l.addFirst(nums[i]);
                    ans.add(l);
                }

                prev=nums[i];

            }

        }

        return ans;
    }

    //18. 4Sum
    public void twoSum(int[] numbers, Long target,LinkedList<LinkedList<Integer>>ans,int i) {

        // int [] ans=new int[2];



        int j=numbers.length-1;

        HashSet<String>map=new HashSet<>();

        while(i<j)
        {
            if(numbers[i]+numbers[j]==target)
            {
                StringBuilder sb=new StringBuilder();
                sb.append(Integer.toString(numbers[i]));
                sb.append(Integer.toString(numbers[j]));

                String s=sb.toString();

                if(map.contains(s)==false){

                    LinkedList<Integer>ans1=new LinkedList<>();
                    ans1.addLast(numbers[i]);
                    ans1.addLast(numbers[j]);
                    ans.addLast(ans1);
                    map.add(s);
                }
                i++;
                j--;

            }
            else if(numbers[i]+numbers[j]>target)
                j--;
            else
                i++;
        }


    }
    public LinkedList<LinkedList<Integer>> threeSum(int[] nums,Long target, int j) {

        Arrays.sort(nums);
        int n=nums.length;

        LinkedList<LinkedList<Integer>> ans=new LinkedList<>();



        for(int i=j;i<n;i++)
        {

            if (i > j && nums[i] == nums[i - 1]) continue;


            LinkedList<LinkedList<Integer>> ans1=new LinkedList<>();
            twoSum(nums,target-nums[i],ans1,i+1);

            for(LinkedList<Integer>l:ans1)
            {
                l.addFirst(nums[i]);
                ans.addLast(l);
            }





        }

        return ans;
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {

        Arrays.sort(nums);
        int n=nums.length;

        List<List<Integer>> ans=new LinkedList<>();

        int prev=nums[0];
        for(int i=0;i<n;i++)
        {

            if(i==0 || prev!=nums[i])
            {

                LinkedList<LinkedList<Integer>> ans1=threeSum(nums,(long)target-nums[i],i+1);

                for(LinkedList<Integer>l:ans1)
                {
                    l.addFirst(nums[i]);
                    ans.add(l);
                }

                prev=nums[i];

            }

        }

        return ans;

    }



    //11. Container With Most Water
    public int maxArea(int[] height) {


        int ans=0;

        int i=0;
        int j=height.length-1;

        while(i<j)
        {

            ans=Math.max(ans,(j-i)*Math.min(height[i],height[j]));

            //java does not support ternary operateor
            if(height[i]>height[j])
            {
                j--;
            }
            else
                i++;
        }

        return ans;
    }

    //1498. Number of Subsequences That Satisfy the Given Sum Condition
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int mod = (int) 1e9 + 7;
        long sum = 0;

        int i = 0;
        int j = nums.length - 1;
        int n = nums.length;
        int[] exp = new int[n];
        exp[0] = 1;

        for (int k = 1; k < n; k++)
            exp[k] = (exp[k - 1] * 2) % mod;

        while (i <= j) {
            if (nums[i] + nums[j] <= target) {
                sum = (sum + exp[j - i]) % mod;
                i++;
            } else {
                j--;
            }
        }

        return (int) sum;

    }

    //189. Rotate Array
    public void reverse(int []nums, int i,int j)
    {

        while(i<j)
        {
            int temp=nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
            i++;
            j--;
        }
    }
    public void rotate(int[] nums, int k) {

        int n=nums.length;
        k=k%n;

        reverse(nums,0,n-1);
        reverse(nums,0,k-1);
        reverse(nums,k,n-1);
    }

    //1968. Array With Elements Not Equal to Average of Neighbors
    public int[] rearrangeArray(int[] n) {


        for(int i=1;i<n.length-1;i++){

            if(n[i-1]+n[i+1] == 2*n[i]){
                int t=n[i];
                n[i]=n[i+1];
                n[i+1]=t;
            }

        }
        for(int i=n.length-2;i>0;i--){

            if(n[i-1]+n[i+1] == 2*n[i]){
                int t=n[i];
                n[i]=n[i-1];
                n[i-1]=t;
            }

        }
        return n;

    }
//881. Boats to Save People
    public int numRescueBoats(int[] people, int limit) {

        Arrays.sort(people);

        int si=0;
        int ei=people.length-1;

        int count=0;

        while(si<=ei)
        {
            if(people[si]+people[ei]>limit)
            {
                count++;
                ei--;
            }
            else
            {
                si++;
                ei--;
                count++;
            }

        }

        return count;

    }

    //779. K-th Symbol in Grammar
    public int kthGrammar(int n, int k) {

        if(n==1)
            return 0;


        int len=(1<<(n-1));
        System.out.println(len);
        if(k>(len/2))
        {
            /*
            this one liner is better than my mapping
            kthGrammar(n - 1, k - mid) == 1 ? 0 : 1;
             */
            k=k%(len/2);

            if(k==0)
                k=(len/2);

            return kthGrammar(n-1,k)==1?0:1;
        }
        else
            return kthGrammar(n-1,k);
    }

    //1578. Minimum Time to Make Rope Colorful
    public int minCost(String colors, int[] neededTime) {


        char prev=colors.charAt(0);

        int i=0;

        int j=1;

        int n=neededTime.length;

        int minTime=0;

        while(j<n)
        {

            while(j<n && prev==colors.charAt(j))
            {
                /*
                  take a note
                  "aaabbbabbbb"
                  [3,5,10,7,5,3,5,5,4,8,1]
                 */
                minTime+=Math.min(neededTime[i],neededTime[j]);
                if (neededTime[i] < neededTime[j]) {
                    i = j;
                }
                j++;
            }

            if(j<n){
                prev=colors.charAt(j);
                i=j;
                j++;

            }

        }
        return minTime;

    }

    //2149. Rearrange Array Elements by Sign
    public int[] rearrangeArray2149(int[] nums) {


        int i=0;
        int j=1;

        int k=0;

        int n=nums.length;

        int [] ans=new int[n];

        while(k<n)
        {
            if(nums[k]>0)
            {
                ans[i]=nums[k];
                i+=2;
            }
            else
            {

                ans[j]=nums[k];
                j+=2;


            }
            k++;
        }

        return ans;
    }

    //948. Bag of Tokens
    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int score = 0, maxScore = 0;
        int i = 0, j = tokens.length - 1;

        while (i <= j) {
            if (power >= tokens[i]) { // Play tokens[i] face up
                power -= tokens[i++];
                score++;
                maxScore = Math.max(maxScore, score);
            } else if (score > 0) { // Play tokens[j] face down
                power += tokens[j--];
                score--;
            } else {
                break; // Can't play any more tokens
            }
        }

        return maxScore;
    }

    //1750. Minimum Length of String After Deleting Similar Ends
    public int minimumLength(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (i < j && s.charAt(i) == s.charAt(j)) {
            char ch = s.charAt(i);

            while (i <= j && s.charAt(i) == ch) {
                i++;
            }
            while (i <= j && s.charAt(j) == ch) {
                j--;
            }
        }

        return (j < i) ? 0 : j - i + 1;
    }

    //42. Trapping Rain Water
    public int trap(int[] height) {


        int water=0;

        int si=0;
        int ei=height.length-1;
        int lmax=0;
        int rmax=0;
        while(si<ei)
        {
            lmax=Math.max(lmax,height[si]);
            rmax=Math.max(rmax,height[ei]);

            water+= lmax>rmax?rmax-height[ei--]:lmax-height[si++];

        }

        return water;
    }
    public static void main(String[] args) {
     String s="ab#c";
     String s1="ad#c";


/*
0 1
1 2
3 4
3 5
7 8
7 9
9 10

0 1
1 2
3 4
4 5
7 8
8 9
9 10
 */

       // System.out.println(minimumDifference(nums,6));
    }
}


/*
com.assignment.question.LoggerTest.testSetLogFile  Time elapsed: 0.016 s  <<< FAILURE!
org.opentest4j.AssertionFailedError: Setting log file path should be reflected in getLogFile() ==> expected: </tmp/junit6416996473651967313/test7108781242614047375.log> but was: </tmp/junit2493522762416459349/test14901256099438062278.log>
 */