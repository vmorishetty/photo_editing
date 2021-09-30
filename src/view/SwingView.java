package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import view.observer.IObserver;

public class SwingView extends JFrame implements ImageView, ActionListener {

  private IObserver obs;

  private JPanel mainPanel;
  private JScrollPane mainScroll;

  private JLabel nameOfLayers;
  private String layerNames = "Layers: ";

  private JMenuBar menuBar;
  private JMenu menu;
  private JMenu filterMenu;
  private JMenu layerMenu;




  public SwingView() {
    super();

    setTitle("Multi Layer Processing GUI");
    setSize(1000, 800);


    mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(40, 2, 1, 1));

    mainScroll = new JScrollPane(mainPanel);
    add(mainScroll);


    nameOfLayers = new JLabel("Layers: ");
    mainPanel.add(nameOfLayers);

    // MENU BAR
    menuBar = new JMenuBar();
    mainPanel.add(menuBar);

    menu = new JMenu("Multi Image Processing");
    menuBar.add(menu);


    // LAYER OPERATIONS
    layerMenu = new JMenu("Layer Operations");
    menu.add(layerMenu);

    JMenuItem createLayer = new JMenuItem("Create Layer");
    createLayer.setActionCommand("cl");
    createLayer.addActionListener(this);
    layerMenu.add(createLayer);

    JMenuItem removeLayer = new JMenuItem("Remove Layer");
    removeLayer.setActionCommand("rl");
    createLayer.addActionListener(this);
    layerMenu.add(removeLayer);


    JMenuItem currentLayer = new JMenuItem("Set Current Layer");
    currentLayer.setActionCommand("current");
    currentLayer.addActionListener(this);
    layerMenu.add(currentLayer);



    // FILTERS
    filterMenu = new JMenu("Image Operations");
    menu.add(filterMenu);

    JMenuItem blur = new JMenuItem("Blur");
    blur.setActionCommand("blur");
    blur.addActionListener(this);
    filterMenu.add(blur);

    JMenuItem sepia = new JMenuItem("Sepia");
    sepia.setActionCommand("sepia");
    sepia.addActionListener(this);
    filterMenu.add(sepia);

    JMenuItem monochrome = new JMenuItem("Monochrome");
    monochrome.setActionCommand("monochrome");
    monochrome.addActionListener(this);
    filterMenu.add(monochrome);

    JMenuItem sharpen = new JMenuItem("Sharpen");
    sharpen.setActionCommand("sharpen");
    sharpen.addActionListener(this);
    filterMenu.add(sharpen);

    JMenuItem mosaic = new JMenuItem("Mosaic");
    mosaic.setActionCommand("mosaic");
    mosaic.addActionListener(this);
    filterMenu.add(mosaic);

    JMenuItem downscale = new JMenuItem("Downscale");
    downscale.setActionCommand("downscale");
    downscale.addActionListener(this);
    filterMenu.add(downscale);












//    // IMAGE PANEL
//    JPanel imagePanel = new JPanel();
//    imagePanel.setBorder(BorderFactory.createTitledBorder("POPE + BABY YODA COLLAB"));
//    mainPanel.add(imagePanel);
//
//    // IMAGE LABEL
//    String image1 = "pope.png";
//    JLabel imageLabel = new JLabel();
//    imageLabel.setIcon(new ImageIcon(image1));
//
//    // IMAGE SCROLL PANE
//    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
//    imageScrollPane.setPreferredSize(new Dimension(400, 400));
//
//    imagePanel.add(imageScrollPane);
//
//
//
//    //JOptionsPane input dialog
//    JPanel inputDialogPanel = new JPanel();
//    inputDialogPanel.setLayout(new FlowLayout());
//    mainPanel.add(inputDialogPanel);
//
//    JButton inputButton = new JButton("Click to create layer");
//    inputButton.setActionCommand("new layer");
//    inputButton.addActionListener(this);
//    inputDialogPanel.add(inputButton);
//
//    createLayerDisplay = new JLabel("Default");
//    inputDialogPanel.add(createLayerDisplay);

  }


  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "blur":
        obs.perform("blur", "");
        break;
      case "sepia":
        obs.perform("sepia", "");
        break;
      case "monochrome":
        obs.perform("monochrome", "");
        break;
      case "sharpen":
        layerNames += "hello";
        nameOfLayers.setText(layerNames);
        //obs.perform("sharpen", "");
        break;
      case "mosaic":
        String seeds = JOptionPane.showInputDialog("Please enter amount of seeds");
        obs.perform("mosaic", "mosaic " + seeds);
        break;
      case "downscale":
        String width = JOptionPane.showInputDialog("Please enter amount of seeds");
        String height = JOptionPane.showInputDialog("Please enter amount of seeds");
        obs.perform("downscale", "downscale " + width + " " + height);
        break;
    }
  }

  @Override
  public void setObserver(IObserver obs) {
    this.obs = obs;
  }


  @Override
  public void renderMessage(String message) throws IOException { }


}
