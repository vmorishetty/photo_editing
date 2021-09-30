import controller.GUIController;
import controller.LayerController;
import controller.LayerTextController;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.JFrame;
import layer.MultiLayer;
import java.io.IOException;
import swingdemo.SwingFeaturesFrame;
import view.SwingView;
import view.observer.GUIObserver;
import view.observer.IObserver;

/**
 * Represents the Main class to test all of the operation on an image.
 */
public class Main {

  /**
   * Main function that allows for performing the image modifications.
   * @param args String of arguments
   */
  public static void main(String [] args) throws IOException {

//    MultiLayer ml = new MultiLayer();
//    LayerController control = new LayerTextController(ml, new InputStreamReader(System.in),
//        System.out);
//    control.readScript();
//    MultiLayer ml2 = new MultiLayer();
//    LayerController control2 = new LayerTextController(ml2, new InputStreamReader(System.in),
//        System.out);


    // control2.readScript("script2.txt");

    SwingView frame = new SwingView();
    IObserver gui = new GUIObserver();
    MultiLayer multiLayer = new MultiLayer();
    LayerController controller = new GUIController(multiLayer, frame, gui);
    controller.readScript();



  }
}