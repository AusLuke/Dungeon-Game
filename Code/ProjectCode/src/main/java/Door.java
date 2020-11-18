import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

public class Door extends Tile
{
    boolean visited = false;
    Image image = new Image("door.jpg");
    ImagePattern imagePattern = new ImagePattern(image);
    Media doorOpen = new Media(getClass().getClassLoader().getResource("open door.mp3").toString());
    MediaPlayer track = new MediaPlayer(doorOpen);
//    Door()
//    {
//        this.setWidth(100);
//        this.setHeight(100);
//        this.setFill(Paint.valueOf("#000000"));
//    }

    //If you encounter a new door, fill it, otherwise return 0 to avoid printing to the chat bar
    public int setImage()
    {
        this.setFill(imagePattern);
        if (!visited)
        {
            visited = !visited;
            return 0;
        }
        track.setVolume(0.75);
        track.play();
        return 4;
    }

}