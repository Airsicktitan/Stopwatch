import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopwatch extends JFrame implements ActionListener {
    JPanel p = new JPanel(new GridBagLayout()); // Use GridBagLayout for the main panel
    JLabel l = new JLabel("00:00:00");
    JButton stop = new JButton("STOP");
    JButton start = new JButton("RESET");
    private int counter = 0;
    int delay = 1000;
    ActionListener taskPerformer = evt -> updateLabel();

    Timer timer = new Timer(delay, taskPerformer);

    public Stopwatch() {
        setTitle("Stopwatch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        p.setPreferredSize(new Dimension(250, 250));
        stop.setFocusable(false);
        start.setFocusable(false);

        l.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        l.setPreferredSize(new Dimension(150, 50));
        l.setFont(new Font("Calibri", Font.BOLD, 25));
        l.setVerticalAlignment(SwingConstants.CENTER);
        l.setHorizontalAlignment(SwingConstants.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 2; // Span two columns for the label to be centered above the buttons
        gbc.gridx = 0; // Start position in the first column
        gbc.gridy = 0; // Start in the first row
        gbc.insets = new Insets(10, 0, 5, 0); // Top padding
        gbc.anchor = GridBagConstraints.CENTER;
        p.add(l, gbc);

        // Resetting grid width for buttons to be side by side
        gbc.gridwidth = 1; // Each button takes one column
        gbc.gridy = 1; // Position the buttons in the second row
        gbc.gridx = 0; // Button STOP at column 0
        gbc.insets = new Insets(0, 0, 10, 5); // Padding between buttons and around them
        gbc.anchor = GridBagConstraints.LINE_END; // Align to the right of the cell
        p.add(stop, gbc);

        gbc.gridx = 1; // Button RESET at column 1
        gbc.anchor = GridBagConstraints.LINE_START; // Align to the left of the cell
        gbc.insets = new Insets(0, 5, 10, 0); // Padding between buttons and around them
        p.add(start, gbc);
        start.addActionListener(this);
        stop.addActionListener(this);

        timer.start();

        this.setPreferredSize(new Dimension(300, 200));
        this.add(p);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null); // Center on screen
        this.setVisible(true);
    }

    private void updateLabel() {
        int minutes = (counter % 3600) / 60;
        int seconds = counter % 60;
        int hours = counter / 3600;
        l.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        counter++;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == stop) {
            if(timer.isRunning()) {
                stop.setText("START");
                timer.stop();
            } else {
                stop.setText("STOP");
                timer.start();
            }
        } else {
            counter = 0;
            updateLabel();
        }
    }
}
