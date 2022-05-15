// Time Complexity : O(N * 3^(N/7))
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// Create a list of list of char, where if char is withing braces, put them in the same list
// Othewise the character would be single one in the list
// Now we will write backtracting method, with base case being size of string equal to size of list of list
// As we know all the words would be of same length, only char varying will be the once which are within the braces
// We would generate and store the list as outcome from base case
// Finally we will convert the list to string array and return.
class Solution {
    List<List<Character>> braceContent;
    public String[] expand(String s) {
        braceContent = new ArrayList<>();
        //create the bracecontent
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c >= 'a' && c <='z'){
                List<Character> content = new ArrayList<>();
                content.add(c);
                braceContent.add(content);
            }
            else if(c == '{'){
                i++;
                List<Character> content = new ArrayList<>();
                while(c != '}'){
                    c = s.charAt(i++);
                    int x = (int)c;
                    if(x >= 97 && x <=122){
                        content.add(c);
                    }
                }
                Collections.sort(content);
                braceContent.add(content);
                i--;
            }
        }
        List<String> allWords = new ArrayList<>();
        generateWords(new StringBuilder(), allWords);
        return allWords.toArray(new String[0]);
    }
    void generateWords(StringBuilder curString, List<String> allWords){
        if(curString.length() == braceContent.size()){
            allWords.add(curString.toString());
            return;
        }
        List<Character> currOptions = braceContent.get(curString.length());
        for(char c: currOptions){
            curString.append(c);
            generateWords(curString, allWords);
            curString.deleteCharAt(curString.length()-1);
        }
    }
}