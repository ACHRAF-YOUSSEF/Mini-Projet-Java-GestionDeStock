package presentation;

import dao.ILogin;
import dao.LoginImpl;
import metier.entity.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Login extends JFrame {
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
            "Caissiere"
    });
    // JButtons:
    private final JButton loginButton = new JButton("Login");
    // JPanels:
    // panelA = [loginLabel + panelB + loginButton]:
    private final JPanel panelA = new JPanel(null);
    // panelB = [nomLabel + nomTextField + motDePassLabel + passwordField + comboBox]:
    private final JPanel panelB = new JPanel(new GridLayout(5, 1, 10, 10));
    // ILogin
    ILogin loginSystem = new LoginImpl();

    public Login() {
        // titre:
        super("Login");

        // setting the Layout Manager for the JFrame
        this.setLayout(null);

        // changing the Background of the JFrame
        this.getContentPane().setBackground(new Color(80, 80, 80));

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

        // setting the bounds for loginLabel, panelB and loginButton:
        loginLabel.setBounds((xSize / 2) - 570, 0, 400, 80);
        panelB.setBounds((xSize / 2) - 560, 100, 400, (ySize - taskBarSize) - 330);
        loginButton.setBounds((xSize / 2) - 520, (ySize - taskBarSize) - 200, 300, 80);

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
                        new Caissiere(utilisateur.getNom());
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
        /*showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            }
            else {
                passwordField.setEchoChar('*');
            }
        });*/

        // setting focusable for the loginButton JButton + making the borders and the background blue
        loginButton.setFocusable(false);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        loginButton.setBackground(Color.BLUE);

        // adding components to the panelA:
        panelA.add(loginLabel);
        panelA.add(panelB);
        panelA.add(loginButton);

        // adding border to the panelA:
        panelA.setBorder(
                BorderFactory.createLineBorder(
                        Color.BLUE, 5
                )
        );

        // setting the bounds of the panelA:
        panelA.setBounds((xSize / 2) - 400, 10, 800, (ySize - taskBarSize) - 50);

        // adding components to the JFrame:
        this.add(panelA);

        // setup JFrame:
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(xSize, ySize - taskBarSize);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}
