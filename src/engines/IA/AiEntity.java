package engines.IA;

import gameplay.Direction;
import gameplay.Entity;
import pacman.Ghost;
import pacman.Pacman;

public class AiEntity implements Runnable{

    private Entity entity;
    private Ghost ghost;
    private Pacman pacman;
    private boolean isAbleToFollow;

    public AiEntity(Entity entity, Ghost ghost, Pacman pacman){
        this.entity = entity;
        this.ghost = ghost;
        this.pacman = pacman;
        isAbleToFollow = true;
        moove();
    }

    private void moove(){
        ghost.setDirection(Direction.North);
        while(ghost.isAlive()){
            if(algoDijkstra() <= 7 && isAbleToFollow){ //ghost va passer dans l'état de déplacement "poursuite"
                followPM();
                isAbleToFollow = false;
                Thread thread = new Thread();
                thread.start();
            }
            else{ //ghost va rester dans un état de déplacement "classique"

            }
        }
    }

    private int algoDijkstra(){
        int distance = 0;


        return distance;
    }

    private void followPM(){

    }

    private void pickDirection(){
        int choice = 1 + (int)(Math.random() * 5);
        switch(choice){
            case 1:
                ghost.setDirection(Direction.North);
                break;
            case 2:
                ghost.setDirection(Direction.East);
                break;
            case 3:
                ghost.setDirection(Direction.South);
                break;
            case 4:
                ghost.setDirection(Direction.West);
                break;
            default :
                System.err.println("Error with direction in AI motor");
                break;
        }
    }


    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            isAbleToFollow = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
