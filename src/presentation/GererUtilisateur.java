package presentation;

import dao.GestionImpl;
import dao.IGestion;
import metier.entity.Utilisateur;
import presentation.tableModeles.TableModeleUtilisateur;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;

public class GererUtilisateur extends JFrame {
    // components
    // DefaultTableCellRenderer
    private final DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    // JPanel
    private final JPanel tableJPanel = new JPanel(new GridLayout(1, 1));
    // JLabels
    private final JLabel titre = new JLabel("Gerer Utilisateur");
    private final JLabel id = new JLabel("ID");
    private final JLabel userType = new JLabel("Type De L'utilisateur");
    private final JLabel nom = new JLabel("Nom");
    private final JLabel mot_de_pass = new JLabel("Mot De Pass");
    private final JLabel confirmer_mot_de_pass = new JLabel("Confirmer Le Mot De Pass");
    // JTextFields
    private final JTextField rechercherTextField = new JTextField();
    private final JTextField idTextField = new JTextField();
    private final JTextField nomTextField = new JTextField();
    // JPasswordFields
    private final JPasswordField passwordField = new JPasswordField();
    private final JPasswordField confirmPasswordField = new JPasswordField();
    // JComboBox
    private final JComboBox<String> comboBox = new JComboBox<>(new String[] {
       "Admin",
       "Caissiere"
    });
    // ImageIcons
    private ImageIcon backIcon = new ImageIcon("src\\images\\back.png");
    private ImageIcon findIcon = new ImageIcon("src\\images\\find.png");
    // JButtons
    private final JButton retour = new JButton();
    private final JButton rechercher = new JButton();
    private final JButton ajouter = new JButton("créer un compte");
    private final JButton update = new JButton("mis à jour un compte");
    private final JButton supprimer = new JButton("supprimer un compte");
    private final JButton annuler = new JButton("annuler");
    // IGestion
    private final IGestion gestion = GestionImpl.getGestion();
    // TableModeleUtilisateur
    private final TableModeleUtilisateur me = new TableModeleUtilisateur();
    // JTable
    private final JTable table = new JTable(me);
    // JScrollPane
    private final JScrollPane jsp = new JScrollPane(table);

    public GererUtilisateur() {
        // titre:
        super("GererUtilisateur");

        // setting the Layout Manager for the JFrame:
        this.setLayout(null);

        // changing the Background of the JFrame:
        this.getContentPane().setBackground(Color.WHITE);

        // toolkit --> getting screen size (xSize, ySize) and tasBarSize:
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = (int) tk.getScreenSize().getWidth();
        int ySize = (int) tk.getScreenSize().getHeight();
        Insets screenMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int taskBarSize = screenMax.bottom;

        // resizing the icons:
        backIcon = Utils.resizeImageIcon(backIcon, 100, 80);
        findIcon = Utils.resizeImageIcon(findIcon, 40, 40);

        // adding the ImageIcons to the JButtons:
        retour.setIcon(backIcon);
        rechercher.setIcon(findIcon);

        // setting focusable for the JButtons:
        retour.setFocusable(false);
        rechercher.setFocusable(false);
        annuler.setFocusable(false);
        ajouter.setFocusable(false);
        supprimer.setFocusable(false);
        update.setFocusable(false);

        // making the borders and the background transparent for the retour + rechercher JButtons:
        // retour JButton:
        retour.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        retour.setBackground(new Color(0,0, 0, Transparency.TRANSLUCENT));

        // rechercher JButton:
        rechercher.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        rechercher.setBackground(new Color(0,0, 0, Transparency.TRANSLUCENT));

        // setting the fonts + colors:
        //fonts:
        titre.setFont(new Font(null, Font.PLAIN, 50));
        rechercherTextField.setFont(new Font(null, Font.PLAIN, 15));
        annuler.setFont(new Font(null, Font.PLAIN, 20));
        ajouter.setFont(new Font(null, Font.PLAIN, 20));
        supprimer.setFont(new Font(null, Font.PLAIN, 20));
        update.setFont(new Font(null, Font.PLAIN, 20));
        id.setFont(new Font(null, Font.PLAIN, 20));
        nom.setFont(new Font(null, Font.PLAIN, 20));
        mot_de_pass.setFont(new Font(null, Font.PLAIN, 20));
        confirmer_mot_de_pass.setFont(new Font(null, Font.PLAIN, 20));
        idTextField.setFont(new Font(null, Font.PLAIN, 15));
        nomTextField.setFont(new Font(null, Font.PLAIN, 15));
        passwordField.setFont(new Font(null, Font.PLAIN, 15));
        confirmPasswordField.setFont(new Font(null, Font.PLAIN, 15));
        userType.setFont(new Font(null, Font.PLAIN, 20));
        comboBox.setFont(new Font(null, Font.PLAIN, 15));

        // colors (Foreground + Background):
        // Foreground:
        titre.setForeground(Color.BLACK);
        ajouter.setForeground(Color.WHITE);
        annuler.setForeground(Color.WHITE);
        update.setForeground(Color.WHITE);
        supprimer.setForeground(Color.WHITE);

        // Background:
        retour.setBackground(Color.WHITE);
        rechercher.setBackground(Color.WHITE);
        ajouter.setBackground(MyColors._AB00C8.getColor());
        annuler.setBackground(MyColors._006E00.getColor());
        update.setBackground(MyColors._0074B3.getColor());
        supprimer.setBackground(MyColors._D20000.getColor());

        // centering each table entry to center by changing the cell renderer of each one :
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // getting the list of Utilisateur and updating the TableModeleUtilisateur's data:
        me.chargerTable(gestion.getAllUtilisateur());

        // adding the mouseListener to the JTable:
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();

                    int row = target.getSelectedRow();

                    int id = (int) target.getValueAt(row, 0);

                    idTextField.setText(String.valueOf(id));

                    Utilisateur utilisateur = gestion.getUtilisateur(id);

                    nomTextField.setText(utilisateur.getNom());
                    comboBox.setSelectedIndex((utilisateur.getAdmin() == 1)? 0 : 1);
                }
            }
        });

        // adding the ActionListener to the JButtons:
        retour.addActionListener(e -> {
            new Admin();
            dispose();
        });
        rechercher.addActionListener(e -> {
            List<Utilisateur> list = gestion.getUtilisateurPMC(rechercherTextField.getText());

            me.chargerTable(list);
        });
        ajouter.addActionListener(e -> {
            if (
                nomTextField.getText().equals("")
                || String.valueOf(passwordField.getPassword()).equals("")
                || !String.valueOf(passwordField.getPassword()).equals(String.valueOf(confirmPasswordField.getPassword()))
            ) {
                if (!String.valueOf(passwordField.getPassword()).equals(String.valueOf(confirmPasswordField.getPassword()))) {
                    JOptionPane.showMessageDialog(
                            GererUtilisateur.this,
                            "le mot de pass et le confirmer le mot de pass doivent être le même!"
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            GererUtilisateur.this,
                            "erreur de saisie"
                    );
                }
            } else {
                try {
                    Utilisateur utilisateur = new Utilisateur(
                            nomTextField.getText(),
                            String.valueOf(passwordField.getPassword()),
                            (Objects.equals(comboBox.getSelectedItem(), "Admin"))? 1 : 0
                    );

                    if (
                            gestion.getAllUtilisateur()
                                    .stream()
                                    .filter(utilisateur::equals)
                                    .toList()
                                    .size() == 0
                    ) {
                        gestion.ajouterUtilisateur(utilisateur);

                        JOptionPane.showMessageDialog(
                                GererUtilisateur.this,
                                "utilisateur à été ajouter!"
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                                GererUtilisateur.this,
                                "Utilisateur deja existant!"
                        );
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            GererUtilisateur.this,
                            "erreur de saisie de l'id!"
                    );
                }

                me.chargerTable(gestion.getAllUtilisateur());
            }
        });
        update.addActionListener(e -> {
            if (
                idTextField.getText().equals("")
                || nomTextField.getText().equals("")
                || String.valueOf(passwordField.getPassword()).equals("")
                || !String.valueOf(passwordField.getPassword()).equals(String.valueOf(confirmPasswordField.getPassword()))
            ) {
                JOptionPane.showMessageDialog(
                        GererUtilisateur.this,
                        "erreur de saisie"
                );
            } else {
                try {
                    int id = Integer.parseInt(idTextField.getText());
                    Utilisateur utilisateur = gestion.getUtilisateur(id);

                    if (utilisateur == null) {
                        JOptionPane.showMessageDialog(
                                GererUtilisateur.this,
                                "impossible de faire le mis à jour ce compte!"
                        );
                    } else {
                        utilisateur.setNom(nomTextField.getText());
                        utilisateur.setMot_de_pass(String.valueOf(passwordField.getPassword()));
                        utilisateur.setAdmin((Objects.equals(comboBox.getSelectedItem(), "Admin"))? 1 : 0);

                        JOptionPane.showMessageDialog(
                                GererUtilisateur.this,
                                "utilisateur à été modifier!"
                        );

                        gestion.modifierUtilisateur(utilisateur);
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            GererUtilisateur.this,
                            "erreur de saisie de l'id!"
                    );
                }

                me.chargerTable(gestion.getAllUtilisateur());
            }
        });
        supprimer.addActionListener(e -> {
            if (idTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(
                        GererUtilisateur.this,
                        "erreur de saisie"
                );
            } else {
                try {
                    int id = Integer.parseInt(idTextField.getText());

                    if (gestion.getUtilisateur(id) == null) {
                        JOptionPane.showMessageDialog(
                                GererUtilisateur.this,
                                "impossible de supprimer ce compte!"
                        );
                    } else {
                        gestion.supprimerUtilisateur(id);

                        JOptionPane.showMessageDialog(
                                GererUtilisateur.this,
                                "utilisateur à été supprimer!"
                        );
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            GererUtilisateur.this,
                            "erreur de saisie de l'id!"
                    );
                }

                me.chargerTable(gestion.getAllUtilisateur());
            }
        });
        annuler.addActionListener(e -> {
            idTextField.setText("");
            nomTextField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
        });

        // setting the bounds of the components:
        retour.setBounds(
                40,
                20,
                100,
                60
        );
        titre.setBounds(
                100,
                150,
                400,
                60
        );
        tableJPanel.setBounds(
                xSize - 850,
                220,
                800,
                (ySize - taskBarSize) - 300
        );
        rechercherTextField.setBounds(
                tableJPanel.getX(),
                tableJPanel.getY() - 50,
                120,
                40
        );
        rechercher.setBounds(
                rechercherTextField.getX() + rechercherTextField.getWidth() + 20,
                rechercherTextField.getY(),
                40,
                40
        );
        id.setBounds(
                titre.getX(),
                titre.getY() + 80,
                200,
                40
        );
        idTextField.setBounds(
                id.getX(),
                id.getY() + 40,
                200,
                40
        );
        userType.setBounds(
                idTextField.getX(),
                idTextField.getY() + 40,
                200,
                40
        );
        comboBox.setBounds(
                userType.getX(),
                userType.getY() + 40,
                200,
                40
        );
        nom.setBounds(
                comboBox.getX(),
                comboBox.getY() + 40,
                200,
                40
        );
        nomTextField.setBounds(
                nom.getX(),
                nom.getY() + 40,
                200,
                40
        );
        mot_de_pass.setBounds(
                nomTextField.getX(),
                nomTextField.getY() + 40,
                200,
                40
        );
        passwordField.setBounds(
                mot_de_pass.getX(),
                mot_de_pass.getY() + 40,
                200,
                40
        );
        confirmer_mot_de_pass.setBounds(
                passwordField.getX(),
                passwordField.getY() + 40,
                400,
                40
        );
        confirmPasswordField.setBounds(
                confirmer_mot_de_pass.getX(),
                confirmer_mot_de_pass.getY() + 40,
                200,
                40
        );
        ajouter.setBounds(
                confirmPasswordField.getX(),
                confirmPasswordField.getY() + 50,
                200,
                40
        );
        update.setBounds(
                confirmPasswordField.getX() + ajouter.getWidth() + 10,
                confirmPasswordField.getY() + 50,
                240,
                40
        );
        supprimer.setBounds(
                confirmPasswordField.getX(),
                update.getY() + 50,
                240,
                40
        );
        annuler.setBounds(
                confirmPasswordField.getX() + supprimer.getWidth() + 10,
                update.getY() + 50,
                200,
                40
        );

        // adding border to the JTextFields + JPasswordFields:
        rechercherTextField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );
        idTextField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );
        nomTextField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );
        passwordField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );
        confirmPasswordField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );

        // adding the JScrollPane to the tableJPanel JPanel:
        tableJPanel.add(jsp);

        // adding components to the JFrame:
        this.add(retour);
        this.add(titre);
        this.add(rechercher);
        this.add(rechercherTextField);
        this.add(tableJPanel);
        this.add(id);
        this.add(idTextField);
        this.add(userType);
        this.add(comboBox);
        this.add(nom);
        this.add(nomTextField);
        this.add(mot_de_pass);
        this.add(passwordField);
        this.add(confirmer_mot_de_pass);
        this.add(confirmPasswordField);
        this.add(ajouter);
        this.add(update);
        this.add(supprimer);
        this.add(annuler);

        // setup JFrame:
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(xSize, ySize - taskBarSize);
        this.setVisible(true);
    }
}
