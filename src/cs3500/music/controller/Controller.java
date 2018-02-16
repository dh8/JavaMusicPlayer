package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import javax.swing.*;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.NoteMetaData;
import cs3500.music.view.JDisplay;
import cs3500.music.view.MagicView;
import cs3500.music.view.ViewModel;

/**
 * Controls the MagicView (and does so very nicely, we might add!)
 */
public final class Controller {
  /**
   * @param MagicView         The view that the controller controls
   * @param KeyboardHandler   Defines response to keyboard events
   * @param MouseHandler      Defines response to mouse events
   * @param Timer             Keeps midi and GUI views in sync
   */
  protected MagicView view;
  private KeyboardHandler kbh;
  private MouseHandler mh;
  private Timer timer;
  IMusicModel model;

  /**
   * Default constructor
   * Controller needs a MagicView to operate on
   * @param view    the MagicView (ie composite view)
   */
  public Controller(IMusicModel model) throws IOException {
    this.view = new MagicView(new ViewModel(model));
    this.kbh = new KeyboardHandler();
    this.mh = new MouseHandler();
    this.model = model;

    this.timer = new Timer(this.model.getTempo() / 1000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        view.incBeat();
      }
    });
  }

  /**
   * TEST THE CONTROLLER
   * @param model
   * @param test
   */
  public Controller(IMusicModel model, MagicView view) throws IOException {
    this.view = view;//new MockMagicView(new ViewModel(model));
    this.kbh = new KeyboardHandler();
    this.mh = new MouseHandler();
    this.model = model;

    this.timer = new Timer(this.model.getTempo() / 1000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        view.incBeat();
      }
    });
  }

  /**
   * Set up the controller
   */
  public void initialize() {
    this.addKeyCodes();
    this.addMouseCodes();
    this.timer.start();
    this.view.initialize();
    this.view.setKeyListener(kbh);
    this.view.setMouseListener(mh);
  }

  /**
   * Set up all the keys that we want to listen for
   */
  public void addKeyCodes() {
    kbh.addOnKeyPressed(KeyEvent.VK_S, new Runnable() {
      @Override
      public void run() {
        shiftSelected(0, -1, 0);
      }
    });

    kbh.addOnKeyPressed(KeyEvent.VK_W, new Runnable() {
      @Override
      public void run() {
        //System.out.println("CALLED");
        shiftSelected(0, 1, 0);
      }
    });

    kbh.addOnKeyPressed(KeyEvent.VK_A, new Runnable() {
      @Override
      public void run() {
        shiftSelected(-1, 0, 0);
      }
    });

    kbh.addOnKeyPressed(KeyEvent.VK_D, new Runnable() {
      @Override
      public void run() {
        shiftSelected(1, 0, 0);
      }
    });

    kbh.addOnKeyPressed(KeyEvent.VK_E, new Runnable() {
      @Override
      public void run() {
        //view.extend();
        shiftSelected(0, 0, 1);
      }
    });

    kbh.addOnKeyPressed(KeyEvent.VK_Q, new Runnable() {
      @Override
      public void run() {
        shiftSelected(0, 0, -1);
      }
    });

    kbh.addOnKeyPressed(KeyEvent.VK_SPACE, new Runnable() {
      @Override
      public void run() {
        view.pause();
      }
    });

    kbh.addOnKeyPressed(KeyEvent.VK_HOME, new Runnable() {
      @Override
      public void run() {
        view.home();
      }
    });

    kbh.addOnKeyPressed(KeyEvent.VK_END, new Runnable() {
      @Override
      public void run() {
        view.end();
      }
    });

    kbh.addOnKeyPressed(KeyEvent.VK_X, new Runnable() {
      @Override
      public void run() {
        //view.delete();
        deleteSelected();
      }
    });
  }

  /**
   * Set up all the mouse events that we want to listen for
   */
  public void addMouseCodes() {
    mh.addClickEvent("click", new Runnable() {
      @Override
      public void run() {

        view.handleClick(mh.clickX, mh.clickY);
        if(JDisplay.hasNewNote) {
          JDisplay.hasNewNote = false;
          model.addNote(JDisplay.newNoteBeat, JDisplay.newNoteMetaData.note, 2, 70, 5);
        }
      }
    });
  }

  /**
   * Deletes the selected notes from the model
   */
  public void deleteSelected() {
    HashSet<String> deselected = new HashSet<>();

    for(Iterator<Map.Entry<String, Note>> it =
            JDisplay.selected.entrySet().iterator(); it.hasNext(); ) {
      Map.Entry<String, Note> entry = it.next();
      int beatNum = Integer.parseInt(entry.getKey().split(",")[0]);
      if(!deselected.contains(entry.getKey())) {
        JDisplay.clearNoteFromSelected(beatNum, entry.getValue(), deselected);
        this.model.removeNote(beatNum, entry.getValue());
      }
    }
    JDisplay.selected.clear();
  }

  /**
   * Shifts the selected notes in the 3 axes
   * @param horiz   horizontal shift
   * @param vert    veritcal shift
   * @param extend  extend/retract
   */
  public void shiftSelected(int horiz, int vert, int extend) {
    HashMap<String, Note> selected = JDisplay.selected;

    HashMap<String, NoteMetaData> newNotes = new HashMap<>();
    HashSet<String> deselected = new HashSet<>();

    for(Iterator<Map.Entry<String, Note>> it = selected.entrySet().iterator(); it.hasNext();) {
      Map.Entry<String, Note> entry = it.next();
      int beatNum = Integer.parseInt(entry.getKey().split(",")[0]);
      if(!deselected.contains(entry.getKey())) {
        JDisplay.clearNoteFromSelected(beatNum, entry.getValue(), deselected);
        NoteMetaData nMeta = this.model.getNote(beatNum).get(entry.getValue().key());
        int noteStart = this.model.getNoteStartBeat(beatNum, nMeta.note);
        int noteEnd = this.model.getNoteEndBeat(beatNum, nMeta.note);
        newNotes.put(noteStart+","+noteEnd, nMeta);
        this.model.removeNote(beatNum, entry.getValue());
      }
    }
    selected.clear();

    for(Iterator<Map.Entry<String, NoteMetaData>> it =
            newNotes.entrySet().iterator(); it.hasNext();) {
      Map.Entry<String, NoteMetaData> entry = it.next();
      int noteStart = Integer.parseInt(entry.getKey().split(",")[0])+horiz;
      int noteEnd = Integer.parseInt(entry.getKey().split(",")[1])+horiz;

      NoteMetaData enhanced = entry.getValue();
      int instrument = enhanced.instrument;
      int volume = enhanced.volume;
      enhanced.note = enhanced.note.inc(vert);
      this.model.addNote(noteStart, enhanced.note, noteEnd - noteStart + extend,
                         volume, instrument);
    }
  }


  // used for testing
  public void setMouseListen(MouseHandler s) {
    this.mh = s;
    //this.view.setMouseListener(s);
  }

  // used for testing
  public void setKeyListen(KeyboardHandler s) {
    this.kbh = s;
    //this.view.setKeyListener(s);
  }

  // gets the view(this is for testing)
  public MagicView getView() {
    return view;
  }
}