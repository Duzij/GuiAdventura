import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpController implements Initializable {
    public WebView helpView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebEngine webEngine = helpView.getEngine();
        URL url = this.getClass().getResource("help.html");
        webEngine.load(url.toString());
    }
}
