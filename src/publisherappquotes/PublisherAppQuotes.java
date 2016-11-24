// Giorgio Latour
// Publisher App for Quotations
// IHRTLUHC
package publisherappquotes;

import data.PublisherDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PublisherAppQuotes extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLLoginWindow.fxml"));
        Parent root = (Parent) loader.load();

        FXMLLoginWindowController controller = (FXMLLoginWindowController) loader.getController();
        PublisherDAO dao = new PublisherDAO();
        controller.setModel(dao);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Publisher Utility Login");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
