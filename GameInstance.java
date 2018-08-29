package sample;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.entity.Desk;
import sample.entity.assets.Card;
import sample.entity.assets.CardInfo;

import java.util.Collections;
import java.util.Stack;

import static sample.entity.assets.Config.*;
import static sample.entity.assets.Config.PATH.CARD_PICTURE_PATH;
import static sample.entity.assets.Config.SIZE.TURN_POSITION_X;
import static sample.entity.assets.Config.SIZE.TURN_POSITION_Y;

public class GameInstance {
    
    private static GameInstance gameInstance = new GameInstance(); 
    private Desk desk = new Desk(); 
    private Stack<Card> mixedDeck;
    private Stack<Card>[] fullDeck;
    private Stack<Card> drop = new Stack<>(); 
    private Card firstCard; 
    private int firstCardIndex; 
    private int count; 
    private Label countLabel = new Label(); 
    
    private GameInstance() {
        initDeck();
    } 
    
    private void onCardClick( Card card, int j ) {
        if( firstCard == null && ( card == fullDeck[j].lastElement() ) ) { 
            firstCard = card;
            firstCardIndex = j;
        } else if( card.getValue() == firstCard.getValue() 
                && ( j != firstCardIndex )
                && ( card == fullDeck[j].lastElement() ) ) { 
            Main.getPane().getChildren().removeAll(
                    fullDeck[j].lastElement().getCardImageView(),
                    fullDeck[firstCardIndex].lastElement().getCardImageView() ); 
            fullDeck[j].removeElement( fullDeck[j].lastElement() ); 
            fullDeck[firstCardIndex].removeElement( fullDeck[firstCardIndex].lastElement() );
            firstCard = null;
            count++; 
            countLabel.setText( TURNS + count ); 
        } else { 
            firstCard = null; 
        }
    }
    
    private Card[][] initDeck() {
        Card[][] deck = new Card[4][13]; 
        String cardPath;
        CardInfo.Value cardValue; 
        CardInfo.Suit cardSuit;
        for( Integer i = 0; i < 4; i++ ) { 
            switch( i ) { 
                case 0:
                    cardSuit = CardInfo.Suit.clubs; 
                    break;     
                case 1:         
                    cardSuit = CardInfo.Suit.diamonds; 
                    break;                  
                case 2:
                    cardSuit = CardInfo.Suit.hearts;
                    break;
                case 3:
                    cardSuit = CardInfo.Suit.spades;
                    break;
                default:
                    cardSuit = null;
            }
            for( Integer j = 0; j < 13; j++ ) { 
                cardPath = CARD_PICTURE_PATH
                        .concat( i.toString() ) 
                        .concat( "." ) 
                        .concat( j.toString() ) 
                        .concat( EXPOSITION ); 
                
                switch( j ) { 
                    case 0:
                        cardValue = CardInfo.Value.ace;
                        break;
                    case 1:
                        cardValue = CardInfo.Value.two;
                        break;
                    case 2:
                        cardValue = CardInfo.Value.three;
                        break;
                    case 3:
                        cardValue = CardInfo.Value.four;
                        break;
                    case 4:
                        cardValue = CardInfo.Value.five;
                        break;
                    case 5:
                        cardValue = CardInfo.Value.six;
                        break;
                    case 6:
                        cardValue = CardInfo.Value.seven;
                        break;
                    case 7:
                        cardValue = CardInfo.Value.eight;
                        break;
                    case 8:
                        cardValue = CardInfo.Value.nine;
                        break;
                    case 9:
                        cardValue = CardInfo.Value.ten;
                        break;
                    case 10:
                        cardValue = CardInfo.Value.jack;
                        break;
                    case 11:
                        cardValue = CardInfo.Value.queen;
                        break;
                    case 12:
                        cardValue = CardInfo.Value.king;
                        break;
                    default:
                        cardValue = null;
                }
                deck[i][j] = new Card( cardPath, cardSuit, cardValue );
                
            }
        }
        return deck;
    }
    
   
    private void shuffleDeck() {
        mixedDeck = new Stack<>();
        for( Card[] suit : initDeck() ) { 
            for( Card card : suit ) {
                mixedDeck.add( card );
            }
        }
        Collections.shuffle( mixedDeck ); 
    }
    
   
    private void initListeners() {
        Main.getScene().setOnKeyPressed( event -> { 
            if( event.getCode() == KeyCode.R ) { 
                Main.getPane().getChildren().clear(); 
                desk = new Desk(); 
                init(); 
            }   
        } );
        
        for( int i = 0; i < fullDeck.length - 1; i++ ) {
            for( Card card : fullDeck[i] ) { 
                int j = i; 
                card.getCardImageView().setOnMouseClicked( event -> onCardClick( card, j ) ); 
            }
        }
        
        
        for( Card card : drop ) {
            card.getBackImageView().setOnMouseClicked( ( MouseEvent event ) -> {
                card.getCardImageView().setFitHeight( 100 ); 
                card.getCardImageView().setFitWidth( 75 ); 
                card.getCardImageView().setLayoutX( 200 );
                card.getCardImageView().setLayoutY( 550 );
                Main.getPane().getChildren().add( card.getCardImageView() );  
                Main.getPane().getChildren().remove( card.getBackImageView() ); 
                fullDeck[9].push( card );
                count++;
                countLabel.setText( TURNS + count );
                card.getCardImageView().setOnMouseClicked( event1 -> onCardClick( card, 9 ) ); 
            } );
        }
    }
    
    
    public void init() {
        count = 0; 
        fullDeck = new Stack[10]; 
        for( int i = 0; i < 10; i++ ) { 
            fullDeck[i] = new Stack<>();
        }
        
        countLabel.setText( TURNS + count );
        Main.getPane().getChildren().add( countLabel ); 
        countLabel.setFont( new Font( FONT, SIZE.SIZE ) );
        countLabel.setTextFill ( Color.web ( COLOR ));
        countLabel.setTranslateX ( TURN_POSITION_X ); 
        countLabel.setTranslateY ( TURN_POSITION_Y ); 
        
        shuffleDeck();
        int i = 0, j = 0;
        while( mixedDeck.size() > 7 ) { 
            Card card = mixedDeck.pop();
            card.getCardImageView().setFitHeight( 100 ); 
            card.getCardImageView().setFitWidth( 75 );
            card.getCardImageView().setLayoutX( 200 + ( i * 100 ) ); 
            card.getCardImageView().setLayoutY( 50 + ( j * 50 ) );
            Main.getPane().getChildren().add( card.getCardImageView() ); 
            
            fullDeck[i].push( card ); 
            if( j == 4 ) { 
                i++;
                j = 0;
            } else {
                j++;
            }
        }
        
        while( !mixedDeck.empty() ) { 
            Card card = mixedDeck.pop();
            card.getBackImageView().setFitHeight( 100 );
            card.getBackImageView().setFitWidth( 75 );
            card.getBackImageView().setLayoutX( 100 );
            card.getBackImageView().setLayoutY( 550 );
            Main.getPane().getChildren().add( card.getBackImageView() );
            drop.push( card );
        }
        
        initListeners();
    }
    
    public static GameInstance getInstance() {
        return gameInstance;
    }
}
