package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static sample.entity.assets.Config.MAIN_TITLE;
import static sample.entity.assets.Config.SIZE.WINDOW_HEIGHT;
import static sample.entity.assets.Config.SIZE.WINDOW_WIDTH;


public class Main extends Application {
    
    private static Stage stage;        
    private static Pane root = new Pane(); 
    private static Scene scene = new Scene( root, WINDOW_WIDTH, WINDOW_HEIGHT ); 
    private static GameInstance gameInstance; 
    
    @Override
    public void start( Stage primaryStage ) throws Exception {
        primaryStage.setTitle( MAIN_TITLE );
        primaryStage.setScene( scene );       
        primaryStage.show();                 
        stage = primaryStage;                 
        gameInstance = GameInstance.getInstance();
        gameInstance.init();                 
    }
    
    public static Pane getPane() {
        return root;
    }
    
    public static Scene getScene() {
        return scene;
    }
    
    public static void main( String[] args ) {
        launch( args );
    }
}
