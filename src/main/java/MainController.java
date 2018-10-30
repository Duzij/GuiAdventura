import com.sun.javafx.stage.StageHelper;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import logika.GamePlan;
import logika.IGame;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static impl.org.controlsfx.autocompletion.SuggestionProvider.create;


public class MainController implements Initializable {
    public TextField textField;
    public TextArea textArea;
    public Label directions;
    public ImageView chisel;
    public ImageView hammer;
    public ImageView wallpaper;
    public Label cigaretteCount;
    public ImageView cigarette;

    private SuggestionProvider provider;
    private IGame game;
    private NavigationController controller;
    /**
     *
     * @param game
     */
    void Init(IGame game)
    {
        this.game = game;

        OpenNavigationWindow();

        createNewGame(game);

        Tooltip.install(hammer, new Tooltip("kladivo"));
        Tooltip.install(chisel, new Tooltip("dláto"));
        Tooltip.install(wallpaper, new Tooltip("plakát"));
        Tooltip.install(cigarette, new Tooltip("počet ciraget"));

        textField.setOnKeyPressed(ke -> {
            KeyCode kc = ke.getCode();
            if ((kc.equals(KeyCode.ENTER))) {
                executeCommand();
            }
        });

        //scrolling textArea to bottom
        textArea.textProperty().addListener((ChangeListener<Object>) (observable, oldValue, newValue) -> textArea.setScrollTop(Double.MAX_VALUE));
    }

    private void OpenNavigationWindow() {
        Parent root;
        try {
            if(!IsWindowOpened("Navigace"))
            {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource(
                                "navigation.fxml"
                        )
                );
                root = loader.load();
                Stage stage = new Stage();
                controller = loader.getController();
                stage.setTitle("Navigace");
                stage.setScene(new Scene(root, 960, 560));
                stage.show();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Vytvoří se nová hra
     * @param game
     */
    private void createNewGame(IGame game) {
        setDirections();

        ObservableList autoCompletions = game.getGamePlan().getCommands();
        provider = create(autoCompletions);

        new AutoCompletionTextFieldBinding<>(textField, provider);

        game.getGamePlan().addChangeRoomListener(() -> {setDirections(); setGameNavigation();});
        game.getGamePlan().addPlayerItemsListener(this::setPlayerItems);
        game.getGamePlan().addCigaretteCountListener(() -> cigaretteCount.setText(String.valueOf(game.getGamePlan().getCigarettesCount())));

        Image cigaretteImage = new Image("cigarette.png", 16,16,false, false);
        cigarette.setImage(cigaretteImage);

        Image hammerImage = new Image("hammer.png", 16,16,false, false);
        hammer.setImage(hammerImage);

        Image chilselImage = new Image("chisel.png", 16,16,false, false);
        chisel.setImage(chilselImage);

        Image wallpaperImage = new Image("wallpaper.png", 16,16,false, false);
        wallpaper.setImage(wallpaperImage);

        textArea.setText(game.returnGreetings());


        setGameNavigation();
    }

    private void setGameNavigation() {
        controller.SetNagivationImage(game.getGamePlan().getCurrentRoom());
    }

    /**
     * V UI se nastaví průhlednost jednotlivých klíčových věci, pokud jsou v inverntáři.
     */
    private void setPlayerItems() {
        GamePlan plan = game.getGamePlan();

        if (plan.getPlayerItems().contains("dláto"))
        {
            chisel.setOpacity(1);
        }
        if (plan.getPlayerItems().contains("kladivo"))
        {
            hammer.setOpacity(1);
        }
        if (plan.getPlayerItems().contains("plakát"))
        {
            wallpaper.setOpacity(1);
        }
    }

    /**
     * Metoda nastaví výstupy v UI na aktuální
     */
    private void setDirections()
    {
        directions.setText(this.game.getGamePlan().getDirections());
    }

    @FXML
    public void executeCommand(ActionEvent e)
    {
       executeCommand();
    }

    /**
     * Metoda provede příkaz, který je zadán v textovém poli. Taktéž se musí znovu načíst příkazy pro nápovědu v textovém poli.
     */
    public void executeCommand()
    {
        GamePlan plan = game.getGamePlan();

        if (!plan.getEndOfTheGame())
        {
            textArea.appendText("\n" + game.executeCommand(textField.getText()));
            textArea.appendText("\n");

            textField.clear();

            ObservableList filteredAutoCompletions = game.getGamePlan().getCommands();
            provider.clearSuggestions();
            provider.addPossibleSuggestions(filteredAutoCompletions);
        }
        else {
            closeNavigationWindow();
            textArea.setText(game.returnEpilogue());
        }
    }

    private void closeNavigationWindow() {
        for (Stage window : StageHelper.getStages()) {
            if (window.getTitle().equals("Navigace")) {
                window.close();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //setting focus on command field
        Platform.runLater(() -> textField.requestFocus());
    }



    /**
     * Metoda spustí novou hru
     * @param actionEvent událost
     */
    public void createNewGame(ActionEvent actionEvent) {
        game.startNewGame();
        createNewGame(game);
    }

    /**
     * Nejprve se provede kontrola, zda není okno s nápovědou otevřené a poté se nápověda otevře
     * @param actionEvent událost
     */
    public void showHelp(ActionEvent actionEvent) {
        Parent root;
        try {
            if(!IsWindowOpened("Nápověda"))
            {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("help.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Nápověda");
                stage.setScene(new Scene(root, 450, 450));
                stage.show();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda zjisti, zda je okno s nadpisem jiz otevrene
     * @param windowTitle název okna
     * @return hodnota, zda je okno otevřené
     */
    private boolean IsWindowOpened(String windowTitle) {
        ObservableList<Stage> stages = StageHelper.getStages();
        for (Stage window : stages) {
            if (window.getTitle().equals(windowTitle)) {
                return true;
            }
        }
        return false;
    }

    void shutdown() {
        closeNavigationWindow();
    }
}
