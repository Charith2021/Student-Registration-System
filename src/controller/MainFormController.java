package controller;

import javafx.animation.FadeTransition;
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
import javafx.util.Duration;
import util.AppBarIcon;
import util.NavActionListener;

import java.io.IOException;


public class MainFormController {

    public ImageView imgMinimize;
    public ImageView imgClose;
    public ImageView imgNavv;
    public AnchorPane pneAppBar;
    public Label lblTitle;
    public AnchorPane pneStage;
    private double xMousePos;
    private double yMousePos;
    private  AppBarIcon icon = AppBarIcon.NAV_ICON_NONE;
    private NavActionListener navActionListener=null;  //by default it is null


    public void initialize() throws IOException {
        initWindow();
    }

    public void navigate(String title, String url, AppBarIcon icon){
        navigate(title,url,icon,null);
    }



    public  void  navigate(String title,String url,AppBarIcon icon,NavActionListener navActionListener){

        try {
            imgNavv.setVisible(true);
            this.icon=icon;   ///methanin set karana icon eka uda hadapu variable(icon) eka athulata da gattha
            this.navActionListener=navActionListener;
            switch (icon){
                case  NAV_ICON_NONE:
                    imgNavv.setVisible(false);
                    imgNavv.setUserData(null);  //danata hover ekak tyanam eka ayn krala danawa
                    break;
                case NAV_ICON_HOME:
                    imgNavv.setImage(new Image("/asset/icons/home white.png"));
                    imgNavv.setUserData(new Image("/asset/icons/home.png"));
                    break;
                case NAV_ICON_BACK:
                    imgNavv.setImage(new Image("/asset/icons/arrow white.png"));
                    imgNavv.setUserData(new Image("/asset/icons/arrow_back.png"));
                break;
            }




          Parent  root = FXMLLoader.load(this.getClass().getResource(url));
            pneStage.getChildren().clear();

            FadeTransition ft=new FadeTransition(Duration.millis(250),pneStage);
            ft.setFromValue(0);
            ft.setToValue(1);

            pneStage.getChildren().add(root);
            ft.play();
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
        imgNavv.setVisible(false);
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

        imgNavv.setOnMouseEntered(event -> swapNavIcon());
        imgNavv.setOnMouseExited(event -> swapNavIcon());
        imgNavv.setOnMouseClicked(event -> {
            if(navActionListener !=null){
                navActionListener.handle();
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

    private void swapNavIcon() {
        if(icon != AppBarIcon.NAV_ICON_NONE){
            Image temp= imgNavv.getImage();
            imgNavv.setImage((Image) imgNavv.getUserData());
            imgNavv.setUserData(temp);     //mouse eka gatthama ayeth white wenna
        }
    }
}
