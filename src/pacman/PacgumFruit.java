package pacman;

import Gameplay.Item;
import Gameplay.SceneCase;

public class PacgumFruit implements Item {

    private SceneCase position;

    public PacgumFruit(SceneCase position){
        this.position = position;
    }

    public boolean isCharacter(){
        return false;
    }

    public boolean isItem(){
        return true;
    }

    public boolean canInteractWithItem(){
        return false;
    }

    public boolean isEnemy(){
        return false;
    }

    public SceneCase getPosition(){
        return position;
    }
}
