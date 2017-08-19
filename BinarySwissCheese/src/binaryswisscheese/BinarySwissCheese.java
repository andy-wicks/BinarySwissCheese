/*
 * This program is made available under the Creative Commons License.
 * No liability to hardware, software or data will be accepted by the author.
 * The program may be changed and distributed freely, as long as the name of
 * the original author remains in the code.
 */
package binaryswisscheese;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

/**
 *
 * @author Andy Wicks
 * @dateStarted 16-Aug-2017
 * @lastUpdated 16-Aug-2017
 * @purpose To calculate a set of numbers for the Binary Swiss Cheese method of
 * selecting which items to trial.
 *
 */
public class BinarySwissCheese extends JFrame implements ActionListener, KeyListener {

    CommonCode cc = new CommonCode();
    JPanel pnl = new JPanel(new BorderLayout());
    Font fnt = new Font("Courier New", Font.PLAIN, 36);
    JEditorPane lft = new JEditorPane();
    JEditorPane cen = new JEditorPane();
    JEditorPane rht = new JEditorPane();
    JScrollPane left = new JScrollPane();
    JScrollPane cent = new JScrollPane();
    JScrollPane rght = new JScrollPane();

    // Place the global data structures here, e.g. :-
    ArrayList<Integer> dec = new ArrayList<>();
    ArrayList<String> bin = new ArrayList<>();

    public static void main(String[] args) {
        BinarySwissCheese prg = new BinarySwissCheese();
    }

    public BinarySwissCheese() {
        model();
        view();
        controller();
    }

    private void model() {
        // This is the logic of the program.

    }

    private void view() {
        JMenuBar menuBar;
        JMenu fyle;

        JToolBar toolBar = new JToolBar();
        Font fnt = new Font("Georgia", Font.PLAIN, 24);

        // Setting up the MenuBar
        menuBar = new JMenuBar();
        fyle = new JMenu("File");
        fyle.setToolTipText("File tasks");
        fyle.setFont(fnt);

        JMenuItem mnuItem = null;

        mnuItem = makeMenuItem("New", "New", "Create a new something or other", fnt);
        fyle.add(mnuItem);

        fyle.addSeparator();

        mnuItem = makeMenuItem("Close", "Close", "Close something or other", fnt);
        fyle.add(mnuItem);

        menuBar.add(fyle);

        mnuItem = makeMenuItem("Exit", "Exit", "Close this program", fnt);
        menuBar.add(mnuItem);

        setJMenuBar(menuBar);

        setLayout(new BorderLayout());

        // Use setSize and setLocationRelative for a specific 
        // size of window or setExtendedState to fill the screen.
        //
        //setSize(500, 500);
        //setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setTitle("Binary Swiss Cheese Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        left = new JScrollPane(lft, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        left.setPreferredSize(new Dimension(260, 0));
        add(left, BorderLayout.WEST);

        cent = new JScrollPane(cen, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(cent, BorderLayout.CENTER);

        rght = new JScrollPane(rht, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        rght.setPreferredSize(new Dimension(560, 0));
        add(rght, BorderLayout.EAST);

        JPanel bot = new JPanel();
        bot.setLayout(new FlowLayout());
        JLabel copy = new JLabel("<html><body>Copyright (c) Andy Wicks 2017</body></html>");
        copy.setFont(fnt);
        bot.add(copy);
        add(bot, BorderLayout.SOUTH);

        setVisible(true);

        // Setting up the ButtonBar
        JButton button = null;
        button = makeNavigationButton("Create", "New",
                "A new something-or-other",
                "New");
        toolBar.add(button);
        button = makeNavigationButton("closed door", "Close",
                "Close this thingy",
                "Close");
        toolBar.add(button);
        toolBar.addSeparator();
        button = makeNavigationButton("exit", "Exit",
                "Exit from this program",
                "Exit");
        toolBar.add(button);

        add(toolBar, BorderLayout.NORTH);
    }

    private void controller() {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ("New".equals(ae.getActionCommand())) {
            String disp = "";
            dec.clear();
            bin.clear();
            int itms = Integer.parseInt(JOptionPane.showInputDialog(null, "How many items are being swapped in and out?"));
            int tsts = Integer.parseInt(JOptionPane.showInputDialog(null, "How many tests are to be run?"));
            int max = 1;

            int st = itms;
            while (st > 0) {
                max = max * 2;
                st--;
            }

            for (int i = 0; i < tsts; i++) {
                int rn = ThreadLocalRandom.current().nextInt(1, max - 1);  // This generates a random number between 1 and tsts.
                String si = Integer.toString(i + 1);
                String srn = Integer.toString(rn);
                String bn = Integer.toBinaryString(rn);

                while (bn.length() < itms) {
                    bn = "0" + bn;
                }
                bin.add(bn);

                disp += si + " " + srn + " " + bn + "\n";
            }

            cen.setFont(fnt);
            cen.setAlignmentY(LEFT_ALIGNMENT);
            cen.setText(disp);
            
            int ln = bin.get(0).length();
            int[] no = new int[ln];

            for (int i = 0; i < no.length; i++) {
                no[i] = 0;
            }

            for (String str : bin) {
                for (int i = 0; i < ln; i++) {
                    if ("1".equals(str.substring(i, i+1))) {
                        no[i]++;
                    }
                }
            }

            disp = "Number of times each item is used:\n";
            for (int i = 0; i < ln; i++) {
                disp += (i + 1) + " ~ " + no[i] + "\n";
            }

            rht.setFont(fnt);
            rht.setAlignmentY(LEFT_ALIGNMENT);
            rht.setText(disp);
        }

        if ("Close".equals(ae.getActionCommand())) {
            JOptionPane.showMessageDialog(this, "Close clicked");
        }

        if ("Exit".equals(ae.getActionCommand())) {
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        System.out.println("keyTyped has not been coded yet.");
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        System.out.println("keyPressed has not been coded yet.");
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        System.out.println("keyReleased has not been coded yet.");
    }

    protected JMenuItem makeMenuItem(String txt,
            String actionCommand,
            String toolTipText,
            Font fnt) {

        JMenuItem mnuItem = new JMenuItem();
        mnuItem.setFont(fnt);
        mnuItem.setText(txt);
        mnuItem.setToolTipText(toolTipText);
        mnuItem.setActionCommand(actionCommand);
        mnuItem.addActionListener(this);

        return mnuItem;
    }

    protected JButton makeNavigationButton(String imageName,
            String actionCommand,
            String toolTipText,
            String altText) {

        //Look for the image.
        String imgLocation = cc.appDir + "\\icons\\"
                + imageName
                + ".png";

        //Create and initialize the button.
        JButton button = new JButton();
        button.setToolTipText(toolTipText);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);

        File fyle = new File(imgLocation);
        if (fyle.exists() && !fyle.isDirectory()) {
            // image found
            Icon img;
            img = new ImageIcon(imgLocation);
            button.setIcon(img);
        } else {
            // image NOT found
            button.setText(altText);
            System.err.println("Resource not found: " + imgLocation);
        }

        return button;
    }

}
