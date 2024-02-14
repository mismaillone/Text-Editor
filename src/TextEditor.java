import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.Buffer;

public class TextEditor implements ActionListener {
//   declaring properties of Text Editor
    JFrame frame;
    JMenuBar menuBar;
    JMenu  file, edit;
//    File menu items
 JMenuItem newFile, openFile, saveFile;
// edit menu items
 JMenuItem cut, copy, paste, selectAll,close;

 JTextArea textarea;
    TextEditor() {
//        initialize a frame

        frame = new JFrame();
   // set icon in Applications
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\acer\\IdeaProjects\\TextEditor\\src\\icon\\code.png");
        frame.setIconImage(icon);

//        initialise a new bar
        menuBar = new JMenuBar();

        // initialise a text area

        textarea = new JTextArea();

//        initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");


//        initialize file menu items
    newFile = new JMenuItem("New File");
    openFile = new JMenuItem("Open File");
    saveFile = new JMenuItem("Save File");
    // add action Listener to file
         newFile.addActionListener(this);
         openFile.addActionListener(this);
         saveFile.addActionListener(this);
//    add menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // Initialize edit menu items

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // Adding action listener to edit menu items
            cut.addActionListener(this);
            copy.addActionListener(this);
            paste.addActionListener(this);
            selectAll.addActionListener(this);
            close.addActionListener(this);

            // adding edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

  // add menus to menu Bar
        menuBar.add(file);
        menuBar.add(edit);

//   set menu bar to frame
        frame.setJMenuBar(menuBar);

        // Create content Pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0 , 0 ));
        // add text area to the panel

        panel.add(textarea, BorderLayout.CENTER);
        // Create scroll Pane
        JScrollPane scrollPane = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // add scroll Pane to Panel
        panel.add(scrollPane);

        // add panel to frame

        frame.add(panel);

        // set Dimensions of Frame
        frame.setBounds(100,100, 600,600); // size of window and position where it appears
       frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == cut ) {
            // perform cut operation
            textarea.cut();
        }
        if(actionEvent.getSource() == copy) {
            // perform copy Operation
            textarea.copy();
        }
        if(actionEvent.getSource() == paste) {
            // perform paste Operation
            textarea.paste();
        }
        if(actionEvent.getSource() == selectAll) {
            // perform selectAll Operation
            textarea.selectAll();
        }
        if(actionEvent.getSource() == close) {
            // perform close Operation
            System.exit(0);
        }

        // file menu

        if(actionEvent.getSource() == openFile) {
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            // if we have clicked on Open Button
            if(chooseOption == JFileChooser.APPROVE_OPTION) {
                // Getting the selected File
                File file = fileChooser.getSelectedFile();
                // Get the path of selected file
                String filePath = file.getPath();
                try{

                    // Initilaze the file reader

                    FileReader fileReader = new FileReader(filePath);
                // Initialize Buffer reader

                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermeditae = "", output = "";
                    // Read content of the file Line by Line
                    while ((intermeditae = bufferedReader.readLine())!=null) {
                        output+= intermeditae+"\n";
                    }
                    textarea.setText(output);

                }catch(IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }

        //
        if(actionEvent.getSource() == saveFile) {
            // initialize file picker

            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);

            // check if we clicked save button
            if(chooseOption==JFileChooser.APPROVE_OPTION) {
                // create a new file with chosen directory path and file name
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
            try {
                // initialize file writer
                FileWriter fileWriter = new FileWriter(file);
                 // initialize buffer write
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                // write contents of text area to file
                textarea.write(bufferedWriter);
                bufferedWriter.close();
            }
            catch (IOException ioException) {
                ioException.printStackTrace();
            }

            }
        }

        // new windows item
        if(actionEvent.getSource()== newFile) {
            TextEditor newTextExitor = new TextEditor();
        }
    }
    public static void main(String[] args) {

        TextEditor texteditor = new TextEditor();

    }
}