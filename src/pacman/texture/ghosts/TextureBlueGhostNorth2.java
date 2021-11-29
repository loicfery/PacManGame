package pacman.texture.ghosts;

import pacman.view.Texture;
import javafx.scene.image.Image;

import java.io.File;

public class TextureBlueGhostNorth2 implements Texture {
    @Override
    public Image getTexture() {
        return new Image(new File("ressources/textures/ghost/blue_ghost/blue_ghost_north_2.gif").toURI().toString());
    }
}
