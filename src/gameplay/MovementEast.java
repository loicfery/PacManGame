package gameplay;

public class MovementEast implements Movement {

    private final Entity entity;

    public MovementEast(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public Direction getDirection() {
        return Direction.East;
    }

    public int[] nextPosition() {
        if(entity.getPosition().getSceneCaseLink() != null){
            return new int[]{entity.getPosition().getSceneCaseLink().getX(),entity.getPosition().getSceneCaseLink().getY()};
        }
        return new int[]{entity.getPosition().getX() + 1, entity.getPosition().getY()};
    }
}
