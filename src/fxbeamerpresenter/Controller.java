package fxbeamerpresenter;

import fxbeamerpresenter.model.Clock;
import fxbeamerpresenter.model.PDFModel;
import fxbeamerpresenter.utils.PDFViewer;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/**
 *
 * @author Ced
 */
public class Controller implements Initializable {

    private Clock clock;
	private Clock diffClock;
    private PDFModel pdfModel;
    private PDFViewer pdfViewer;
    private FileChooser fileChooser;

    @FXML
    private Pane pane;

    @FXML
    private Button startPauseButton, resetButton, previousButton, nextButton, selectButton;

    @FXML
    private Button pdfBrowseButton, notesBrowseButton;

    @FXML
    private Label pageLabel;

    @FXML
    private TextField pdfPath, notesPath;

    @FXML
    private void handleStartPauseButton(ActionEvent event) {
        clock.togglePause();
        startPauseButton.setText(clock.isPause() ? "Play" : "Pause");
		diffClock.setColor(Color.GREEN, Color.rgb(50, 50, 50));
    }

    @FXML
    private void handleResetButton(ActionEvent event) {
        clock.reset();
    }

    @FXML
    private void handlePreviousButton(ActionEvent event) {
        pdfModel.decrPage();
    }

    @FXML
    private void handleNextButton(ActionEvent event) {
        pdfModel.incrPage();
    }

    @FXML
    private void handlePDFBrowseButton(ActionEvent event) {
        fileChooser.setTitle("Select PDF file");
        File file = fileChooser.showOpenDialog(pdfBrowseButton.getScene().getWindow());
        if (file != null) {
            pdfPath.setText(file.getAbsolutePath());
            fileChooser.setInitialDirectory(file.getParentFile());
        }
    }

    @FXML
    private void handleNotesBrowseButton(ActionEvent event) {
        fileChooser.setTitle("Select notes file");
        File file = fileChooser.showOpenDialog(notesBrowseButton.getScene().getWindow());
        if (file != null) {
            notesPath.setText(file.getAbsolutePath());
            fileChooser.setInitialDirectory(file.getParentFile());
        }
    }
    
    @FXML private void handleSelectButton(ActionEvent event) {
        pdfModel.setPdfPath(pdfPath.getText());
        pdfModel.setNotesPath(notesPath.getText());
        pdfModel.setPage(1);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pdfViewer = new PDFViewer();
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );

    }

    /**
     * @param clock the clock to set
     */
    public void setClock(Clock clock) {
        this.clock = clock;
    }
	
	/**
     * @param diffClock the diffClock to set
     */
    public void setDiffClock(Clock diffClock) {
        this.diffClock = diffClock;
    }

    /**
     * @param pdfModel the pdfModel to set
     */
    public void setPdfModel(PDFModel pdfModel) {
        this.pdfModel = pdfModel;
    }

    public void initListener() {
        pdfModel.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            switch (evt.getPropertyName()) {
                case "page":
                    pageLabel.setText(evt.getNewValue() + "");
                    pdfViewer.viewPDF(pdfModel);
                    break;
                case "pdfPath":
                    pdfPath.setText(evt.getNewValue().toString());
                    break;
                case "notesPath":
                    notesPath.setText(evt.getNewValue().toString());
                    break;
            }
        });
    }
}
