import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Notepad extends JFrame implements ActionListener {
    private JTextArea pageTA = new JTextArea();
    private String scratchpad = "";
    private TextArea textArea = new TextArea("", 0,0, TextArea.SCROLLBARS_VERTICAL_ONLY);
    private MenuBar menuBar = new MenuBar(); // first, create a MenuBar item
    private Menu file = new Menu(); // our File menu
    // what's going in File? let's see...
    private MenuItem openFile = new MenuItem();  // an open option
    private MenuItem saveFile = new MenuItem(); // a save option
    private MenuItem aboutUS=new MenuItem();//about us option
    private MenuItem close = new MenuItem();

    private Menu Edit =new Menu();//ths sets our edit bar
    //this is what happen when a user click on the edit button
    private MenuItem  Cut =new MenuItem();
    private  MenuItem Copy=new MenuItem();
    private MenuItem Paste=new MenuItem();
    private MenuItem SelectALL=new MenuItem();



    public Notepad() {
        this.setSize(500, 300); // set the initial size of the window
        this.setTitle("Java Notepad PROJECT (HIIT)"); // set the title of the window
        setDefaultCloseOperation(EXIT_ON_CLOSE); // set the default close operation (exit when it gets closed)
        this.textArea.setFont(new Font("Lucisa Console", Font.BOLD+Font.ITALIC, 13)); // set a default font for the TextArea
        //this is to set the background color of the text area
        this.textArea.setBackground(Color.WHITE);

        // text area
        this.getContentPane().setLayout(new BorderLayout()); // the BorderLayout bit makes it fill it automatically
        this.getContentPane().add(textArea);

        this.setMenuBar(this.menuBar);
        this.menuBar.add(this.Edit);


        // add our menu bar into the GUI
        this.setMenuBar(this.menuBar);
        this.menuBar.add(this.file); // we'll configure this later

        // Menu Bar
        this.file.setLabel("File");


        // now we can start working on the content of the menu~ this gets a little rep

        // time for the repetitive stuff. let's add the "Open" option
        this.openFile.setLabel("Open"); // set the label of the menu item
        this.openFile.addActionListener(this); // add an action listener (so we know when it's been clicked
        this.openFile.setShortcut(new MenuShortcut(KeyEvent.VK_O, false)); // set a keyboard shortcut
        this.file.add(this.openFile); // add it to the "File" menu
        this.setBackground(Color.blue);


        // and the save...
        this.saveFile.setLabel("Save");
        this.saveFile.addActionListener(this);
        this.saveFile.setShortcut(new MenuShortcut(KeyEvent.VK_S, false));
        this.file.add(this.saveFile);
        //and about us
        this.aboutUS.setLabel("About Us");
        this.aboutUS.addActionListener(this);
        this.aboutUS.setShortcut(new MenuShortcut(KeyEvent.VK_ALT,false));
        this.file.add(this.aboutUS);

        // and finally, the close option
        this.close.setLabel("Close");
        // close option with shortcut
        this.close.setShortcut(new MenuShortcut(KeyEvent.VK_F4, false));
        this.close.addActionListener(this);
        this.file.add(this.close);


        this.setMenuBar(menuBar);
        this.menuBar.add(this.Edit);
        //seting the label for the edit button
        this.Edit.setLabel("Edit");

        //edit menu list
        this.Cut.setLabel("Cut ");
       // this.Cut.setShortcut(new MenuShortcut(KeyEvent.VK_TAB,false));
        this.Cut.addActionListener(this);
        this.Edit.add(this.Cut);

        //the copy option
        this.Copy.setLabel("Copy");
        //this.Copy.setShortcut(new MenuShortcut(KeyEvent.VK_C));
        this.Copy.addActionListener(this);
        this.Edit.add(this.Copy);


        //the paste option
        this.Paste.setLabel("Paste ");
      //  this.Paste.setShortcut(new MenuShortcut(KeyEvent.VK_V));
        this.Paste.addActionListener(this);
        this.Edit.add(this.Paste);

        //the selectAll option
        this.SelectALL.setLabel("Select All ");
        //this.SelectALL.setShortcut(new MenuShortcut(KeyEvent.VK_A));
        this.SelectALL.addActionListener(this);
        this.Edit.add(this.SelectALL);

    this.setIconImage(new ImageIcon(getClass().getResource("Chrysanthemum.jpg")).getImage());

    }





    public void actionPerformed (ActionEvent e) {



        // if the source of the event was our "close" option
        if (e.getSource() == this.close)
            this.dispose(); // dispose all resources and close the application

            // if the source was the "open" option
        else if (e.getSource() == this.openFile) {
            JFileChooser open = new JFileChooser(); // open up a file chooser (a dialog for the user to browse files to open)
            int option = open.showOpenDialog(this); // get the option that the user selected (approve or cancel)
            // NOTE: because we are OPENing a file, we call showOpenDialog~
            // if the user clicked OK, we have "APPROVE_OPTION"
            // so we want to open the file
            if (option == JFileChooser.APPROVE_OPTION) {
                this.textArea.setText(""); // clear the TextArea before applying the file contents
                try {
                    // create a scanner to read the file (getSelectedFile().getPath() will get the path to the file)
                    Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
                    while (scan.hasNext()) // while there's still something to read
                        this.textArea.append(scan.nextLine() + "\n"); // append the line to the TextArea
                } catch (Exception ex) { // catch any exceptions, and...
                    // ...write to the debug console
                    System.out.println(ex.getMessage());
                }
            }
        }

        // and lastly, if the source of the event was the "save" option
        else if (e.getSource() == this.saveFile) {
            JFileChooser save = new JFileChooser(); // again, open a file chooser
            int option = save.showSaveDialog(this); // similar to the open file, only this time we call
            // showSaveDialog instead of showOpenDialog
            // if the user clicked OK (and not cancel)
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    // using buffer reader to write to a file
                    BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
                    out.write(this.textArea.getText()); // write the contents of the TextArea to the file
                    out.close(); // close the file stream
                } catch (Exception ex) { // again, catch any exceptions and...
                    // ...write to the debug expection
                    System.out.println(ex.getMessage());
                }
            }
        }



        else  if (e.getSource()==this.aboutUS){
            JOptionPane.showMessageDialog(null,"This app is developed by ADEEYO ADEWALE TEMITAYO" +
                    "as a  project in Hiit Plc " );
        }


    }

    //this is the main class .

    public static void main(String args[]) {
        Notepad app = new Notepad();
      //stting app to visible
        app.setVisible(true);

    }
}
