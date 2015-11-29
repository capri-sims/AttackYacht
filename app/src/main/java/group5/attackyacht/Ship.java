/*
********************************************************************************
*** Ship.java
*** Group 5
********************************************************************************
*** Purpose:
*** Object to handle variables relevant to ship game pieces
********************************************************************************
*** Date:
*** 11/21/15
********************************************************************************
*** Change Log:
*** 11/21/15 - CS - Class created and laid out
*** 11/21/15 - CS - Created & designed Ship
*** 11/21/15 - CS - Created & designed setType
*** 11/21/15 - CS - Created & designed getType
*** 11/21/15 - CS - Created & designed getRow
*** 11/21/15 - CS - Created & designed getCol
*** 11/21/15 - CS - Created & designed hit
*** 11/21/15 - CS - Tested & completed all methods
********************************************************************************
*/

// Project Package
package group5.attackyacht;

public class Ship {

    private static String type; //type must correspond to the image to be displayed
    //Valid types: water, destroyed, ship_bottom, ship_top, ship_middle_h, ship_middle_v, ship_left, ship_right
    private static int health;
    private static boolean hit;
    private int row;
    private int col;

/*
********************************************************************************
*** Ship
*** Group 5
********************************************************************************
*** Purpose:
*** Constructor for ship object
*** Inputs:
*** String type, int row, int col
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/21/15
********************************************************************************
*/
    public Ship(String type, int row, int col){
        this.type = type;
        health = 100;
        hit = false;
        this.row = row;
        this.col = col;
    }

/*
********************************************************************************
*** hit
*** Group 5
********************************************************************************
*** Purpose:
*** Handles all changes required when a ship piece is attacked
*** Inputs:
*** n/a
*** Outputs:
*** Boolean false, Boolean true
********************************************************************************
*** Date
*** 11/21/15
********************************************************************************
*/
    public static boolean hit(){
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

/*
********************************************************************************
*** setType
*** Group 5
********************************************************************************
*** Purpose:
*** Set the type variable to alter image used for ship piece
*** Inputs:
*** String type
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/21/15
********************************************************************************
*/
    public void setType(String type){
        this.type = type;
    }

/*
********************************************************************************
*** getType
*** Group 5
********************************************************************************
*** Purpose:
*** Return row
*** Inputs:
*** n/a
*** Outputs:
*** String type
********************************************************************************
*** Date
*** 11/21/15
********************************************************************************
*/
    public String getType(){
        return type;
    }

/*
********************************************************************************
*** getRow
*** Group 5
********************************************************************************
*** Purpose:
*** Return row
*** Inputs:
*** n/a
*** Outputs:
*** int row
********************************************************************************
*** Date
*** 11/21/15
********************************************************************************
*/
    public int getRow(){ return row; }

/*
********************************************************************************
*** getCol
*** Group 5
********************************************************************************
*** Purpose:
*** Return column
*** Inputs:
*** n/a
*** Outputs:
*** int col
********************************************************************************
*** Date
*** 11/21/15
********************************************************************************
*/
    public int getCol(){ return col; }
}
