package pacman.texture;

import engines.graphic.view.Texture;
import javafx.scene.image.Image;

import java.io.File;

public class TextureWallHorizontalRightCrossroad implements Texture {

    public Image getTexture(){
        return new Image(new File("ressources/wall/crossroad/W_horizontal_right_crossroad.gif").toURI().toString());
    }
}