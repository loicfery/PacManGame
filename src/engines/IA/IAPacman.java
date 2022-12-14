package engines.IA;

import gameplay.*;
import gameplay.Character;
import pacman.Ghost;
import pacman.Pacman;
import scene.SceneCase;
import scene.Wall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class IAPacman implements IAEngine {

    private Ghost ghost;
    private Pacman pacman;
    private SceneCase[][] sceneCase;
    private boolean isAbleToFollow;
    private boolean calledByFollow = false;
    private boolean isAbleToTurnAround;
    private boolean calledByTurnAround = false;
    private boolean isFollowingPM;
    private boolean calledWhenFollowing = false;

    public IAPacman(SceneCase[][] sceneCase) {
        this.sceneCase = sceneCase;
    }

    public IAPacman(Ghost ghost, Pacman pacman, SceneCase[][] sceneCase) {
        this.ghost = ghost;
        this.pacman = pacman;
        this.sceneCase = sceneCase;
        isAbleToFollow = true;
        isAbleToTurnAround = true;
        isFollowingPM = false;
        move();
    }

    /**
     * Méthode temporaire qui permet de générer un mouvement aléatoire pour a un fantôme.
     *
     * @return random Movement
     */
    public synchronized Movement generateRandomMovement(Entity entity) {
        try {
            wait(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        Random random = new Random();

        Direction[] allDirections = Direction.values();
        ArrayList<Direction> directions = new ArrayList<>();
        directions.add(allDirections[0]);
        directions.add(allDirections[1]);
        directions.add(allDirections[2]);
        directions.add(allDirections[3]);


        if (sceneCase[entity.getPosition().getX()][entity.getPosition().getY()].getCaseContent(Wall.class.toString()) != null) {
            for (Object o : sceneCase[entity.getPosition().getX()][entity.getPosition().getY()].getCaseContent(Wall.class.toString())) {
                Wall w = (Wall) o;
                directions.remove(w.getSceneElement());
            }
        }

        assert entity.isCharacter() : "Error : entity not a character";

        switch (directions.get(random.nextInt(directions.size() - 1))) {
            case North:
                ((Character)entity).setDirection(Direction.North);
                return new MovementNorth(entity);
            case East:
                ((Character)entity).setDirection(Direction.East);
                return new MovementEast(entity);
            case South:
                ((Character)entity).setDirection(Direction.South);
                return new MovementSouth(entity);
            case West:
                ((Character)entity).setDirection(Direction.West);
                return new MovementWest(entity);
        }
        return null;
    }

    private void move() { //permet au fantôme de se deplacer
        ghost.setDirection(Direction.North);
        List<Object> walls = sceneCase[ghost.getPosition().getX()][ghost.getPosition().getY()].getCaseContent(Wall.class.toString());
        while (ghost.isAlive()) {
            if (distBeetween() <= 5 && isAbleToFollow) { //ghost va passer dans l'état de déplacement "poursuite"
                isAbleToFollow = false;
                followPM(walls);
                Thread threadFollow = new Thread(this);
                calledByFollow = true;
                threadFollow.start();
            } else { //ghost va rester dans un état de déplacement "classique"
                if (isWallOrCrossRoad(walls)) { //il y a un mur devant ou plusieurs possibilité de chemin alors
                    ArrayList<Direction> directions = initializeDirections();
                    directions = pickGoodCases(directions, walls);
                    pickDirection(directions);
                }
                if (isAbleToTurnAround) {
                    turnAround();
                    isAbleToTurnAround = false;
                    Thread threadTurnAround = new Thread(this);
                    calledByTurnAround = true;
                    threadTurnAround.start();
                }
            }
        }
    }

    private boolean isWallOrCrossRoad(List<Object> walls) {
        if (walls.isEmpty()) return true;
        int wallAround = 4;
        for (Object o : walls) {
            Wall wall = (Wall) o;
            if (wall.getSceneElement() == ghost.getDirection())
                return true;
            wallAround--;
        }
        return wallAround < 2;
    }

    private double distBeetween() { //calculer la distance mathématique entre le fantôme et pacman
        SceneCase ghostPos = ghost.getPosition();
        SceneCase pacmanPos = pacman.getPosition();
        return Math.sqrt(Math.pow(ghostPos.getX() - pacmanPos.getX(), 2) + (Math.pow(ghostPos.getY() - pacmanPos.getY(), 2)));
    }

    private void followPM(List<Object> walls) { //suivre PM en empruntant le chemin le plus court (par Dijkstra ou par indice de position)
        isFollowingPM = true;
        Thread threadWhenImFollowingPM = new Thread(this);
        calledWhenFollowing = true;
        threadWhenImFollowingPM.start();
        while (isFollowingPM) {
            if (isWallOrCrossRoad(walls)) {
                ghost.setDirection(findPM(walls));
            }
        }
    }

    private Direction findPM(List<Object> walls) {
        ArrayList<Direction> directions = initializeDirections();
        for (Object o : walls) {
            Wall wall = (Wall) o;
            if (wall.getSceneElement() == Direction.North) {
                directions.remove(Direction.North);
            }
            if (wall.getSceneElement() == Direction.East) {
                directions.remove(Direction.East);
            }
            if (wall.getSceneElement() == Direction.South) {
                directions.remove(Direction.South);
            }
            if (wall.getSceneElement() == Direction.West) {
                directions.remove(Direction.West);
            }
        }
        if (pacmanBelow()) directions.remove(Direction.North);
        else directions.remove(Direction.South);
        if (pacmanOnRight()) directions.remove(Direction.West);
        else directions.remove(Direction.East);
        return directions.get(0);
    }

    private boolean pacmanOnRight() {
        return ghost.getPosition().getX() < pacman.getPosition().getX();
    }

    private boolean pacmanBelow() {
        return ghost.getPosition().getY() < pacman.getPosition().getY();
    }

    private ArrayList<Direction> initializeDirections() {
        ArrayList<Direction> directions = new ArrayList<>();
        directions.add(Direction.North);
        directions.add(Direction.East);
        directions.add(Direction.South);
        directions.add(Direction.West);
        return directions;
    }

    private ArrayList<Direction> pickGoodCases(ArrayList<Direction> directions, List<Object> walls) { //remove les positions ou le fantôme ne peux pas aller
        directions.remove(ghost.getDirection());
        for (Object o : walls) {
            Wall wall = (Wall) o;
            directions.remove(wall.getSceneElement());
        }
        return directions;
    }

    private void pickDirection(ArrayList<Direction> directions) { //choisir une direction entre celles qui sont possibles
        Random random = new Random();
        ghost.setDirection(directions.get(random.nextInt(directions.size()) - 1));
    }

    private void turnAround() { //le fantôme a une chance faire demi-tour
        Random random = new Random();
        int choice = random.nextInt(3);
        if (choice == 1) { //une chance sur trois de changer de direction
            ghost.setDirection(getOppositeDirection(ghost.getDirection()));
        }
    }

    private Direction getOppositeDirection(Direction direction) {
        if (direction == Direction.North)
            direction = Direction.South;
        else if (direction == Direction.East)
            direction = Direction.West;
        else if (direction == Direction.South)
            direction = Direction.North;
        else if (direction == Direction.West)
            direction = Direction.East;
        return direction;
    }

    @Override
    public void run() {
        try {
            if (calledByFollow) {
                calledByFollow = false;
                Thread.sleep(10000); //il ne peut plus suivre PM pendant 10 secondes
                isAbleToFollow = true;
            }
            if (calledByTurnAround) {
                calledByTurnAround = false;
                Thread.sleep(7000); //il ne peut plus faire demi-tour pendant 7 secondes
                isAbleToTurnAround = true;
            }
            if (calledWhenFollowing) {
                calledWhenFollowing = false;
                Thread.sleep(10000); //il poursuit PM pendant 10 secondes
                isFollowingPM = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
