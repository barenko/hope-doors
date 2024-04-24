package hope;

import lombok.Builder;

import java.util.List;
import java.util.Random;

@Builder
public class User {
    Random rd;

    public Door chooseDoor(List<Door> doors){
        return doors.get(rd.nextInt(doors.size()));
    }
}
