import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DnDGame extends Application
{
    private Scene scene;
    private BorderPane borderPane;
    private MenuBar menuBar;
    private StackPane pane;
    private GameBoard gameBoard;

    Menu menu = new Menu("Options");
    MenuItem menuExit = new MenuItem("Exit");

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

        Image background = new Image("background.png");
        BackgroundImage backgroundImage1 = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background gameBG = new Background(backgroundImage1);

        //BorderPane sits on top of the Scene
        borderPane = new BorderPane();
        borderPane.setPadding(new Insets(0, 0, 0, 0));
        borderPane.setTop(menuBar);
        borderPane.setBackground(gameBG);

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
    }
}
