package presentation;

import dao.GestionImpl;
import dao.IGestion;
import metier.entity.Inventaire;
import metier.entity.Produit;
import presentation.tableModeles.TableModeleInventaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;

public class Destocker extends JFrame {
    // components
    // JPanel
    private final JPanel tableJPanel = new JPanel(new GridLayout(1, 1));
    // JLabels
    private final JLabel titre = new JLabel("Destocker");
    private final JLabel codeProduit = new JLabel("Code Produit");
    private final JLabel stockDisponible = new JLabel("Stock Disponible");
    private final JLabel remarques = new JLabel("Remarque");
    private final JLabel quantity = new JLabel("Quatité");
    // JTextFields
    private final JTextField rechercherTextField = new JTextField();
    private final JTextField codeProduitTextField = new JTextField();
    private final JTextField stockDisponibleTextField = new JTextField();
    private final JTextField quantityTextField = new JTextField();
    // JComboBox
    private final JComboBox<String> remarquesComboBox = new JComboBox<>(new String[] {
        "expiré",
        "non périmé"
    });
    // ImageIcons
    private ImageIcon backIcon = new ImageIcon("src\\images\\back.png");
    private ImageIcon findIcon = new ImageIcon("src\\images\\find.png");
    // JButtons
    private final JButton retour = new JButton();
    private final JButton rechercher = new JButton();
    private final JButton submit = new JButton("soumettre");
    // IGestion
    private final IGestion gestion = GestionImpl.getGestion();
    // TableModeleInventaire
    private final TableModeleInventaire me = new TableModeleInventaire();
    // JTable
    private final JTable table = new JTable(me);
    // JScrollPane
    private final JScrollPane jsp = new JScrollPane(table);

    public Destocker() {
        // titre:
        super("Destocker");

        // setting the Layout Manager for the JFrame
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

        // setting focusable for the JButtons
        retour.setFocusable(false);
        rechercher.setFocusable(false);
        submit.setFocusable(false);

        // making the borders and the background transparent for the retour + rechercher JButtons:
        // retour JButton:
        retour.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        retour.setBackground(new Color(0,0, 0, Transparency.TRANSLUCENT));

        // rechercher JButton:
        rechercher.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        rechercher.setBackground(new Color(0,0, 0, Transparency.TRANSLUCENT));

        // getting the list of Inventaire and updating the TableModeleInventaire's data:
        me.chargerTable(gestion.getAllInventaire());

        // adding tool tips
        codeProduitTextField.setToolTipText("saisie la quantité du produit");
        remarquesComboBox.setToolTipText("saisie la remarque");
        quantityTextField.setToolTipText("saisie la quantité du produit");
        stockDisponibleTextField.setToolTipText("saisie le stock disponible du produit");
        rechercherTextField.setToolTipText("faire une recherche");

        // adding the mouseListener to the JTable:
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();

                    int row = target.getSelectedRow();

                    int id = (int) target.getValueAt(row, 0);

                    codeProduitTextField.setText(String.valueOf(id));

                    Inventaire inventaire = gestion.getInventaire(id);

                    codeProduitTextField.setText(String.valueOf(inventaire.getProduit().getCode_produit()));
                    stockDisponibleTextField.setText(String.valueOf(inventaire.getQuantite()));
                    remarquesComboBox.setSelectedItem(inventaire.getRemarques());
                }
            }
        });

        // adding the ActionListener to the JButtons:
        retour.addActionListener(e -> {
            new presentation.GererInventaire();
            dispose();
        });
        rechercher.addActionListener(e -> {
            List<Inventaire> list = gestion.getInventairePMC(rechercherTextField.getText());

            me.chargerTable(list);
        });
        submit.addActionListener(e -> {
            if (
                    codeProduitTextField.getText().equals("")
                    || stockDisponibleTextField.getText().equals("")
                    || quantityTextField.getText().equals("")
            ) {
                JOptionPane.showMessageDialog(
                        Destocker.this,
                        "erreur de saisie"
                );
            } else {
                try {
                    int code = Integer.parseInt(codeProduitTextField.getText());
                    int stock = Integer.parseInt(stockDisponibleTextField.getText());
                    int quantity = Integer.parseInt(quantityTextField.getText());
                    Inventaire inventaire = gestion.getInventaire(code);

                    if (inventaire.getQuantite() != stock) {
                        JOptionPane.showMessageDialog(
                                Destocker.this,
                                "stock_disponible incorrecte!"
                        );
                    } else if (quantity > stock) {
                        JOptionPane.showMessageDialog(
                                Destocker.this,
                                "La quantité est > au stock_disponible!"
                        );
                    } else {
                        if (quantity <= 0) {
                            JOptionPane.showMessageDialog(
                                    Destocker.this,
                                    "Il faut que la quantité soit >= 1"
                            );
                        } else {
                            inventaire.setQuantite(stock - quantity);
                            inventaire.setRemarques(
                                    (String) remarquesComboBox.getSelectedItem()
                            );

                            gestion.destocker(inventaire);
                        }
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            Destocker.this,
                            "erreur de saisie du code_produit ou la quntité ou le stock_disponible!"
                    );
                }

                me.chargerTable(gestion.getAllInventaire());
            }
        });

        // setting the fonts + colors:
        //fonts:
        titre.setFont(new Font(null, Font.PLAIN, 50));
        submit.setFont(new Font(null, Font.PLAIN, 20));
        codeProduit.setFont(new Font(null, Font.PLAIN, 20));
        stockDisponible.setFont(new Font(null, Font.PLAIN, 20));
        remarques.setFont(new Font(null, Font.PLAIN, 20));
        quantity.setFont(new Font(null, Font.PLAIN, 20));
        stockDisponibleTextField.setFont(new Font(null, Font.PLAIN, 15));
        codeProduitTextField.setFont(new Font(null, Font.PLAIN, 15));
        remarquesComboBox.setFont(new Font(null, Font.PLAIN, 15));
        quantityTextField.setFont(new Font(null, Font.PLAIN, 15));

        // colors (Foreground + Background):
        // Foreground:
        titre.setForeground(Color.BLACK);
        submit.setForeground(Color.WHITE);

        // Background:
        retour.setBackground(Color.WHITE);
        rechercher.setBackground(Color.WHITE);
        submit.setBackground(MyColors._006E00.getColor());

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
        codeProduit.setBounds(
                titre.getX(),
                titre.getY() + 80,
                200,
                40
        );
        codeProduitTextField.setBounds(
                codeProduit.getX(),
                codeProduit.getY() + 40,
                200,
                40
        );
        stockDisponible.setBounds(
                codeProduitTextField.getX(),
                codeProduitTextField.getY() + 40,
                200,
                40
        );
        stockDisponibleTextField.setBounds(
                stockDisponible.getX(),
                stockDisponible.getY() + 40,
                200,
                40
        );
        remarques.setBounds(
                stockDisponibleTextField.getX(),
                stockDisponibleTextField.getY() + 40,
                200,
                40
        );
        remarquesComboBox.setBounds(
                remarques.getX(),
                remarques.getY() + 40,
                200,
                40
        );
        quantity.setBounds(
                remarquesComboBox.getX(),
                remarquesComboBox.getY() + 40,
                200,
                40
        );
        quantityTextField.setBounds(
                quantity.getX(),
                quantity.getY() + 40,
                200,
                40
        );
        submit.setBounds(
                quantityTextField.getX(),
                quantityTextField.getY() + 60,
                200,
                40
        );

        // adding border to the JTextFields:
        rechercherTextField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );
        codeProduitTextField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );
        stockDisponibleTextField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );
        quantityTextField.setBorder(
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
        this.add(codeProduit);
        this.add(codeProduitTextField);
        this.add(stockDisponible);
        this.add(stockDisponibleTextField);
        this.add(remarques);
        this.add(remarquesComboBox);
        this.add(quantity);
        this.add(quantityTextField);
        this.add(submit);

        // setup JFrame:
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(xSize, ySize - taskBarSize);
        this.setVisible(true);
    }
}
