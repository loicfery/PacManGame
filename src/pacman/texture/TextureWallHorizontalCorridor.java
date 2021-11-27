package pacman.texture;

import engines.graphic.view.Texture;
import javafx.scene.image.Image;

import java.io.File;

public class TextureWallHorizontalCorridor implements Texture {

    public Image getTexture(){
        return new Image(new File("ressources/wall/corridor/W_horizontal_corridor.gif").toURI().toString());
    }
}