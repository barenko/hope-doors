package hope;

import org.junit.Test;

import java.util.Map;
import java.util.Random;

import static hope.Game.CHANGE_DOOR;
import static hope.Game.WINNER;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AppTest {
    private Map<String,Boolean> scenario(Integer seed){
        Random random = new Random();

        if(seed != 0) random.setSeed(seed);

        User user = User.builder()
                .rd(random)
                .build();

        Game game = Game.builder()
                .rd(random)
                .amountOfDoors(3)
                .user(user)
                .build();

        game.run();

        return game.getResult();
    }

    @Test
    public void test1(){
        Map<String,Boolean> res = scenario(2);

        assertFalse("ChangeDoor wrong", res.get(CHANGE_DOOR));
        assertFalse("Winner wrong", res.get(WINNER));
    }

    @Test
    public void test2(){
        Map<String,Boolean> res = scenario(1);

        assertTrue("ChangeDoor wrong", res.get(CHANGE_DOOR));
        assertTrue("Winner wrong", res.get(WINNER));
    }

    @Test
    public void test3(){
        Map<String,Boolean> res = scenario(8);

        assertTrue("ChangeDoor wrong", res.get(CHANGE_DOOR));
        assertFalse("Winner wrong", res.get(WINNER));
    }

    @Test
    public void test4(){
        Map<String,Boolean> res = scenario(3);

        assertFalse("ChangeDoor wrong", res.get(CHANGE_DOOR));
        assertTrue("Winner wrong", res.get(WINNER));
    }

    @Test
    public void test5(){
        Double changedDoorWinnerCounter = 0D;
        Double notChangedDoorWinnerCounter = 0D;
        Double changedDoorTotal=0D;
        Double notChangedDoorTotal=0D;

        for (int i = 0; i < 1000000; i++) {
            Map<String,Boolean> res = scenario(0);
            if(res.get(WINNER)){
                if(res.get(CHANGE_DOOR)){
                    changedDoorWinnerCounter++;
                } else {
                    notChangedDoorWinnerCounter++;
                }
            }
            if(res.get(CHANGE_DOOR)){
                changedDoorTotal++;
            }else {
                notChangedDoorTotal++;
            }
        }

        Double changedDoorWinnerRate = changedDoorWinnerCounter/changedDoorTotal;
        Double notChangedDoorWinnerRate = notChangedDoorWinnerCounter/notChangedDoorTotal;

        assertTrue("ChangeDoor chance is 66%", changedDoorWinnerRate > .66 && changedDoorWinnerRate < .67);
        assertTrue("NotChangeDoor chance is 33%", notChangedDoorWinnerRate > .33 && notChangedDoorWinnerRate < .34);
    }
}
