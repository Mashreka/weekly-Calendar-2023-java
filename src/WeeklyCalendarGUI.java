import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;


public class WeeklyCalendarGUI extends JFrame {
    private CalendarDay[] week; //CalenderDay class as a variable type
    private JTextArea[] activityTextAreas;

    public WeeklyCalendarGUI() {
        week = new CalendarDay[7]; //Initializing the variable 
        LocalDate today = LocalDate.now(); // Find the today's date

        // Find the start of the week (Monday)
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);

        for (int i = 0; i < 7; i++) {
            week[i] = new CalendarDay(startOfWeek.plusDays(i));
        }

        initializeGUI();
    }

    private void initializeGUI() {
        setTitle(" Weekly Calendar ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 7, 4, 1));
        LocalDate today = LocalDate.now();

        activityTextAreas = new JTextArea[7];

        for (int i = 0; i < 7; i++) {
            CalendarDay day = week[i];

            JPanel datePanel = new JPanel();
            datePanel.setLayout(new BorderLayout());

            JPanel dateMonthPanel = new JPanel();
            dateMonthPanel.setLayout(new BorderLayout());
            dateMonthPanel.setBackground(new Color(164, 237, 180));

            JLabel dateLabel = new JLabel(day.getDate().getDayOfWeek() + "");
            dateLabel.setPreferredSize(new Dimension(30, 20));
            dateLabel.setFont(new Font("Arial", Font.BOLD, 13));
            dateLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center  horizontally

            JLabel monthLabel = new JLabel(day.getDate().getDayOfMonth() +
                    "\n" + day.getDate().getMonth().name());
            monthLabel.setPreferredSize(new Dimension(30, 20));
            monthLabel.setFont(new Font("Arial", Font.PLAIN, 13));
            monthLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center  horizontally

            dateMonthPanel.add(dateLabel, BorderLayout.NORTH);
            dateMonthPanel.add(monthLabel, BorderLayout.SOUTH);

            if (day.getDate().isEqual(today)) {
                dateMonthPanel.setBackground(new Color(225, 166, 227));
                dateMonthPanel.setOpaque(true);
            }


            JTextArea activityTextArea = new JTextArea(day.getActivity());
            activityTextAreas[i] = activityTextArea;
            activityTextArea.setEditable(false);
            JScrollPane pane = new JScrollPane(activityTextArea);
            pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            activityTextArea.setBackground(Color.WHITE);

            if (day.getDate().isEqual(today)) {
                activityTextArea.setBackground(new Color(225, 166, 227));
                activityTextArea.setOpaque(true);

            }

            JButton addButton = new JButton("Add Activity");
            addButton.addActionListener(new AddActivityListener(i));
            addButton.setPreferredSize(new Dimension(30, 50));
            addButton.setFont(new Font("Arial", Font.BOLD, 15));
            addButton.setBackground(new Color(27, 103, 179));
            addButton.setForeground(Color.WHITE);
            addButton.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            addButton.setOpaque(true);

            datePanel.add(dateMonthPanel, BorderLayout.NORTH);
            datePanel.add(pane, BorderLayout.CENTER);
            datePanel.add(addButton, BorderLayout.SOUTH);
            add(datePanel);
        }
        setSize(1000, 500);
        //setResizable(false);
        setLocationRelativeTo(null);
        setBackground(Color.green);
        setVisible(true);
    }

    private class AddActivityListener implements ActionListener {
        private int dayIndex;
        public AddActivityListener(int dayIndex) {
            this.dayIndex = dayIndex;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String activity = JOptionPane.showInputDialog("Enter activity for " + week[dayIndex].getDate().getDayOfWeek());
            if (activity != null && !activity.isEmpty()) {
                week[dayIndex].setActivity(activity);
                activityTextAreas[dayIndex].append(activity + "\n");
            }
        }

    }
}

