package hope;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Door {
    @Builder.Default
    boolean prize=false;
    @Builder.Default
    boolean open=false;

    public void open(){open=true;}
}
