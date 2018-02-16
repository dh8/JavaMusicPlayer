package cs3500.music.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.model.MusicModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;

/**
 * Represents the console view
 */
public class ConsoleView implements MusicEditorView {
  ViewModel model;

  /**
   * @param model                  the model
   * @param filename               the filename we are going to build from
   * @throws FileNotFoundException
   */
  public ConsoleView(String filename) throws FileNotFoundException {
    this.model = new ViewModel(filename);
    this.model.getBeatMax(); // make sure we don't have an empty piece!!
  }

  @Override
  public void initialize() {
    System.out.println(this.model);
  }


  public ViewModel getModel() {
    return model;
  }
}
