package cs3500.music.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import cs3500.music.model.MusicModel;
import cs3500.music.model.NoteMetaData;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;

/**
 * represents the Midi view
 */
public class MidiView implements MusicEditorView {
  private final Synthesizer synth;
  private final Receiver receiver;
  protected ViewModel model;
  private int beatNum = 0;
  boolean pause = false;

  /**
   *
   * @param synth generates sound for our view
   * @param receiver interpreting to generate sound or raw MIDI output.
   * @param filename the filename that we are requesting
   * @throws FileNotFoundException
   */
  public MidiView(String filename) throws FileNotFoundException {
    Synthesizer dummySynth = null;
    Receiver dummyReceiver = null;

    try {
      dummySynth = MidiSystem.getSynthesizer();
      dummyReceiver = dummySynth.getReceiver();
      dummySynth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }

    this.synth = dummySynth;
    this.receiver = dummyReceiver;

    this.model = new ViewModel(filename);
    this.model.getBeatMax(); // make sure we don't have an empty piece!!
    this.setUpInstruments();
  }

  public MidiView(ViewModel model) {
    Synthesizer dummySynth = null;
    Receiver dummyReceiver = null;

    try {
      dummySynth = MidiSystem.getSynthesizer();
      dummyReceiver = dummySynth.getReceiver();
      dummySynth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }

    this.synth = dummySynth;
    this.receiver = dummyReceiver;
    this.model = model;
    this.setUpInstruments();
  }

  public void syncModel(ViewModel model) {
    this.model = model;
  }

  private void setUpInstruments() {
    if(this.synth.getDefaultSoundbank() != null) {
      Instrument[] instruments = this.synth.getDefaultSoundbank().getInstruments();
      for (int i = 0; i <= 15; i++) {
        this.synth.getChannels()[i].programChange(instruments[i].getPatch().getProgram());
      }
    }
  }

  /**
   *
   * @param filename
   * @param md a MidiDevice
   * @throws FileNotFoundException
   */
  public MidiView(String filename, MidiDevice md) throws FileNotFoundException {
    Synthesizer dummySynth = null;
    Receiver dummyReceiver = null;

    try {
      dummySynth = md;
      dummyReceiver = dummySynth.getReceiver();
      dummySynth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }

    this.synth = dummySynth;
    this.receiver = dummyReceiver;

    this.model = new ViewModel(filename);
    this.model.getBeatMax(); // make sure we don't have an empty piece!!
  }
  /**
   * Relevant classes and methods from the javax.sound.midi library:
   * <ul>
   *  <li>{@link MidiSystem#getSynthesizer()}</li>
   *  <li>{@link Synthesizer}
   *    <ul>
   *      <li>{@link Synthesizer#open()}</li>
   *      <li>{@link Synthesizer#getReceiver()}</li>
   *      <li>{@link Synthesizer#getChannels()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link Receiver}
   *    <ul>
   *      <li>{@link Receiver#send(MidiMessage, long)}</li>
   *      <li>{@link Receiver#close()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link MidiMessage}</li>
   *  <li>{@link ShortMessage}</li>
   *  <li>{@link MidiChannel}
   *    <ul>
   *      <li>{@link MidiChannel#getProgram()}</li>
   *      <li>{@link MidiChannel#programChange(int)}</li>
   *    </ul>
   *  </li>
   * </ul>
   * @throws InvalidMidiDataException
   * @throws InterruptedException
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   *   https://en.wikipedia.org/wiki/General_MIDI
   *   </a>
   */
  public void playNotes() throws InvalidMidiDataException {
    int beatMax = this.model.getBeatMax();

    for(int i = 0; i < beatMax; i++) {
      this.playNote(i);
    }
  }

  public void playNote(int i) throws InvalidMidiDataException {
    if(this.pause) {
      return;
    }

    // i is the beatNum
    if(this.model.playingAt(i)) {
      HashMap<String, NoteMetaData> active = this.model.getNote(i);
      for(Entry<String, NoteMetaData> entry : active.entrySet()) {
        String key = entry.getKey();
        NoteMetaData value = entry.getValue();
        if(value.articulate) {
          int noteLength = this.model.duration(i, value.note);
          int tempo = this.model.getTempo();
          MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, value.instrument+1,
                                               value.note.toMidi(), value.volume);
          MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, value.instrument+1,
                                              value.note.toMidi(), value.volume);
          this.receiver.send(start, (i - beatNum) * tempo);
          this.receiver.send(stop, (i - beatNum) * tempo + tempo * noteLength);
        }
      }
    }
  }

  @Override
  public void initialize() {
    //this.setUpInstruments();
    try {
      playNotes();
    } catch (InvalidMidiDataException e) {
      System.out.println("Invalid MIDI data");
    }
    try {
      Thread.sleep((this.model.getTempo() / 1000) * this.model.getBeatMax() + 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void setBeatPos(int beatNum) {
    this.beatNum = beatNum;
    try {
      playNote(beatNum);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  public void pause() {
    this.pause = !pause;
  }
}