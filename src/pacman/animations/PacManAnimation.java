package pacman.animations;

import engines.graphic.ImageViewEntities;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import pacman.Pacman;

public class PacManAnimation implements EntityAnimation {

    @Override
    public void movementAnimation(ImageViewEntities entity) {
        Pacman pacman = (Pacman) entity.getEntity();
        ImageView image_pacman = entity.getImageView();

        switch (pacman.getDirection()) {
            case North:
                if (entity.getEntity().getPosition().getSceneCaseLink() == null) {
                    new Timeline(
                            new KeyFrame(Duration.seconds(0), event -> image_pacman.setImage(pacman.getTextures().get(0))),
                            new KeyFrame(Duration.seconds(0.025), event -> image_pacman.setLayoutY(image_pacman.getLayoutY() - 4)),
                            new KeyFrame(Duration.seconds(0.05), event -> image_pacman.setLayoutY(image_pacman.getLayoutY() - 4)),
                            new KeyFrame(Duration.seconds(0.075), event -> image_pacman.setLayoutY(image_pacman.getLayoutY() - 4)),
                            new KeyFrame(Duration.seconds(0.1), event -> image_pacman.setLayoutY(image_pacman.getLayoutY() - 4)),
                            new KeyFrame(Duration.seconds(0.1), event -> image_pacman.setImage(pacman.getTextures().get(1))),
                            new KeyFrame(Duration.seconds(0.125), event -> image_pacman.setLayoutY(image_pacman.getLayoutY() - 4)),
                            new KeyFrame(Duration.seconds(0.15), event -> image_pacman.setLayoutY(image_pacman.getLayoutY() - 4)),
                            new KeyFrame(Duration.seconds(0.175), event -> image_pacman.setLayoutY(image_pacman.getLayoutY() - 4)),
                            new KeyFrame(Duration.seconds(0.2), event -> image_pacman.setLayoutY(image_pacman.getLayoutY() - 4)),
                            new KeyFrame(Duration.seconds(0.2), event -> image_pacman.setImage(pacman.getTextures().get(0)))
                    ).play();
                }
                break;
            case East:
                if (pacman.getPosition().getX() == 0 && pacman.getPosition().getY() == 13) {
                    new Timeline(
                            new KeyFrame(Duration.seconds(0), event -> image_pacman.setImage(pacman.getTextures().get(0))),
                            new KeyFrame(Duration.seconds(0.025), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() + 4)),
                            new KeyFrame(Duration.seconds(0.05), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() + 4)),
                            new KeyFrame(Duration.seconds(0.075), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() + 4)),
                            new KeyFrame(Duration.seconds(0.1), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() + 4)),
                            new KeyFrame(Duration.seconds(0.1), event -> image_pacman.setImage(pacman.getTextures().get(1))),
                            new KeyFrame(Duration.seconds(0.125), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() - (25 * 32))),
                            new KeyFrame(Duration.seconds(0.15), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() + 4)),
                            new KeyFrame(Duration.seconds(0.175), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() + 4)),
                            new KeyFrame(Duration.seconds(0.2), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() + 4)),
                            new KeyFrame(Duration.seconds(0.2), event -> image_pacman.setImage(pacman.getTextures().get(0)))
                    ).play();
                } else {
                    new Timeline(
                            new KeyFrame(Duration.seconds(0), event -> image_pacman.setImage(pacman.getTextures().get(0))),
                            new KeyFrame(Duration.seconds(0.025), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() + 4)),
                            new KeyFrame(Duration.seconds(0.05), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() + 4)),
                            new KeyFrame(Duration.seconds(0.075), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() + 4)),
                            new KeyFrame(Duration.seconds(0.1), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() + 4)),
                            new KeyFrame(Duration.seconds(0.1), event -> image_pacman.setImage(pacman.getTextures().get(1))),
                            new KeyFrame(Duration.seconds(0.125), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() + 4)),
                            new KeyFrame(Duration.seconds(0.15), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() + 4)),
                            new KeyFrame(Duration.seconds(0.175), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() + 4)),
                            new KeyFrame(Duration.seconds(0.2), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() + 4)),
                            new KeyFrame(Duration.seconds(0.2), event -> image_pacman.setImage(pacman.getTextures().get(0)))
                    ).play();
                }
                break;
            case South:
                new Timeline(
                        new KeyFrame(Duration.seconds(0), event -> image_pacman.setImage(pacman.getTextures().get(0))),
                        new KeyFrame(Duration.seconds(0.025), event -> image_pacman.setLayoutY(image_pacman.getLayoutY() + 4)),
                        new KeyFrame(Duration.seconds(0.05), event -> image_pacman.setLayoutY(image_pacman.getLayoutY() + 4)),
                        new KeyFrame(Duration.seconds(0.075), event -> image_pacman.setLayoutY(image_pacman.getLayoutY() + 4)),
                        new KeyFrame(Duration.seconds(0.1), event -> image_pacman.setLayoutY(image_pacman.getLayoutY() + 4)),
                        new KeyFrame(Duration.seconds(0.1), event -> image_pacman.setImage(pacman.getTextures().get(1))),
                        new KeyFrame(Duration.seconds(0.125), event -> image_pacman.setLayoutY(image_pacman.getLayoutY() + 4)),
                        new KeyFrame(Duration.seconds(0.15), event -> image_pacman.setLayoutY(image_pacman.getLayoutY() + 4)),
                        new KeyFrame(Duration.seconds(0.175), event -> image_pacman.setLayoutY(image_pacman.getLayoutY() + 4)),
                        new KeyFrame(Duration.seconds(0.2), event -> image_pacman.setLayoutY(image_pacman.getLayoutY() + 4)),
                        new KeyFrame(Duration.seconds(0.2), event -> image_pacman.setImage(pacman.getTextures().get(0)))
                ).play();
                break;
            case West:
                if (pacman.getPosition().getX() == 25 && pacman.getPosition().getY() == 13) {
                    new Timeline(
                            new KeyFrame(Duration.seconds(0), event -> image_pacman.setImage(pacman.getTextures().get(0))),
                            new KeyFrame(Duration.seconds(0.025), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() - 4)),
                            new KeyFrame(Duration.seconds(0.05), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() - 4)),
                            new KeyFrame(Duration.seconds(0.075), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() - 4)),
                            new KeyFrame(Duration.seconds(0.1), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() - 4)),
                            new KeyFrame(Duration.seconds(0.1), event -> image_pacman.setImage(pacman.getTextures().get(1))),
                            new KeyFrame(Duration.seconds(0.125), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() + (25 * 32))),
                            new KeyFrame(Duration.seconds(0.15), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() - 4)),
                            new KeyFrame(Duration.seconds(0.175), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() - 4)),
                            new KeyFrame(Duration.seconds(0.2), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() - 4)),
                            new KeyFrame(Duration.seconds(0.2), event -> image_pacman.setImage(pacman.getTextures().get(0)))
                    ).play();
                } else {
                    new Timeline(
                            new KeyFrame(Duration.seconds(0), event -> image_pacman.setImage(pacman.getTextures().get(0))),
                            new KeyFrame(Duration.seconds(0.025), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() - 4)),
                            new KeyFrame(Duration.seconds(0.05), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() - 4)),
                            new KeyFrame(Duration.seconds(0.075), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() - 4)),
                            new KeyFrame(Duration.seconds(0.1), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() - 4)),
                            new KeyFrame(Duration.seconds(0.1), event -> image_pacman.setImage(pacman.getTextures().get(1))),
                            new KeyFrame(Duration.seconds(0.125), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() - 4)),
                            new KeyFrame(Duration.seconds(0.15), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() - 4)),
                            new KeyFrame(Duration.seconds(0.175), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() - 4)),
                            new KeyFrame(Duration.seconds(0.2), event -> image_pacman.setLayoutX(image_pacman.getLayoutX() - 4)),
                            new KeyFrame(Duration.seconds(0.2), event -> image_pacman.setImage(pacman.getTextures().get(0)))
                    ).play();
                }
                break;
        }

    }

    @Override
    public void deadAnimation(ImageViewEntities entity) {
        Pacman pacman = (Pacman) entity.getEntity();
        ImageView image_pacman = entity.getImageView();
        new Timeline(
                new KeyFrame(Duration.seconds(0), event -> image_pacman.setImage(pacman.getTextures().get(0))),
                new KeyFrame(Duration.seconds(0.1), event -> image_pacman.setImage(pacman.getTextures().get(1))),
                new KeyFrame(Duration.seconds(0.2), event -> image_pacman.setImage(pacman.getTextures().get(2))),
                new KeyFrame(Duration.seconds(0.3), event -> image_pacman.setImage(pacman.getTextures().get(3))),
                new KeyFrame(Duration.seconds(0.4), event -> image_pacman.setImage(pacman.getTextures().get(4))),
                new KeyFrame(Duration.seconds(0.5), event -> image_pacman.setImage(pacman.getTextures().get(5))),
                new KeyFrame(Duration.seconds(0.6), event -> image_pacman.setImage(pacman.getTextures().get(6))),
                new KeyFrame(Duration.seconds(0.7), event -> image_pacman.setImage(pacman.getTextures().get(7))),
                new KeyFrame(Duration.seconds(0.8), event -> image_pacman.setImage(pacman.getTextures().get(8))),
                new KeyFrame(Duration.seconds(0.9), event -> image_pacman.setImage(pacman.getTextures().get(9))),
                new KeyFrame(Duration.seconds(1.1), event -> image_pacman.setImage(pacman.getTextures().get(10))),
                new KeyFrame(Duration.seconds(1.3), event -> image_pacman.setVisible(false))
        ).play();
    }
}
