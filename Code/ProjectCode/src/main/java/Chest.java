import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

public class Chest extends Tile
{
    boolean visited = false;
    Image image = new Image("chest.jpg");
    ImagePattern imagePattern = new ImagePattern(image);
    Media chestOpen = new Media(getClass().getClassLoader().getResource("chest open.mp3").toString());
    MediaPlayer track = new MediaPlayer(chestOpen);
//    Chest()
//    {
//        this.setWidth(100);
//        this.setHeight(100);
//        this.setFill(Paint.valueOf("#000000"));
//    }

    //If you encounter a new chest, fill it, otherwise return 0 to avoid printing to the chat bar
    public int setImage()
    {
        if (visited) return 0;
        this.setFill(imagePattern);
        visited = true;
        track.setVolume(0.5);
        track.play();
        return 3;
    }
}