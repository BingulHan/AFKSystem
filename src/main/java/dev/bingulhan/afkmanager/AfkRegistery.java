package dev.bingulhan.afkmanager;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public final class AfkRegistery {

    @Setter(AccessLevel.NONE)
    private List<Boolean> movementList = new ArrayList<>();

    private boolean afk = false;
    private int afkDuration = 0;

}
