package recursionAndBackTracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class practice {


    //leetcode 1239
    static int  len1=1;

    public static void maxLength(List<String> arr, int len, int idx, int[] mask) {


        if (idx == arr.size()) {

            if (len > len1)
                len1 = len;

            return;
        }
        System.out.println(idx);
        for (int i = idx; i < arr.size(); i++) {
            String s = arr.get(i);

            boolean res = true;

            for (int j = 0; j < s.length(); j++) {
                char ch = s.charAt(j);

                if (mask[ch - 'a'] > 0)
                    res = false;

                mask[ch - 'a']++;

            }

            if (res == false) {
                for (int j = 0; j < s.length(); j++) {
                    char ch = s.charAt(j);
                    mask[ch - 'a']--;
                }

                if (len > len1)
                    len1 = len;

                continue;

            }

            System.out.println(s+ " "+res);

            maxLength(arr, len + s.length(), i + 1, mask);


            for (int j = 0; j < s.length(); j++) {
                char ch = s.charAt(j);
                mask[ch - 'a']--;
            }

        }
    }

    public static int maxLength(List<String> arr) {

        int[] mask = new int[26];
        maxLength(arr, 0, 0, mask);

      return len1;
    }

    public static void main(String[] args) {
        List<String> arr = List.of("un","iq","ue");

        System.out.println(maxLength(arr));


    }
}
