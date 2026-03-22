package mono.factories.core.inital;

import javafx.application.Application;
import javafx.stage.Stage;

public class Core extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        EngineInit.init();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
