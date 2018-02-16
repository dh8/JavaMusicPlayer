package cs3500.music.view;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.*;

/**
 * Represents the GUI view
 */
public class GuiView extends javax.swing.JFrame implements MusicEditorView {

  private final JDisplay displayPanel; // You may want to refine this to a subtype of JPanel
  private final JScrollPane scrollPane;
  public static ViewModel model;
  private boolean paused = false;

  /**
   * @param model         the model that we are referencing for our Gui construction
   * @param scrollPane    provides a scrollable view
   * @param displayPanel  lightweight container
   * @param filename      the file name we are referencing to build the model
   * @throws IOException
   */
  public GuiView(String filename) throws IOException {
    this.displayPanel = new JDisplay();
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.scrollPane = new JScrollPane(displayPanel);
    this.getContentPane().add(scrollPane);
    this.pack();

    this.model = new ViewModel(filename);
    this.model.getBeatMax(); // make sure we don't have an empty piece!!
  }

  public GuiView(ViewModel model) throws IOException {
    this.displayPanel = new JDisplay();
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.scrollPane = new JScrollPane(displayPanel);
    this.getContentPane().add(scrollPane);
    this.pack();

    this.model = model;
    this.model.getBeatMax(); // make sure we don't have an empty piece!!
  }

  @Override
  public void initialize() {
    this.setVisible(true);
  }

  @Override
  public Dimension getPreferredSize(){
    return displayPanel.getPreferredSize();
  }

  public void addKeyListener(KeyListener l) {
    this.displayPanel.setFocusable(true);
    this.displayPanel.addKeyListener(l);
  }

  public void addMouseListener(MouseListener l) {
    this.displayPanel.setFocusable(true);
    this.displayPanel.addMouseListener(l);
  }

  public void handleClick(int x, int y) {
    this.displayPanel.handleClick(x, y);
    this.displayPanel.repaint();
  }

  public void setBeatPos(int beatNum) {
        /*if(this.paused) {
            return;
        }*/
    int xPos = this.displayPanel.setBeatPos(beatNum);
    if(xPos > this.scrollPane.getViewport().getWidth()+
              this.scrollPane.getHorizontalScrollBar().getValue()) {
      this.scrollPane.getHorizontalScrollBar().setValue(xPos);
    }
  }

  public void moveUp() {
    this.displayPanel.moveUp();
  }

  public void moveDown() {
    this.displayPanel.moveDown();
  }

  public void moveLeft() {
    this.displayPanel.moveLeft();

  }

  public void moveRight() {
    this.displayPanel.moveRight();
  }

  public void extend() {
    this.displayPanel.extend();
  }

  public void retract() {
    this.displayPanel.retract();
  }

  public void pause() {
    this.paused = !paused;
  }

  public void home() {
    //this.displayPanel.setBeatPos(0);
    this.scrollPane.getHorizontalScrollBar().setValue(0);
  }

  public void end() {
    int xPos = this.displayPanel.setBeatPos(this.model.getBeatMax());
    this.displayPanel.setBeatPos(xPos);
    this.scrollPane.getHorizontalScrollBar().setValue(xPos);
  }

  public void delete() {
    this.displayPanel.delete();
  }
}