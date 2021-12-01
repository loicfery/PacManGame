package gameplay;

public interface Character extends Entity {

    Team getTeam();
    void setTeam(Team team);
    Direction getDirection();
    void setDirection(Direction direction);
    boolean isAlive();
    boolean isAnimate();
    void setAnimate(boolean isAnimate);
}
