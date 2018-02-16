package cs3500.music.view;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.*;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.model.Note;
import cs3500.music.model.NoteMetaData;

/**
 * A fully featured view that does everything
 */
public class JDisplay extends JPanel {
  private int noteColWidth;
  private int measureHeight;
  private int measureWidth = 80;
  private int yOffset = 20;
  private int noteColOffset = 20;
  private int notesHeight = 0;
  private HashMap<String, Integer> noteHeights = new HashMap<String, Integer>();
  public static HashMap<String, Note> selected = new HashMap<>();
  public static int newNoteBeat = -1;
  public static NoteMetaData newNoteMetaData;
  public static boolean hasNewNote = false;
  private int beatNum = 0;
  private boolean ready = false;

  /**
   * @param noteColWidth       the note column width
   * @param measureHeight      the measure height
   * @param measureWidth       the measure width
   * @param yOffset            the y offset
   * @param noteColOffset      the note column offset
   * @param notesHeight        the notes height
   * @param g the graphics     the g graphics
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.paintBeatNums(g);
    this.paintNoteLabels(g);
    this.drawVerticalLines(g);
    this.drawHorizontalLines(g);
    this.drawNotes(g);
    this.setPreferredSize(new Dimension((this.measureWidth*GuiView.model.getBeatMax())/4 + 100,
                                        notesHeight+100));
    this.drawCursor(g);
    ready = true;
  }

  /**
   * Return the dimensions that the window would be in a perfect world if we had an infinitely large
   * screen
   * @return    Dimension that is a preferred dimension
   */
  @Override
  public Dimension getPreferredSize() {
    if(!ready) {
      return new Dimension(500, 500);
    }
    return new Dimension((this.measureWidth*GuiView.model.getBeatMax())/4 + 100, notesHeight+100);
  }

  /**
   *
   * @param beatNum   The number of the desired beat in the piece
   * @return          The x position where the desired beat starts
   */
  public int setBeatPos(int beatNum) {
    if(beatNum > GuiView.model.getBeatMax()) {
      throw new IllegalArgumentException("Beat exceeds bounds");
    }
    this.beatNum = beatNum;
    return noteColWidth + 20 + (measureWidth / 4) * beatNum;
  }

  /**
   * Find all beats associated with a note to be removed fromt the selected set
   * @param beatNum     A beat where the note is occurring
   * @param n           The note
   * @param deselected  A list of deselected notes to be built up
   */
  public static void clearNoteFromSelected(int beatNum, Note n, HashSet<String> deselected) {
    int bnStart = GuiView.model.getStartBeat(beatNum, n);
    int bnEnd = GuiView.model.getEndBeat(beatNum, n);
    for(int i = bnStart; i <= bnEnd; i++) {
      String key = i + "," + n.key();
      if(selected.containsKey(key)) {
        deselected.add(key);
      }
    }
  }

  /**
   * Move selected notes up a pitch
   */
  public void moveUp() {
    //shift(0, 1, 0);
  }

  /**
   * Move selected notes down a pitch
   */
  public void moveDown() {
    //shift(0, -1, 0);
  }

  /**
   * Move selected notes left a beat
   */
  public void moveLeft() {
    //shift(-1, 0, 0);
  }

  /**
   * Move selected notes right a beat
   */
  public void moveRight() {
    //shift(1, 0, 0);
  }

  /**
   * Make selected notes longer a beat
   */
  public void extend() {
    //shift(0, 0, 1);
  }

  /**
   * Make selected notes shorter a beat
   */
  public void retract() {
    //shift(0, 0, -1);
  }

  /**
   * Shift a note up/down and left/right, as well as extend and retract it
   * @param horiz   Shift up or down?
   * @param vert    Shift left or right?
   * @param extend  Extend left or right?
   */
  /*private void shift(int horiz, int vert, int extend) {
    HashMap<String, NoteMetaData> newNotes = new HashMap<>();
    HashSet<String> deselected = new HashSet<>();

    for(Iterator<Map.Entry<String, Note>> it = selected.entrySet().iterator(); it.hasNext();) {
      Map.Entry<String, Note> entry = it.next();
      int beatNum = Integer.parseInt(entry.getKey().split(",")[0]);
      if(!deselected.contains(entry.getKey())) {
        clearNoteFromSelected(beatNum, entry.getValue(), deselected);
        NoteMetaData nMeta = GuiView.model.getNote(beatNum).get(entry.getValue().key());
        int noteStart = GuiView.model.getStartBeat(beatNum, nMeta.note);
        int noteEnd = GuiView.model.getEndBeat(beatNum, nMeta.note);
        newNotes.put(noteStart+","+noteEnd, nMeta);
        GuiView.model.removeNote(beatNum, entry.getValue());
      }
    }
    selected.clear();


      Map.Entry<String, NoteMetaData> entry = it.next();
      int noteStart = Integer.parseInt(entry.getKey().split(",")[0]);
      int noteEnd = Integer.parseInt(entry.getKey().split(",")[1]);

      NoteMetaData enhanced = entry.getValue();
      int instrument = enhanced.instrument;
      int volume = enhanced.volume;
      enhanced.note = enhanced.note.inc(vert);
      GuiView.model.addNote(noteStart, enhanced.note, noteEnd-noteStart+extend, volume, instrument);
    }
  }*/

  /**
   * Remove all selected notes from the composition
   */
  public void delete() {
    /*HashSet<String> deselected = new HashSet<>();

    for(Iterator<Map.Entry<String, Note>> it = selected.entrySet().iterator(); it.hasNext(); ) {
      Map.Entry<String, Note> entry = it.next();
      int beatNum = Integer.parseInt(entry.getKey().split(",")[0]);
      if(!deselected.contains(entry.getKey())) {
        clearNoteFromSelected(beatNum, entry.getValue(), deselected);
        GuiView.model.removeNote(beatNum, entry.getValue());
      }
    }
    selected.clear();*/
  }

  /**
   * Handle any click event
   * If a note is clicked, select it
   * If empty space is clicked, add a note to it
   * @param x   x pos of the click
   * @param y   y pos of the click
   */
  public void handleClick(int x, int y) {
    // did we click on a note?
    int beatNum = getBeatFromX(x);
    Note note = getPitchFromY(y);

    HashMap<String, NoteMetaData> active = GuiView.model.getNote(beatNum);
    if(active.containsKey(note.key())) {

      ArrayList<String> associated = GuiView.model.associatedNotes(beatNum, note);
      if(selected.containsKey(beatNum+","+note.key())) {
        for(int i = 0; i < associated.size(); i++) {
          selected.remove(associated.get(i));
        }
      }
      else {
        for(int i = 0; i < associated.size(); i++) {
          selected.put(associated.get(i), note);
        }
      }
    }
    else {
      // TODO add notes
      //GuiView.model.addNote(beatNum, note, 2, 70, 5);
      System.out.println("NO NOTES HERE");
      newNoteBeat = beatNum;
      hasNewNote = true;
      newNoteMetaData = new NoteMetaData(note, true);
    }
  }

  /**
   * Return the beat associated with an x position
   * @param x
   * @return
   */
  private int getBeatFromX(int x) {
    int beatNum = (x - noteColWidth - 20) / (measureWidth / 4);
    return beatNum;
  }

  /**
   * Return the pitch associated with a y position
   */
  private Note getPitchFromY(int y) {
    int pitchNum = (y - yOffset) / measureHeight;
    Note curr = GuiView.model.highestNote();

    for(int i = 0; i < pitchNum; i++)
      curr = curr.inc(-1);

    return curr;
  }

  /**
   * Paint the note labels on the side of the piece
   * @param g   Graphics
   */
  public void paintNoteLabels(Graphics g) {
    Note minNote = GuiView.model.lowestNote();
    Note maxNote = GuiView.model.highestNote();
    noteColWidth = g.getFontMetrics().stringWidth(maxNote.toString());

    Note curr = maxNote;

    int yPos = yOffset + 20;
    int fontHeight = g.getFontMetrics().getHeight();
    int yPadding = 20;
    measureHeight = fontHeight + yPadding;

    notesHeight = 0;

    while (curr.compareTo(minNote) >= 0) {
      g.drawString(curr.toString(), 0, yPos + 10);
      curr = curr.inc(-1);
      yPos += fontHeight + yPadding;
      notesHeight += fontHeight + yPadding;
    }
  }

  /**
   * Label the beat numbers every 16 beats
   * @param g   Graphics
   */
  public void paintBeatNums(Graphics g) {
    int beatMax = GuiView.model.getBeatMax();

    int xPos = noteColWidth + 20;
    for (int i = 0; i < beatMax; i += 16) {
      g.drawString(Integer.toString(i), xPos, yOffset);
      xPos += 4 * measureWidth;
    }
  }

  /**
   * Draw the moving red cursor at the appropriate position based on the
   * current beat number
   * @param g
   */
  public void drawCursor(Graphics g) {
    int xPos = noteColWidth + 20 + (measureWidth / 4) * beatNum;
    g.setColor(new Color(255, 0, 0));
    g.drawLine(xPos, yOffset + noteColOffset - g.getFontMetrics().getHeight(), xPos,
               yOffset + noteColOffset - g.getFontMetrics().getHeight() + notesHeight);
    g.setColor(new Color(0, 0, 0));
  }

  /**
   * Draw the vertical lines
   * @param g
   */
  public void drawVerticalLines(Graphics g) {
    int beatMax = GuiView.model.getBeatMax();

    int xPos = noteColWidth + 20;
    int inc = 4;
    boolean done = false;

    for (int i = 0; i <= beatMax; i += inc) {
      g.drawLine(xPos, yOffset + noteColOffset - g.getFontMetrics().getHeight(), xPos,
                 yOffset + noteColOffset - g.getFontMetrics().getHeight() + notesHeight);
      xPos += measureWidth;
      if(i + inc > beatMax && !done) {
        done = true;
        i = beatMax - inc;
      }
    }
  }

  /**
   * Draw the horizontal lines
   * @param g
   */
  public void drawHorizontalLines(Graphics g) {
    Note minNote = GuiView.model.lowestNote();
    Note maxNote = GuiView.model.highestNote();

    Note curr = maxNote;
    int lineNum = 1;

    int noteY = yOffset + noteColOffset - g.getFontMetrics().getHeight();
    while (curr.compareTo(minNote) >= 0) {
      noteHeights.put(curr.toString(), noteY);
      curr = curr.inc(-1);

      lineNum++;
      noteY += g.getFontMetrics().getHeight() + 20;
    }

    int lineY = yOffset + noteColOffset - g.getFontMetrics().getHeight();
    int startX = noteColWidth + 20;
    int beatMax = GuiView.model.getBeatMax();
    int length = ((beatMax / 4) + (beatMax % 4 == 0 ? 0 : 1)) * measureWidth;

    for (int i = 0; i < lineNum; i++) {
      g.drawLine(startX, lineY, startX + length, lineY);
      lineY += g.getFontMetrics().getHeight() + 20;
    }
  }

  /**
   * Draw the notes themselves
   * @param g
   */
  public void drawNotes(Graphics g) {

    int beatMax = GuiView.model.getBeatMax();

    int xPos = noteColWidth + 20;
    for (int i = 0; i <= beatMax; i++) {
      if (GuiView.model.playingAt(i)) {
        HashMap<String, NoteMetaData> active = GuiView.model.getNote(i);
        for(Entry<String, NoteMetaData> entry : active.entrySet()) {
          String key = entry.getKey();
          NoteMetaData value = entry.getValue();

          //System.out.println(noteHeights.keySet());
          int yPos = noteHeights.get(value.note.toString());
          //System.out.println(noteHeights.keySet());

          if(value.articulate) {
            g.setColor(new Color(0, 0, 0));
          }
          else {
            g.setColor(new Color(0, 255, 0));
          }

          String selectKey = i + "," + value.note.key();
          if(selected.containsKey(selectKey)) {
            g.setColor(new Color(0, 0, 255));
          }

          g.fillRect(xPos, yPos, measureWidth / 4, g.getFontMetrics().getHeight() + 20);
        }
      }
      xPos += measureWidth / 4;
    }
  }
}
