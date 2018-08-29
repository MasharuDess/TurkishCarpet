package sample.entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

import static sample.entity.assets.Config.PATH.DESK_PICTURE_PATH;


public class Desk {
    private Image image = new Image( getClass().getResourceAsStream( DESK_PICTURE_PATH ) );
    private ImageView imageView = new ImageView( image ); 
    
    public Desk() {
        init();
    } 
    
    private void init() {
        imageView.setFitWidth( Main.getPane().getWidth() );   
        imageView.setFitHeight( Main.getPane().getHeight() ); 
        Main.getPane().getChildren().add( imageView );       
    }
}
