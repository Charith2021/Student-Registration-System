import controller.MainFormController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import redis.clients.jedis.Jedis;
import util.AppBarIcon;
import util.JedisClient;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        JedisClient.getInstance().getClient().shutdown();
    }));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {



        AnchorPane root = FXMLLoader.load(this.getClass().getResource("/view/SplashScreenForm.fxml"));
        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();

     /*   FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/MainForm.fxml"));   //making an object
        Parent root = fxmlLoader.load();
        Scene mainScene=new Scene(root);
        primaryStage.setScene(mainScene);
        MainFormController ctrl=fxmlLoader.getController();
        ctrl.navigate("Student Management System","/view/HomeForm.fxml", AppBarIcon.NAV_ICON_NONE);
        mainScene.setUserData(ctrl);
        mainScene.setFill(Color.TRANSPARENT); //making the scene transparent to make the gap visible
        primaryStage.initStyle(StageStyle.TRANSPARENT); //primary stage eke tamai close and minimize buttons tynne. a nisa eka transparent karala dala ape anchor pane eke anchor pane ekak dala eka blue karala task bar ekak api hada gattha
        primaryStage.setTitle("Student Management System");
        primaryStage.show();
        primaryStage.centerOnScreen(); */
    }

}
