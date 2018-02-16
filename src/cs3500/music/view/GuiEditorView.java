package cs3500.music.view;

import cs3500.music.controller.KeyboardHandler;

/**
 * Created by a172772 on 4/2/16.
 */
public interface GuiEditorView extends MusicEditorView {
  public void setKeyListener(KeyboardHandler kbh);
  //public void addNote(int beatNum, Note n, int duration, int volume, int instrument);
}
