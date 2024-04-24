package hope;

import lombok.Builder;
import lombok.Getter;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Game implements Runnable {
    public static final String WINNER = "winner";
    public static final String CHANGE_DOOR = "changeDoor";
    List<Door> doors;
    private Random rd;
    private User user;

    @Getter
    private Map<String,Boolean> result;

    @Builder
    public Game(Integer amountOfDoors, Random rd, User user){
        doors = new ArrayList<Door>();
        this.rd = rd;
        this.user = user;
        Integer choosedDoor = rd.nextInt(amountOfDoors);

        for (int i = 0; i < amountOfDoors; i++) {
            doors.add(Door.builder()
                    .prize(choosedDoor == i)
                    .build());
        }
    }

    public static <T> T random(List<T> items, Predicate<? super T> filter, Random rd){
        List<T> filtered = items.stream().filter(filter).collect(Collectors.toList());
        return filtered.get(rd.nextInt(filtered.size()));
    }

    public void run(){
        Door chosenDoor = user.chooseDoor(doors);

        random(doors, d-> !d.isPrize() && !chosenDoor.equals(d), rd).open();

        Door chosenAgainDoor = user.chooseDoor(doors.stream().filter(d->!d.isOpen()).collect(Collectors.toList()));

        Boolean winner = chosenAgainDoor.isPrize();

        result = new HashMap<String, Boolean>();
        result.put(CHANGE_DOOR, !chosenAgainDoor.equals(chosenDoor));
        result.put(WINNER, winner);
    }
}
