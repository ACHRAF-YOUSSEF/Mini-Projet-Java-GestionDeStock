package presentation;

import javax.swing.*;

import java.util.List;
import java.awt.Image;
import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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

        Font font_1 = new Font();

        font_1.setSize(40);
        font_1.setStyle(Font.BOLD);

        p.setFont(font_1);
        p.add("Facture");
        p.setAlignment(Element.ALIGN_CENTER);

        document.add(p);
        document.add(Chunk.NEWLINE);

        Font font_2 = new Font();

        font_2.setStyle(Font.NORMAL);
        font_2.setSize(15);

        Font font_3 = new Font();

        font_3.setStyle(Font.NORMAL);
        font_3.setSize(10);

        PdfPTable table1 = new PdfPTable(new float[] {
                1f, 1f, 1f, 1f, 1f, 1f
        });

        table1.getDefaultCell().setBorder(0);
        table1.setWidthPercentage(100);
        table1.setHorizontalAlignment(Element.ALIGN_LEFT);

        Phrase code_produit = new Phrase("Code Produit", font_2);
        Phrase nom = new Phrase("Nom Produit", font_2);
        Phrase categorie = new Phrase("Categorie", font_2);
        Phrase quatite = new Phrase("Quatité", font_2);
        Phrase prix = new Phrase("Prix", font_2);
        Phrase totale = new Phrase("Totale", font_2);
        Phrase argentP = new Phrase("Argent Totale", font_2);
        Phrase argentARetourner = new Phrase("Argent A Retourner", font_2);

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
            Produit produit = inv.getProduit();

            table1.addCell(new Phrase(String.valueOf(produit.getCode_produit()), font_3));
            table1.addCell(new Phrase(produit.getNom(), font_3));
            table1.addCell(new Phrase(produit.getCategorie(), font_3));
            table1.addCell(new Phrase(String.valueOf(inv.getQuantite()), font_3));
            table1.addCell(new Phrase(String.valueOf(produit.getPrix()), font_3));
            table1.addCell(new Phrase(String.valueOf(
                    inv.getQuantite() * produit.getPrix()
            ), font_3));

            totaleQty += inv.getQuantite();
            totalePrix += produit.getPrix();
            tTotale += produit.getPrix() * inv.getQuantite();
        }

        document.add(table1);

        table1 = new PdfPTable(new float[] {
                1f, 1f, 1f, 1f, 1f, 1f
        });

        table1.getDefaultCell().setBorder(0);
        table1.setWidthPercentage(100);
        table1.setHorizontalAlignment(Element.ALIGN_MIDDLE);

        table1.addCell("");
        table1.addCell("");
        table1.addCell(totale);
        table1.addCell(new Phrase(String.valueOf(totaleQty), font_3));
        table1.addCell(new Phrase(String.valueOf(totalePrix), font_3));
        table1.addCell(new Phrase(String.valueOf(tTotale), font_3));

        document.add(table1);
        document.add(Chunk.NEWLINE);

        table1 = new PdfPTable(new float[] {
                1f, 1f, 1f, 1f, 1f, 1f
        });

        table1.getDefaultCell().setBorder(0);
        table1.setWidthPercentage(100);
        table1.setHorizontalAlignment(Element.ALIGN_MIDDLE);

        table1.addCell(argentP);
        table1.addCell("");
        table1.addCell("");
        table1.addCell("");
        table1.addCell("");
        table1.addCell(new Phrase(String.valueOf(argent), font_3));

        document.add(table1);
        document.add(Chunk.NEWLINE);

        table1 = new PdfPTable(new float[] {
                1f, 1f, 1f, 1f, 1f, 1f
        });

        table1.getDefaultCell().setBorder(0);
        table1.setWidthPercentage(100);
        table1.setHorizontalAlignment(Element.ALIGN_MIDDLE);

        table1.addCell(argentARetourner);
        table1.addCell("");
        table1.addCell("");
        table1.addCell("");
        table1.addCell("");
        table1.addCell(new Phrase(String.valueOf(money), font_3));

        document.add(table1);

        document.close();
        System.out.println("Done");
    }
}
