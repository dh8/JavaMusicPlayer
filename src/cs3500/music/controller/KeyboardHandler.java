package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by a172772 on 4/2/16.
 */
public class KeyboardHandler implements KeyListener {
  private Map<Integer, Runnable> keyTypedMap;
  private Map<Integer, Runnable> keyPressedMap;
  private Map<Integer, Runnable> keyReleasedMap;

  public KeyboardHandler() {
    this.keyTypedMap = new HashMap<>();
    this.keyPressedMap = new HashMap<>();
    this.keyReleasedMap = new HashMap<>();
  }

  @Override
  public void keyTyped(KeyEvent e) {
    int key = e.getKeyCode();
    if(keyTypedMap.containsKey(key)) {
      keyTypedMap.get(key).run();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    if(keyPressedMap.containsKey(key)) {
      keyPressedMap.get(key).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();
    if(keyTypedMap.containsKey(key)) {
      keyTypedMap.get(key).run();
    }
  }

  public void addOnKeyReleased(int key, Runnable r) {
    this.keyReleasedMap.put(key, r);
  }

  public void addOnKeyPressed(int key, Runnable r) {
    this.keyPressedMap.put(key, r);
  }

  public void addOnKeyTyped(int key, Runnable r) {
    this.keyTypedMap.put(key, r);
  }
}
