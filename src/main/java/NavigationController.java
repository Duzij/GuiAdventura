import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logika.Room;

import java.net.URL;
import java.util.ResourceBundle;

public class NavigationController  implements Initializable {
    public ImageView navigation;

    void SetNagivationImage(Room room)
    {
        if (room.getName().equals("cela"))
        {
            Image img= new Image("rooms/cela.png", 960,540,false, false);
            navigation.setImage(img);
        }

        if (room.getName().equals("pred_celou"))
        {
            Image img= new Image("rooms/pred_celou.png", 960,540,false, false);
            navigation.setImage(img);
        }

        if (room.getName().equals("toalety"))
        {
            Image img= new Image("rooms/toalety.png", 960,540,false, false);
            navigation.setImage(img);
        }

        if (room.getName().equals("kabinka"))
        {
            Image img= new Image("rooms/kabinka.png", 960,540,false, false);
            navigation.setImage(img);
        }

        if (room.getName().equals("vychazkovy_prostor"))
        {
            Image img= new Image("rooms/vychazkovy_prostor.png", 960,540,false, false);
            navigation.setImage(img);
        }

        if (room.getName().equals("misto_pro_posilovani"))
        {
            Image img= new Image("rooms/posilovani.png", 960,540,false, false);
            navigation.setImage(img);
        }
        if (room.getName().equals("skupinka_veznu"))
        {
            Image img= new Image("rooms/skupinka_veznu.png", 960,540,false, false);
            navigation.setImage(img);
        }
        if (room.getName().equals("hra_kamen_nuzky"))
        {
            Image img= new Image("rooms/hra_kamen_nuzky.png", 960,540,false, false);
            navigation.setImage(img);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
