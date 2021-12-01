package pacman;

import engines.graphic.ImageViewEntities;
import engines.physic.Collision;
import gameplay.*;
import pacman.animations.PacManAnimation;

public class ThreadPacman extends Thread implements ThreadEntity {

    private Pacman pacman;
    private long time = 200;
    private Movement movement;
    private boolean end = false;
    private Game game;
    private Movement futureMovement = null;
    private Collision collision = null;
    private ImageViewEntities imageViewEntities;
    private final PacManAnimation pacManAnimation;

    public ThreadPacman(Pacman pacman, Game game) {
        this.pacman = pacman;
        this.game = game;
        this.pacManAnimation = new PacManAnimation();
    }

    public synchronized void run() {
        while (!end) {
            System.out.println();
            if (pacman.getDirection() != Direction.Stop) {
                if (futureMovement != null) {
                    game.treatmentCollisionGame(futureMovement);
                    if (collision == null) {
                        movement = futureMovement;
                        futureMovement = null;
                        pacman.setDirection(futureMovement.getDirection());
                        pacManAnimation.movementAnimation(imageViewEntities);
                    }
                    else{
                        if (collision.getSecondObjectCollision() instanceof NormalFruit || collision.getSecondObjectCollision() instanceof PacgumFruit) {
                            movement = futureMovement;
                            futureMovement = null;
                            pacman.setDirection(movement.getDirection());
                            pacManAnimation.movementAnimation(imageViewEntities);
                            waitMovement();
                            game.getImageViewEntity((Entity) collision.getSecondObjectCollision()).getImageView().setVisible(false);
                        }
                        else{
                            game.treatmentCollisionGame(movement);
                            if(collision != null) {
                                if (collision.getSecondObjectCollision() instanceof NormalFruit || collision.getSecondObjectCollision() instanceof PacgumFruit) {
                                    pacman.setDirection(movement.getDirection());
                                    pacManAnimation.movementAnimation(imageViewEntities);
                                    waitMovement();
                                    game.getImageViewEntity((Entity) collision.getSecondObjectCollision()).getImageView().setVisible(false);
                                }
                            }
                            else {
                                pacman.setDirection(movement.getDirection());
                                pacManAnimation.movementAnimation(imageViewEntities);
                            }
                        }

                    }
                }
                else {
                    if (movement != null) {
                        game.treatmentCollisionGame(movement);
                        if (collision == null) {
                            pacman.setDirection(movement.getDirection());
                            pacManAnimation.movementAnimation(imageViewEntities);
                            waitMovement();
                        }
                        else{
                            if(collision.getSecondObjectCollision() instanceof NormalFruit || collision.getSecondObjectCollision() instanceof PacgumFruit){
                                pacman.setDirection(movement.getDirection());
                                pacManAnimation.movementAnimation(imageViewEntities);
                                waitMovement();
                                game.getImageViewEntity((Entity) collision.getSecondObjectCollision()).getImageView().setVisible(false);
                            }
                        }
                    }
                }
            }
             else {
                if (futureMovement == null) {
                    movement = null;
                    try {
                        while (movement == null) {
                            wait();
                        }
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
                else {
                    if (collision != null) {
                        pacman.setDirection(movement.getDirection());
                    }
                }
            }
            try {
                if ( collision == null) {
                    wait(time);
                }
                else{
                    if(collision.getSecondObjectCollision() instanceof PacgumFruit || collision.getSecondObjectCollision() instanceof NormalFruit){
                        wait(time);
                    }
                }
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void waitMovement(){
        try{
            wait(time);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public void setImageViewEntities(ImageViewEntities imageViewEntities) {
        pacman = (Pacman) imageViewEntities.getEntity();
        this.imageViewEntities = imageViewEntities;
    }

    public synchronized void setMovement(Movement movement) {
        if (this.movement != null) {
            futureMovement = movement;

        } else {
            this.movement = movement;
            pacman.setDirection(movement.getDirection());
            notifyAll();
        }
    }

    public Entity getEntity() {
        return pacman;
    }

    public void setCollision(Collision collision) {
        this.collision = collision;
    }
}
