import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import logika.ChangeRoomListener;
import logika.GamePlan;
import logika.IGame;
import org.controlsfx.control.textfield.TextFields;
import org.omg.CORBA.INITIALIZE;

import javax.swing.text.DefaultCaret;
import java.net.URL;
import java.util.ResourceBundle;

import static javax.swing.text.DefaultCaret.ALWAYS_UPDATE;

public class MainController implements Initializable {
    public TextField textField;
    public TextArea textArea;
    public Label directions;

    private IGame game;

    public void Init(IGame game)
    {
        this.game = game;

        game.getGamePlan().addListener(new ChangeRoomListener() {
            @Override
            public void onChange() {
                setDirections();
            }
        });

        textArea.setText(game.returnGreetings());
        TextFields.bindAutoCompletion(textField,game.getGamePlan().getCommands());

        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                KeyCode kc = ke.getCode();
                if ((kc.equals(KeyCode.ENTER))) {
                    executeCommand();
                }
            }
        });

        textArea.textProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue,
                                Object newValue) {
                textArea.setScrollTop(Double.MAX_VALUE);
            }
        });
    }

    public void setDirections()
    {
        directions.setText(this.game.getGamePlan().getDirections());
    }

    @FXML
    public void executeCommand(ActionEvent e)
    {
       executeCommand();
    }

    public void executeCommand()
    {
        GamePlan plan = game.getGamePlan();
        if (!plan.getEndOfTheGame())
        {
            textArea.appendText("\n" + game.executeCommand(textField.getText()));
            textArea.appendText("\n");
            textField.clear();
        }
        else {
            textArea.setText(game.returnEpilogue());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
