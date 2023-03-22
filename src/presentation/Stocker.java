package presentation;

import com.toedter.calendar.JCalendar;
import dao.GestionImpl;
import dao.IGestion;
import presentation.tableModeles.TableModeleProduit;

import javax.swing.*;
import java.awt.*;

public class Stocker extends JFrame {
    // components
    // JCalender : site -> https://toedter.com/jcalendar/
    private final JCalendar calendar = new JCalendar();
    // JPanel
    private final JPanel tableJPanel = new JPanel(new GridLayout(1, 1));
    // JLabels
    private final JLabel titre = new JLabel("Stocker");
    private final JLabel codeProduit = new JLabel("Code Produit");
    private final JLabel dateExpiration = new JLabel("Date D'expiration");
    private final JLabel transactionID = new JLabel("ID De Transaction");
    private final JLabel quantity = new JLabel("Quatité");
    // JTextFields
    private final JTextField rechercherTextField = new JTextField();
    private final JTextField codeProduitTextField = new JTextField();
    private final JTextField transactionIDTextField = new JTextField();
    private final JTextField quantityTextField = new JTextField();
    //

    // ImageIcons
    private ImageIcon backIcon = new ImageIcon("D:\\semestre 2\\java avancée\\ex_cours\\chapitre4\\GestionDeStock\\src\\images\\back.png");
    private ImageIcon findIcon = new ImageIcon("D:\\semestre 2\\java avancée\\ex_cours\\chapitre4\\GestionDeStock\\src\\images\\find.png");
    // JButtons
    private final JButton retour = new JButton();
    private final JButton rechercher = new JButton();
    private final JButton submit = new JButton("soumettre");
    // IGestion
    private final IGestion gestion = GestionImpl.getGestion();
    // TableModeleProduit
    private final TableModeleProduit me = new TableModeleProduit();
    // JTable
    private final JTable table = new JTable(me);
    // JScrollPane
    private final JScrollPane jsp = new JScrollPane(table);

    public Stocker() {
        // titre:
        super("Stocker");

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

        // making the borders and the background transparent for the retour + rechercher JButtons:
        // retour JButton:
        retour.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        retour.setBackground(new Color(0,0, 0, Transparency.TRANSLUCENT));

        // rechercher JButton:
        rechercher.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        rechercher.setBackground(new Color(0,0, 0, Transparency.TRANSLUCENT));

        // adding the ActionListener to the JButtons:
        retour.addActionListener(e -> {
            new GererInventaire();
            dispose();
        });
        rechercher.addActionListener(e -> {});

        // setting the fonts + colors:
        //fonts:
        titre.setFont(new Font(null, Font.PLAIN, 50));
        codeProduit.setFont(new Font(null, Font.PLAIN, 20));
        codeProduitTextField.setFont(new Font(null, Font.PLAIN, 15));

        // colors (Foreground + Background):
        // Foreground:
        titre.setForeground(Color.BLACK);

        // Background:
        retour.setBackground(Color.WHITE);
        rechercher.setBackground(Color.WHITE);

        // setting the bounds of the components:
        retour.setBounds(
                40,
                80,
                100,
                60
        );
        titre.setBounds(100,
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
                400,
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

        // adding border to the JTextFields:
        rechercherTextField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );
        codeProduitTextField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );

        // adding the JScrollPane to the tableJPanel JPanel:
//        tableJPanel.add(jsp);

        // adding components to the JFrame:
        this.add(retour);
        this.add(titre);
        this.add(rechercher);
        this.add(rechercherTextField);
        this.add(tableJPanel);
        this.add(codeProduit);
        this.add(codeProduitTextField);

        // setup JFrame:
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(xSize, ySize - taskBarSize);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Stocker();
    }
}
