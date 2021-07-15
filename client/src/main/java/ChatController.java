import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.ListRequest;
import model.ListResponse;

public class ChatController implements Initializable {

    public ListView<String> listView;
    public TextField statusBar;
    private String root = "client/clientFiles";
    private NettyNetwork network;

    public void send(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        network = new NettyNetwork(command -> {
            switch (command.getType()) {
                case LIST_RESPONSE:
                    ListResponse files = (ListResponse) command;
                    Platform.runLater(() -> {
                        listView.getItems().clear();
                        listView.getItems().addAll(files.getNames());
                    });
                    break;
            }
        });
    }

}
