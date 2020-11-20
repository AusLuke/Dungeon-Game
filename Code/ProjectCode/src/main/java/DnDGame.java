import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DnDGame extends Application
{
    //Initialize all scenes
    private Scene opening;
    private Scene scene;
    private Scene menuScene;
    private Scene aboutUsScene;

    private BorderPane borderPane;
    private TextArea chatBox;
    private TextField commandBar;
    private MenuBar menuBar;
    private Menu menu = new Menu("Options");
    private MenuItem menuExit = new MenuItem("Exit");

    //Create StackPane and children nodes for the opening scene
    private StackPane openingPane;
    private Button playButton = new Button();
    private Button muteButton1 = new Button();

    //Create StackPane and children nodes for the menu scene
    private StackPane menuPane;
    private VBox menuOptions = new VBox();
    private Button playNow = new Button("Play now!");
    private Button aboutUs = new Button("About Us");
    private Button exit = new Button("Exit");
    private Button back = new Button();
    private Button muteButton2 = new Button();

    //Create StackPane and children nodes for the "about us" scene
    private StackPane aboutUsPane;
    private Text aboutUsText;

    //Add music for opening scene, menu, and SFX for buttons
    Media introMusic = new Media(getClass().getClassLoader().getResource("introMusic.mp3").toString());
    MediaPlayer introTrack = new MediaPlayer(introMusic);
    Media menuMusic = new Media(getClass().getClassLoader().getResource("elevatorMusic.mp3").toString());
    MediaPlayer menuTrack = new MediaPlayer(menuMusic);
    Media hover = new Media(getClass().getClassLoader().getResource("FFXIV_Enter_Chat.mp3").toString());
    MediaPlayer trackHover= new MediaPlayer(hover);
    Media enter = new Media(getClass().getClassLoader().getResource("FFXIV_Confirm.mp3").toString());
    MediaPlayer trackConfirm = new MediaPlayer(enter);

    Media intro = new Media(getClass().getClassLoader().getResource("Dragon Intro.mp4").toString());
    MediaPlayer introVideo = new MediaPlayer(intro);
    MediaView mediaView = new MediaView(introVideo);

    private StackPane pane;

    private Level1 level1;
    private Level2 level2 = new Level2();
    private Level3 level3 = new Level3();
    private int currentLevel;

    private boolean messages = true;

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
        //Switch for music
        final boolean[] flip = {true};

        makeMenu();

        //Create background for BorderPane
        Image background = new Image("background.png");
        BackgroundImage backgroundImage1 = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background gameBG = new Background(backgroundImage1);

        //BorderPane sits on top of the Scene
        borderPane = new BorderPane();
        borderPane.setPadding(new Insets(0, 0, 10, 0));
        borderPane.setBackground(gameBG);
        borderPane.setTop(menuBar);

        //Create the chat box
        chatBox = new TextArea();
        chatBox.setStyle("-fx-opacity: 0.70; -fx-font-weight:bold");
        chatBox.setWrapText(true);
        chatBox.setEditable(false);
        chatBox.setPrefWidth(240);
        chatBox.appendText("Welcome to level 1!\n");
        borderPane.setLeft(chatBox);

        //Create the command bar
        commandBar = new TextField();
        commandBar.setStyle("-fx-opacity: 0.70; -fx-font-weight:bold");
        commandBar.setPrefHeight(25);
        commandBar.setAlignment(Pos.CENTER);
        commandBar.appendText("Type commands here!");
        borderPane.setBottom(commandBar);

        //Pane sits on top of the BorderPane
        pane = new StackPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setCenter(pane);

        //Create a game board which has a Grid Pane that sits on top of the Pane
        level1 = new Level1();
        level1.getGameBoard().setAlignment(Pos.CENTER);
        pane.getChildren().addAll(level1.getGameBoard());
        pane.setAlignment(Pos.CENTER);
        currentLevel = 1;

        primaryStage.setTitle("DnD");

        //Setting up the video intro and mute button
        introVideo.setAutoPlay(true);
        introVideo.setCycleCount(MediaPlayer.INDEFINITE);
        introVideo.setMute(true);

        //Create background for play button and mute
        playButton.setPrefSize(400,420);
        BackgroundImage playBanner = new BackgroundImage(new Image("dragonBanner.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background playBG = new Background(playBanner);
        playButton.setBackground(playBG);

        muteButton1.setPrefSize(50,50);
        muteButton2.setPrefSize(50,50);
        BackgroundImage volumeImage = new BackgroundImage(new Image("volume.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background volumeBG = new Background(volumeImage);
        muteButton1.setBackground(volumeBG);
        muteButton2.setBackground(volumeBG);

        //Background for mute image
        BackgroundImage muteImage = new BackgroundImage(new Image("mute.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(0.80, 0.80, true, true, false, false));
        Background muteBG = new Background(muteImage);

        //Display the opening screen
        openingPane = new StackPane(mediaView, playButton, muteButton1);
        StackPane.setAlignment(playButton, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(muteButton1, Pos.BOTTOM_RIGHT);
        opening = new Scene(openingPane, 1900 ,900);
        primaryStage.setScene(opening);
        primaryStage.show();

        //Display menu options
        menuOptions.getChildren().addAll(playNow, aboutUs, exit);
        menuOptions.setAlignment(Pos.CENTER);
        menuPane = new StackPane(menuOptions, muteButton2);
        StackPane.setAlignment(muteButton2, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(menuOptions, Pos.BOTTOM_RIGHT);

        //Create background for main menu
        Image backgroundMenu = new Image("menuscreen.jpg");
        BackgroundImage backgroundImageMenu = new BackgroundImage(backgroundMenu, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background gameBGMenu = new Background(backgroundImageMenu);
        menuPane.setBackground(gameBGMenu);

        //Change size and create the option banners
        playNow.setPrefSize(200.0,70.0);
        aboutUs.setPrefSize(200.0,70.0);
        exit.setPrefSize(200.0,70.0);
        Font font = Font.font("Britannic Bold",FontWeight.BOLD,20.0);
        playNow.setFont(font);
        aboutUs.setFont(font);
        exit.setFont(font);


        Image image = new Image("banner.png");
        BackgroundImage backgroundImageOption = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background gameBGOption = new Background(backgroundImageOption);
        playNow.setBackground(gameBGOption);
        aboutUs.setBackground(gameBGOption);
        exit.setBackground(gameBGOption);

        aboutUsPane = new StackPane(back);
        aboutUsText = new Text();
        aboutUsText.setFont(Font.font("Times New Roman MS", FontWeight.EXTRA_BOLD,22));
        aboutUsText.setTextAlignment(TextAlignment.LEFT);


        aboutUsPane = new StackPane(aboutUsText, back);
        StackPane.setAlignment(aboutUsText, Pos.TOP_LEFT);
        StackPane.setAlignment(back, Pos.BOTTOM_CENTER);

        scene = new Scene(menuPane, 1900, 900);
        menuScene = new Scene(borderPane, 1900, 900);
        aboutUsScene = new Scene(aboutUsPane, 1900, 900);
        final String test = "About Us:\n" +
                "\n" +
                "We are Sam Alammar, Luke Austin, Alex Choi, and  Andrew Macatangay. We are a  group of game developers from \n" +
                "Chicago, Illinois. Collectively, we are known as Bohn Jell Entertainment.\n" +
                "\n" +
                "Welcome... to the Dankest Dungeon.\n" +
                "You are Trogdor, a dragon who thirsts for gold and blood. You must descend through the dungeons, surviving as \n" +
                "many levels as you can, and collecting as much gold as you can until you are satisfied with your bounty... or die.\n\n" +
                "Good Luck.";
        introTrack.setVolume(0.50);
        introTrack.setCycleCount(MediaPlayer.INDEFINITE);
        introTrack.play();

        menuTrack.setVolume(0.50);
        menuTrack.setCycleCount(MediaPlayer.INDEFINITE);


        //If the play button is clicked on, then show menu options
        playButton.setOnAction(actionEvent ->
        {
            // Confirm SFX
            trackConfirm.setVolume(0.50);
            trackConfirm.seek(Duration.ZERO);
            trackConfirm.play();

            //Create scene and add BorderPane on top of it
            primaryStage.setScene(scene);
            primaryStage.show();
            introTrack.stop();

            if (flip[0])
                menuTrack.play();
        });

        //If the play button is clicked on, then start level 1
        playNow.setOnAction(actionEvent ->
        {
            trackConfirm.setVolume(0.05);
            trackConfirm.seek(Duration.ZERO);
            trackConfirm.play();

            //Create scene and add BorderPane on top of it
            primaryStage.setScene(menuScene);
            primaryStage.show();
            menuTrack.stop();
            if (flip[0])
            {
                if (currentLevel == 1)
                    level1.onMusic();
                else if (currentLevel == 2)
                    level2.onMusic();
                else if (currentLevel == 3)
                    level3.onMusic();
            }
        });

        //Create background for about us pane
        BackgroundImage aboutUsImage = new BackgroundImage(new Image("aboutUsPic.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background aboutBG = new Background(aboutUsImage);
        aboutUsText.setStyle("-fx-background-color: rgba(0,0,0,0.7); -fx-font-weight: bold");
        aboutUsPane.setBackground(aboutBG);

        //Create image for back button
        back.setPrefSize(300,320);
        BackgroundImage backImage = new BackgroundImage(new Image("backIMG.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background backBG = new Background(backImage);
        back.setBackground(backBG);


        aboutUs.setOnAction(actionEvent ->
        {
            try
            {
                animateTeletype(test, aboutUsText);
            }
            catch (InterruptedException ignored){};

            primaryStage.setScene(aboutUsScene);
            primaryStage.show();
        });

        exit.setOnAction(actionEvent ->
        {
            Platform.exit();
        });

        back.setOnAction(actionEvent ->
        {
            primaryStage.setScene(scene);
            primaryStage.show();
        });

        muteButton1.setOnAction(actionEvent ->
        {

            if (flip[0]) {
                muteButton1.setBackground(muteBG);
                muteButton2.setBackground(muteBG);
                introTrack.pause();
            }
            else {
                muteButton1.setBackground(volumeBG);
                muteButton2.setBackground(volumeBG);
                introTrack.play();
            }

            flip[0] = !flip[0];
        });

        muteButton2.setOnAction(actionEvent ->
        {
            if (flip[0]) {
                muteButton1.setBackground(muteBG);
                muteButton2.setBackground(muteBG);
                menuTrack.pause();
            }
            else {
                muteButton1.setBackground(volumeBG);
                muteButton2.setBackground(volumeBG);
                menuTrack.play();
            }

            flip[0] = !flip[0];
        });

        // Button handlers for various things.
        playNow.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                playNow.setStyle("-fx-text-fill:#5DBFD7;");
                trackHover.seek(Duration.ZERO);
                trackHover.setVolume(0.05);
                trackHover.play();
            }
        });

        playNow.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                playNow.setStyle("-fx-text-fill:BLACK;");
            }
        });

        aboutUs.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                aboutUs.setStyle("-fx-text-fill:#5DBFD7;");
                trackHover.seek(Duration.ZERO);
                trackHover.setVolume(0.05);
                trackHover.play();
            }
        });

        aboutUs.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                aboutUs.setStyle("-fx-text-fill:BLACK;");
            }
        });

        exit.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                exit.setStyle("-fx-text-fill:#5DBFD7;");
                trackHover.seek(Duration.ZERO);
                trackHover.setVolume(0.05);
                trackHover.play();
            }
        });

        exit.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                exit.setStyle("-fx-text-fill:BLACK;");
            }
        });

        menuExit.setOnAction(actionEvent ->
        {
            //Create scene and add BorderPane on top of it
            primaryStage.setScene(scene);
            primaryStage.show();
            if (currentLevel == 1)
                level1.offMusic();
            else if (currentLevel == 2)
                level2.offMusic();
            else if (currentLevel == 3)
                level3.offMusic();

            if (flip[0])
                menuTrack.play();
        });

        //If the GridPane is clicked on, then output to the chat window if applicable
        level1.setOnMouseClicked(MouseEvent ->
        {
            //Gets the current tile state of the board used for printing messages
            int state = level1.getState();

            //Print out the messages if applicable
            if (state == 1 && messages)
                chatBox.appendText("Moved to a tile!\n");
            else if (state == 2 && messages)
                chatBox.appendText("Bumped into a wall!\n");
            else if (state == 3)
                chatBox.appendText("You found a key in the chest!\n");
            else if (state == 4)
                chatBox.appendText("You've found the door!\n");
            else if (state == 5)
            {
                chatBox.appendText("You've opened the door!\n");
                chatBox.clear();

                chatBox.appendText("Welcome to level 2!\n");

                level2.getGameBoard().setAlignment(Pos.CENTER);
                pane.getChildren().removeAll(level1.getGameBoard());
                pane.getChildren().addAll(level2.getGameBoard());
                Image background2 = new Image("levelTwoBackground.jpg");
                BackgroundImage backgroundImage2 = new BackgroundImage(background2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
                Background gameBG2 = new Background(backgroundImage2);
                borderPane.setBackground(gameBG2);
                currentLevel = 2;
                level1.offMusic();
                if (flip[0])
                    level2.onMusic();

            }

            //Reset state to 0
            level1.setState();
        });

        //If the GridPane is clicked on, then output to the chat window if applicable
        level2.setOnMouseClicked(MouseEvent ->
        {
            //Gets the current tile state of the board used for printing messages
            int state = level2.getState();

            //Print out the messages if applicable
            if (state == 1 && messages)
                chatBox.appendText("Moved to a tile!\n");
            else if (state == 2 && messages)
                chatBox.appendText("Bumped into a wall!\n");
            else if (state == 3)
                chatBox.appendText("You found a key in the chest!\n");
            else if (state == 4)
                chatBox.appendText("You've found the door!\n");
            else if (state == 5)
            {
                chatBox.appendText("You've opened the door!\n");

                level3 = new Level3();
                chatBox.clear();
                chatBox.appendText("Welcome to level 3!\n");
                level3.getGameBoard().setAlignment(Pos.CENTER);
                pane.getChildren().removeAll(level2.getGameBoard());
                pane.getChildren().addAll(level3.getGameBoard());
                Image background3 = new Image("level3Background.jpg");
                BackgroundImage backgroundImage3 = new BackgroundImage(background3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
                Background gameBG3 = new Background(backgroundImage3);
                borderPane.setBackground(gameBG3);
                currentLevel = 3;
                level2.offMusic();
                if (flip[0])
                    level3.onMusic();
            }

            //Reset state to 0
            level2.setState();
        });

        //If the GridPane is clicked on, then output to the chat window if applicable
        level3.setOnMouseClicked(MouseEvent ->
        {
            //Gets the current tile state of the board used for printing messages
            int state = level3.getState();

            //Print out the messages if applicable
            if (state == 1 && messages)
                chatBox.appendText("Moved to a tile!\n");
            else if (state == 2 && messages)
                chatBox.appendText("Bumped into a wall!\n");
            else if (state == 3)
                chatBox.appendText("You found a key in the chest!\n");
            else if (state == 4)
                chatBox.appendText("You've found the door!\n");
            else if (state == 5)
            {
                chatBox.appendText("You've opened the door!\n");
                level3.offMusic();
                if (flip[0])
                    level3.onMusic();
            }

            //Reset state to 0
            level3.setState();
        });

        //Clears the command bar when clicked on
        commandBar.setOnMouseClicked(MouseEvent ->
        {
            commandBar.clear();
        });

        //When an input is entered into the command bar, take action
        commandBar.setOnKeyPressed(e -> {if(e.getCode().equals(KeyCode.ENTER))
        {

            String command = commandBar.getText();
            //Commands
            if (command.equals("!commands"))
            {
                chatBox.appendText("\nCommands:\n");
                chatBox.appendText("!inventory\n");
                chatBox.appendText("!pathMessages\n");
                chatBox.appendText("!musicOn or !musicOff\n\n");
            }
            else if (command.equals("!inventory"))
            {
                if (currentLevel == 1)
                    chatBox.appendText(level1.getPlayerInventory());
                else if (currentLevel == 2)
                    chatBox.appendText(level2.getPlayerInventory());
                else if (currentLevel == 3)
                    chatBox.appendText(level3.getPlayerInventory());
            }
            else if (command.equals("!pathMessages"))
            {
                chatBox.appendText((messages) ? "Tile messages off!\n" : "Tile messages on!\n");
                messages = !messages;
            }
            else if (command.equals("!musicOn")) {
                if (currentLevel == 1)
                    level1.onMusic();
                else if (currentLevel == 2)
                    level2.onMusic();
                else if (currentLevel == 3)
                    level3.onMusic();
                flip[0] = true;
                muteButton1.setBackground(volumeBG);
                muteButton2.setBackground(volumeBG);
            }
            else if (command.equals("!musicOff"))
            {
                if (currentLevel == 1)
                    level1.offMusic();
                else if (currentLevel == 2)
                    level2.offMusic();
                else if (currentLevel == 3)
                    level3.offMusic();
                flip[0] = false;
                muteButton1.setBackground(muteBG);
                muteButton2.setBackground(muteBG);
            }
            else
                chatBox.appendText("Command not found!\n");

            //Clear the command bar of the previous message
            commandBar.clear();
        }});
    }
    static Timer timer = null;
    public static void animateTeletype(final String input, final Text text) throws InterruptedException
    {
        final String[] s = new String[1];
        s[0] = " ";
        final int[] i = new int[1];
        i[0] = 0;
        text.setText("");
        StringBuilder txt = new StringBuilder("");
        timer = new Timer(30, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                s[0] = input.substring(i[0], i[0]+1);
                i[0]++;
                txt.append(s[0]);
                text.setText(txt.toString());
                if(text.getText().equals(input))
                    timer.stop();
            }
        });
        timer.start();
    }
}
