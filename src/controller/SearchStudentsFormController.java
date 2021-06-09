package controller;

import javafx.scene.control.TextField;
import util.MaterialUI;

public class SearchStudentsFormController {
    public TextField txtQuery;

    public  void  initialize(){
        MaterialUI.paintTextFields(txtQuery);

    }
}
