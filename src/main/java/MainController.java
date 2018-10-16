import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import logika.IGame;
import org.omg.CORBA.INITIALIZE;

public class MainController {
    public ChoiceBox commandsChoiceBox;
    public TextArea textArea;

    private IGame game;

    public void Init(IGame game)
    {
        this.game = game;
        textArea.setText(game.returnGreetings());
        commandsChoiceBox.setItems(game.getGamePlan().getCommands());
    }

}
