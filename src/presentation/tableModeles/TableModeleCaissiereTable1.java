package presentation.tableModeles;

import dao.GestionImpl;
import dao.IGestion;
import metier.entity.*;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableModeleCaissiereTable1 extends AbstractTableModel {
    private final IGestion gestion = GestionImpl.getGestion();
    private List<Inventaire> inventaires = new ArrayList<>();
    private final String[] titres = {
            "Code Produit",
            "Nom Produit",
            "Categorie",
            "Prix",
            "Stock Disponible"
    };

    @Override
    public int getRowCount() {
        return inventaires.size();
    }

    @Override
    public int getColumnCount() {
        return titres.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        final int code_produit = inventaires.get(rowIndex).getCode_produit();

        return switch (columnIndex) {
            case 0 -> code_produit;
            case 1 -> gestion.getProduit(code_produit).getNom();
            case 2 -> gestion.getProduit(code_produit).getCategorie();
            case 3 -> gestion.getProduit(code_produit).getPrix();
            case 4 -> inventaires.get(rowIndex).getQuantite();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return titres[column];
    }

    public void chargerTable(List<Inventaire> list) {
        inventaires = list;

        fireTableDataChanged();
    }
}
