package controller;

import com.jfoenix.controls.JFXRippler;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.AppBarIcon;
import util.NavActionListener;

import java.io.IOException;

public class HomeFormController {

    public JFXRippler rprAddNewStudent;
    public AnchorPane pneAddNewStudent;
    public JFXRippler rprSearchStudents;
    public AnchorPane pneSearchStudents;
    public ImageView imgClose;
    public ImageView imgMinimize;

    public void initialize() {

        rprAddNewStudent.setControl(pneAddNewStudent);
        rprSearchStudents.setControl(pneSearchStudents);
        pneAddNewStudent.setFocusTraversable(true);
        pneSearchStudents.setFocusTraversable(true);
    }


    public void rprSearchStudents_OnKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER ||keyEvent.getCode() == KeyCode.SPACE){
            rprAddNewStudent.createManualRipple().run();
        }

    }

    public void rprAddNewStudent_OnKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode()==KeyCode.ENTER||keyEvent.getCode()==KeyCode.SPACE){
            rprSearchStudents.createManualRipple().run();
        }

    }

    public void pneAddNewStudent_OnMouseClicked(MouseEvent mouseEvent) throws IOException {
       navigateaction("Add new Student","/view/StudentForm.fxml");
    }

    public void pneSearchStudents_OnMouseClicked(MouseEvent mouseEvent) {
       navigateaction("Search Student","/view/SearchStudentsForm.fxml");
    }






    public void pneAddNewStudent_OnKeyRelease(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode()==KeyCode.ENTER||keyEvent.getCode()==KeyCode.SPACE) {
            navigateaction("Add new student","/view/StudentForm.fxml");
        }
    }

    public void pneSearchStudents_OnKeyRelease(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.SPACE) {
            navigateaction("Search Student", "/view/SearchStudentsForm.fxml");
        }
    }

    public  void navigateaction(String title,String url){

        MainFormController ctrl = (MainFormController) pneSearchStudents.getScene().getUserData();
        ctrl.navigate(title, url, AppBarIcon.NAV_ICON_BACK, () -> ctrl.navigate("Student Payment System","/view/HomeForm.fxml",AppBarIcon.NAV_ICON_HOME));
    }
}
