package cs3500.music.view;

import java.io.IOException;

/**
 * represents a mock MagicView
 */
public class MockMagicView extends MagicView {
  String actions = "";

  public MockMagicView(ViewModel model) throws IOException {
    super(model);
  }

  public void handleClick(int x, int y) {
    actions += "handled a click at " + x + ", " + y + "\n";
  }

  public void incBeat() {
    actions += "incremented the beat by 1\n";
  }

  public void pause() {
    paused = !paused;
    actions += "Paused set to " + this.paused + "\n";
  }

  public void home() {
    actions += "Returned to beginning\n";
  }

  public void end() {
    actions += "Jumped to end\n";
  }

  public String getMostRecentAction() {
    return this.actions;
  }

  public void clearActions() {
    this.actions = "";
  }
}