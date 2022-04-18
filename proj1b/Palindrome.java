public class Palindrome {

    /**
     * To convert word into Deque.
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> ret = new ArrayDeque<>();

        /** .charAt(int i) can get the i-th character of the string. */
        for (int i = 0; i < word.length(); i++) {
            ret.addLast(word.charAt(i));
        }
        return ret;
    }

    /**
     * Return true if the word is palindrome. Any word of length 1 or 0 is a palindrome.
     */
    public boolean isPalindrome(String word) {
        /** Not use Deque. */
        /*
        int len = word.length();
        for (int i = 0; i < len / 2; i++) {
            if (word.charAt(i) != word.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
        */
        /** Use Deque and recursion. */
        return isPalindrome(wordToDeque(word));
    }

    /**
     * Helper for isPalindrome(the use-Deque&recursion version.
     */
    private boolean isPalindrome(Deque<Character> dq) {
        if (dq.size() == 1 || dq.size() == 0) {
            return true;
        } else if (dq.removeFirst() == dq.removeLast()) {
            return isPalindrome(dq);
        }
        return false;
    }

    /**
     * The method will return true if the word is a palindrome according to
     * the character comparison test provided by the CharacterComparator passed in as argument cc.
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindrome(wordToDeque(word), cc);
    }

    /**
     * Helper.
     */
    private boolean isPalindrome(Deque<Character> dq, CharacterComparator cc) {
        if (dq.size() == 0 || dq.size() == 1) {
            return true;
        } else if (cc.equalChars(dq.removeLast(), dq.removeFirst())) {
            return true;
        }
        return false;
    }
}
