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

import static org.junit.Assert.assertEquals;

/**
 *  tests for the keyboardHandler
 *  NOTE: many mouseHandler tests are also included
 */
public class KeyboardHandlerTest {

  //////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////// TESTING MOVING A NOTE /////////////////////////////////////////
  ////////////////////////////////// Right, left, up, down /////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////

  /**
   *  Testing moving the note up:
   *  i.e. clicking the desired note and then typing w
   */
  @Test
  public void testMoveUp() throws IOException {
    // instantiate the controller, custom handlers etc.
    MouseHandler mouseHandler = new MouseHandler();
    MouseEvent mouse = new MouseEvent(new Box(0), 0, 0, 0, 87, 148, 1, false);
    StringBuilder out = new StringBuilder();

    String filename = "testCase1.txt";

    /**
     * Get the model
     */
    CompositionBuilder<MusicModel> builder = new MusicModel.Builder();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(new File(filename)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    IMusicModel model = MusicReader.parseFile(reader, builder);

    Controller myCont = new Controller(model);

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        out.append("Clicked the mouse");
      }
    };

    mouseHandler.addClickEvent("click", runnable);
    mouseHandler.mouseClicked(mouse);


    KeyboardHandler keyboardHandler = new KeyboardHandler();
    KeyEvent key = new KeyEvent(new Box(0), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_W, 'W');
    myCont.setKeyListen(keyboardHandler);
    Runnable runnable2 = new Runnable() {
      @Override
      public void run() {
        out.append(", and typed w. Note moved up");
      }
    };
    keyboardHandler.addOnKeyPressed(KeyEvent.VK_W, runnable2);
    keyboardHandler.keyPressed(key);
    keyboardHandler.keyReleased(key);

    assertEquals(out.toString(), "Clicked the mouse, and typed w. Note moved up");
  }

  /**
   *  Testing moving the note down:
   *  i.e. clicking the desired note and then typing s
   */
  @Test
  public void testMoveDown() throws IOException {
    // instantiate the controller, custom handlers etc.
    MouseHandler mouseHandler = new MouseHandler();
    MouseEvent mouse = new MouseEvent(new Box(0), 0, 0, 0, 87, 148, 1, false);
    StringBuilder out = new StringBuilder();

    String filename = "testCase1.txt";

    /**
     * Get the model
     */
    CompositionBuilder<MusicModel> builder = new MusicModel.Builder();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(new File(filename)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    IMusicModel model = MusicReader.parseFile(reader, builder);

    Controller myCont = new Controller(model);

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        out.append("Clicked the mouse");
      }
    };

    mouseHandler.addClickEvent("click", runnable);
    mouseHandler.mouseClicked(mouse);


    KeyboardHandler keyboardHandler = new KeyboardHandler();
    KeyEvent key = new KeyEvent(new Box(0), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_S, 'S');
    myCont.setKeyListen(keyboardHandler);
    Runnable runnable2 = new Runnable() {
      @Override
      public void run() {
        out.append(", and typed s. Note moved down");
      }
    };
    keyboardHandler.addOnKeyPressed(KeyEvent.VK_S, runnable2);
    keyboardHandler.keyPressed(key);
    keyboardHandler.keyReleased(key);

    assertEquals(out.toString(), "Clicked the mouse, and typed s. Note moved down");
  }


  /**
   *  Testing moving the note right:
   *  i.e. clicking the desired note and then typing d
   */
  @Test
  public void testMoveRight() throws IOException {
    // instantiate the controller, custom handlers etc.
    MouseHandler mouseHandler = new MouseHandler();
    MouseEvent mouse = new MouseEvent(new Box(0), 0, 0, 0, 87, 148, 1, false);
    StringBuilder out = new StringBuilder();

    String filename = "testCase1.txt";

    /**
     * Get the model
     */
    CompositionBuilder<MusicModel> builder = new MusicModel.Builder();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(new File(filename)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    IMusicModel model = MusicReader.parseFile(reader, builder);

    Controller myCont = new Controller(model);

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        out.append("Clicked the mouse");
      }
    };

    mouseHandler.addClickEvent("click", runnable);
    mouseHandler.mouseClicked(mouse);


    KeyboardHandler keyboardHandler = new KeyboardHandler();
    KeyEvent key = new KeyEvent(new Box(0), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_D, 'D');
    myCont.setKeyListen(keyboardHandler);
    Runnable runnable2 = new Runnable() {
      @Override
      public void run() {
        out.append(", and typed d. Note moved right");
      }
    };
    keyboardHandler.addOnKeyPressed(KeyEvent.VK_D, runnable2);
    keyboardHandler.keyPressed(key);
    keyboardHandler.keyReleased(key);

    assertEquals(out.toString(), "Clicked the mouse, and typed d. Note moved right");
  }



  /**
   *  Testing moving the note left:
   *  i.e. clicking the desired note and then typing a
   */
  @Test
  public void testMoveLeft() throws IOException {
    // instantiate the controller, custom handlers etc.
    MouseHandler mouseHandler = new MouseHandler();
    MouseEvent mouse = new MouseEvent(new Box(0), 0, 0, 0, 87, 148, 1, false);
    StringBuilder out = new StringBuilder();

    String filename = "testCase1.txt";

    /**
     * Get the model
     */
    CompositionBuilder<MusicModel> builder = new MusicModel.Builder();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(new File(filename)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    IMusicModel model = MusicReader.parseFile(reader, builder);

    Controller myCont = new Controller(model);

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        out.append("Clicked the mouse");
      }
    };

    mouseHandler.addClickEvent("click", runnable);
    mouseHandler.mouseClicked(mouse);


    KeyboardHandler keyboardHandler = new KeyboardHandler();
    KeyEvent key = new KeyEvent(new Box(0), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_A, 'A');
    myCont.setKeyListen(keyboardHandler);
    Runnable runnable2 = new Runnable() {
      @Override
      public void run() {
        out.append(", and typed a. Note moved left");
      }
    };
    keyboardHandler.addOnKeyPressed(KeyEvent.VK_A, runnable2);
    keyboardHandler.keyPressed(key);
    keyboardHandler.keyReleased(key);

    assertEquals(out.toString(), "Clicked the mouse, and typed a. Note moved left");
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////TESTING EXTENDING, RETRACTING A NOTE ///////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////


  /**
   *  Testing extending note:
   *  i.e. clicking the desired note and then typing e
   */
  @Test
  public void testExtendNote() throws IOException {
    // instantiate the controller, custom handlers etc.
    MouseHandler mouseHandler = new MouseHandler();
    MouseEvent mouse = new MouseEvent(new Box(0), 0, 0, 0, 87, 148, 1, false);
    StringBuilder out = new StringBuilder();

    String filename = "testCase1.txt";

    /**
     * Get the model
     */
    CompositionBuilder<MusicModel> builder = new MusicModel.Builder();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(new File(filename)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    IMusicModel model = MusicReader.parseFile(reader, builder);

    Controller myCont = new Controller(model);

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        out.append("Clicked the mouse");
      }
    };

    mouseHandler.addClickEvent("click", runnable);
    mouseHandler.mouseClicked(mouse);


    KeyboardHandler keyboardHandler = new KeyboardHandler();
    KeyEvent key = new KeyEvent(new Box(0), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_E, 'E');
    myCont.setKeyListen(keyboardHandler);
    Runnable runnable2 = new Runnable() {
      @Override
      public void run() {
        out.append(", and typed e. Note was extended");
      }
    };
    keyboardHandler.addOnKeyPressed(KeyEvent.VK_E, runnable2);
    keyboardHandler.keyPressed(key);
    keyboardHandler.keyReleased(key);

    assertEquals(out.toString(), "Clicked the mouse, and typed e. Note was extended");
  }



  /**
   *  Testing retracting the note:
   *  i.e. clicking the desired note and then typing q
   */
  @Test
  public void testRetractNote() throws IOException {
    // instantiate the controller, custom handlers etc.
    MouseHandler mouseHandler = new MouseHandler();
    MouseEvent mouse = new MouseEvent(new Box(0), 0, 0, 0, 87, 148, 1, false);
    StringBuilder out = new StringBuilder();

    String filename = "testCase1.txt";

    /**
     * Get the model
     */
    CompositionBuilder<MusicModel> builder = new MusicModel.Builder();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(new File(filename)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    IMusicModel model = MusicReader.parseFile(reader, builder);

    Controller myCont = new Controller(model);

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        out.append("Clicked the mouse");
      }
    };

    mouseHandler.addClickEvent("click", runnable);
    mouseHandler.mouseClicked(mouse);


    KeyboardHandler keyboardHandler = new KeyboardHandler();
    KeyEvent key = new KeyEvent(new Box(0), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_Q, 'Q');
    myCont.setKeyListen(keyboardHandler);
    Runnable runnable2 = new Runnable() {
      @Override
      public void run() {
        out.append(", and typed q. Note was retracted");
      }
    };
    keyboardHandler.addOnKeyPressed(KeyEvent.VK_Q, runnable2);
    keyboardHandler.keyPressed(key);
    keyboardHandler.keyReleased(key);

    assertEquals(out.toString(), "Clicked the mouse, and typed q. Note was retracted");
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////TESTING DELETING A NOTE ////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////

  /**
   *  Testing deleting the note:
   *  i.e. clicking the desired note and then typing x
   */
  @Test
  public void testDeleteNote() throws IOException {
    // instantiate the controller, custom handlers etc.
    MouseHandler mouseHandler = new MouseHandler();
    MouseEvent mouse = new MouseEvent(new Box(0), 0, 0, 0, 87, 148, 1, false);
    StringBuilder out = new StringBuilder();

    String filename = "testCase1.txt";

    /**
     * Get the model
     */
    CompositionBuilder<MusicModel> builder = new MusicModel.Builder();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(new File(filename)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    IMusicModel model = MusicReader.parseFile(reader, builder);

    Controller myCont = new Controller(model);

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        out.append("Clicked the mouse");
      }
    };

    mouseHandler.addClickEvent("click", runnable);
    mouseHandler.mouseClicked(mouse);


    KeyboardHandler keyboardHandler = new KeyboardHandler();
    KeyEvent key = new KeyEvent(new Box(0), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_X, 'X');
    myCont.setKeyListen(keyboardHandler);
    Runnable runnable2 = new Runnable() {
      @Override
      public void run() {
        out.append(", and typed x. Note was deleted");
      }
    };
    keyboardHandler.addOnKeyPressed(KeyEvent.VK_X, runnable2);
    keyboardHandler.keyPressed(key);
    keyboardHandler.keyReleased(key);

    assertEquals(out.toString(), "Clicked the mouse, and typed x. Note was deleted");
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////TESTING PAUSING COMPOSITION ////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////

  /**
   *  Testing pausing composition:
   *  i.e. clicking the desired note and then typing space
   */
  @Test
  public void testPauseComp() throws IOException {
    // instantiate the controller, custom handlers etc.
    MouseHandler mouseHandler = new MouseHandler();
    MouseEvent mouse = new MouseEvent(new Box(0), 0, 0, 0, 87, 148, 1, false);
    StringBuilder out = new StringBuilder();

    String filename = "testCase1.txt";

    /**
     * Get the model
     */
    CompositionBuilder<MusicModel> builder = new MusicModel.Builder();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(new File(filename)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    IMusicModel model = MusicReader.parseFile(reader, builder);

    Controller myCont = new Controller(model);

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        out.append("Clicked the mouse");
      }
    };

    mouseHandler.addClickEvent("click", runnable);
    mouseHandler.mouseClicked(mouse);


    KeyboardHandler keyboardHandler = new KeyboardHandler();
    KeyEvent key = new KeyEvent(new Box(0), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_SPACE, 'X');
    myCont.setKeyListen(keyboardHandler);
    Runnable runnable2 = new Runnable() {
      @Override
      public void run() {
        out.append(", and typed space. Composition was paused");
      }
    };
    keyboardHandler.addOnKeyPressed(KeyEvent.VK_SPACE, runnable2);
    keyboardHandler.keyPressed(key);
    keyboardHandler.keyReleased(key);

    assertEquals(out.toString(), "Clicked the mouse, and typed space. Composition was paused");
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////TESTING GOING TO THE START AND BEGINNING /////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////

  /**
   *  Testing going to the start of a composition
   *  i.e. clicking the desired note and then typing home
   */
  @Test
  public void testHomeComp() throws IOException {
    // instantiate the controller, custom handlers etc.
    MouseHandler mouseHandler = new MouseHandler();
    MouseEvent mouse = new MouseEvent(new Box(0), 0, 0, 0, 87, 148, 1, false);
    StringBuilder out = new StringBuilder();

    String filename = "testCase1.txt";

    /**
     * Get the model
     */
    CompositionBuilder<MusicModel> builder = new MusicModel.Builder();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(new File(filename)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    IMusicModel model = MusicReader.parseFile(reader, builder);

    Controller myCont = new Controller(model);

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        out.append("Clicked the mouse");
      }
    };

    mouseHandler.addClickEvent("click", runnable);
    mouseHandler.mouseClicked(mouse);


    KeyboardHandler keyboardHandler = new KeyboardHandler();
    KeyEvent key = new KeyEvent(new Box(0), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_HOME, 'X');
    myCont.setKeyListen(keyboardHandler);
    Runnable runnable2 = new Runnable() {
      @Override
      public void run() {
        out.append(", and typed home. Went to the beginning of the comp");
      }
    };
    keyboardHandler.addOnKeyPressed(KeyEvent.VK_HOME, runnable2);
    keyboardHandler.keyPressed(key);
    keyboardHandler.keyReleased(key);

    assertEquals(out.toString(), "Clicked the mouse, and typed home. "
                                 + "Went to the beginning of the comp");
  }


  /**
   *  Testing going to the end of a composition
   *  i.e. clicking the desired note and then typing end
   */
  @Test
  public void testEndComp() throws IOException {
    // instantiate the controller, custom handlers etc.
    MouseHandler mouseHandler = new MouseHandler();
    MouseEvent mouse = new MouseEvent(new Box(0), 0, 0, 0, 87, 148, 1, false);
    StringBuilder out = new StringBuilder();

    String filename = "testCase1.txt";

    /**
     * Get the model
     */
    CompositionBuilder<MusicModel> builder = new MusicModel.Builder();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(new File(filename)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    IMusicModel model = MusicReader.parseFile(reader, builder);

    Controller myCont = new Controller(model);

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        out.append("Clicked the mouse");
      }
    };

    mouseHandler.addClickEvent("click", runnable);
    mouseHandler.mouseClicked(mouse);


    KeyboardHandler keyboardHandler = new KeyboardHandler();
    KeyEvent key = new KeyEvent(new Box(0), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_END, 'X');
    myCont.setKeyListen(keyboardHandler);
    Runnable runnable2 = new Runnable() {
      @Override
      public void run() {
        out.append(", and typed end. Went to the end of the comp");
      }
    };
    keyboardHandler.addOnKeyPressed(KeyEvent.VK_END, runnable2);
    keyboardHandler.keyPressed(key);
    keyboardHandler.keyReleased(key);

    assertEquals(out.toString(), "Clicked the mouse, and typed end. "
                                 + "Went to the end of the comp");
  }
}