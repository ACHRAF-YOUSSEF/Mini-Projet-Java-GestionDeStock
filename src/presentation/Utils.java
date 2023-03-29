package presentation;

import javax.swing.*;

import java.util.List;
import java.awt.Image;
import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dao.GestionImpl;
import metier.entity.Inventaire;
import metier.entity.Produit;

public class Utils {
    public static ImageIcon resizeImageIcon(ImageIcon imageIcon, int width, int height) {
        return new ImageIcon(imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    public static void generatePDF(String FILE_NAME, List<Inventaire> list, double argent, double money) throws Exception {
        if (FILE_NAME.equals("")) {
            FILE_NAME = "pdfs\\pdf_0.pdf";
        }

        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream(FILE_NAME));
        document.open();
        System.out.println("Writing Now!");

        Paragraph p = new Paragraph();
        p.add("Facture");
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);
        document.add(Chunk.NEWLINE);

        Font font = new Font();
        font.setStyle(Font.BOLD);
        font.setSize(8);

        PdfPTable table1 = new PdfPTable(new float[] {
                1f, 1f, 1f, 1f, 1f, 1f
        });

        table1.setWidthPercentage(100);
        table1.setHorizontalAlignment(Element.ALIGN_LEFT);

        Phrase code_produit = new Phrase("Code Produit", font);
        Phrase nom = new Phrase("Nom Produit", font);
        Phrase categorie = new Phrase("Categorie", font);
        Phrase quatite = new Phrase("Quatité", font);
        Phrase prix = new Phrase("Prix", font);
        Phrase totale = new Phrase("Totale", font);
        Phrase argentP = new Phrase("Argent Totale", font);
        Phrase argentARetourner = new Phrase("Argent A Retourner", font);

        table1.addCell(code_produit);
        table1.addCell(nom);
        table1.addCell(categorie);
        table1.addCell(quatite);
        table1.addCell(prix);
        table1.addCell(totale);

        int totaleQty = 0;
        double totalePrix = 0.0;
        double tTotale = 0.0;

        for (Inventaire inv: list) {
            Produit produit = GestionImpl.getGestion().getProduit(inv.getCode_produit());

            table1.addCell(String.valueOf(inv.getCode_produit()));
            table1.addCell(produit.getNom());
            table1.addCell(produit.getCategorie());
            table1.addCell(String.valueOf(inv.getQuantite()));
            table1.addCell(String.valueOf(produit.getPrix()));
            table1.addCell(String.valueOf(
                    inv.getQuantite() * produit.getPrix()
            ));

            totaleQty += inv.getQuantite();
            totalePrix += produit.getPrix();
            tTotale += produit.getPrix() * inv.getQuantite();
        }

        table1.addCell("");
        table1.addCell("");
        table1.addCell(totale);
        table1.addCell(String.valueOf(totaleQty));
        table1.addCell(String.valueOf(totalePrix));
        table1.addCell(String.valueOf(tTotale));

        table1.addCell("");
        table1.addCell("");
        table1.addCell(argentP);
        table1.addCell(String.valueOf(argent));
        table1.addCell("");
        table1.addCell("");

        table1.addCell("");
        table1.addCell("");
        table1.addCell(argentARetourner);
        table1.addCell(String.valueOf(money));
        table1.addCell("");
        table1.addCell("");

        document.add(table1);

        document.close();
        System.out.println("Done");
    }
}
