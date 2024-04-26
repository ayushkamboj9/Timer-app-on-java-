import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimerApp extends JFrame {
    private JLabel label1, label2;
    private JTextField entry_hours, entry_minutes, entry_seconds;
    private JButton start_button, pause_button, reset_button;
    private Timer timer;
    private int totalSeconds;
    private boolean paused = false;
    private boolean exit_flag = false;

    public TimerApp() {
        setTitle("Timer App");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        label1 = new JLabel("00:00:00");
        label1.setFont(new Font("Helvetica", Font.PLAIN, 40));
        label1.setForeground(Color.decode("#934439"));
        label1.setBounds(50, 20, 250, 50);
        add(label1);

        label2 = new JLabel("Enter time in HH:MM:SS format");
        label2.setFont(new Font("Helvetica", Font.PLAIN, 12));
        label2.setBounds(50, 110, 200, 20);
        add(label2);

        entry_hours = new JTextField();
        entry_hours.setBounds(50, 140, 30, 20);
        add(entry_hours);

        JLabel label_hours = new JLabel("Hours");
        label_hours.setBounds(85, 140, 40, 20);
        add(label_hours);

        entry_minutes = new JTextField();
        entry_minutes.setBounds(125, 140, 30, 20);
        add(entry_minutes);

        JLabel label_minutes = new JLabel("Minutes");
        label_minutes.setBounds(160, 140, 50, 20);
        add(label_minutes);

        entry_seconds = new JTextField();
        entry_seconds.setBounds(200, 140, 30, 20);
        add(entry_seconds);

        JLabel label_seconds = new JLabel("Seconds");
        label_seconds.setBounds(235, 140, 50, 20);
        add(label_seconds);

        start_button = new JButton("Start");
        start_button.setBounds(50, 180, 100, 30);
        start_button.setBackground(Color.decode("#F19C79"));
        start_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startTimer();
            }
        });
        add(start_button);

        pause_button = new JButton("Pause");
        pause_button.setBounds(160, 180, 100, 30);
        pause_button.setBackground(Color.decode("#D0B8AC"));
        pause_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pauseTimer();
            }
        });
        add(pause_button);

        reset_button = new JButton("Reset");
        reset_button.setBounds(270, 180, 100, 30);
        reset_button.setBackground(Color.decode("#F3D8C7"));
        reset_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetTimer();
            }
        });
        add(reset_button);

        setVisible(true);
    }

    private void startTimer() {
        int h = Integer.parseInt(entry_hours.getText());
        int m = Integer.parseInt(entry_minutes.getText());
        int s = Integer.parseInt(entry_seconds.getText());
        totalSeconds = h * 3600 + m * 60 + s;

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (totalSeconds > 0 && !paused) {
                    int hours = totalSeconds / 3600;
                    int minutes = (totalSeconds % 3600) / 60;
                    int seconds = totalSeconds % 60;
                    label1.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
                    totalSeconds--;
                } else if (totalSeconds <= 0) {
                    label1.setText("Time is up");
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    private void pauseTimer() {
        paused = !paused;
    }

    private void resetTimer() {
        timer.stop();
        label1.setText("00:00:00");
        paused = false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TimerApp();
            }
        });
    }
}
