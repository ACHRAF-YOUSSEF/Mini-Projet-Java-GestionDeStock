package presentation.tableModeles;

import metier.entity.Utilisateur;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableModeleUtilisateur extends AbstractTableModel {
    private List<Utilisateur> utilisateurs = new ArrayList<>();
    private final String[] titres = {
            "ID",
            "Nom",
            "Mot De Pass",
            "isAdmin"
    };

    @Override
    public int getRowCount() {
        return utilisateurs.size();
    }

    @Override
    public int getColumnCount() {
        return titres.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> utilisateurs.get(rowIndex).getId();
            case 1 -> utilisateurs.get(rowIndex).getNom();
            case 2 -> utilisateurs.get(rowIndex).getMot_de_pass();
            case 3 -> utilisateurs.get(rowIndex).getAdmin();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return titres[column];
    }

    public void chargerTable(List<Utilisateur> list) {
        utilisateurs = list;

        fireTableDataChanged();
    }
}
