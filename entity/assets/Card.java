package sample.entity.assets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import static sample.entity.assets.Config.PATH.CARD_BACK_PICTURE_PATH;

public class Card extends Pane {
    
    private Image cardImage;            
    private ImageView cardImageView;    
    private Image backImage;            
    private ImageView backImageView;    
    private CardInfo.Suit suit;        
    private CardInfo.Value value;       
    
    public Card( String imagePath, CardInfo.Suit suit, CardInfo.Value value ) {
        init( imagePath, suit, value );
    }
    
    private void init( String imagePath, CardInfo.Suit suit, CardInfo.Value value ) {
        cardImage = new Image( getClass().getResourceAsStream( imagePath ) ); 
        cardImageView = new ImageView( cardImage ); 
        backImage = new Image( getClass().getResourceAsStream( CARD_BACK_PICTURE_PATH ) ); 
        backImageView = new ImageView( backImage ); 
        this.suit = suit; 
        this.value = value; 
    }
    
    public ImageView getCardImageView() {
        return cardImageView;
    } 
    
    public ImageView getBackImageView() {
        return backImageView;
    } 
    
    public CardInfo.Value getValue() {
        return value;
    }
}
