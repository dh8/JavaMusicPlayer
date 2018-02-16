package cs3500.music.controller;

import org.junit.Test;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.MockMagicView;
import cs3500.music.view.ViewModel;

import static org.junit.Assert.assertEquals;

/**
 * represents tests for the controller
 */
public class ControllerTest {
  Controller c;
  MockMagicView view;

  public ControllerTest() throws IOException {
    String filename = "testCase1.txt";
    CompositionBuilder<MusicModel> builder = new MusicModel.Builder();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(new File(filename)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    IMusicModel model = MusicReader.parseFile(reader, builder);
    view = new MockMagicView(new ViewModel(model));

    this.c = new Controller(model, view);
  }

  @Test
     public void testEnd() {
    KeyboardHandler keyboardHandler = new KeyboardHandler();
    c.setKeyListen(keyboardHandler);
    c.initialize();
    KeyEvent key = new KeyEvent(new Box(0), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_END, 'W');
    keyboardHandler.keyPressed(key);

    assertEquals(view.getMostRecentAction(), "Jumped to end\n");
  }

  @Test
  public void testBegin() {
    KeyboardHandler keyboardHandler = new KeyboardHandler();
    c.setKeyListen(keyboardHandler);
    c.initialize();
    KeyEvent key = new KeyEvent(new Box(0), KeyEvent.VK_HOME, 0, 0, KeyEvent.VK_HOME, 'W');
    keyboardHandler.keyPressed(key);

    assertEquals(view.getMostRecentAction(), "Returned to beginning\n");
  }

  @Test
  public void testPause() {
    KeyboardHandler keyboardHandler = new KeyboardHandler();
    c.setKeyListen(keyboardHandler);
    c.initialize();
    KeyEvent key = new KeyEvent(new Box(0), KeyEvent.VK_SPACE, 0, 0, KeyEvent.VK_SPACE, 'W');
    keyboardHandler.keyPressed(key);

    assertEquals(view.getMostRecentAction(), "Paused set to true\n");
  }


  @Test
  public void testPauseAndStart() {
    KeyboardHandler keyboardHandler = new KeyboardHandler();
    c.setKeyListen(keyboardHandler);
    c.initialize();
    KeyEvent key = new KeyEvent(new Box(0), KeyEvent.VK_SPACE, 0, 0, KeyEvent.VK_SPACE, 'W');
    keyboardHandler.keyPressed(key);

    KeyEvent key2 = new KeyEvent(new Box(0), KeyEvent.VK_SPACE, 0, 0, KeyEvent.VK_SPACE, 'W');
    keyboardHandler.keyPressed(key2);

    assertEquals(view.getMostRecentAction(), "Paused set to true\n"
                                             + "Paused set to false\n");
  }

  @Test
  public void testClick() {
    MouseHandler mouseHandler = new MouseHandler();
    MouseEvent mouseAction = new MouseEvent(new Box(0), 0, 0, 0, 213, 720, 1, false);
    c.setMouseListen(mouseHandler);
    c.initialize();
    mouseHandler.mouseClicked(mouseAction);
    assertEquals(view.getMostRecentAction(), "handled a click at 213, 720\n");
  }

  @Test
  public void testComplexInteraction() {
    MouseHandler mouseHandler = new MouseHandler();
    KeyboardHandler keyboardHandler = new KeyboardHandler();
    MouseEvent mouseAction = new MouseEvent(new Box(0), 0, 0, 0, 213, 720, 1, false);
    c.setKeyListen(keyboardHandler);
    c.setMouseListen(mouseHandler);
    c.initialize();
    mouseHandler.mouseClicked(mouseAction);
    MouseEvent mouseAction2 = new MouseEvent(new Box(0), 0, 0, 0, 23, 72, 1, false);
    mouseHandler.mouseClicked(mouseAction2);
    KeyEvent key2 = new KeyEvent(new Box(0), KeyEvent.VK_SPACE, 0, 0, KeyEvent.VK_SPACE, 'W');
    keyboardHandler.keyPressed(key2);

    KeyEvent key3 = new KeyEvent(new Box(0), KeyEvent.VK_HOME, 0, 0, KeyEvent.VK_HOME, 'W');
    keyboardHandler.keyPressed(key3);

    assertEquals(view.getMostRecentAction(), "handled a click at 213, 720\n"
                                             + "handled a click at 23, 72\n"
                                             + "Paused set to true\n"
                                             + "Returned to beginning\n");
  }


}