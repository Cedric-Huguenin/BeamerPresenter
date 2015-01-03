package fxbeamerpresenter;

import fxbeamerpresenter.model.Clock;
import fxbeamerpresenter.model.PDFModel;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

/**
 *
 * @author Ced
 */
public class FxBeamerPresenter extends Application {

    private Clock clock;
    private PDFModel pdfModel;

    @FXML
    private Pane pane;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainActivity.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        
        Controller controller = fxmlLoader.<Controller>getController();       
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        // add background image
        ImageView background = new ImageView(new Image(getClass().getResourceAsStream("DigitalClock-background.png")));
        // add digital clock
        clock = new Clock(Color.ORANGERED, Color.rgb(50, 50, 50));
        clock.setLayoutX(45);
        clock.setLayoutY(186);
        clock.getTransforms().add(new Scale(0.83f, 0.83f, 0, 0));
        controller.setClock(clock); 
        // add background and clock to sample
        pane = (Pane) scene.lookup("#pane");
        pane.getChildren().addAll(background, clock);
        //root.getChildren().addAll(background, clock);

        pdfModel = new PDFModel();
        controller.setPdfModel(pdfModel);
        
        controller.initListener();
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
