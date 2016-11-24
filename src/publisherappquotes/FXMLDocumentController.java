// Giorgio Latour
// Publisher App for Quotations
// IHRTLUHC
package publisherappquotes;

import data.PublisherDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLDocumentController implements Initializable {

    PublisherDAO model;

    @FXML
    private Label publishStatus;
    @FXML
    private TextArea textArea;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Button publishQuote;

    public void setModel(PublisherDAO model) {
        this.model = model;
    }

    public void setComboBox() {
       comboBox.getItems().addAll(model.getCategories());
        
        //choiceBox.getItems().addAll(model.getCategories());
        //publishStatus.setText("");
    }

    public int getChoice() {
        return comboBox.getSelectionModel().getSelectedIndex();
    }
    
    @FXML
    private void resetStatus(){
        publishStatus.setText("");
    }
    
    
    @FXML
    private void publishQuote(ActionEvent event) {
        if (!textArea.getText().trim().isEmpty() && !(comboBox.getValue() == null)) {
            String quote = textArea.getText();
            String user = model.getCurrentUser();
            String category = model.getCategories().get(getChoice());

            model.publishQuote(quote, user, category);
            publishStatus.setText("Quote published!");
            textArea.clear();
        } else {
            publishStatus.setText("Please enter a quote and select a category.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
