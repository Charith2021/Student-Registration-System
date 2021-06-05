package controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;


public class MainFormController {

    public  static final int NAV_ICON_NONE=0;
    public  static final int NAV_ICON_BACK=1;
    public  static final int NAV_ICON_HOME=2;


    public ImageView imgMinimize;
    public ImageView imgClose;
    public AnchorPane pneAppBar;
    public Label lblTitle;
    public AnchorPane pneStage;
    public ImageView imgNav;
    private double xMousePos;
    private double yMousePos;


    public void initialize() throws IOException {
        initWindow();
    }

    public  void  navigate(String title,String url,int icon){

        try {
          Parent  root = FXMLLoader.load(this.getClass().getResource(url));
            pneStage.getChildren().clear();
            pneStage.getChildren().add(root);
            lblTitle.setText(title);

            Stage primaryStage =  (Stage) (pneStage.getScene().getWindow());

            Platform.runLater(() -> {
                primaryStage.sizeToScene();
                primaryStage.centerOnScreen();
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void initWindow() {
        lblTitle.setMouseTransparent(true);
        //imgNav.setVisible(false);
        Platform.runLater(() -> lblTitle.setText(((Stage) (imgClose.getScene().getWindow())).getTitle()));
        pneAppBar.setOnMousePressed(event -> {
            xMousePos = event.getX();
            yMousePos = event.getY();
        });

        pneAppBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown()) {
                    Window mainWindow = imgMinimize.getScene().getWindow();  //imgMinimize wa aran tynne a anchor pane eka athule tyana ona ma element ekak via access krnna plwn scene,window
                    mainWindow.setX(event.getScreenX() - xMousePos);
                    mainWindow.setY(event.getScreenY() - yMousePos);

                }

            }
        });

        imgClose.setOnMouseEntered(event -> imgClose.setImage(new Image("asset/icons/close.png")));
        imgClose.setOnMouseExited(event -> imgClose.setImage(new Image("asset/icons/close-1.png")));
        imgClose.setOnMouseClicked(event -> System.exit(0));
        //Or
        // imgClose.setOnMouseClicked(event -> ((Stage)(imgClose.getScene().getWindow())).close());

        imgMinimize.setOnMouseEntered(event -> imgMinimize.setImage(new Image("asset/icons/minimize.png")));
        imgMinimize.setOnMouseExited(event -> imgMinimize.setImage(new Image("asset/icons/minimize-1.png")));
        imgMinimize.setOnMouseClicked(event -> ((Stage) (imgMinimize.getScene().getWindow())).setIconified(true));
    }


}
