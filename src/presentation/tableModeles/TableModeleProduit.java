package presentation.tableModeles;

import metier.entity.Produit;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableModeleProduit extends AbstractTableModel {
    private List<Produit> produits = new ArrayList<>();
    private final String[] titres = {
            "Code Produit",
            "Nom Produit",
            "Categorie",
            "Prix"
    };

    @Override
    public int getRowCount() {
        return produits.size();
    }

    @Override
    public int getColumnCount() {
        return titres.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> produits.get(rowIndex).getCode_produit();
            case 1 -> produits.get(rowIndex).getNom();
            case 2 -> produits.get(rowIndex).getCategorie();
            case 3 -> produits.get(rowIndex).getPrix();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return titres[column];
    }

    public void chargerTable(List<Produit> list) {
        produits = list;

        fireTableDataChanged();
    }
}
