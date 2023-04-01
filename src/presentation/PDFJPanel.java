package presentation;

import javax.swing.*;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import java.awt.*;

public class PDFJPanel extends JPanel {
    public PDFJPanel(String filePath) {
        //
        this.setLayout(new BorderLayout());

        //
        SwingController controller = new SwingController();
        SwingViewBuilder factory = new SwingViewBuilder(controller);

        //
        JPanel viewerComponentPanel = factory.buildViewerPanel();

        //
        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()
                )
        );

        //
        this.add(viewerComponentPanel);

        //
        controller.openDocument(filePath);
    }
}
