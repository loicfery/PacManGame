package pacman.texture.ghosts;

import engines.graphic.view.Texture;
import javafx.scene.image.Image;

import java.io.File;

public class TextureBlueGhostSouth implements Texture {
    @Override
    public Image getTexture() {
        return new Image(new File("ressources/ghost/blue_ghost/blue_ghost_south.gif").toURI().toString());
    }
}