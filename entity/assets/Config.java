package sample.entity.assets;

public final class Config {
    
    public static final class SIZE {
        public static final Integer WINDOW_WIDTH = 1280;
        public static final Integer WINDOW_HEIGHT = 720; 
        public static final Integer TURN_POSITION_X = 1050; 
        public static final Integer TURN_POSITION_Y = 650; 
        public static final Integer SIZE = 38; 
    }
    
    public static final class PATH {
        public static final String DESK_PICTURE_PATH = "assets/textures/desk.jpg"; 
        public static final String CARD_PICTURE_PATH = "textures/cards/";
        public static final String CARD_BACK_PICTURE_PATH = CARD_PICTURE_PATH.concat( "Back.jpg" );
    }
    
    public static final String FONT = "Roboto"; 
    public static final String COLOR = "#FFFFFF"; 
    public static final String MAIN_TITLE = "Турецкий ковёр"; 
    public static final String TURNS = "Ходов: "; 
    public static final String EXPOSITION = ".jpg"; 
}
