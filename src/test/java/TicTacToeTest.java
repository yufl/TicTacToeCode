import com.interview.TicTacToe;
import org.junit.Test;
import org.junit.Assert;

public class TicTacToeTest {

    @Test
    public void testIsSpaceOccupied() {
        TicTacToe ticTacToe = new TicTacToe();
        try {
            Assert.assertFalse(ticTacToe.isSpaceOccupied(1,2));
            ticTacToe.xPlace(1, 2);
            Assert.assertTrue(ticTacToe.isSpaceOccupied(1,2));
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
        try {
            ticTacToe.isSpaceOccupied(3,2);
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
        try {
            ticTacToe.isSpaceOccupied(1,3);
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testXPlace() {
        TicTacToe ticTacToe = new TicTacToe();
        try {
            Assert.assertFalse(ticTacToe.isSpaceOccupied(1,2));
            ticTacToe.xPlace(1, 2);
            Assert.assertTrue(ticTacToe.isSpaceOccupied(1,2));
        } catch (Exception e) {
            Assert.assertTrue(false);
        }

        try {
            ticTacToe.xPlace(1, 2);
        } catch (IllegalStateException e) {
            Assert.assertTrue(true);
        }
        try {
            ticTacToe.xPlace(3,2);
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
        try {
            ticTacToe.xPlace(1,3);
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testOPlace() {
        TicTacToe ticTacToe = new TicTacToe();
        try {
            Assert.assertFalse(ticTacToe.isSpaceOccupied(1,2));
            ticTacToe.oPlace(1, 2);
            Assert.assertTrue(ticTacToe.isSpaceOccupied(1,2));
        } catch (Exception e) {
            Assert.assertTrue(false);
        }

        try {
            ticTacToe.oPlace(1, 2);
        } catch (IllegalStateException e) {
            Assert.assertTrue(true);
        }
        try {
            ticTacToe.oPlace(3,2);
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
        try {
            ticTacToe.oPlace(1,3);
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testGetGameResult() {
        TicTacToe ticTacToe = new TicTacToe();
        Assert.assertEquals(ticTacToe.getGameResult(), -1l);
        ticTacToe.xPlace(0,0);
        ticTacToe.xPlace(0,1);
        ticTacToe.xPlace(0,2);
        Assert.assertEquals(ticTacToe.getGameResult(), 1l);
        ticTacToe = new TicTacToe();
        ticTacToe.oPlace(0,0);
        ticTacToe.oPlace(0,1);
        ticTacToe.oPlace(0,2);
        Assert.assertEquals(ticTacToe.getGameResult(), 2l);
        ticTacToe = new TicTacToe();
        ticTacToe.oPlace(0,0);
        ticTacToe.xPlace(0,1);
        ticTacToe.xPlace(0,2);

        ticTacToe.xPlace(1,0);
        ticTacToe.oPlace(1,1);
        ticTacToe.oPlace(1,2);

        ticTacToe.oPlace(2,0);
        ticTacToe.xPlace(2,1);
        ticTacToe.xPlace(2,2);
        ticTacToe.printSpace();
        System.out.println(ticTacToe.getGameResult());
        Assert.assertEquals(ticTacToe.getGameResult(), 0l);
    }
}
