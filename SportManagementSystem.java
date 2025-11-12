import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SportManagementSystem extends JFrame {

    private JButton addPlayerBtn, createTeamBtn, registerEventBtn, enterScoreBtn, declareResultBtn, viewDetailsBtn, exitBtn;
    private JTextArea displayArea;
    private final Color primary = new Color(255, 30, 49);
    private final Color background = new Color(139, 139, 140);

    public SportManagementSystem() {

        setTitle("Sports Management System");
        setSize(850, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(background);

        JLabel header = new JLabel(" Sports Management System", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 26));
        header.setForeground(primary);
        header.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(header, BorderLayout.NORTH);


        JPanel menuPanel = new JPanel(new GridLayout(7, 1, 10, 10));
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addPlayerBtn = createStyledButton(" Add Player");
        createTeamBtn = createStyledButton(" Create Team");
        registerEventBtn = createStyledButton(" Register Event");
        enterScoreBtn = createStyledButton(" Enter Scores");
        declareResultBtn = createStyledButton(" Declare Result");
        viewDetailsBtn = createStyledButton(" View Details");
        exitBtn = createStyledButton(" Exit");

        menuPanel.add(addPlayerBtn);
        menuPanel.add(createTeamBtn);
        menuPanel.add(registerEventBtn);
        menuPanel.add(enterScoreBtn);
        menuPanel.add(declareResultBtn);
        menuPanel.add(viewDetailsBtn);
        menuPanel.add(exitBtn);
        add(menuPanel, BorderLayout.WEST);

        // ===== DISPLAY AREA =====
        displayArea = new JTextArea();
        displayArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        displayArea.setBackground(Color.WHITE);
        displayArea.setForeground(Color.DARK_GRAY);
        displayArea.setBorder(BorderFactory.createLineBorder(primary, 2));
        displayArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        JLabel footer = new JLabel("© 2025 NIET College Project | Made by Your Name", SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        footer.setForeground(Color.GRAY);
        add(footer, BorderLayout.SOUTH);

        // ===== ACTION LISTENERS =====
        addPlayerBtn.addActionListener(e -> addPlayer());
        createTeamBtn.addActionListener(e -> createTeam());
        registerEventBtn.addActionListener(e -> registerEvent());
        enterScoreBtn.addActionListener(e -> enterScores());
        declareResultBtn.addActionListener(e -> declareResult());
        viewDetailsBtn.addActionListener(e -> viewDetails());
        exitBtn.addActionListener(e -> System.exit(0));

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(primary);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btn.setBackground(new Color(89, 2, 12));
            }

            public void mouseExited(MouseEvent evt) {
                btn.setBackground(primary);
            }
        });
        return btn;
    }


    private void addPlayer() {
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField sportField = new JTextField();

        Object[] fields = {"Name:", nameField, "Age:", ageField, "Sport:", sportField};

        int option = JOptionPane.showConfirmDialog(this, fields, "Add Player", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String age = ageField.getText().trim();
            String sport = sportField.getText().trim();

            if (name.isEmpty() || age.isEmpty() || sport.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try (FileWriter fw = new FileWriter("players.txt", true)) {
                fw.write(name + "," + age + "," + sport + "\n");
                displayArea.setText(" Player added successfully!\n");
            } catch (IOException ex) {
                displayArea.setText(" Error writing to file: " + ex.getMessage());
            }
        }
    }


    private void createTeam() {
        JTextField teamNameField = new JTextField();
        JComboBox<String> sportCombo = new JComboBox<>(new String[]{"Cricket", "Basketball", "Football", "Volleyball"});
        JTextField membersField = new JTextField();

        Object[] fields = {"Team Name:", teamNameField, "Sport:", sportCombo, "Members (comma separated):", membersField};

        int option = JOptionPane.showConfirmDialog(this, fields, "Create Team", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String teamName = teamNameField.getText().trim();
            String sport = (String) sportCombo.getSelectedItem();
            String members = membersField.getText().trim();

            if (teamName.isEmpty() || members.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try (FileWriter fw = new FileWriter("teams.txt", true)) {
                fw.write(sport + "," + teamName + "," + members + "\n");
                displayArea.setText(sport + " team created successfully!\n");
            } catch (IOException ex) {
                displayArea.setText(" Error writing to file: " + ex.getMessage());
            }
        }
    }


    private void registerEvent() {
        JTextField eventNameField = new JTextField();
        JTextField teamField = new JTextField();

        Object[] fields = {"Event Name:", eventNameField, "Teams (comma separated):", teamField};

        int option = JOptionPane.showConfirmDialog(this, fields, "Register Event", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String eventName = eventNameField.getText().trim();
            String teams = teamField.getText().trim();

            if (eventName.isEmpty() || teams.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try (FileWriter fw = new FileWriter("events.txt", true)) {
                fw.write(eventName + "," + teams + "\n");
                displayArea.setText(" Event registered successfully!\n");
            } catch (IOException ex) {
                displayArea.setText(" Error writing to file: " + ex.getMessage());
            }
        }
    }


    private void enterScores() {
        JTextField eventField = new JTextField();
        JTextField teamField = new JTextField();
        JTextField scoreField = new JTextField();

        Object[] fields = {
                "Event Name:", eventField,
                "Team Name:", teamField,
                "Score:", scoreField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Enter Scores", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String event = eventField.getText().trim();
            String team = teamField.getText().trim();
            String score = scoreField.getText().trim();

            if (event.isEmpty() || team.isEmpty() || score.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try (FileWriter fw = new FileWriter("scores.txt", true)) {
                fw.write(event + "," + team + "," + score + "\n");
                displayArea.setText("Score recorded successfully!\n");
            } catch (IOException ex) {
                displayArea.setText(" Error writing to file: " + ex.getMessage());
            }
        }
    }


    private void declareResult() {
        JTextField eventField = new JTextField();
        JTextField winningTeamField = new JTextField();

        Object[] fields = {
                "Event Name:", eventField,
                "Winning Team:", winningTeamField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Declare Result", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String event = eventField.getText().trim();
            String winner = winningTeamField.getText().trim();

            if (event.isEmpty() || winner.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try (FileWriter fw = new FileWriter("results.txt", true)) {
                fw.write("Winnning Team of "+ event + "," + winner + "\n");
                displayArea.setText(" Result declared successfully!\n");
            } catch (IOException ex) {
                displayArea.setText(" Error writing to file: " + ex.getMessage());
            }
        }
    }


    private void viewDetails() {
        StringBuilder sb = new StringBuilder();

        sb.append("=== PLAYERS ===\n").append(readFile("players.txt"));
        sb.append("\n=== TEAMS BY SPORT ===\n").append(groupTeamsBySport());
        sb.append("\n=== EVENTS ===\n").append(readFile("events.txt"));
        sb.append("\n=== SCORES ===\n").append(readFile("scores.txt"));
        sb.append("\n=== RESULTS ===\n").append(readFile("results.txt"));

        displayArea.setText(sb.toString());
    }

    private String readFile(String filename) {
        StringBuilder sb = new StringBuilder();
        File file = new File(filename);
        if (!file.exists()) return "No data available.\n";
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                sb.append("• ").append(sc.nextLine()).append("\n");
            }
        } catch (Exception e) {
            sb.append("Error reading file.\n");
        }
        return sb.toString();
    }

    private String groupTeamsBySport() {
        File file = new File("teams.txt");
        if (!file.exists()) return "No teams created yet.\n";

        Map<String, java.util.List<String>> sportTeams = new LinkedHashMap<>();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",", 3);
                if (parts.length < 3) continue;
                String sport = parts[0];
                String team = parts[1];
                String members = parts[2];
                sportTeams.computeIfAbsent(sport, k -> new ArrayList<>()).add(team + " (" + members + ")");
            }
        } catch (Exception e) {
            return "Error reading teams.\n";
        }

        StringBuilder sb = new StringBuilder();
        for (String sport : sportTeams.keySet()) {
            sb.append(" ").append(sport).append(":\n");
            for (String team : sportTeams.get(sport)) {
                sb.append("   • ").append(team).append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SportManagementSystem::new);
    }
}

