import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logika.Game;
import logika.IGame;
import uiText.TextInterface;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("main.fxml"));

        Parent root = loader.load();

        MainController c = loader.getController();
        c.Init(new Game());

        primaryStage.setTitle("Adventůra - útěk z vězení");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.setOnHidden(e -> c.shutdown());
        primaryStage.show();
    }


    public static void main(String[] args) {
        IGame game = new Game();

        if (args.length > 0 && args[0].equals("-text"))
        {
            TextInterface ui = new TextInterface(game);
            ui.play();
        }
        else {
            launch(args);
        }
    }
}
