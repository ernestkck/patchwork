package comp1140.ass2;

import java.util.ArrayList;

/**
 * Created by jack1 on 27/09/2017.
 */
public class testGame {     //Author: Jack de Kleuver (u5740954) Note: this class is not used by the program it is a simply a mirror or the JavaFX code that can be used to run tests on


    private static String PATCH_CIRCLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefg";
    private static final String[] parts = PATCH_CIRCLE.split("A");
    private static final String ADJUSTED_CIRCLE = parts[1] + parts[0] + "A";
    private static String placementString = "";
    private static GuiPatch currentPatch;
    private int neutral_token = PATCH_CIRCLE.indexOf("A");
    private static final String URI_BASE = "assets/";
    private ArrayList<GuiPatch> patchList = new ArrayList();
    public static boolean specialTile = false;

    testGame(String circle, String place){
        loadPlacements(circle, place);
    }

    public void loadPlacements(String patchCircle, String placement){
        PATCH_CIRCLE = patchCircle;
        placementString = placement;
        neutral_token = 0;
        patchList.clear();
        makePatchCircle();
        int position = 0;
        while (position < placementString.length()){
            if (placementString.charAt(position) == '.'){
                position ++;
            }
            else{
                for (int i = 0; i < patchList.size(); i++){
                    if (patchList.get(i).getName() == placementString.charAt(position)) placePatch(patchList.get(i));
                }
                position += 4;
            }
        }
    }
    public void makePatchCircle(){
        for (char t: PATCH_CIRCLE.toCharArray()) {
            patchList.add(new GuiPatch(t));
        }
    }
    public void placePatch(GuiPatch patch){
        neutral_token = (patchList.indexOf(patch)+1) % patchList.size();
        //patchList.remove(patch);
        patch.setDisable(true);
    }
    public int getNeutral_token(){
        return neutral_token;
    }
}
