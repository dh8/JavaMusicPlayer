package cs3500.music.controller;

import org.junit.Test;

import java.awt.*;
import java.awt.event.MouseEvent;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the mouse handler
 */
public class MouseHandlerTest {



    @Test
    public void testingMouseClickWorked() throws Exception {

      MouseHandler mouseHandler = new MouseHandler();
      Button a = new Button("click");

      StringBuilder out = new StringBuilder();
      MouseEvent mouse = new MouseEvent(a, 3, 2, 2, 400, 400, 1, true);

      Runnable runnable = new Runnable() {
        @Override
        public void run() {
          out.append("That click worked.");
        }
      };
      mouseHandler.addClickEvent("click", runnable);
      mouseHandler.mouseClicked(mouse);
      assertEquals("That click worked.", out.toString());
    }
}