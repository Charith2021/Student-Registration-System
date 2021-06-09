package util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MaterialUI {

    public static void paintTextFields(TextField... textFields) {
        for (TextField txt : textFields) {
            AnchorPane pneTextContainer = (AnchorPane) txt.getParent();
            String floatedText=txt.getAccessibleText();

            Canvas canvas = new Canvas();
            GraphicsContext ctx= canvas.getGraphicsContext2D();  //graphic context eka eliyata adala ganna ona. eke tama shapes ona ekak adinna puluwan

            pneTextContainer.getChildren().add(0,canvas);  //index 0n kiyanne inna child la gen palaweni ma ekkena tama canvas eka, canvas eka udata dammoth type krnna ba


            pneTextContainer.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
                canvas.setWidth(newValue.getWidth());
                canvas.setHeight(newValue.getHeight());
                redrawTextFiledCanvas(canvas,ctx,floatedText,false);
            });

            txt.focusedProperty().addListener((observable, oldValue, newValue) -> {
                redrawTextFiledCanvas(canvas,ctx,floatedText,newValue);

            });

            pneTextContainer.setOnMouseClicked(event -> txt.requestFocus());
        }
    }

    private static void redrawTextFiledCanvas(Canvas canvas, GraphicsContext ctx, String floatedText, boolean focus){
        ctx.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        ctx.setStroke(focus? Color.valueOf("#6200EE") : Color.rgb(0, 0, 0, 0.3));            //nam
        ctx.strokeRoundRect(2, 4, canvas.getWidth() - 4, canvas.getHeight() - 6, 10, 10);
        ctx.setFill(Color.WHITE);
        ctx.fillRect(10, 0, new Text(floatedText).getLayoutBounds().getWidth() + 10, 20);
        ctx.setFill(focus?Color.valueOf("#6200EE") : Color.rgb(0, 0, 0, 0.6));
        ctx.fillText(floatedText, 15, 10);
    }

}
