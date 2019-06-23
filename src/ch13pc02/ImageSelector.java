package ch13pc02;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 *
 * @author Frank
 */
public class ImageSelector extends JFrame {
    //fields
    //size restrictions
    private final int width = 400;
    private final int height = 300;
    
    //components of the menu
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem getItem, exitItem;
    
    //components of the filechooser
    private JFileChooser fileChooser;
    
    //components for the image
    private JLabel image;
    private JPanel panel;
    
    //constructor
    public ImageSelector() {
        //set the title
        this.setTitle("Image Viewer");
        
        //set an action for the close button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(width, height));
        
        //build the menu bar
        buildMenuBar();
        buildPanel();
        
        this.add(panel);
        
        //pack and display the window
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        
    }
    
    //methods
    private void buildMenuBar() {
        //Create the menu bar
        menuBar = new JMenuBar();
        
        //create the menus
        buildFileMenu();
                
        //add menus to the menu bar
        menuBar.add(fileMenu);
                
        //set the window's menu bar
        setJMenuBar(menuBar);
    }
    
    private void buildFileMenu() {
        //create menu items
        getItem = new JMenuItem("Get Image");
        getItem.setMnemonic(KeyEvent.VK_G);
        getItem.addActionListener(new imageListener());
        
        exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.addActionListener(new exitListener());
        
        //create a JMenu object for the File Menu
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        //add items to the menu
        fileMenu.add(getItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
    }
    
    private void buildPanel() {
        panel = new JPanel();
    }
    
    
    /**
     * private inner class that handles the event generated when the user
     * selects Get Image from the File Menu
     */
    private class imageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //open file chooser
            fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String fileName = file.getAbsolutePath();
                
                image = new JLabel("", new ImageIcon(fileName), JLabel.CENTER);
                image.setToolTipText(file.getName());
                panel.add(image, BorderLayout.CENTER);
                panel.revalidate();
                panel.repaint();
            }
        }
    }
    
    /**
     * private inner class that handles the event generated when the user
     * selects Exit from the File Menu
     */
    private class exitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
