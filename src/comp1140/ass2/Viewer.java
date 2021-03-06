package comp1140.ass2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A very simple viewer for piece placements in the Patchwork game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
public class Viewer extends Application {       //Author: Jack de Kleuver (u5740954)
    private static final int VIEWER_WIDTH = 933;
    private static final int VIEWER_HEIGHT = 700;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    TextField textField;

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    void makePlacement(String placement) {
        root.getChildren().clear();
        ImageView bg = new ImageView(new Image(Viewer.class.getResourceAsStream("gui/" + URI_BASE + "grid.png")));
        root.getChildren().add(bg);
        root.getChildren().add(controls);
        int currentY = 100;
        int currentX = 50;
        Image image;
        int turn = 0;
        int previousTurn = 0;
        int timeA = 0;
        int timeB = 0;
        char[] placementArray = placement.toCharArray();
        System.out.println(Arrays.toString(placementArray));
        ArrayList<ImageView> patchList = new ArrayList<ImageView>();
        for (int i = 0; i < placementArray.length; i++){
            if (placementArray[i] != '.'){
                if (Character.isUpperCase(placementArray[i])){
                    image = new Image(Viewer.class.getResourceAsStream("gui/" + URI_BASE + placementArray[i] + ".png"));
                }
                else {
                    image = new Image(Viewer.class.getResourceAsStream("gui/" + URI_BASE + placementArray[i] + "_.png"));
                }
                if (placementArray[i] == 'h') turn = previousTurn;
                currentX = 11 + turn * 461 + (placementArray[i+1]-65)*50 + rotateOffset(((placementArray[i+3]-65) % 4)*90, image.getHeight(), image.getWidth(), 'X');
                currentY = 100 + (placementArray[i+2]-65)*50 - rotateOffset(((placementArray[i+3]-65) % 4)*90, image.getHeight(), image.getWidth(), 'Y');
                patchList.add(new ImageView(image));
                patchList.get(patchList.size()-1).setX(currentX);
                patchList.get(patchList.size()-1).setY(currentY);
                patchList.get(patchList.size()-1).setRotate(((placementArray[i+3]-65) % 4)*90);
                if ((placementArray[i+3]-65) / 4 == 1) patchList.get(patchList.size()-1).setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                if (turn == 0){
                    timeA += Patch.valueOf("" + placementArray[i]).getTimeCost();
                    System.out.println(placementArray[i]);
                }
                else {
                    timeB += Patch.valueOf("" + placementArray[i]).getTimeCost();
                    System.out.println(Patch.valueOf("" + placementArray[i]).getTimeCost());
                    System.out.println(placementArray[i]);
                }
                i += 3;
            }
            else {
                if (turn == 0){
                    timeA = timeB+1;
                }
                else {
                    timeB = timeA+1;
                }
            }
            System.out.println("Player A time: " + timeA);
            System.out.println("Player B time: " + timeB);
            System.out.println(turn);
            System.out.println("");
            previousTurn = turn;
            if (timeA > timeB){
                turn = 1;
            }
            else if (timeB > timeA){
                turn = 0;
            }
            System.out.println(turn);

        }
        root.getChildren().addAll(patchList);
    }
    private int rotateOffset(int angle, double height, double width, char axis){
        if (axis == 'X'){
            switch ((angle/90)%2){
                case 1:  return ((int) (height-width)/2);
                default: return 0;
            }
        }
        else {
            switch ((angle/90)%2){
                case 1:  return ((int) (height-width)/2);
                default: return 0;
            }
        }
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Display");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(250);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
        root.getChildren().add(controls);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Patchwork Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        makeControls();
        makePlacement("");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
