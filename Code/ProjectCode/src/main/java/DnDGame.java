import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DnDGame extends Application
{
    private Scene scene;

    private BorderPane borderPane;
    private TextArea chatBox;
    private MenuBar menuBar;
    private Menu menu = new Menu("Options");
    private MenuItem menuExit = new MenuItem("Exit");

    private StackPane pane;

    private GameBoard gameBoard;

    public static void main(String[] args) { launch(args); }

    public void makeMenu()
    {
        //Create MenuBar
        menuBar = new MenuBar();

        //Add menu items to menu
        menu.getItems().addAll(menuExit);
        menuBar.getMenus().addAll(menu);
    }

    public void start(Stage primaryStage) throws Exception
    {
        makeMenu();

        //Create background for BorderPane
        Image background = new Image("background.png");
        BackgroundImage backgroundImage1 = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background gameBG = new Background(backgroundImage1);

        //BorderPane sits on top of the Scene
        borderPane = new BorderPane();
        borderPane.setPadding(new Insets(0, 0, 0, 0));
        borderPane.setBackground(gameBG);
        borderPane.setTop(menuBar);

        //Create the chat box
        chatBox = new TextArea();
        chatBox.setStyle("-fx-opacity: 0.70; -fx-font-weight:bold");
        chatBox.setWrapText(true);
        chatBox.setEditable(false);
        chatBox.setPrefWidth(310);
        borderPane.setLeft(chatBox);

        //Pane sits on top of the BorderPane
        pane = new StackPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setCenter(pane);

        //Create a game board which has a Grid Pane that sits on top of the Pane
        gameBoard = new GameBoard();
        gameBoard.getGameBoard().setAlignment(Pos.CENTER);
        pane.getChildren().addAll(gameBoard.getGameBoard());
        pane.setAlignment(Pos.CENTER);

        //Create scene and add BorderPane on top of it
        scene = new Scene(borderPane, 1600, 960);
        primaryStage.setTitle("DnD");
        primaryStage.setScene(scene);
        primaryStage.show();

        //If the GridPane is clicked on, then output to the chat window if applicable
        gameBoard.setOnMouseClicked(MouseEvent ->
        {
            //Gets the current tile state of the board used for printing messages
            int state = gameBoard.getState();

            if (state == 1)
                chatBox.appendText("Moved to a tile!\n");
            else if (state == 2)
                chatBox.appendText("Bumped into a wall!\n");
            else if (state == 3)
                chatBox.appendText("You found a key in the chest!\n");
            else if (state == 4)
                chatBox.appendText("You've found the door!\n");

            //Reset state to 0
            gameBoard.setState();
        });
    }
}
