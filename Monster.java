/** Monster.java
  * @author Patrick Skinner
  * */

import java.util.*;

class Monster{
    int x;
    int y;
    
    public Monster(int x, int y){
      this.x = x;
      this.y = y;
    }
    
    /* Move towards the nearest creature, or move randomly if not in range*/
    void move(){
      Evo.monster_array[x][y] -= 1;
      
      Random r = new Random();
      int heading;
      if( nearest_ceature() == 0){
        heading = r.nextInt(4) + 1;
      } else {
        heading = nearest_ceature();
      }
      
      if((heading == 1) && (y != (Evo.grid_size - 1))){
        y += 1;
      } else if ((heading == 2) && (x != (Evo.grid_size - 1))){
        x += 1;
      } else if ((heading == 3) && (y != 0)){
        y -= 1;
      } else if ((heading == 4) && (x != 0)){
        x -= 1;
      }
      
      Evo.monster_array[x][y] += 1;
    }
    
    /*Find the nearest creature and return its direction*/
    int nearest_ceature(){
      int smallestX = 0;
      int smallestY = 0;
      for(int sX = (x - Evo.hood_size); sX <= (x + Evo.hood_size); sX++){
        for(int sY = (y - Evo.hood_size); sY <= (y + Evo.hood_size); sY++){
          if((sY < Evo.grid_size) && (sX < Evo.grid_size) && (sY > -1) && (sX > -1)){
            if(Evo.creature_array[sX][sY] != 0){
              if(((Math.abs(sY-y) + Math.abs(sX-x)) < ((Math.abs(smallestY) + Math.abs(smallestX)))) || (smallestY == 0 && smallestX == 0)){
                smallestY = sY-y;
                smallestX = sX-x;
              }
            }
          }
          if(sY == Evo.grid_size || sY < 0) break;
        }
        if(sX == Evo.grid_size || sX < 0) break;
      }
      
      if(smallestY != 0){
        if(smallestY < 0){
          return 3;
        }
        return 1;
      }
      
      if(smallestX != 0){
        if(smallestX < 0){
          return 4;
        }
        return 2;
      }
      return 0;
    }
    
    /**Return the X and Y coordinates of the monsters as a string.*/
    @Override public String toString(){
      return "X: " + x + ", Y: " + y;
    }
  }
  