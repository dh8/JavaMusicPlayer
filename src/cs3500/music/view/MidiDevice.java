package cs3500.music.view;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Patch;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;
import javax.sound.midi.VoiceStatus;

/**
 * A mock midi device
 * For testing use
 */
public class MidiDevice implements Synthesizer {

  private final int tempo;
  private StringBuilder log = new StringBuilder();

  /**
   *
   * @param tempo     the given temp for the composition
   * @param log       the output, composed of the notes
   *
   */
  public MidiDevice(int tempo) {
    this.tempo = tempo;
    this.log = new StringBuilder();
  }

  public String getLog() {
    return log.toString();
  }

  @Override
  public int getMaxPolyphony() {
    return 0;
  }

  @Override
  public long getLatency() {
    return 0;
  }

  @Override
  public MidiChannel[] getChannels() {
    return new MidiChannel[0];
  }

  @Override
  public VoiceStatus[] getVoiceStatus() {
    return new VoiceStatus[0];
  }

  @Override
  public boolean isSoundbankSupported(Soundbank soundbank) {
    return false;
  }

  @Override
  public boolean loadInstrument(Instrument instrument) {
    return false;
  }

  @Override
  public void unloadInstrument(Instrument instrument) {

  }

  @Override
  public boolean remapInstrument(Instrument from, Instrument to) {
    return false;
  }

  @Override
  public Soundbank getDefaultSoundbank() {
    return null;
  }

  @Override
  public Instrument[] getAvailableInstruments() {
    return new Instrument[0];
  }

  @Override
  public Instrument[] getLoadedInstruments() {
    return new Instrument[0];
  }

  @Override
  public boolean loadAllInstruments(Soundbank soundbank) {
    return false;
  }

  @Override
  public void unloadAllInstruments(Soundbank soundbank) {

  }

  @Override
  public boolean loadInstruments(Soundbank soundbank, Patch[] patchList) {
    return false;
  }

  @Override
  public void unloadInstruments(Soundbank soundbank, Patch[] patchList) {

  }

  @Override
  public Info getDeviceInfo() {
    return null;
  }

  @Override
  public void open() throws MidiUnavailableException {

  }

  @Override
  public void close() {

  }

  @Override
  public boolean isOpen() {
    return false;
  }

  @Override
  public long getMicrosecondPosition() {
    return 0;
  }

  @Override
  public int getMaxReceivers() {
    return 0;
  }

  @Override
  public int getMaxTransmitters() {
    return 0;
  }

  /**
   * CLASS INVARIANTS:
   * @throws MidiUnavailableException
   */
  @Override
  public Receiver getReceiver() throws MidiUnavailableException {
    return new Receiver() {
      int i = 1;
      boolean starting = true;
      long tempTime;
      long anotherTemp;
      List list = new ArrayList<>();


      /**
       * Class Invariants: we append the output for testing
       */
      @Override
      public void send(MidiMessage message, long timeStamp) {
        if (starting) {
          log.append("The notes in this composition: \n");
          starting = false;
        }
        ShortMessage messag = (ShortMessage) message;

        if (messag.getCommand() == ShortMessage.NOTE_ON) {
          tempTime = timeStamp / tempo;
          log.append(
              "Note " + Integer.toString(i) +  " Pitch: " + messag.getData1() +
              " Starts at: " + timeStamp / tempo );
          i++;
        } else {
          anotherTemp = tempTime + (timeStamp/tempo) - 1;
          log.append(" Ends at " + anotherTemp);
          log.append(" The instrument is: " + messag.getChannel());
          log.append(" The volume: " + messag.getData2());
          log.append('\n');
        }



      }

      @Override
      public void close() {
        log.append("closed");
      }
    };
  }

  @Override
  public List<Receiver> getReceivers() {
    return null;
  }

  @Override
  public Transmitter getTransmitter() throws MidiUnavailableException {
    return null;
  }

  @Override
  public List<Transmitter> getTransmitters() {
    return null;
  }
}
