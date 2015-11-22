package group5.attackyacht;

/**
 * Created by Capri on 11/21/2015.
 */
public class Ship {

    private String type; //type must correspond to the image to be displayed
    //Valid types: water, destroyed, ship_bottom, ship_top, ship_middle_h, ship_middle_v, ship_left, ship_right
    private int health;
    private boolean hit;

    public Ship(String type){
        this.type = type;
        health = 100;
        hit = false;
    }

    public boolean hit(){
        hit = true;

        if(type == "water"){
            return false;
        }
        else{
            health = 0;
            type = "destroyed";
            return true;
        }
    }

}
