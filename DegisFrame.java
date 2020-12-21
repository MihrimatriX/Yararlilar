import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class DegisFrame extends Application 
{
    /**
     * Frame Değiştirme JavaFX
     */

    Stage window;
    Scene scene1, scene2;
    
    public static void main(final String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) 
    {
        window = primaryStage;
        // Button 1
        final Label label1 = new Label("İlk pencere!");
        final Button button1 = new Button("Diğer pencereye git!");
        button1.setOnAction(e -> window.setScene(scene2));

        // Layout 1 - children laid out in vertical column
        final VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 200, 200);

        // Button 2
        final Button button2 = new Button("İlk pencereye git!");
        button2.setOnAction(e -> window.setScene(scene1));

        // Layout 2
        final StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 600, 300);
        
        //Display scene 1 at first
        window.setScene(scene1);
        window.setTitle("JavaFx Uygulaması");
        window.show();
    }
}