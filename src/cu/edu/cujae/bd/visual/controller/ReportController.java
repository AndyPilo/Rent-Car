package cu.edu.cujae.bd.visual.controller;

import cu.edu.cujae.bd.service.ServicesLocator;
import cu.edu.cujae.bd.visual.models.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReportController {
    
    @FXML
    private Button closeButton;

    public void onReport(){
        try {
        JasperReport report = null;
        String path = "src\\cu\\edu\\cujae\\bd\\visual\\controller\\rptCars.jasper";
        report = (JasperReport) JRLoader.loadObjectFromFile(path);
        JasperPrint jPrint = JasperFillManager.fillReport(report, null, ServicesLocator.getConnection());
        JasperViewer view = new JasperViewer(jPrint,false);
        view.setDefaultCloseOperation(0);
        view.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }

    }

    public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        Model.getInstanse().getViewFactory().closeStage(stage);
    }

    public void minimice(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.setIconified(true);
    }
}
