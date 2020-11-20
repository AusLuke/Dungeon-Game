import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Level2 extends GridPane
{
    Media levelTwoMusic = new Media(getClass().getClassLoader().getResource("Determination OST.mp3").toString());
    MediaPlayer track = new MediaPlayer(levelTwoMusic);
    Player player = new Player();
    private int state;

    Level2()
    {
        track.setVolume(0.05);
        track.setCycleCount(MediaPlayer.INDEFINITE);

        this.setHgap(1);
        this.setVgap(1);

        player.setCoordinates(4, 2);

        //Create map for game board
        for(int x = 0; x < 6; x++)
            for(int y = 0; y < 5; y++)
                this.add(new Tile(), x, y, 1, 1);
        this.add(player, 4, 2);
        this.add(new Wall(), 1, 1);
        this.add(new Wall(), 1, 2);
        this.add(new Wall(), 1, 3);
        this.add(new Wall(), 2, 2);
        this.add(new Wall(), 3, 1);
        this.add(new Wall(), 3, 2);
        this.add(new Wall(), 3, 3);
        this.add(new Wall(), 5, 1);
        this.add(new Wall(), 5, 2);
        this.add(new Wall(), 5, 3);
        this.add(new Door(), 0, 4);
        this.add(new Chest(), 1, 4);

        //If the game board is clicked on, swap the tiles
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
            try
            {
                //Store the node that the use has clicked on and get it's coordinates
                Node source = e.getPickResult().getIntersectedNode();
                Integer colIndex = GridPane.getColumnIndex(source);
                Integer rowIndex = GridPane.getRowIndex(source);

                int playerC = (int) player.getCoordinates().getKey();
                int playerR = (int) player.getCoordinates().getValue();

                //Tile moves: Left, Right, Down, Up
                if (playerC == colIndex - 1 && playerR == rowIndex)
                    swap(source, playerC, playerR, colIndex, rowIndex);
                else if (playerC == colIndex + 1 && playerR == rowIndex)
                    swap(source, playerC, playerR, colIndex, rowIndex);
                else if (playerR == rowIndex - 1 && playerC == colIndex)
                    swap(source, playerC, playerR, colIndex, rowIndex);
                else if (playerR == rowIndex + 1 && playerC == colIndex)
                    swap(source, playerC, playerR, colIndex, rowIndex);
            }
            catch(Exception ignored){}
        });
    }

    //Swaps two tiles if possible
    void swap(Node source, int playerC, int playerR, int colIndex, int rowIndex)
    {
        //Update the state depending on the type of tile and return
        if (source.getClass() == Wall.class)
        {
            state = ((Wall)source).setImage();
            return;
        }
        else if(source.getClass() == Chest.class){
            state = ((Chest) source).setImage();

            //If the chest has been opened, update the player's key status
            if (state == 3)
                player.setKey();
            return;
        }
        else if (source.getClass() == Door.class)
        {
            state = ((Door)source).setImage();

            if(!((Door)source).getVisited()){
                ((Door)source).setVisited();
                return;
            }

            //If the player has a key, open the door, and update the player's key status
            if (player.checkKey())
            {
                state = 5;
                player.setKey();
            }
            return;
        }

        //Find grass tile and see if it is a new tile or has previously been visited
        Node tile = null;

        for (Node x : this.getChildren())
            if (GridPane.getColumnIndex(x) == colIndex && GridPane.getRowIndex(x) == rowIndex)
                tile = x;

        //Swap the player with a tile and update the state
        Player temp = player;
        player.setCoordinates(colIndex, rowIndex);
        this.getChildren().remove(temp);
        this.add(new Tile(1), playerC, playerR, 1, 1);
        this.add(temp, colIndex, rowIndex, 1, 1);
        state = ((Tile)tile).getStatus();
    }

    //Return current game board
    GridPane getGameBoard()
    {
        return this;
    }

    //Get current state of the board or the last tile clicked on
    int getState()
    {
        return state;
    }

    //Reset state to 0 to avoid duplicate messages
    void setState()
    {
        state = 0;
    }

    //Return the string to be printed that displays the player's inventory
    public String getPlayerInventory()
    {
        return player.getInventory();
    }

    public void onMusic()
    {
        track.play();
    }

    public void offMusic()
    {
        track.pause();
    }
}