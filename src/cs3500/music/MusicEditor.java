package cs3500.music;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.controller.Controller;
import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.GuiView;
import cs3500.music.view.MidiView;
import cs3500.music.view.MusicEditorView;

/**
 * Represents the music editor
 */
public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException,
                                                InterruptedException {
    if(args.length != 2) {
      throw new IllegalArgumentException("You need two command line arguments, thank you.");
    }

    if(args[1].equals("magic")) {

      String filename = args[0];
      CompositionBuilder<MusicModel> builder = new MusicModel.Builder();
      BufferedReader reader = null;
      try {
        reader = new BufferedReader(new FileReader(new File(filename)));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      IMusicModel model = MusicReader.parseFile(reader, builder);

      Controller myCont = new Controller(model);
      myCont.initialize();

    }
    else {
      MusicEditorView myView = Factory.make(args[0], args[1]);
      myView.initialize();
    }
    //MusicEditorView myView = Factory.make(args[0], args[1]);
  }

  /**
   * represents a factory for the musicEditor
   */
  public static class Factory {

    /**
     *
     * @param filename                        the file name that we are going ot build from
     * @param type                            the view that we want to use (console, midi, visual)
     * @return
     * @throws IOException
     * @throws IllegalArgumentException       if the given string isn't
     *                                        (console, midi, visual)
     */
    public static MusicEditorView make(String filename, String type) throws IOException {
      if(type.equals("console")) {
        // we don't have a console view yet
        return new ConsoleView(filename);
      }
      else if(type.equals("midi")) {
        return new MidiView(filename);
      }
      else if(type.equals("visual")) {
        return new GuiView(filename);
      }
      /*else if(type.equals("magic")) {
        return new MagicView(filename);
      }*/
      else {
        throw new IllegalArgumentException("Please enter valid strings");
      }
    }
  }
}