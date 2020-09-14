package org.una.tramites;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2 
public class TramitesApplication extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/Dashboard.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
       
        stage.show();
        
    }
    public static void main(String[] args) {
       SpringApplication.run(TramitesApplication.class, args);
       launch(); 
        
    }

}
