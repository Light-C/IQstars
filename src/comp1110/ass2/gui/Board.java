package comp1110.ass2.gui;


import comp1110.ass2.IQStars;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import comp1110.ass2.Games;

import javafx.scene.control.Slider;


import java.util.*;

// FIXME Task 8 (CR): Implement a basic playable IQ Stars game in JavaFX that only allows pieces to be placed in valid places
// FIXME Task 9 (D): Implement challenges (you may use the set of challenges in the Games class)
// FIXME Task 11 (HD): Implement hints (should become visible when the user presses '/' -- see gitlab issue for details)
// FIXME Task 12 (HD): Generate interesting challenges (each challenge must have exactly one solution)
/**
 * @Tianjian Yang
 */

public class Board extends Application {

    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;


    private static final int MARGIN_X = 60;
    private static final int BOARD_MARGIN = 25;
    private static final int BOARD_X = MARGIN_X;
    public int w=96; //star width
    public int a=25;    // first star xais
    public int b=30;    // first star yxais
    public double[][][] ns = {{{0,0}, {a,b}}, // coordinate to positions
            {{1, 0}, {a+w, b}},
            {{2, 0}, {a+2*w, b}},
            {{3, 0}, {a+3*w, b}},
            {{4, 0}, {a+4*w, b}},
            {{5, 0}, {a+5*w, b}},
            {{6, 0}, {a+6*w, b}},
            {{0, 1}, {a+0.5*w, b+0.87*w}},
            {{1, 1}, {a+1.5*w, b+0.87*w}},
            {{2, 1}, {a+2.5*w, b+0.87*w}},
            {{3, 1}, {a+3.5*w, b+0.87*w}},
            {{4, 1}, {a+4.5*w, b+0.87*w}},
            {{5, 1}, {a+5.5*w, b+0.87*w}},
            {{0, 2}, {a, b+1.732*w}},
            {{1, 2}, {a+w, b+1.732*w}},
            {{2, 2}, {a+2*w, b+1.732*w}},
            {{3, 2}, {a+3*w, b+1.732*w}},
            {{4, 2}, {a+4*w, b+1.732*w}},
            {{5, 2}, {a+5*w, b+1.732*w}},
            {{6, 2}, {a+6*w, b+1.732*w}},
            {{0, 3}, {a+0.5*w, b+2.598*w}},
            {{1, 3}, {a+1.5*w, b+2.598*w}},
            {{2, 3}, {a+2.5*w, b+2.598*w}},
            {{3, 3}, {a+3.5*w, b+2.598*w}},
            {{4, 3}, {a+4.5*w, b+2.598*w}},
            {{5, 3}, {a+5.5*w, b+2.598*w}},
    };


    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group pieces = new Group();
    private final Group restpiece = new Group();

    private TextField textField;

    /* the difficulty slider */
    private final Slider difficulty = new Slider();
    public Label invalid = new Label("invalid pieces");
    public Label finished = new Label("You win!");
    public String s; //state
    public int rondom; //random
    private void newGame() {
        root.getChildren().remove(finished);

        int d = (int)difficulty.getValue()-1;

        switch (d){
            case (int) 0:
                rondom = 0 + (int) (Math.random() * (23 - 0));
                break;
            case (int) 1:
                rondom = 24 + (int) (Math.random() * (47 - 24));
                break;
            case 2:
                rondom = 48 + (int) (Math.random() * (71 - 48));
                break;
            case 3:
                rondom = 72 + (int) (Math.random() * (95 - 72));
                break;
            case 4:
                rondom = 96 + (int) (Math.random() * (119 - 96));
                break;
            default:
                rondom = 0;
                break;
        }
        s = Games.ALL_CHALLENGES[rondom];

        makeGameState(s);
    }
    private void restart() {
        s = Games.ALL_CHALLENGES[rondom];
        makeGameState(s);
        root.getChildren().remove(finished);
    }
    public String[] findpieceword(String gameStateString){
        // find w
        char[] gameStatechar = gameStateString.toCharArray();
        int number = 0;
        for (int i =0;i<gameStatechar.length;i++){
            if (gameStatechar[i]=='W'){
                number = i;
            }
        }
        ArrayList<String> allword = new ArrayList<String>();

        // all piece
        String[] piece_word = new String[number/4];
        int count = 0;
        for (int i = 0;i<number;i=i+4){
            piece_word[count] = gameStateString.substring(i,i+4);
            allword.add(gameStateString.substring(i,i+4));
            count++;
        }

        return piece_word;
    }
    public String[] findwizardword(String gameStateString){
        // find w
        char[] gameStatechar = gameStateString.toCharArray();
        int number = 0;
        for (int i =0;i<gameStatechar.length;i++){
            if (gameStatechar[i]=='W'){
                number = i;
            }
        }
        ArrayList<String> allword = new ArrayList<String>();

        // all wizard
        int index = gameStateString.length()-number-1;
        String[] wizard_word = new String[index/3];
        count = 0;
        for (int i = 0;i<index;i=i+3){
            wizard_word[count] = gameStateString.substring(number+1+i,number+4+i);
            allword.add(gameStateString.substring(number+1+i,number+4+i));
            count++;
        }

        return wizard_word;
    }
    public ArrayList<String> otherposition(String piece){
        char[] piecew = piece.toCharArray();
        boolean r0 = piece.contains("r0");
        boolean r1 = piece.contains("r1");
        boolean r2 = piece.contains("r2");
        boolean o0 = piece.contains("o0");
        boolean o1 = piece.contains("o1");
        boolean o2 = piece.contains("o2");
        boolean o3 = piece.contains("o3");
        boolean o4 = piece.contains("o4");
        boolean o5 = piece.contains("o5");
        boolean y0 = piece.contains("y0");
        boolean y1 = piece.contains("y1");
        boolean y2 = piece.contains("y2");
        boolean y3 = piece.contains("y3");
        boolean y4 = piece.contains("y4");
        boolean y5 = piece.contains("y5");
        boolean g0 = piece.contains("g0");
        boolean g1 = piece.contains("g1");
        boolean g2 = piece.contains("g2");
        boolean g3 = piece.contains("g3");
        boolean g4 = piece.contains("g4");
        boolean g5 = piece.contains("g5");
        boolean b0 = piece.contains("b0");
        boolean b1 = piece.contains("b1");
        boolean b2 = piece.contains("b2");
        boolean b3 = piece.contains("b3");
        boolean b4 = piece.contains("b4");
        boolean b5 = piece.contains("b5");
        boolean i0 = piece.contains("i0");
        boolean i1 = piece.contains("i1");
        boolean i2 = piece.contains("i2");
        boolean p0 = piece.contains("p0");
        boolean p1 = piece.contains("p1");
        boolean p2 = piece.contains("p2");
        boolean p3 = piece.contains("p3");
        boolean p4 = piece.contains("p4");
        boolean p5 = piece.contains("p5");

        int c = (int) piecew[2]-48;
        int r = (int) piecew[3]-48;

        int c_add1 = c+1;
        int r_add1 = r+1;
        int c_add2 = c+2;
        int r_add2 = r+2;
        int c_add3 = c+3;
        int r_add3 = r+3;
        int c_minus1 = c-1;
        int r_minus1 = r-1;
        int c_minus2 = c-2;
        int r_minus2 = r-2;
        int c_minus3 = c-3;
        int r_minus3 = r-3;
        String ca1 = String.valueOf(c_add1);
        String ra1 = String.valueOf(r_add1);
        String ca2 = String.valueOf(c_add2);
        String ra2 = String.valueOf(r_add2);
        String ca3 = String.valueOf(c_add3);
        String ra3 = String.valueOf(r_add3);
        String cm1 = String.valueOf(c_minus1);
        String rm1 = String.valueOf(r_minus1);
        String cm2 = String.valueOf(c_minus2);
        String rm2 = String.valueOf(r_minus2);
        String cm3 = String.valueOf(c_minus3);
        String rm3 = String.valueOf(r_minus3);

        ArrayList<String> other = new ArrayList<String>();

        if(r0){
            if(r==0||r==2){
                other.add(ca1 + r);
                other.add(c + ra1);
                other.add(ca1 + ra1);
            }
            if(r==1||r==3){
                other.add(ca1 + ra1);
                other.add(ca1 + r);
                other.add(ca2 + ra1);
            }

        }
        if(r1){
            if(r==0||r==2){
                other.add(c + ra1);
                other.add(c + ra2);
                other.add(cm1 + ra1);
            }
            if(r==1||r==3){
                other.add(c + ra2);
                other.add(c + ra1);
                other.add(ca1 + ra1);
            }
        }
        if(r2){
            if(r==0||r==2){
                other.add(c + ra1);
                other.add(ca1 + r);
                other.add(cm1 + ra1);
            }
            if(r==1||r==3){
                other.add(ca1 + r);
                other.add(c + ra1);
                other.add(ca1 + ra1);
            }
        }
        if(o0){
            if(r==0||r==2){
                other.add(ca1 + r);
                other.add(ca1 + ra1);
            }
            if(r==1||r==3){
                other.add(ca1 + r);
                other.add(ca2 + ra1);
            }
        }
        if(o1){
            if(r==0||r==2){
                other.add(c + ra1);
                other.add(c + ra2);
            }
            if(r==1||r==3){
                other.add(c + ra2);
                other.add(ca1 + ra1);
            }
        }
        if(o2){
            if(r==0||r==2){
                other.add(cm1 + ra1);
                other.add(cm2 + ra1);
            }
            if(r==1||r==3){
                other.add(c + ra1);
                other.add(cm1 + ra1);
            }
        }
        if(o3){
            if(r==0||r==2){
                other.add(c + ra1);
                other.add(ca1 + ra1);
            }
            if(r==1||r==3){
                other.add(ca1 + ra1);
                other.add(ca2 + ra1);
            }

        }
        if(o4){
            if(r==0||r==2){
                other.add(cm1 + ra1);
                other.add(c + ra2);
            }
            if(r==1||r==3){
                other.add(c + ra1);
                other.add(c + ra2);
            }
        }
        if(o5){
            if(r==0||r==2){
                other.add(ca1 + r);
                other.add(cm1 + ra1);
            }
            if(r==1||r==3){
                other.add(ca1 + r);
                other.add(c + ra1);
            }
        }
        if(y0){
            if(r==0||r==2){
                other.add(ca1 + r);
                other.add(ca2 + r);
                other.add(c + ra1);
            }
            if(r==1||r==3){
                other.add(ca1 + r);
                other.add(ca2 + r);
                other.add(ca1 + ra1);
            }
        }
        if(y1){
            if(r==0||r==2){
                other.add(c + ra1);
                other.add(cm1 + ra1);
                other.add(ca1 + ra2);
            }
            if(r==1||r==3){
                other.add(ca1 + ra1);
                other.add(ca1 + ra2);
                other.add(c + ra1);
            }
        }
        if(y2){
            if(r==0||r==2){
                other.add(ca1 + r);
                other.add(c + ra1);
                other.add(c + ra2);
            }
            if(r==1||r==3){
                other.add(ca1 + r);
                other.add(ca1 + ra1);
                other.add(c + ra2);
            }
        }
        if(y3){
            if(r==0||r==2){
                other.add(c + ra1);
                other.add(cm1 + ra1);
                other.add(cm2 + ra1);
            }
            if(r==1||r==3){
                other.add(ca1 + ra1);
                other.add(c + ra1);
                other.add(cm1 + ra1);
            }
        }
        if(y4){
            if(r==0||r==2){
                other.add(ca1 + ra1);
                other.add(ca1+ ra2);
                other.add(c + ra1);
            }
            if(r==1||r==3){
                other.add(ca2 + ra1);
                other.add(ca1 + ra2);
                other.add(ca1 + ra1);
            }
        }
        if(y5){
            if(r==0||r==2){
                other.add(cm1 + ra1);
                other.add(cm1 + ra2);
                other.add(c + ra2);
            }
            if(r==1||r==3){
                other.add(c + ra1);
                other.add(c + ra2);
                other.add(cm1 + ra2);
            }
        }
        if(g0){
            if(r==0||r==2){
                other.add(ca1 + r);
                other.add(ca1 + ra1);
                other.add(ca2 + ra2);
            }
            if(r==1||r==3){
                other.add(ca1 + r);
                other.add(ca2 + ra1);
                other.add(ca2 + ra2);
            }
        }
        if(g1){
            if(r==0||r==2){
                other.add(c + ra1);
                other.add(c + ra2);
                other.add(cm1 + ra3);
            }
            if(r==1||r==3){
                other.add(ca1 + ra1);
                other.add(c + ra2);
                other.add(c + ra3);
            }
        }
        if(g2){
            if(r==0||r==2){
                other.add(cm1 + ra1);
                other.add(cm2 + ra1);
                other.add(cm3 + ra1);
            }
            if(r==1||r==3){
                other.add(c + ra1);
                other.add(cm1 + ra1);
                other.add(cm2 + ra1);
            }
        }
        if(g3){
            if(r==0||r==2){
                other.add(c + ra1);
                other.add(ca1 + ra2);
                other.add(ca2 + ra2);
            }
            if(r==1||r==3){
                other.add(ca1 + ra1);
                other.add(ca1 + ra2);
                other.add(ca2 + ra2);
            }
        }
        if(g4){
            if(r==0||r==2){
                other.add(cm1 + ra1);
                other.add(cm1+ ra2);
                other.add(cm1 + ra3);
            }
            if(r==1||r==3){
                other.add(c + ra1);
                other.add(cm1 + ra2);
                other.add(c + ra3);
            }
        }
        if(g5){
            if(r==0||r==2){
                other.add(ca1 + r);
                other.add(ca2 + r);
                other.add(cm1 + ra1);
            }
            if(r==1||r==3){
                other.add(ca1 + r);
                other.add(ca2 + r);
                other.add(c + ra1);
            }
        }
        if(b0){
            if(r==0||r==2){
                other.add(ca1 + r);
                other.add(ca2 + r);
                other.add(ca1 + ra1);
            }
            if(r==1||r==3){
                other.add(ca1 + r);
                other.add(ca2 + r);
                other.add(ca2 + ra1);
            }
        }
        if(b1){
            if(r==0||r==2){
                other.add(c + ra1);
                other.add(ca1 + ra2);
                other.add(c + ra2);
            }
            if(r==1||r==3){
                other.add(ca1 + ra1);
                other.add(ca1 + ra2);
                other.add(c + ra2);
            }
        }
        if(b2){
            if(r==0||r==2){
                other.add(cm1 + ra1);
                other.add(cm1 + ra2);
                other.add(cm2 + ra1);
            }
            if(r==1||r==3){
                other.add(c + ra1);
                other.add(cm1 + ra2);
                other.add(cm1 + ra1);
            }
        }
        if(b3){
            if(r==0||r==2){
                other.add(ca1 + ra1);
                other.add(c + ra1);
                other.add(cm1 + ra1);
            }
            if(r==1||r==3){
                other.add(ca2 + ra1);
                other.add(ca1 + ra1);
                other.add(c + ra1);
            }
        }
        if(b4){
            if(r==0||r==2){
                other.add(ca1 + r);
                other.add(c + ra1);
                other.add(ca1 + ra2);
            }
            if(r==1||r==3){
                other.add(ca1 + r);
                other.add(ca1 + ra1);
                other.add(ca1 + ra2);
            }
        }
        if(b5){
            if(r==0||r==2){
                other.add(c + ra1);
                other.add(cm1 + ra1);
                other.add(cm1 + ra2);
            }
            if(r==1||r==3){
                other.add(ca1 + ra1);
                other.add(c + ra1);
                other.add(cm1 + ra2);
            }
        }
        if(i0){
            if(r==0||r==2){
                other.add(ca1 + r);
                other.add(ca2 + r);
            }
            if(r==1||r==3){
                other.add(ca1 + r);
                other.add(ca2 + r);
            }
        }
        if(i1){
            if(r==0||r==2){
                other.add(c + ra1);
                other.add(ca1 + ra2);
            }
            if(r==1||r==3){
                other.add(ca1 + ra1);
                other.add(ca1 + ra2);
            }
        }
        if(i2){
            if(r==0||r==2){
                other.add(cm1 + ra1);
                other.add(cm1 + ra2);
            }
            if(r==1||r==3){
                other.add(c + ra1);
                other.add(cm1 + ra2);
            }
        }
        if(p0){
            if(r==0||r==2){
                other.add(ca1 + r);
                other.add(ca1 + ra1);
                other.add(ca1 + ra2);
            }
            if(r==1||r==3){
                other.add(ca1 + r);
                other.add(ca2 + ra1);
                other.add(ca1 + ra2);
            }
        }
        if(p1){
            if(r==0||r==2){
                other.add(c + ra1);
                other.add(c + ra2);
                other.add(cm1 + ra2);
            }
            if(r==1||r==3){
                other.add(ca1 + ra1);
                other.add(c + ra2);
                other.add(cm1 + ra2);
            }
        }
        if(p2){
            if(r==0||r==2){
                other.add(c + ra1);
                other.add(ca1 + ra1);
                other.add(ca2 + r);
            }
            if(r==1||r==3){
                other.add(ca1 + ra1);
                other.add(ca2 + ra1);
                other.add(ca2 + r);
            }
        }
        if(p3){
            if(r==0||r==2){
                other.add(cm1 + ra1);
                other.add(c + ra2);
                other.add(ca1 + ra2);
            }
            if(r==1||r==3){
                other.add(c + ra1);
                other.add(c + ra2);
                other.add(ca1 + ra2);
            }
        }
        if(p4){
            if(r==0||r==2){
                other.add(ca1 + r);
                other.add(cm1+ ra1);
                other.add(c + ra2);
            }
            if(r==1||r==3){
                other.add(ca1 + r);
                other.add(c + ra1);
                other.add(c + ra2);
            }
        }
        if(p5){
            if(r==0||r==2){
                other.add(ca1 + r);
                other.add(ca1 + ra1);
                other.add(cm1 + ra1);
            }
            if(r==1||r==3){
                other.add(ca1 + r);
                other.add(ca2 + ra1);
                other.add(c + ra1);
            }
        }
        return other;
    }
    ArrayList<String> getState(String gameStateString) {
    pieces.getChildren().clear();
    System.out.println(gameStateString);
    if(!IQStars.isGameStateValid(gameStateString)) {
        invalid.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        invalid.setTextFill(Color.color(1, 0, 0));
        invalid.setLayoutX(BOARD_WIDTH/2-180);
        invalid.setLayoutY(BOARD_HEIGHT/2+130);
        pieces.getChildren().add(invalid);
    }

    // find w
    char[] gameStatechar = gameStateString.toCharArray();
    int number = 0;
    for (int i =0;i<gameStatechar.length;i++){
        if (gameStatechar[i]=='W'){
            number = i;
        }
    }
    ArrayList<String> allword = new ArrayList<String>();

    // all piece
    String[] piece_word = new String[number/4];
    int count = 0;
    for (int i = 0;i<number;i=i+4){
        piece_word[count] = gameStateString.substring(i,i+4);
        allword.add(gameStateString.substring(i,i+4));
        count++;
    }

    // all wizard
    char[] words = gameStateString.toCharArray();
    int index = gameStateString.length()-number-1;
    String[] wizard_word = new String[index/3];
    count = 0;
    for (int i = 0;i<index;i=i+3){
        wizard_word[count] = gameStateString.substring(number+1+i,number+4+i);
        allword.add(gameStateString.substring(number+1+i,number+4+i));
        count++;
    }
    return allword;

}
    ArrayList<String> makeGameState(String gameStateString) {

        pieces.getChildren().clear();
        System.out.println(gameStateString);
        if(!IQStars.StateValid(gameStateString)) {
            invalid.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
            invalid.setTextFill(Color.color(1, 0, 0));
            invalid.setLayoutX(BOARD_WIDTH/2-180);
            invalid.setLayoutY(BOARD_HEIGHT/2+130);
            pieces.getChildren().add(invalid);
        }
        String[] piece_word = findpieceword(gameStateString);
        String[] wizard_word = findwizardword(gameStateString);


        ArrayList<String> allposition = new ArrayList<String>();
        for(String piece:piece_word) {
            ArrayList<String> all = new ArrayList<String>();
            ArrayList<String> other = new ArrayList<String>();
            other = otherposition(piece);

            all.add(piece.substring(2));
            all.addAll(other);

            allposition.add(piece.substring(2));
            allposition.addAll(other);
            double[][] allarray= new double[all.size()][2];
            // put int i=0 front of for;
            int i=0;
            for(String p:all){

                // p.charAt(0) '2'
                allarray[i][0]=p.charAt(0)-48;
                allarray[i][1]=p.charAt(1)-48;
                i++;
            }
            double x,y;
            Image image = new Image("file:assets/"+piece.substring(0, 1)+".png");


            for(double[][] d:ns){
                for(double[] e:allarray){
                    if(Arrays.equals(d[0], e)){
                        x = d[1][0];
                        y = d[1][1];
                        ImageView iv = new ImageView();
                        iv.setImage(image);
                        iv.setFitWidth(w);
                        iv.setPreserveRatio(true);
                        iv.setSmooth(true);
                        iv.setCache(true);
                        iv.setLayoutX(x);
                        iv.setLayoutY(y);
                        pieces.getChildren().add(iv);
                    }
                }
            }
            System.out.println("makeGameState-piece word check");
        }

        for(String wpiece:wizard_word){
            double[] warray= new double[2];
            warray[0] = wpiece.charAt(1)-48;
            warray[1] = wpiece.charAt(2)-48;
            allposition.add(wpiece.substring(1));
            double x,y;
            Image image = new Image("file:assets/"+wpiece.substring(0, 1)+"w.png");
            for(double[][] d:ns){
                if(Arrays.equals(d[0], warray)){
                    x = d[1][0];
                    y = d[1][1];
                    ImageView iv = new ImageView();
                    iv.setImage(image);
                    iv.setFitWidth(w);
                    iv.setPreserveRatio(true);
                    iv.setSmooth(true);
                    iv.setCache(true);
                    iv.setLayoutX(x);
                    iv.setLayoutY(y);
                    pieces.getChildren().add(iv);
                }
            }
        }

        return null;


    }

    private void makeControls() {
        Label l = new Label("Game State:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button b = new Button("Refresh");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makeGameState(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(l, textField, b);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(BOARD_HEIGHT - 100);
        controls.getChildren().add(hb);

        Button button = new Button("Restart");
        button.setLayoutX(BOARD_X + BOARD_MARGIN + 370);
        button.setLayoutY(BOARD_HEIGHT - 55);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent w) {
                restart();
            }
        });
        Button button2 = new Button("New Game");
        button2.setLayoutX(BOARD_X + BOARD_MARGIN + 270);
        button2.setLayoutY(BOARD_HEIGHT - 55);
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                newGame();
            }
        });

        controls.getChildren().add(button);
        controls.getChildren().add(button2);

        difficulty.setMin(1);
        difficulty.setMax(5);
        difficulty.setValue(0);
        difficulty.setShowTickLabels(true);
        difficulty.setShowTickMarks(true);
        difficulty.setMajorTickUnit(1);
        difficulty.setMinorTickCount(0);
        difficulty.setSnapToTicks(true);

        difficulty.setLayoutX(BOARD_X + BOARD_MARGIN + 120);
        difficulty.setLayoutY(BOARD_HEIGHT - 50);
        controls.getChildren().add(difficulty);

        final Label difficultyCaption = new Label("Difficulty:");
        difficultyCaption.setTextFill(Color.GREY);
        difficultyCaption.setLayoutX(BOARD_X + BOARD_MARGIN + 50);
        difficultyCaption.setLayoutY(BOARD_HEIGHT - 50);
        controls.getChildren().add(difficultyCaption);
    }

    public int count=0;
    private void setUpHandlers(Scene scene) {
        /* create handlers for key press and release events */
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SLASH) {
                hints();
                count++;
            }
        });
    }

    public void hints() {
        ArrayList<String> allposition = new ArrayList<String>();
        ArrayList<String> allsolutionposition = new ArrayList<String>();
        allposition = getState(s);
        allsolutionposition = getState(Games.ALL_CHALLENGES_SOLUTIONS[rondom]);

        List<String> possiblepiece = new ArrayList<>();


        for(String sol: allsolutionposition){
            if(!allposition.contains(sol)){
                possiblepiece.add(sol);
            }
        }
        System.out.println(possiblepiece);

        if(possiblepiece.size()>0){
            if(possiblepiece.get(0).length()==4){
                s = possiblepiece.get(0) + s;}
            if(possiblepiece.get(0).length()==3){
                s = s + possiblepiece.get(0);}
            makeGameState(s);
            if(possiblepiece.size()==1){
                finished.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
                finished.setTextFill(Color.color(1, 0, 0));
                finished.setLayoutX(BOARD_WIDTH/2-180);
                finished.setLayoutY(BOARD_HEIGHT/2+130);
                root.getChildren().add(finished);
            }
        }
        else {
            finished.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
            finished.setTextFill(Color.color(1, 0, 0));
            finished.setLayoutX(BOARD_WIDTH/2-180);
            finished.setLayoutY(BOARD_HEIGHT/2+130);
            root.getChildren().add(finished);}
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IQ Stars");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        root.getChildren().add(controls);


        Image image = new Image("file:assets/blankBoardNumbered.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitWidth(720);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);
        root.getChildren().add(iv1);

        root.getChildren().add(restpiece);
        root.getChildren().add(pieces);


        setUpHandlers(scene);
        makeControls();


        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

}
