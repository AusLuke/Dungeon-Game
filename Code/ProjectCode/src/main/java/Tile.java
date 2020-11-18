import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle
{
    Image image = new Image("grass.png");
    ImagePattern imagePattern = new ImagePattern(image);
    Image image2 = new Image("fog.png");
    ImagePattern imagePattern2 = new ImagePattern(image2);
    int tileStatus = 1;
    Media doorOpen = new Media(getClass().getClassLoader().getResource("walk.wav").toString()); // Sound effect, you can see it in every object that has a sound effect
    MediaPlayer track = new MediaPlayer(doorOpen);

    Tile()
    {
        this.setWidth(100);
        this.setHeight(100);
        this.setFill(imagePattern2);
    }

    //Flagged constructor for swapping tiles with the player
    Tile(int flag)
    {
        System.out.print("Shar muta");
        tileStatus = 0;
        this.setWidth(100);
        this.setHeight(100);
        track.setVolume(0.75);
        track.play();
        this.setFill(imagePattern);
    }

    //Return if the tile is new (1) or not (0)
    public int getStatus()
    {
        return tileStatus;
    }
}
