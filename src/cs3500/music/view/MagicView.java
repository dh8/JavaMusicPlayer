package cs3500.music.view;

import java.io.IOException;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;
import cs3500.music.model.IMusicModel;

/**
 * This is our composite view View
 * It contains within it a GuiView and a MidiView
 * that it craftily manipulates, and is only
 * too happy to work in concert with a nice
 * friendly controller.
 */
public class MagicView implements GuiEditorView {
  private GuiView guiView;
  private MidiView midiView;
  private KeyboardHandler kbh;
  private MouseHandler mh;
  private int currentBeat = 0;
  boolean paused = false;
  //Timer timer;

  /**
   *
   * @param filename        The music file to read
   * @throws IOException
   */
  public MagicView(ViewModel model) throws IOException {
    this.guiView = new GuiView(model);
    this.midiView = new MidiView(model);
  }

  /**
   * Set up key listening
   * @param kbh
   */
  public void setKeyListener(KeyboardHandler kbh) {
    this.kbh = kbh;
    this.guiView.addKeyListener(kbh);
  }

  public void setMouseListener(MouseHandler mh) {
    this.mh = mh;
    this.guiView.addMouseListener(mh);
  }

  @Override
  public void initialize() {
    this.guiView.initialize();
    //this.midiView.initialize();
  }

  /*@Override
  public void addNote(int beatNum, Note n, int duration, int volume, int instrument) {
    GuiView.model.addNote(beatNum, n, duration, volume, instrument);
  }*/

  public void handleClick(int x, int y) {
    guiView.handleClick(x, y);
  }

  public void incBeat() {
    if(this.paused) {
      guiView.repaint();
      return;
    }
    currentBeat++;
    currentBeat %= GuiView.model.getBeatMax();

    midiView.setBeatPos(currentBeat);
    guiView.setBeatPos(currentBeat);
    guiView.repaint();
    this.midiView.syncModel(GuiView.model);
  }

  public void pause() {
    this.paused = !paused;
    guiView.pause();
    midiView.pause();
  }

  public void home() {
    currentBeat = 0;
    this.midiView.setBeatPos(currentBeat);
    this.guiView.setBeatPos(currentBeat);
    guiView.home();
    guiView.repaint();
    if(!this.paused) {
      this.pause();
    }
  }

  public void end() {
    currentBeat = GuiView.model.getBeatMax();
    this.midiView.setBeatPos(currentBeat);
    this.guiView.setBeatPos(currentBeat);
    if(!this.paused) {
      this.pause();
    }
  }
}