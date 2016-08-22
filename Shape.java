/** Shape.java
  * @author Patrick Skinner
  * */

package EvolutionGame;

import java.util.Random;
import java.awt.*;

/**The tiles that make up the game world.*/
public class Shape
{
  private int x, y, width, height;
  private Color shapeColor;
  
  /**A contructor to create a new tile.
    * 
    * @param x - The x coordiante of the tile.
    * @param y - The y coordinate of the tile.
    * @param type - What object is currently occupying the tile.
    * */
  public Shape(int x, int y, int type)
  {
    height = 8;
    width = height;
    this.x = x;
    this.y = y;
    if(type == 0){
      shapeColor = Color.green; //Creature
    }
    if(type == 1){
      shapeColor = new Color(255,50,0); //Monster
    }
    if(type == 2){
      //shapeColor = new Color(255,204,0); //Food
      shapeColor = Color.orange;
    }
    if(type == 3){
      shapeColor = new Color(153,0,255); //Mushroom
    }
  }
  
  /**Draw the tile*/
  public void draw(Graphics g)
  {
        g.setColor(shapeColor);
        g.fillRect(x, y, width, height);
   }
}