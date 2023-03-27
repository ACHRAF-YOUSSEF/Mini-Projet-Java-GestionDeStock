package presentation;

import dao.ILogin;
import dao.LoginImpl;
import metier.entity.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Login_0 extends JFrame {
    // components:
    // JLabels:
    private final JLabel loginLabel = new JLabel("Login", JLabel.CENTER);
    private final JLabel nomLabel = new JLabel("nom:", JLabel.LEFT);
    private final JLabel motDePassLabel = new JLabel("mot de pass:", JLabel.LEFT);
    // JTextFields:
    private final JTextField nomTextField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    // JComboBoxs:
    JComboBox<String> comboBox = new JComboBox<>(new String[] {
            "Admin",
            "Cassiere"
    });
    // JButtons:
    private final JButton loginButton = new JButton("Login");
    // JPanels:
    // panelA = [loginLabel + panelB + panelD]:
    private final JPanel panelA = new JPanel(new BorderLayout(10, 10));
    // panelB = [nomLabel + nomTextField + motDePassLabel + passwordField + comboBox]:
    private final JPanel panelB = new JPanel(new GridLayout(5, 1, 10, 10));
    // panelC = [panelA]
    private final JPanel panelC = new JPanel(new GridLayout(1, 1, 10, 10));
    // panelD = [loginButton]
    private final JPanel panelD = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    // ILogin
    ILogin loginSystem = new LoginImpl();

    public Login_0() {
        // titre:
        super("Login");

        // changing the Background of the JFrame
        panelC.setBackground(new Color(80, 80, 80));

        // toolkit --> getting screen size (xSize, ySize) and tasBarSize:
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = (int) tk.getScreenSize().getWidth();
        int ySize = (int) tk.getScreenSize().getHeight();
        Insets screenMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int taskBarSize = screenMax.bottom;

        // setting  the fonts
        nomLabel.setFont(new Font(null, Font.PLAIN, 50));
        nomTextField.setFont(new Font(null, Font.PLAIN, 50));
        motDePassLabel.setFont(new Font(null, Font.PLAIN, 50));
        passwordField.setFont(new Font(null, Font.PLAIN, 50));
        loginLabel.setFont(new Font(null, Font.PLAIN, 50));
        loginButton.setFont(new Font(null, Font.PLAIN, 50));
        comboBox.setFont(new Font(null, Font.PLAIN, 50));

        // adding components to the panelB:
        panelB.add(nomLabel);
        panelB.add(nomTextField);
        panelB.add(motDePassLabel);
        panelB.add(passwordField);
        panelB.add(comboBox);

        // adding the ActionListener to the JButton
        loginButton.addActionListener(e -> {
            if (nomLabel.getText().equals("") || String.valueOf(passwordField.getPassword()).equals("")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Erreur de saisie"
                );
            } else {
                Utilisateur utilisateur = new Utilisateur(
                        nomTextField.getText(),
                        String.valueOf(passwordField.getPassword()),
                        (Objects.equals(comboBox.getSelectedItem(), "Admin"))? 1 : 0
                );

                if (loginSystem.validate(utilisateur)) {
                    if (utilisateur.getAdmin() == 1) {
                        new Admin();
                    } else {
                        new Cassiere();
                    }

                    dispose();
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Utilisateur non valide"
                    );
                }
            }
        });

        // setting focusable for the loginButton JButton + making the borders and the background blue
        loginButton.setFocusable(false);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        loginButton.setBackground(Color.BLUE);

        // adding components to the panelD:
        panelD.add(loginButton);

        // adding components to the panelA:
        panelA.add(loginLabel, BorderLayout.NORTH);
        panelA.add(panelB);
        panelA.add(panelD, BorderLayout.SOUTH);

        // adding border to the panelA:
        panelA.setBorder(
                BorderFactory.createLineBorder(
                        Color.BLUE, 5
                )
        );

        // adding border to the panelB:
        panelB.setBorder(
                BorderFactory.createEmptyBorder(
                        100,
                        100,
                        100,
                        100
                )
        );

        // adding border to the panelC:
        panelC.setBorder(
                BorderFactory.createEmptyBorder(
                       10,
                        360,
                        10,
                        360
                )
        );

        // adding components to the panelC JPanel
        panelC.add(panelA);

        // adding components to the JFrame:
        this.add(panelC);

        // setup JFrame:
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(xSize, ySize - taskBarSize);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Login_0();
    }
}
