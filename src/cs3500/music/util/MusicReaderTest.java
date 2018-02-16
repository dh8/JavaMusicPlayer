package cs3500.music.util;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.model.MusicModel;

import static org.junit.Assert.assertEquals;


/**
 *  represents tests for the music reader
 */
public class MusicReaderTest {

  /**
   *
   tempo 111600
   note 0 4 1 70 127
   note 4 8 1 70 127
   note 4 7 1 72 127
   note 6 8 1 73 127
   */
  @Test
  public void testMusicReader() throws FileNotFoundException {

    CompositionBuilder<MusicModel> builder = new MusicModel.Builder();
    BufferedReader reader = new BufferedReader(new FileReader(new File("testCase1.txt")));
    MusicModel model = MusicReader.parseFile(reader, builder);

    assertEquals(model.toString(), "  A#4   B4   C5  C#5 \n"
                                   + "0  X                 \n"
                                   + "1  |                 \n"
                                   + "2  |                 \n"
                                   + "3  |                 \n"
                                   + "4  X         X       \n"
                                   + "5  |         |       \n"
                                   + "6  |         |    X  \n"
                                   + "7  |              |  \n");

  }




}
