package cs3500.music;

import org.junit.Test;

import java.io.IOException;

import cs3500.music.view.ConsoleView;
import cs3500.music.view.MagicView;
import cs3500.music.view.MusicEditorView;

import static org.junit.Assert.assertEquals;


/**
 *  represents tests for the musicEditor
 */
public class MusicEditorTest {

  @Test
  public void factoryTest1() throws IOException {
    MusicEditorView fac = MusicEditor.Factory.make("testCase1.txt", "console");
    assertEquals(fac instanceof ConsoleView, true);
  }

  // commented out because the sleep mechanism in midi
  // view causes problems with testing instance of

//  @Test
//  public void factoryTest2() throws IOException {
//    MusicEditorView fac = MusicEditor.Factory.make("testCase1.txt", "midi");
//    assertEquals(fac instanceof MidiView, true);
//  }

  @Test
  public void factoryTest3() throws IOException {
    MusicEditorView fac = MusicEditor.Factory.make("testCase1.txt", "magic");
    assertEquals(fac instanceof MagicView, true);
  }


  @Test (expected=IllegalArgumentException.class)
  public void factoryTest4() throws IOException {
    MusicEditorView fac = MusicEditor.Factory.make("testCase1.txt", "OMGGG");

  }





}
