package pacman.texture.items;

import engines.graphic.view.Texture;
import javafx.scene.image.Image;

import java.io.File;

public class TextureFruit implements Texture {
    @Override
    public Image getTexture() {
        return new Image(new File("ressources/textures/items/fruit.gif").toURI().toString());
    }
}
