import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

public class Wall extends Tile
{
    boolean visited = false;
    Image image = new Image("rock.jpg");
    ImagePattern imagePattern = new ImagePattern(image);
    Media error = new Media(getClass().getClassLoader().getResource("FFXIV_Error.mp3").toString());
    MediaPlayer track = new MediaPlayer(error);
//    Wall()
//    {
//        this.setWidth(100);
//        this.setHeight(100);
//        this.setFill(Paint.valueOf("#000000"));
//    }

    //If you encounter a new wall, fill it, otherwise return 0 to avoid printing to the chat bar
    public int setImage()
    {
        if (visited) return 0;
        this.setFill(imagePattern);
        visited = true;
        track.setVolume(0.05);
        track.play();
        return 2;
    }
}
