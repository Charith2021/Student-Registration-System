package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import util.MaterialUI;


public class StudentFormController {
    public TextField txtNIC;
    public TextField txtFullName;
    public TextField txtAddress;
    public TextField txtDob;
    public TextField txtMobile;
    public TextField txtMail;

    public  void  initialize(){

        MaterialUI.paintTextFields(new TextField[]{txtNIC,txtFullName,txtAddress,txtDob,txtMobile,txtMail});  //mixing
    }





}
