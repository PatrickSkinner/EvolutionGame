/** ShapePanel.java
  * @author Patrick Skinner
  * */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/** This is a horrific class that will be replaced by a nicer non-JPanel UI at a later date, or maybe it already has and
  * this is just here for legacy reasons, who knows, I'll probably for to update this comment when I do replace this class.
  */
public class ShapePanel extends JPanel
{
  private static int shapeCount;
  private static ArrayList<Shape> drawObjects = new ArrayList();
  private JButton init;
  private JButton step;
  private JButton stepMult;
  private JButton reinit;
  private JButton gen;
  private JButton debug;
  private JLabel countLabel;
  private JTextField displayCount;
  private JLabel enLabel;
  private JTextField displayEn;
  private static DrawingPanel drawPanel;
  private JPanel controlPanel;
  
  private javax.swing.Timer timer;
  private JButton start;
  private JButton stop;
  
  private static BufferedImage[] imgArray;
  
  public ShapePanel()
  {
    init = new JButton("Initialize");
    step = new JButton("Step");
    stepMult = new JButton("5 Steps");
    reinit = new JButton("Reinitialize");
    debug = new JButton("Debug");
    gen = new JButton("15 Generations");
    countLabel = new JLabel("Count:");
    displayCount = new JTextField(3);
    enLabel = new JLabel("Energy:");
    displayEn = new JTextField(3);
    start = new JButton("Start");
    stop = new JButton("Stop");
    
    ButtonListener listener = new ButtonListener();
    init.addActionListener (listener);
    step.addActionListener (listener);
    gen.addActionListener (listener);
    stepMult.addActionListener (listener);
    reinit.addActionListener (listener);
    debug.addActionListener (listener);
    start.addActionListener (listener);
    stop.addActionListener (listener);
    
    controlPanel = new JPanel();
    controlPanel.setPreferredSize( new Dimension(100,400) );
    
    timer = new javax.swing.Timer(50, listener);
    
    drawPanel = new DrawingPanel();
    
    controlPanel.add(init);
    controlPanel.add(reinit);
    controlPanel.add(step);
    controlPanel.add(stepMult);
    controlPanel.add(gen);
    controlPanel.add(debug);
    controlPanel.add(countLabel);
    controlPanel.add(displayCount);
    controlPanel.add(enLabel);
    controlPanel.add(displayEn);
    controlPanel.add(start);
    controlPanel.add(stop);
    add(controlPanel);
    
    add(drawPanel);
  }
  
  public static void main(String[]args)
  {
    imgArray = new BufferedImage[4];
    try {
      imgArray[0] = ImageIO.read(new File("Creature.png"));
      imgArray[1] = ImageIO.read(new File("Monster.png"));
      imgArray[2] = ImageIO.read(new File("Apple.png"));
      imgArray[3] = ImageIO.read(new File("Mushroom.png"));
    } catch (IOException e) {
      System.out.println(e.toString());
    }
    
    JFrame shapeFrame = new JFrame();
    shapeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    shapeFrame.getContentPane().add(new ShapePanel());
    shapeFrame.pack();
    shapeFrame.setVisible(true);
    shapeFrame.setTitle("343");
  }
  
  private class ButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent aE)
    {
      if (aE.getSource() == timer){
        drawPanel.repaint();
        drawObjects.clear();
        Evo.timeStep();
        
        shapeCount = 0;
        for(int x = 0; x < Evo.grid_size; x++){
          for(int y = 0; y < Evo.grid_size; y++){
            if(Evo.strawb_array[x][y] > 0){
              drawObjects.add(new Shape(x*8,y*8,2)); 
            }
            if(Evo.mushroom_array[x][y] > 0){
              drawObjects.add(new Shape(x*8,y*8,3));
            }
            if(Evo.creature_array[x][y] > 0){
              drawObjects.add(new Shape(x*8,y*8,0)); 
              shapeCount++;
            }
            if(Evo.monster_array[x][y] > 0){
              drawObjects.add(new Shape(x*8,y*8,1));
            }
          }
        }
        if(shapeCount < 13){
          Evo.reinitialize();
        }
      } else {
        if (aE.getSource() == init)
        {
          drawObjects.clear();
          Evo.initialize();
          shapeCount = 0;
          for(int x = 0; x < Evo.grid_size; x++){
            for(int y = 0; y < Evo.grid_size; y++){
              if(Evo.strawb_array[x][y] > 0){
                drawObjects.add(new Shape(x*8,y*8,2));
              }
              if(Evo.mushroom_array[x][y] > 0){
                drawObjects.add(new Shape(x*8,y*8,3));  
              }
              if(Evo.creature_array[x][y] > 0){
                drawObjects.add(new Shape(x*8,y*8,0));
                shapeCount++;
              }
              if(Evo.monster_array[x][y] > 0){
                drawObjects.add(new Shape(x*8,y*8,1)); 
              }
            }
          }
        }
        if (aE.getSource() == step)
        {
          drawObjects.clear();
          Evo.timeStep();
          shapeCount = 0;
          for(int x = 0; x < Evo.grid_size; x++){
            for(int y = 0; y < Evo.grid_size; y++){
              if(Evo.strawb_array[x][y] > 0){
                drawObjects.add(new Shape(x*8,y*8,2)); 
              }
              if(Evo.mushroom_array[x][y] > 0){
                drawObjects.add(new Shape(x*8,y*8,3));
              }
              if(Evo.creature_array[x][y] > 0){
                drawObjects.add(new Shape(x*8,y*8,0)); 
                shapeCount++;
              }
              if(Evo.monster_array[x][y] > 0){
                drawObjects.add(new Shape(x*8,y*8,1));
              }
            }
          }
        }
        if (aE.getSource() == stepMult)
        {
          for(int i = 0; i < 5; i++){
            drawObjects.clear();
            Evo.timeStep();
            drawPanel.repaint();
          }
          
          shapeCount = 0;
          for(int x = 0; x < Evo.grid_size; x++){
            for(int y = 0; y < Evo.grid_size; y++){
              if(Evo.strawb_array[x][y] > 0){
                drawObjects.add(new Shape(x*8,y*8,2)); 
              }
              if(Evo.mushroom_array[x][y] > 0){
                drawObjects.add(new Shape(x*8,y*8,3));
              }
              if(Evo.creature_array[x][y] > 0){
                drawObjects.add(new Shape(x*8,y*8,0)); 
                shapeCount++;
              }
              if(Evo.monster_array[x][y] > 0){
                drawObjects.add(new Shape(x*8,y*8,1));
              }
            }
          }
        }
        if (aE.getSource() == reinit)
        {
          drawObjects.clear();
          Evo.reinitialize();
          shapeCount = 0;
          for(int x = 0; x < Evo.grid_size; x++){
            for(int y = 0; y < Evo.grid_size; y++){
              if(Evo.strawb_array[x][y] > 0){
                drawObjects.add(new Shape(x*8,y*8,2)); 
              }
              if(Evo.mushroom_array[x][y] > 0){
                drawObjects.add(new Shape(x*8,y*8,3)); 
              }
              if(Evo.creature_array[x][y] > 0){
                drawObjects.add(new Shape(x*8,y*8,0));
                shapeCount++;
              }
              if(Evo.monster_array[x][y] > 0){
                drawObjects.add(new Shape(x*8,y*8,1));
              }
            }
          }
        }
        if (aE.getSource() == gen)
        {
          drawObjects.clear();
          for(int j = 0; j < 15; j++){
            for(int i = 0; i < 25; i++){
              drawObjects.clear();
              Evo.timeStep();
              drawPanel.repaint();
            }
            Evo.reinitialize();
          }
          drawPanel.repaint();
          shapeCount = 0;
          for(int x = 0; x < Evo.grid_size; x++){
            for(int y = 0; y < Evo.grid_size; y++){
              if(Evo.strawb_array[x][y] > 0){
                drawObjects.add(new Shape(x*8,y*8,2)); 
              }
              if(Evo.mushroom_array[x][y] > 0){
                drawObjects.add(new Shape(x*8,y*8,3)); 
              }
              if(Evo.creature_array[x][y] > 0){
                drawObjects.add(new Shape(x*8,y*8,0));
                shapeCount++;
              }
              if(Evo.monster_array[x][y] > 0){
                drawObjects.add(new Shape(x*8,y*8,1));
              }
            }
          }
        }
        if (aE.getSource() == debug)
        {
          for(int n = 0; n < Evo.creature_count; n++){
            System.out.println(Evo.creatures[n].toString());
          }
          System.out.println();
        }
        
        if (aE.getSource() == start)
        {
          timer.start();
        }
        if (aE.getSource() == stop)
        {
          timer.stop();
        }
      }
      
      displayCount.setText(Integer.toString(shapeCount));  
      int totalEn = 0;
      for(Creature c : Evo.creatures){
        totalEn += c.energy_level;
      }
      displayEn.setText(Integer.toString(totalEn/Evo.creature_count));  
      drawPanel.repaint();
    }
  }
  
  private class DrawingPanel extends JPanel
  {
    public DrawingPanel()
    {
      setPreferredSize( new Dimension(Evo.grid_size*8,Evo.grid_size*8) );
      setBackground(new Color(80, 170, 56)); 
    }
    
    public void paintComponent(Graphics g)
    {
      super.paintComponent(g);
      for (Shape s : drawObjects)
      {
        s.draw(g, imgArray);
      }
    }
  }
  
}