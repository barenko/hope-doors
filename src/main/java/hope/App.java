package hope;

import java.util.Random;

public class App {
    public static void main(String[] args) {
        Random random = new Random();

        User user = User.builder()
                .rd(random)
                .build();

        Game game = Game.builder()
                .rd(random)
                .amountOfDoors(3)
                .user(user)
                .build();

        game.run();

        System.out.println(game.getResult());
    }
}
