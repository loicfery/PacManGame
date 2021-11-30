package pacman.texture.pacman;

import pacman.view.Texture;
import javafx.scene.image.Image;

import java.io.File;

public class TexturePacmanDead10 implements Texture {

    public Image getTexture(){
        return new Image(new File("ressources/textures/pacman/dead/pacman_dead10.gif").toURI().toString());
    }
}
