package presentation.tableModeles;

import metier.entity.Inventaire;
import metier.entity.Produit;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableModeleInventaire extends AbstractTableModel {
    private List<Inventaire> inventaires = new ArrayList<>();
    private final String[] titres = {
            "Code Produit",
            "Nom Produit",
            "Categorie",
            "Quantité"
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
        final Produit produit = inventaires.get(rowIndex).getProduit();

        return switch (columnIndex) {
            case 0 -> produit.getCode_produit();
            case 1 -> produit.getNom();
            case 2 -> produit.getCategorie();
            case 3 -> inventaires.get(rowIndex).getQuantite();
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
