/*
 * ListNode addTwoNumbers.addTwo(ListNode head1, ListNode head2)
 * */

import org.junit.*;
import org.junit.Assert;

public class addTwoNumbersUnitTest{
    private addTwoNumbers game;

    @Before
    public void setUp(){
        game = new addTwoNumbers();
    }

    @Test
    public void positiveTest_01(){
        // a = 98
        addTwoNumbers.ListNode p1 = game.new ListNode(8);
        p1.next = game.new ListNode(9);
        // b = 432
        addTwoNumbers.ListNode p2 = game.new ListNode(2);
        p2.next = game.new ListNode(3);
        p2.next.next = game.new ListNode(4);

        addTwoNumbers.ListNode result = game.addTwo(p1, p2);

        // c = 530
        int[] expected = new int[]{0, 3, 5};
        assertListValues(expected, result);
    }

    @Test
    public void positiveTest_02(){
        // a = 8
        addTwoNumbers.ListNode p1 = game.new ListNode(8);
        // b = 5
        addTwoNumbers.ListNode p2 = game.new ListNode(5);

        addTwoNumbers.ListNode result = game.addTwo(p1, p2);

        // c = 13
        int[] expected = new int[]{3, 1};
        assertListValues(expected, result);
    }

    @Test
    public void edgeTest(){
        // a = 19
        addTwoNumbers.ListNode p1 = game.new ListNode(9);
        p1.next = game.new ListNode(1);
        // b = 87
        addTwoNumbers.ListNode p2 = game.new ListNode(7);
        p2.next = game.new ListNode(8);

        addTwoNumbers.ListNode result = game.addTwo(p1, p2);

        // c = 106
        int[] expected = new int[]{6, 0, 1};
        assertListValues(expected, result);
    }

    @Test
    public void nullTest(){
        addTwoNumbers.ListNode p1 = game.new ListNode(9);
        p1.next = game.new ListNode(1);

        addTwoNumbers.ListNode p2 = null;

        addTwoNumbers.ListNode result = game.addTwo(p1, p2);

        int[] expected = new int[]{9, 1};
        assertListValues(expected, result);
    }

    @Test
    public void bothnegativeTest(){
        // a = -5
        addTwoNumbers.ListNode p1 = game.new ListNode(-5);
        // b = -8
        addTwoNumbers.ListNode p2 = game.new ListNode(-8);

        addTwoNumbers.ListNode result = game.addTwo(p1, p2);

        // c = -13
        int[] expected = new int[]{-3, 1};
        assertListValues(expected, result);
    }

    @Test
    public void negativeTest_01(){
        // a = -95
        addTwoNumbers.ListNode p1 = game.new ListNode(-5);
        p1.next = game.new ListNode(9);
        // b = 38
        addTwoNumbers.ListNode p2 = game.new ListNode(8);
        p2.next = game.new ListNode(3);

        addTwoNumbers.ListNode result = game.addTwo(p1, p2);

        // c = -57
        int[] expected = new int[]{-7, 5};
        assertListValues(expected, result);
    }

    @Test
    public void negativeTest_02(){
        // a = -95
        addTwoNumbers.ListNode p1 = game.new ListNode(-5);
        p1.next = game.new ListNode(9);
        // b = 38
        addTwoNumbers.ListNode p2 = game.new ListNode(8);
        p2.next = game.new ListNode(3);

        addTwoNumbers.ListNode result = game.addTwo(p2, p1);

        // c = -57
        int[] expected = new int[]{-7, 5};
        assertListValues(expected, result);
    }

    @Test
    public void negativeTest_11(){
        // a = -81
        addTwoNumbers.ListNode p1 = game.new ListNode(-1);
        p1.next = game.new ListNode(8);
        // b = 93
        addTwoNumbers.ListNode p2 = game.new ListNode(3);
        p2.next = game.new ListNode(9);

        addTwoNumbers.ListNode result = game.addTwo(p1, p2);

        // c = 12
        int[] expected = new int[]{2, 1};
        assertListValues(expected, result);
    }

    @Test
    public void negativeTest_12(){
        // a = -81
        addTwoNumbers.ListNode p1 = game.new ListNode(-1);
        p1.next = game.new ListNode(8);
        // b = 93
        addTwoNumbers.ListNode p2 = game.new ListNode(3);
        p2.next = game.new ListNode(9);

        addTwoNumbers.ListNode result = game.addTwo(p2, p1);

        // c = 12
        int[] expected = new int[]{2, 1};
        assertListValues(expected, result);
    }

    @Test
    public void zeroTest_01(){
        // a = -81
        addTwoNumbers.ListNode p1 = game.new ListNode(-1);
        p1.next = game.new ListNode(8);
        // b = 81
        addTwoNumbers.ListNode p2 = game.new ListNode(1);
        p2.next = game.new ListNode(8);

        addTwoNumbers.ListNode result = game.addTwo(p1, p2);

        // c = 0
        int[] expected = new int[]{0};
        assertListValues(expected, result);
    }

    @Test
    public void zeroTest_02(){
        // a = -81
        addTwoNumbers.ListNode p1 = game.new ListNode(-1);
        p1.next = game.new ListNode(8);
        // b = 0
        addTwoNumbers.ListNode p2 = game.new ListNode(0);

        addTwoNumbers.ListNode result = game.addTwo(p1, p2);

        // c = -81
        int[] expected = new int[]{-1, 8};
        assertListValues(expected, result);
    }

    @Test
    public void zeroTest_03(){
        // a = -81
        addTwoNumbers.ListNode p1 = game.new ListNode(-1);
        p1.next = game.new ListNode(8);
        // b = 0
        addTwoNumbers.ListNode p2 = game.new ListNode(0);

        addTwoNumbers.ListNode result = game.addTwo(p2, p1);

        // c = -81
        int[] expected = new int[]{-1, 8};
        assertListValues(expected, result);
    }

    private boolean assertListValues(int[] expected, addTwoNumbers.ListNode head){
        addTwoNumbers.ListNode h = head;
        for(int v : expected){
            Assert.assertNotNull(h);
            Assert.assertEquals(v, h.val);
            h = h.next;
        }
        return true;
    }

    @After
    public void settleDown(){
        game = null;
    }
}
