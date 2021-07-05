package controller;


import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Student;
import model.StudentTM;
import service.StudentService;
import service.StudentServiceRedisImpl;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;
import util.MaterialUI;
import static util.ValidationUtil.*;     //meka damme nathnam isInteger(); method eka mehema call karann ba. Validation.util.isInteger(); kiyala call krnna wnne

import java.text.*;
import java.time.LocalDate;
import java.time.Period;




public class StudentFormController {

    public TextField txtNIC;
    public TextField txtFullName;
    public TextField txtAddress;
    public TextField txtDob;
    public TextField txtMobile;
    public TextField txtMail;
    public Label lblAge;
    public AnchorPane root;
    public JFXButton btnSave;
    public Label lblTitle;
    public ImageView imgLogo;

    private final StudentServiceRedisImpl studentService = new StudentServiceRedisImpl();  //composition nisa

    public void initialize() {
        MaterialUI.paintTextFields(new TextField[]{txtNIC, txtFullName, txtAddress, txtDob, txtMobile, txtMail});  //mixing

        Platform.runLater(() -> {
            if (root.getUserData() != null) {
                StudentTM tm = (StudentTM) root.getUserData();
                Student student = null;
                try {
                    student = studentService.findStudent(tm.getNic());
                } catch (NotFoundException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Something went wrong terribly.Please contact the DEPO", ButtonType.OK).show();
                }

                txtNIC.setEditable(false);
                txtNIC.setText(student.getNic());
                txtFullName.setText(student.getFullName());
                txtAddress.setText(student.getAddress());
                txtMobile.setText(student.getContact());
                txtMail.setText(student.getEmail());
                txtDob.setText(student.getDateOfBirth().toString());

                btnSave.setText("UPDATE STUDENT");
                lblTitle.setText("Update Student");
                imgLogo.setImage(new Image("/asset/Edit.png"));
            }
        });

        setMaxLength(txtDob, 10);
        setMaxLength(txtMobile, 11);
        setAge();
    }

    private void setAge() {
        txtDob.textProperty().addListener((observable, oldValue, newValue) -> {

            if (txtDob.getLength() == 10) {

                LocalDate dob2 = LocalDate.parse(txtDob.getText());     //user type karana date eka
                Period between = Period.between(dob2, LocalDate.now());  //ada date eka. Period eken dates dekak athara difference eka ganna puluwan

                lblAge.setText(between.getYears() + " Years and " + between.getMonths() + " Months " + between.getDays() + " Days old");
            }
        });
    }


    private void setMaxLength(TextField txt, int maxLength) {
        txt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                txt.setText(txt.getText(0, maxLength));
            }
        });
    }


  /*  public void txtDob_OnKeyReleased(KeyEvent keyEvent) {
       if(keyEvent.getCode() == KeyCode.BACK_SPACE ||keyEvent.getCode() == KeyCode.DELETE){
           return;
       }

        if(txtDob.getText().length()==5){              //5 weni number eka gahaddi meka wenna ona
          String text=txtDob.getText();

          txtDob.setText(text.substring(0,4)+"-"+text.substring(4));
          txtDob.positionCaret(6);                  //Caret=cursor
        }else if(txtDob.getText().length()==8){
            String text=txtDob.getText();

            txtDob.setText(text.substring(0,7)+"-"+text.substring(7));
            txtDob.positionCaret(9);
        }
    }   */

    public void txtDob_OnKeyTyped(KeyEvent keyEvent) {
        if (keyEvent.getCharacter().equals("-") && (txtDob.getText().length() == 4 || txtDob.getText().length() == 7)) {  // - eka user gahuwoth
            return;
        }

        if (!Character.isDigit(keyEvent.getCharacter().charAt(0))) {    //if a character is entered it will not be displayed
            keyEvent.consume();     // This is not going to forward to the Java FX Runtime Env.
            return;
        }

        if ((txtDob.getText().length() == 4 || txtDob.getText().length() == 7)) {
            txtDob.appendText("-");
            txtDob.positionCaret(txtDob.getText().length() + 1);
        }
    }


    public void txtMobile_OnKeyTyped(KeyEvent keyEvent) {
        if (keyEvent.getCharacter().equals("-") && (txtMobile.getText().length() == 4 || txtMobile.getText().length() == 7)) {  // - eka user gahuwoth
            return;
        }

        if (!Character.isDigit(keyEvent.getCharacter().charAt(0))) {    //if a character is entered it will not be displayed
            keyEvent.consume();     // This is not going to forward to the Java FX Runtime Env.
            return;
        }

        if (txtMobile.getText().length() == 3) {
            txtMobile.appendText("-");
            txtMobile.positionCaret(txtMobile.getText().length() + 1);
        }
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        try {

            if (!isValidated()) {
                return;
            }

            Student student = new Student(txtNIC.getText(),
                    txtFullName.getText(),
                    txtAddress.getText(),
                    LocalDate.parse(txtDob.getText()),
                    txtMobile.getText(),
                    txtMail.getText());

            if (btnSave.getText().equals("Add new student")) {
                studentService.saveStudent(student);

                txtNIC.clear();
                txtFullName.clear();
                txtAddress.clear();
                txtDob.clear();
                lblAge.setText("Enter DOB to display the age");
                txtMobile.clear();
                txtMail.clear();
                txtNIC.requestFocus();

            } else {
                StudentTM tm = (StudentTM) root.getUserData();
                tm.setFullName(txtFullName.getText());
                tm.setAddress(txtAddress.getText());
                studentService.updateStudent(student);
            }
            new Alert(Alert.AlertType.NONE, "Student has been saved successfully", ButtonType.OK).show();
        } catch (DuplicateEntryException e) {
            new Alert(Alert.AlertType.ERROR, "A student already exits with the same NIC", ButtonType.OK).show();
            txtNIC.requestFocus();
        } catch (NotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something terribly went wrong, please contact DEPPO!", ButtonType.OK).show();
        }

    }

    private boolean isValidated() {

        String nic = txtNIC.getText();
        String fullName = txtFullName.getText();
        String address = txtAddress.getText();
        String dob = txtDob.getText();
        String contact = txtMobile.getText();
        String email = txtMail.getText();

        if (!((nic.length() == 10 && (nic.endsWith("V") || nic.endsWith("v")) && isInteger(nic.substring(0, 9)))
                || (nic.length() == 12 && isInteger(nic)))) {
            new Alert(Alert.AlertType.ERROR, "Invalid NIC").show();
            txtNIC.requestFocus();
            return false;
        } else if (!(isValid(fullName, true, false) && fullName.trim().length() >= 3)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name. Name should contain at least 3 letters and can contain only alphabetic letters and spaces").show();
            txtFullName.requestFocus();
            return false;
        } else if (!(address.trim().length() >= 4 && isValid(address, true, true, ':', '.', ',', '-', '/', '\\'))) {
            new Alert(Alert.AlertType.ERROR, "Invalid Address. Address should be at least 4 digits and can contain only alphabetic letters, spaces and - , . / \\").show();
            txtAddress.requestFocus();
            return false;
        } else if (!isValidDate(dob)) {
            new Alert(Alert.AlertType.ERROR, "Invalid DOB").show();
            txtDob.requestFocus();
            return false;
        } else if (!(contact.length() == 11 && isInteger(contact.substring(0, 3)) && isInteger(contact.substring(4, 11)))) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact Number").show();
            txtMobile.requestFocus();
            return false;
        } else if (!isValidEmail(email)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email. Email can contain only letters, digits, periods and underscore.").show();
            txtMail.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}



