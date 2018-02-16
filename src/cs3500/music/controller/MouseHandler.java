package cs3500.music.controller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a mouse handler
 */
public class MouseHandler implements MouseListener {
  Map<String, Runnable> clickMap;
  public int clickX = 0;
  public int clickY = 0;

  /**
   * @param clickX x coordinate of the click
   * param clickY y coordinate of the click
   */

  MouseHandler() {
    this.clickMap = new HashMap<>();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    this.clickX = e.getX();
    this.clickY = e.getY();
    if(e.isControlDown() && clickMap.containsKey("ctrl_click")) {
      clickMap.get("ctrl_click").run();
    }
    else {
      clickMap.get("click").run();
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  public void addClickEvent(String name, Runnable r) {
    this.clickMap.put(name, r);
  }
}
