package controller;

import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class StudentFormController {
    public TextField txtNIC;
    public TextField txtFullName;

    public  void  initialize(){
        paintTextFields();
    }

    private void paintTextFields() {
        AnchorPane pneTextContainer = (AnchorPane) txtNIC.getParent();
        String floatedText=txtNIC.getAccessibleText();
        Canvas canvas = new Canvas(pneTextContainer.getPrefWidth(), pneTextContainer.getPrefHeight());
        GraphicsContext ctx= canvas.getGraphicsContext2D();  //grapic context eka eliyata adala ganna ona. eke tama shapes ona ekak adinna puluwan

        pneTextContainer.getChildren().add(0,canvas);  //index 0n kiyanne inna child la gen palaweni ma ekkena tama canvas eka, canvas eka udata dammoth type krnna ba
        ctx.setStroke(Color.valueOf("#6200EE"));
        ctx.strokeRoundRect(2,4,canvas.getWidth()-4, canvas.getHeight()-6,10,10 );
        ctx.setFill(Color.WHITE);
        ctx.fillRect(10,0,new Text(floatedText).getLayoutBounds().getWidth()+10,20);
        ctx.setFill(Color.valueOf("#6200EE"));
        ctx.fillText(floatedText,15,10);
    }

}
