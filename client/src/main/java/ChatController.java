import java.net.URL;
import java.util.ResourceBundle;

import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Message;

public class ChatController implements Initializable {

    public ListView<String> listView;
    public TextField statusBar;
    private String root = "client/clientFiles";
    private ObjectDecoderInputStream is;
    private ObjectEncoderOutputStream os;
    private NettyNetwork network;
    private byte[] buffer;

    public void send(ActionEvent actionEvent) {
        String content = statusBar.getText();
        network.writeMessage(new Message(content));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        network = new NettyNetwork(s -> Platform.runLater(() -> statusBar.setText(s.toString())));
    }

}
