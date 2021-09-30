package image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import layer.Layer;
import layer.MultiLayer;


/**
 * This class contains utility methods to read a PPM image from file and returns specific contents
 * of the PPM image file.
 */
public class ImageUtil {


  /**
   * Returns a 2D array of Pixels that is representative of the contents of the given PPM file
   * image.
   * @param filename the location of the file in the computer as a String
   * @return a 2D array of Pixels
   * @throws FileNotFoundException if the given file is not located
   */
  public static Pixel[][] importPPM(String filename) throws FileNotFoundException {
    Scanner sc = new Scanner(new FileInputStream(".\\res\\" + filename));

    StringBuilder builder = new StringBuilder();

    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }


    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();

    Pixel[][] image = new Pixel[height][width];

    sc.nextInt();


    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int[] rgb = new int[3];

        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        rgb[0] = r;
        rgb[1] = g;
        rgb[2] = b;



        image[i][j] = new Pixel(rgb);
      }
    }
    return image;

  }

  /**
   * Returns the maximum RGB value. This value is taken from the contents of the PPM file.
   * @param filename the file location as a String
   * @return an int which represents the maximum RGB value
   * @throws FileNotFoundException if file location is not located
   */
  public static int returnMaxValue(String filename) throws FileNotFoundException {
    Scanner sc = new Scanner(new FileInputStream(filename));

    StringBuilder builder = new StringBuilder();

    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }


    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    sc.nextInt();
    sc.nextInt();

    return sc.nextInt();
  }


  /**
   * Exports the image to a new file in the project file. This exports a P3 ascii .ppm image.
   * @param image the IImage that is to be exported as a PPM
   * @param fileName the desired name of the file for the exported image
   * @throws IOException if the output file is invalid
   */
  public static void exportImageToPPM(IImage image, String fileName) throws IOException {
    File outputImage = new File(".\\res\\" + fileName);
    Appendable ppmData = new StringBuilder();

    ppmData.append("P3" + "\n");
    ppmData.append(String.valueOf(image.imageWidth())).append(" ")
        .append(String.valueOf(image.imageHeight())).append("\n");
    ppmData.append("").append(String.valueOf(image.getMaxValue())).append("\n");

    for (int y = 0; y < image.imageHeight(); y++) {
      for (int x = 0; x < image.imageWidth(); x++) {

        ppmData.append(new Pixel(image.getRGBat(y, x)).toString());

      }
    }

    try {
      FileWriter fileWriter = new FileWriter(outputImage);
      fileWriter.write(ppmData.toString());
      fileWriter.close();
    }
    catch (IOException e) {
      throw new IllegalArgumentException("File is invalid.");
    }

    outputImage.createNewFile();
  }


  /**
   * Returns a 2D array of Pixels that is representative of the contents of the given JPEG/JPG
   * or PNG image file
   * image.
   * @param filename the name of the file images a String
   * @return a 2D array of Pixels
   * @throws FileNotFoundException if the given file is not located
   */
  public static Pixel[][] importJPEGOrPNGImage(String filename) throws IOException {

    BufferedImage image = ImageIO.read(new File(".\\res\\" + filename));

    int width = image.getWidth();
    int height = image.getHeight();

    Pixel[][] pixels = new Pixel[height][width];

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        Color color = new Color(image.getRGB(i, j));

        int[] rgb = new int[]{color.getRed(), color.getGreen(), color.getBlue()};

        pixels[j][i] = new Pixel(rgb);
      }
    }

    return pixels;
  }


  /**
   * Exports the image to a new file in the project file. This exports a P3 ascii .ppm image.
   * @param image the IImage that is to be exported as a PPM
   * @param filename the desired name of the file for the exported image
   * @param extension a String that is the desired extension type for the image
   * @throws IOException if the output file is invalid
   */
  public static void exportImageToJPEGOrPNG(IImage image, String filename, String extension)
      throws IOException {
    BufferedImage bufferedImage = new BufferedImage(image.imageWidth(),
        image.imageHeight(), BufferedImage.TYPE_INT_RGB);


    for (int i = 0; i < image.imageWidth(); i++) {
      for (int j = 0 ;j < image.imageHeight(); j++) {
        int[] rgb = image.getRGBat(j, i);
        int r = rgb[0];
        int g = rgb[1];
        int b = rgb[2];

        int rgbInteger = new Color(r, g, b).getRGB();

        bufferedImage.setRGB(i, j, rgbInteger);
      }
    }


    File outputImage = new File(".\\res\\" + filename);


    if (extension.equals("")) {
      throw new IllegalArgumentException("Invalid filetype in filename.");
    }


    ImageIO.write(bufferedImage, extension, outputImage);

    outputImage.createNewFile();
  }


  /**
   * Imports an image given the image name including the .extension.
   * @param filename the image name with filetype
   * @return a 2D array of pixels of the image data
   * @throws IOException if image file doesn't exist
   */
  public static Pixel[][] importImage(String filename) throws IOException {
    String extension = getExtension(filename);

    Pixel[][] pixels;

    if (extension.equals("ppm")) {
      pixels = importPPM(filename);
    }
    else {
      pixels = importJPEGOrPNGImage(filename);
    }

    return pixels;
  }


  /**
   * Exports an image given the image file name and extension added on to it.
   * @param image an IImage with data in it
   * @param filename the name of the resulting image file after exporting
   * @throws IOException if invalid filename
   */
  public static void exportImage(IImage image, String filename) throws IOException {
    String extension = getExtension(filename);


    switch (extension) {
      case "ppm":
        exportImageToPPM(image, filename);
        break;
      case "jpeg":
        exportImageToJPEGOrPNG(image, filename, extension);
        break;
      case "jpg":
        exportImageToJPEGOrPNG(image, filename, extension);
        break;
      case "png":
        exportImageToJPEGOrPNG(image, filename, extension);
        break;
      default:
    }

  }

  /**
   * Returns the extension of a filename as a String.
   * @param filename the filename of an image
   * @return a String which is the file type
   */
  public static String getExtension(String filename) {
    String extension = "";
    for (int i = 0; i < filename.length(); i++) {
      char c = filename.charAt(i);
      if (c == '.') {
        extension = filename.substring(i + 1);
      }
    }

    return extension;
  }


  /**
   * Exports a MultiLayer into a selected folder in the res folder by putting the MultiLayer's
   * images into separate files and creating a location.txt with the images' computer locations.
   * @param folderName the name of the folder
   * @param ml the MultiLayer to be exported
   * @throws IOException if the folder name is invalid
   */
  public static void exportMultiLayer(String folderName, MultiLayer ml) throws IOException {
    new File(".\\res\\" + folderName).mkdir();
    File location = new File(".\\res\\" + folderName + "\\" + "location.txt");
    FileWriter myWriter = new FileWriter(location.getAbsoluteFile());
    BufferedWriter bw = new BufferedWriter(myWriter);


    for (Layer l: ml.getLayers()) {
      ImageUtil.exportImage(l.getImage(), folderName + "\\"
          + l.getLayerName() + "." + l.getExtension());
      bw.write(".\\res\\" + folderName + "\\"
          + l.getLayerName() + "." + l.getExtension());
      bw.write("\n");
    }

    bw.close();

  }


  /**
   * Imports multiple images from a folder that was created by exporting a preexisting multi layer.
   * @param folderName the name of the folder to be
   * @return a List of string of the image names
   * @throws FileNotFoundException if file is invalid
   */
  public static List<String> importMultiLayer(String folderName) throws FileNotFoundException {
    File file =
        new File(".\\res\\" + folderName + "\\location.txt");

    Scanner sc = new Scanner(file);

    List<String> result = new ArrayList<>();
    while (sc.hasNextLine()) {
      result.add(sc.nextLine().substring(7 + folderName.length()));
    }

    return result;

  }
}

