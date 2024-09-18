package twoPointer;

import java.util.Arrays;

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
    public static void main(String[] args) {
      int [] nums ={0,1,0,3,12};

  moveZeroes(nums);


       // System.out.println(minimumDifference(nums,6));
    }
}
