import controller.MainFormController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/MainForm.fxml"));   //making an object
        Parent root = fxmlLoader.load();
        Scene mainScene=new Scene(root);
        primaryStage.setScene(mainScene);
        MainFormController ctrl=fxmlLoader.getController();
        ctrl.navigate("Student Management System","/view/HomeForm.fxml",MainFormController.NAV_ICON_HOME);
        mainScene.setUserData(ctrl);
        mainScene.setFill(Color.TRANSPARENT); //making the scene transparent to make the gap visible
        primaryStage.initStyle(StageStyle.TRANSPARENT); //primary stage eke tamai close and minimize buttons tynne. a nisa eka transparent karala dala ape anchor pane eke anchor pane ekak dala eka blue karala task bar ekak api hada gattha
        primaryStage.setTitle("Student Management System");
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
