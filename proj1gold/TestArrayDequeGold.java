import static org.junit.Assert.*;

import org.junit.Test;

public class TestArrayDequeGold {
    StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
    ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();

    @Test
    public void testRandom() {
        for (int i = 0; i < 1000; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (!sad.isEmpty() && !ads.isEmpty()) {
                Integer adsRemoveNumber = 1;
                Integer sadRemoveNumber = 1;
                if (numberBetweenZeroAndOne < 0.25) {
                    ads.addFirst(i);
                    sad.addFirst(i);
                    System.out.println("addFirst(" + i + ")");
                } else if (numberBetweenZeroAndOne < 0.5) {
                    ads.addLast(i);
                    sad.addLast(i);
                    System.out.println("addLast(" + i + ")");
                } else if (numberBetweenZeroAndOne < 0.75) {
                    adsRemoveNumber = ads.removeFirst();
                    sadRemoveNumber = sad.removeFirst();
                    System.out.println("removeFirst()");
                } else {
                    adsRemoveNumber = ads.removeLast();
                    sadRemoveNumber = sad.removeLast();
                    System.out.println("removeLast()");
                }
                assertEquals(sadRemoveNumber, adsRemoveNumber);
            } else {
                if (numberBetweenZeroAndOne < 0.5) {
                    ads.addFirst(i);
                    sad.addFirst(i);
                } else {
                    ads.addLast(i);
                    sad.addLast(i);
                }
            }
        }

    }
}
