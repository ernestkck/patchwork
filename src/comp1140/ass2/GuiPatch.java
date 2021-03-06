package comp1140.ass2;

import javafx.geometry.NodeOrientation;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GuiPatch extends ImageView {  //Author: Jack de Kleuver (u5740954)
    Patch patch;
    char rotation = 'A';
    char horizontal;
    char vertical;
    double anchorX, anchorY;
    double mouseX, mouseY;
    boolean turn = Game.getTurn();
    boolean isOffset = false;
    boolean isDraggable = false;

    GuiPatch(char patch){
        this.patch = Patch.valueOf("" + patch);
        if (patch <= 'Z'){
            setImage(new Image(GuiPatch.class.getResourceAsStream("gui/assets/" + patch + ".png")));
        }
        else {
            setImage(new Image(GuiPatch.class.getResourceAsStream("gui/assets/" + patch + "_.png")));
        }
        setPreserveRatio(true);
        setFitWidth(getWidth());
        setDraggable(false);
    }

    public Patch getPatch() {
        return patch;
    }
    public double getWidth(){
        return getImage().getWidth()/2;
    }
    public double getHeight(){
        return getImage().getHeight()/2;
    }
    public void setDraggable(boolean bool) {
        isDraggable = bool;
        ColorAdjust colorAdjust = new ColorAdjust();
        if (bool) {
            colorAdjust.setContrast(0);
        }
        else {
            colorAdjust.setContrast(-0.7);
        }
        setEffect(colorAdjust);
        setDisable(!bool);

        setOnScroll(event -> {
            rotate();
        });

        setOnMousePressed(event -> {
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
        });

        setOnMouseDragged(event -> {
            toFront();
            double movementX = event.getSceneX() - mouseX;
            double movementY = event.getSceneY() - mouseY;
            setLayoutX(getLayoutX() + movementX);
            setLayoutY(getLayoutY() + movementY);
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
            event.consume();
        });

        setOnMouseEntered(event -> {
            Game.setInfo(patch);
        });

        setOnMouseExited(event -> {
            Game.clearInfo();
        });

        setOnMouseReleased(event -> {
            double grid;
            getTurn();
            if (turn) grid = 0;
            else grid = (9*25) + 5.5;
            if (event.getSceneX() > 245+grid && event.getSceneX() < 465+grid && event.getSceneY() > 320 && event.getSceneY() < 550){
                double x = 0;
                double y = 0;
                if (isOffset){
                    x = rotateOffset(((rotation-65) % 4)*90, getHeight(), getWidth(), 'X');
                    y = rotateOffset(((rotation-65) % 4)*90, getHeight(), getWidth(), 'Y');
                }
                int layoutH = (int) Math.round((getLayoutX()-239-grid-x)/25);
                int layoutV = (int) Math.round((getLayoutY()-325+y)/25);
                horizontal = Character.toChars('A' + layoutH)[0];
                vertical = Character.toChars('A' + layoutV)[0];
                setLayoutX(grid + 239 + (horizontal-'A')*25+x);
                setLayoutY(325 + (vertical-'A')*25-y);
                setCurrentPatch();
            }
            else {
                setLayoutX(anchorX);
                setLayoutY(anchorY);
            }
        });


    }
    public void expensive(int buttons){
        ColorAdjust color = new ColorAdjust();
        if (buttons < patch.getButtonCost()){
            color.setBrightness(-0.9);
            setOnMouseReleased(event -> {
                setLayoutX(anchorX);
                setLayoutY(anchorY);
            });
        }
        else {
            color.setBrightness(0);
            setOnMouseReleased(event -> {
                double grid;
                getTurn();
                if (turn) grid = 0;
                else grid = (9*25) + 5.5;
                if (event.getSceneX() > 250+grid && event.getSceneX() < 465+grid && event.getSceneY() > 325 && event.getSceneY() < 550){
                    double x = 0;
                    double y = 0;
                    if (isOffset){
                        x = rotateOffset(((rotation-65) % 4)*90, getHeight(), getWidth(), 'X');
                        y = rotateOffset(((rotation-65) % 4)*90, getHeight(), getWidth(), 'Y');
                    }
                    int layoutH = (int) Math.round((getLayoutX()-239-grid-x)/25);
                    int layoutV = (int) Math.round((getLayoutY()-325+y)/25);
                    horizontal = Character.toChars('A' + layoutH)[0];
                    vertical = Character.toChars('A' + layoutV)[0];
                    setLayoutX(grid + 239 + (horizontal-'A')*25+x);
                    setLayoutY(325 + (vertical-'A')*25-y);
                    setCurrentPatch();
                }
                else {
                    setLayoutX(anchorX);
                    setLayoutY(anchorY);
                }
            });
        }
        setEffect(color);
    }
    public void anchor(){
        anchorX = getLayoutX();
        anchorY = getLayoutY();
    }
    public void toAnchor(){
        setLayoutX(anchorX);
        setLayoutY(anchorY);
    }
    public void rotate(){
        if (rotation == 'H') setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        if (rotation == 'D') setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        if (rotation == 'H') {
            rotation = 'A';
        }
        else {
            rotation += 1;
        }
        double rotateDouble = ((rotation-65) % 4)*90;
        if (!isOffset && (rotation-65)%2 == 1) {
            //setLayoutX(getLayoutX() + rotateOffset(rotateDouble, getHeight(), getWidth(), 'X'));
            //setLayoutY(getLayoutY() - rotateOffset(rotateDouble, getHeight(), getWidth(), 'Y'));
            isOffset = true;
        }
        else if (isOffset && (rotation-65)%2 == 0){
            //setLayoutX(getLayoutX() - rotateOffset(rotateDouble, getHeight(), getWidth(), 'X'));
            //setLayoutY(getLayoutY() + rotateOffset(rotateDouble, getHeight(), getWidth(), 'Y'));
            isOffset = false;
        }
        setRotate(getRotate()+90);
    }
    public void getTurn(){
        turn = Game.getTurn();
    }
    public void setCurrentPatch(){
        Game.setCurrentPatch(this);
    }
    private double rotateOffset(double angle, double height, double width, char axis){
        if (axis == 'X'){
            switch ((int) (angle/90)%2){
                case 1:  return ((height-width)/2);
                default: return 0;
            }
        }
        else {
            switch ((int) (angle/90)%2){
                case 1:  return  (height-width)/2;
                default: return 0;
            }
        }
    }
    public void snap(){
        double grid;
        getTurn();
        if (turn) grid = 0;
        else grid = (9*25) + 5.5;
        double x = 0;
        double y = 0;
        if (isOffset){
            x = rotateOffset(((rotation-65) % 4)*90, getHeight(), getWidth(), 'X');
            y = rotateOffset(((rotation-65) % 4)*90, getHeight(), getWidth(), 'Y');
        }
        int layoutH = (int) Math.round((getLayoutX()-239-grid-x)/25);
        int layoutV = (int) Math.round((getLayoutY()-325+y)/25);
        horizontal = Character.toChars('A' + layoutH)[0];
        vertical = Character.toChars('A' + layoutV)[0];
        setLayoutX(grid + 239 + (horizontal-'A')*25+x);
        setLayoutY(325 + (vertical-'A')*25-y);
    }
    public boolean isDraggable(){
        return isDraggable;
    }
    public void setHorizontal(char horizontal){
        this.horizontal = horizontal;
    }
    public void setVertical(char vertical){
        this.vertical = vertical;
    }
    public void setRotation(char rotation){
        this.rotation = rotation;
    }
    public void setLocation(){
        double grid = (9*25) + 5.5;
        double x = 0;
        double y = 0;
        if ((rotation-65)%2 == 1){
            isOffset = true;
            x = rotateOffset(((rotation-65) % 4)*90, getHeight(), getWidth(), 'X');
            y = rotateOffset(((rotation-65) % 4)*90, getHeight(), getWidth(), 'Y');

        }
        setRotate(((rotation-'A')%4)*90);
        if (rotation-'A' > 3) setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        setLayoutX(grid + 239 + (horizontal-'A')*25+x);
        setLayoutY(325 + (vertical-'A')*25-y);
        setCurrentPatch();
    }

    @Override
    public String toString() {
        return "" + getName() + horizontal + vertical + rotation;
    }
    public char getName(){
        return patch.getName();
    }
}
