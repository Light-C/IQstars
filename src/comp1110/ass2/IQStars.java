package comp1110.ass2;

import java.lang.reflect.Array;
import java.util.*;

public class IQStars {

    /**
     * Determine whether a game string describing either a wizard or a piece
     * is well-formed according to the following criteria:
     * - It consists of exactly three or four characters
     * - If it consists of three characters, it is a well-formed wizard string:
     *      - the first character is a valid colour character (r,o,y,g,b,i,p)
     *      - the second character is a valid column number
     *          - (0 .. 6) if the row number is 0 or 2
     *          - (0 .. 5) otherwise
     *      - the third character is a valid row number (0 .. 3)
     * - If it consists of four characters, it is a well-formed piece string:
     *      - the first character is a valid colour character (r,o,y,g,b,i,p)
     *      - the second character is a valid rotation value
     *          - (0 .. 2) if the colour is r or i
     *          - (0 .. 5) otherwise
     *      - the third character is a valid column number
     *          - (0 .. 6) if the row number is 0 or 2
     *          - (0 .. 5) otherwise
     *      - the fourth character is a valid row number (0 .. 3)
     *      Jialiang Chen
     *      @param gameString A string describing either a piece or a wizard
     *      @return True if the string is well-formed
     */
    static boolean isGameStringWellFormed(String gameString) {
//        return false; // FIXME Task 3 (P): determine whether a wizard or piece string is well-formed

        char[] words = gameString.toCharArray();

        if (words.length == 3 || words.length == 4) {
        if (words.length == 3) {
            if (words[0] == 'r' || words[0] == 'o' || words[0] == 'y' || words[0] == 'g' || words[0] == 'b' || words[0] == 'i' || words[0] == 'p') {
                    int row = Character.getNumericValue(words[2]);
                    int column = Character.getNumericValue(words[1]);
                    int count =0;
                    if (row == 0 || row==1 || row ==2 || row==3 ){
                        if (row ==0 || row==2){
                            for (int i =0;i<=6;i++){
                                if (i==column){
                                    count++;
                                }
                            }
                            if (count!=1){
                                return false;
                            }
                        }else{
                            for (int i =0;i<=5;i++){
                                if (i==column){
                                    count++;
                                }
                            }
                            if (count==1){
                                return true;
                            }
                            else return false;
                        }
                    }else {
                        return false;
                    }
            } else {
                return false;
            }

        } else  {
            if (words[0] == 'r' || words[0] == 'o' || words[0] == 'y' || words[0] == 'g' || words[0] == 'b' || words[0] == 'i' || words[0] == 'p') {
                int row = Character.getNumericValue(words[3]);
                int column = Character.getNumericValue(words[2]);
                int rotation = Character.getNumericValue(words[1]);
                int count = 0;

                for (int i=0; i<=3;i++){
                    if (row==i){
                        count++;
                    }
                }


                if (words[0]=='r' || words[0]=='i'){
                    for (int i = 0;i<=2;i++){
                        if (i ==rotation){
                            count++;
                        }
                    }
                }else {
                    for (int i = 0;i<=5;i++){
                        if (i==rotation){
                            count++;
                        }
                    }
                }
                if (row==0 || row ==2){
                    for (int i =0;i<=6;i++){
                        if (column==i){
                            count++;
                        }
                    }

                }else {
                    for (int i =0;i<=5;i++){
                        if (column==i){
                            count++;
                        }
                    }
                }
                if (count!=3){
                    return false;
                }


            }else {
                return false;
            }
        }
        }else {
            return false;
        }
        return true;
    }

    /**
     * Determine whether a game state string is well-formed:
     * - The string is of the form [piecePlacement]W[wizardPlacement],
     *      where [piecePlacement] and [wizardPlacement] are replaced by the
     *      corresponding strings below
     * - [piecePlacement] string specification:
     *      - it consists of exactly n four-character piece strings (where n = 0 .. 7);
     *      - each piece string is well-formed
     *      - no piece appears more than once in the string
     *      - the pieces are ordered correctly within the string (r,o,y,g,b,i,p)
     * - [wizardPlacement] string specification:
     *      - it consists of exactly n three-character wizard strings (where n is some non-negative integer)
     *      - each wizard string is well-formed
     *      - the strings are ordered correctly within the string (r,o,y,g,b,i,p)
     *      - if there is more than one wizard of a single colour these wizards are ordered first by
     *          row and then by column in ascending order (note that this does not prevent two
     *          wizards of the same colour being placed in the same location - we will check for this
     *          in a later task).
     *          Jialiang Chen
     * @param gameStateString A string describing a game state
     * @return True if the game state string is well-formed
     */
    public static boolean isGameStateStringWellFormed(String gameStateString) {
//        return false; // FIXME Task 4 (P): determine whether a game state string is well-formed
        String[][] all =  statestring(gameStateString);
        if (all ==null){
            return false;
        }
        String[] piece_word = all[0];
        String[] wizard_word = all[1];

        char[] words = gameStateString.toCharArray();
        char[] order = {'r','o','y','g','b','i','p'};
        int number = 0; //usedToCutStrings

        int first = 0;//findTheFirstLetter
        int count = 0;//InedxAsIndex

        for (int i =0;i<gameStateString.length();i++){

            if (words[i]=='W'){
                number = i;
            }
        }

        // theInitialsOfAllPiecesAreNotRepeated
        for (int i =0;i< number;i = i+4){
            for (int j =0;j< number;j = j+4){
                if (j==i){
                    continue;
                }
                if (words[i]==words[j]){
                    return false;
                }
            }
        }


        char[] count_piece_string = new char[piece_word.length];
        count = 0;

        for (int i =0;i<number;i=i+4){
            count_piece_string[count] = words[i];
            count++;
        }
        count = 0;
        first = 0;
        if (count_piece_string.length!=0){
            for (int i =0;i<order.length;i++){
                if (order[i] == count_piece_string[0]){
                    first = i;
                }
            }
            for (int i =first;i<order.length;i++){
                for (int j =0;j<count_piece_string.length;j++){
                    if (count_piece_string[j]==order[i]){
                        count++;
                    }
                }
            }
        }
        if (count!=count_piece_string.length){
            return false;
        }
        return true;
    }



    /**
     * Determine whether a game state is valid.
     *
     * To be valid, the game state must satisfy the following requirements:
     * - string must be well-formed
     * - pieces must be entirely on the board
     * - pieces must not overlap each other
     * - wizards must be on the board
     * - each location may have at most one wizard
     * - each piece must cover all wizards of the same colour
     * - each piece must not cover any wizards of a different colour
     * Tianjian Yang
     * @param gameStateString A game state string
     * @return True if the game state represented by the string is valid
     */
    public static boolean isGameStateValid(String gameStateString) {
//        return false;  // FIXME Task 6 (D): determine whether a game state is valid

        if (!isGameStateStringWellFormed(gameStateString)){
//            System.out.println("f");
            return false;
        }
        char[] gameStatechar = gameStateString.toCharArray();
        int number = 0;
        for (int i =0;i<gameStatechar.length;i++){
            if (gameStatechar[i]=='W'){
                number = i;
            }
        }
        for (int i =4;i<number;i=i+4){
            String front = gameStateString.substring(i-3,i);
            String back = gameStateString.substring(i+1,i+4);
            if (front.equals(back)){
                return false;
            }
        }

        // all piece
        String[] piece_word = new String[number/4];
        int count = 0;
        for (int i = 0;i<number;i=i+4){
            piece_word[count] = gameStateString.substring(i,i+4);
            count++;
        }

        // all wizard
        char[] words = gameStateString.toCharArray();
        int index = gameStateString.length()-number-1;
        if (index%3!=0){
            return false;
        }
        String[] wizard_word = new String[index/3];
        count = 0;
        for (int i = 0;i<index;i=i+3){
            wizard_word[count] = gameStateString.substring(number+1+i,number+4+i);
            count++;
            if (isGameStringWellFormed(wizard_word[count-1])){
                continue;
            }else {
                return false;
            }
        }

        ArrayList<String> all = new ArrayList<String>();
        for(String piece:piece_word){
            ArrayList<String> other = new ArrayList<String>();

            // all piece positions
            other = allotherpieceposition(piece);

            all.add(piece.substring(2));
            all.addAll(other);
            // wizard covered
            for(String wizard: wizard_word){
                if(wizard.substring(0,1).equals(piece.substring(0,1))){
                    if(!other.contains(wizard.substring(1)) && !piece.substring(2).equals(wizard.substring(1)))return false;
                } else {
                    if(other.contains(wizard.substring(1)) || piece.substring(2).equals(wizard.substring(1)))return false;
                }
            }
            for(String i:other){
                String other_piece = piece.substring(0,2) + i;
                if(!isGameStringWellFormed(other_piece)){
                    return false;
                }
            }
        }
        // overlap
        // piece
        Set<String> set=new HashSet<String>(all);
        boolean  overlapresult = all.size() == set.size();
        if(!overlapresult) return false;

        // wizard
        ArrayList<String> wizard_position = new ArrayList<String>();
        for(String wizard: wizard_word){
            wizard_position.add(wizard.substring(1));
        }
        Set<String> set_wizard=new HashSet<String>(wizard_position);
        boolean  wizard_overlapresult= wizard_position.size() == set_wizard.size();
        if(!wizard_overlapresult) return false;


        return true;
    }


    /**
     * Given a string describing a game state, and a location
     * that must be covered by the next move, return a set of all
     * possible viable piece strings which cover the location.
     *
     * For a piece string to be viable it must:
     *  - be a well formed piece string
     *  - be a piece that is not already placed
     *  - not overlap a piece that is already placed
     *  - cover the given location
     *  - cover all wizards of the same colour
     *  - not cover any wizards of a different colour
     * Jialiang Chen
     * @param gameStateString A starting game state string
     * @param col      The location's column.
     * @param row      The location's row.
     * @return A set of all viable piece strings, or null if there are none.
     */
    public static Set<String> getViablePieceStrings(String gameStateString, int col, int row) {
//        return null;  // FIXME Task 7 (P): determine the set of all viable piece strings given an existing game state

        Set<String> all = new HashSet<>();//This collection is used to return all possible combinations
        //First, confirm whether the given string is entered indiscriminately
//        if (!isGameStateValid(gameStateString)){
//            return null;
//        }
        //Change the initial state of the input into a string
        String[][] allpiece = statestring(gameStateString);
        String[] piece_state = allpiece[0];
        String[] wizards_state = allpiece[1];

        boolean judge_wizard =true;

        //convertsTheMatchingPositionToAString
        String needloc = String.valueOf(col)+String.valueOf(row);

        ArrayList<String>[] allstate = get_state_board(gameStateString);
        ArrayList<String> empty_board = allstate[1];
        ArrayList<String> exist_board = allstate[0];
        char[] lack_colors = get_state_colors(gameStateString)[2];


        for (String nl:empty_board){
            for (char lc: lack_colors){
                if (lc=='r' || lc=='i'){
                    for (int i =0;i<3;i++){
                        String loc = String.valueOf(lc)+String.valueOf(i)+nl;
                        ArrayList<String> testloc = allotherpieceposition(loc);
                        testloc.add(nl);
                        if (!testloc.contains(needloc)){
                            continue;
                        }
                        judge_wizard =true;
                        if (wizards_state!=null){
                            for (String wizard :wizards_state){
                                String wloc = wizard.substring(1,3);
                                char wc = wizard.charAt(0);

                                if (wc ==lc){
                                    if (!testloc.contains(wloc)){
                                        judge_wizard=false;
                                    }
                                }else {
                                    if (testloc.contains(wloc)){
                                        judge_wizard=false;
                                    }
                                }
                            }

                        }

                        int count = 0;
                        for (String tloc:testloc){
                            //testWhetherThePiecesAreOnTheChessboard
                            String test_onboard = String.valueOf(lc)+tloc;
                            if (!isGameStringWellFormed(test_onboard)){
                                continue;
                            }
                            if (!exist_board.contains(tloc)){
                                count++;
                            }
                        }

                        if (count==testloc.size() && judge_wizard){
                            all.add(loc);
                        }
                    }


                }else {
                    for (int i =0; i<6;i++){
                        String loc = String.valueOf(lc)+String.valueOf(i)+nl;
                        ArrayList<String> testloc = allotherpieceposition(loc);
                        testloc.add(nl);
                        if (!testloc.contains(needloc)){
                            continue;
                        }
                        judge_wizard =true;
                        if (wizards_state!=null){
                            for (String wizard :wizards_state){
                                String wloc = wizard.substring(1,3);
                                char wc = wizard.charAt(0);

                                if (wc ==lc){
                                    if (!testloc.contains(wloc)){
                                        judge_wizard=false;
                                    }
                                }else {
                                    if (testloc.contains(wloc)){
                                        judge_wizard=false;
                                    }
                                }
                            }

                        }
                        int count = 0;


                        for (String tloc:testloc){
                            String test_onboard = String.valueOf(lc)+tloc;
                            if (!isGameStringWellFormed(test_onboard)){
                                continue;
                            }
                            if (!exist_board.contains(tloc) && judge_wizard){
                                count++;
                            }
                        }

                        if (count==testloc.size()){
                            all.add(loc);
                        }
                    }

                }
            }

        }

        if (all.size()==0){
            return null;
        }
        return all;
    }


    /**
     * Implement a solver for this game that can return the solution to a
     * particular challenge.
     *
     * This task is at the heart of the assignment and requires you to write
     * solver, similar to the boggle solver presented as part of the J14 lecture.
     *
     * NOTE: Simply looking up the provided answers does not constitute a general
     * solver.  Such an implementation is not a solution to this task, and
     * will not receive marks.
     *
     * @param challenge A game state string describing the starting game state.
     * @return A game state string describing the encoding of the solution to
     * the challenge.
     *
     *
     */
    public static String getSolution(String challenge) {
        char[][] color_state =get_state_colors(challenge);
//        缺少的颜色
        char[] lack_colors = color_state[2];
        ArrayList<String>[] all_board = get_state_board(challenge);
//        空的格子
        ArrayList<String> empty_board = all_board[1];

        for (String em : empty_board){
            int x = Integer.parseInt(em.substring(0,1));
            int y = Integer.parseInt(em.substring(0,1));
            Set<String> board= getViablePieceStrings(challenge,x,y);





        }





        return null;  // FIXME Task 10 (CR): determine the solution to the game, given a particular challenge
    }






    /**
     * This function is used to get the correct arrangement of the new string
     * Join one leader piece at a time.。
     * Jialiang Chen
     * @param challenge A string describing either a piece or a wizard
     * @return New string
     */
    public static String getnewchallenge(String challenge,String addloc){
        char addcolor = addloc.charAt(0);
        char[] colors = {'r','o','y','g','b','i','p'};
        Map dict = new HashMap();
        String w = "W";
        for (int i =1;i<challenge.length();i++){
            String judge = challenge.substring(i-1,i);
            if (judge.equals(w)){
                w =challenge.substring(i-1);
            }
        }

        for (int i= 0;i<colors.length;i++){
            dict.put(colors[i],i);
        }
        int index = -1;
        for (char c : colors){
            if (c==addcolor){
                index = (int)dict.get(c);
            }
        }
        String[] pieces = IQStars.statestring(challenge)[0];
        String sol = "";
        int count = 0;

        for (int i =0;i<pieces.length;i++){
            char cu = pieces[i].charAt(0);
            int cu_index =(int) dict.get(cu);
            if (cu_index>index && count == 0){
                sol = sol+addloc;
                count++;
            }
            sol = sol+pieces[i];
            if (i== pieces.length-1 && count == 0){
                sol = sol+addloc;
            }

        }
        sol =sol+w;
        return sol;
    }


    /**
     * This method is used to get the state of
     * all the chess grids on the current chessboard.
     * Input the current chessboard state will return a
     * list where list[0] represents the occupied grid
     * and list[1] represents the empty grid
     * Jialiang Chen
     * @param gameStateString A string describing either a piece or a wizard
     * @return a list containing the status of all chess
     */
    public static ArrayList<String> [] get_state_board(String gameStateString){
        //cutString
        String[][] allpiece = statestring(gameStateString);
        //CoordinatesOfLeader
        String[] piece_state = allpiece[0];
        String[] allboard = new String[26];
        int allboard_count =0;

        //get_the_position_of_all_known_pieces
        ArrayList<String> all_state= new ArrayList<>();

        for(String piece:piece_state) {
            //get_other_locations
            ArrayList<String> colour_state = allotherpieceposition(piece);
            //join_your_own_location
            all_state.add(piece.substring(2,4));
            //join_other_locations
            all_state.addAll(colour_state);
        }

        //empty_chessboard
        for (int j =0;j<4;j++){
            for (int i =0;i<7;i++){
                String board = String.valueOf(i)+String.valueOf(j);
                if (board.equals("61") || board.equals("63")){
                    continue;
                }else {
                    allboard[allboard_count]=board;
                    allboard_count++;
                }
            }
        }
        //find_the_empty_grid
        ArrayList<String> empty_board = new ArrayList<>();
        for (String board : allboard){
            if (!all_state.contains(board)){
                empty_board.add(board);
            }
        }

        ArrayList<String> [] get_state = new ArrayList[2];

        get_state[0] = all_state;
        get_state[1] = empty_board;
        return get_state;
    }

    /**
     * This method is used to get the state of the chessboard color.
     * When inputting the chessboard state, char [] [] will be returned,
     * where char [0] is all the colors of the chessboard,
     * char [1] is the existing color of the chessboard,
     * Char [2] is the missing color of the chessboard
     * Jialiang Chen
     * @ param gamestatestring a string describing either a piece or a wizard
     * @ return a list containing the states of all the chessboard colors
     */
    public  static  char[][] get_state_colors(String gameStateString){
        //cutString
        String[][] allpiece = statestring(gameStateString);
        //coordinatesOfLeader
        String[] piece_state = allpiece[0];

        char[] colors = {'r','o','y','g','b','i','p'};
        char[] exist_colors = new char[piece_state.length];
        int count_color = 0;
        char[] lack_colors = new char[7- piece_state.length];
        for (String c: piece_state) {
            char[] piece_char = c.toCharArray();
            exist_colors[count_color]=piece_char[0];
            count_color++;
        }
        //getMissingColor
        count_color =0;
        for (char c :colors){
            int count=0;
            for (char ec :exist_colors){
                if (ec ==c){
                    count ++;
                }
            }
            if (count==0){
                lack_colors[count_color]=c;
                count_color++;
            }
        }

        char[][] state_color = new char[3][];
        state_color[0] = colors;
        state_color[1] = exist_colors;
        state_color[2] = lack_colors;
        return state_color;
    }


    /**
     * Judge whether the game state string meets the requirements.
     * 1. The form is correct.
     * 2. On the board.
     * 3. There is at most one ordinary chess piece or wizard chess piece in a position.
     * 4. Wizard chess pieces can only be covered by ordinary chess pieces of the same color
     * Tianjian Yang
     * @ param gamestatestring a string describing either a piece or a wizard
     * @ return true if the above requirements are met
     */

    public static boolean StateValid(String gameStateString) {
        char[] gameStatechar = gameStateString.toCharArray();
        int number = 0;
        for (int i =0;i<gameStatechar.length;i++){
            if (gameStatechar[i]=='W'){
                number = i;
            }
        }
        for (int i =4;i<number;i=i+4){
            String front = gameStateString.substring(i-3,i);
            String back = gameStateString.substring(i+1,i+4);
            if (front.equals(back)){
                return false;
            }
        }

        // all piece
        String[] piece_word = new String[number/4];
        int count = 0;
        for (int i = 0;i<number;i=i+4){
            piece_word[count] = gameStateString.substring(i,i+4);
            count++;
        }

        // all wizard
        char[] words = gameStateString.toCharArray();
        int index = gameStateString.length()-number-1;
        if (index%3!=0){
            return false;
        }
        String[] wizard_word = new String[index/3];
        count = 0;
        for (int i = 0;i<index;i=i+3){
            wizard_word[count] = gameStateString.substring(number+1+i,number+4+i);
            count++;
            if (isGameStringWellFormed(wizard_word[count-1])){
                continue;
            }else {
                return false;
            }
        }

        ArrayList<String> all = new ArrayList<String>();
        for(String piece:piece_word){
            ArrayList<String> other = new ArrayList<String>();

            // all piece positions
            other = allotherpieceposition(piece);

            all.add(piece.substring(2));
            all.addAll(other);
            // wizard covered
            for(String wizard: wizard_word){
                if(wizard.substring(0,1).equals(piece.substring(0,1))){
                    if(!other.contains(wizard.substring(1)) && !piece.substring(2).equals(wizard.substring(1)))return false;
                } else {
                    if(other.contains(wizard.substring(1)) || piece.substring(2).equals(wizard.substring(1)))return false;
                }
            }
            for(String i:other){
                String other_piece = piece.substring(0,2) + i;
                if(!isGameStringWellFormed(other_piece)){
                    return false;
                }
            }
        }
        // overlap
        // piece
        Set<String> set=new HashSet<String>(all);
        boolean  overlapresult = all.size() == set.size();
        if(!overlapresult) return false;

        // wizard
        ArrayList<String> wizard_position = new ArrayList<String>();
        for(String wizard: wizard_word){
            wizard_position.add(wizard.substring(1));
        }
        Set<String> set_wizard=new HashSet<String>(wizard_position);
        boolean  wizard_overlapresult= wizard_position.size() == set_wizard.size();
        if(!wizard_overlapresult) return false;



        char[] count_wizard_string = new char[wizard_word.length];
        count = 0;

        for (int i =0;i<index;i=i+3){
            // count_wizard_string 所有wizard的首字母
            count_wizard_string[count] = words[number+1+i];
            count++;
        }

        return true;
    }

    /**
     * This method is used to split the leading pieces in the chessboard state.
     * Divide the whole string according to 4 or 3 strings and return a string [2] [],
     * where string [0] is the string of all the leading pieces and string [1]
     * is all the required wizard positions
     * Jialiang Chen
     * @ param gamestatestring a string describing either a piece or a wizard
     * @ return a string [] [] Contains the cut string
     */
    public static String[][] statestring(String gameStateString){
        char[] words = gameStateString.toCharArray();
        int number = 0; //UsedToCutStrings
        int first = 0;//FindTheFirstLetter
        int count = 0;//InedxAsIndex
        char[] order = {'r','o','y','g','b','i','p'};
        String[] piece_word;
        String[] wizard_word= null ;
        String[][] all1 = new String[2][];


        for (int i =0;i<gameStateString.length();i++){
            if (words[i]=='W'){
                number = i;
            }
        }
        if (number%4!=0){
            return null;
        }
        if (number/4>8){
            return null;
        }
        piece_word = new String[number/4];

        for (int i = 0;i<number;i=i+4){
            piece_word[count] = gameStateString.substring(i,i+4);
            count++;
            if (isGameStringWellFormed(piece_word[count-1])){
                continue;
            }else {
                return null;
            }
        }
//3 character
        if (gameStateString.length()-(number+1)!=0){
            int index = gameStateString.length()-number-1;
            if (index%3!=0){
                return null;
            }
            wizard_word = new String[index/3];
            count = 0;
            for (int i = 0;i<index;i=i+3){
                wizard_word[count] = gameStateString.substring(number+1+i,number+4+i);
                count++;
                if (isGameStringWellFormed(wizard_word[count-1])){
                    continue;
                }else {
                    return null;
                }
            }

            char[] count_wizard_char = new char[wizard_word.length];
            count = 0;

            for (int i =0;i<index;i=i+3){
                // count_Wizard_StringInitialsOfAllWizards
                count_wizard_char[count] = words[number+1+i];
                count++;
            }
            count = 0;
            for (int i =0;i<order.length;i++){
                if (order[i] == count_wizard_char[0]){
                    first = i;
                }
            }
            // TraverseOrderFromTheInitialLetterOfWizard
            for (int i =first;i<order.length;i++){
                // TraverseAllTheInitialsOfTheWizards
                for (int j =0;j<count_wizard_char.length;j++){
                    // Record all the initials of all wizards in order of order, and record
                    if (count_wizard_char[j]==order[i]){
                        count++;
                    }
                }
            }
            // All wizard initials follow the first wizard initials
            if (count!=count_wizard_char.length){
                return null;
            }
            // wizardString
            String wizard = gameStateString.substring(number+1,gameStateString.length());

            char[] wizard_char =wizard.toCharArray();

            for (int i =3;i<wizard_char.length;i=i+3){
                // theInitialsOfTwoAdjacentWizardsAreEqual
                if (wizard_char[i-3]==wizard_char[i]){
                    // simultaneousRowEquality
                    if (wizard_char[i-1] ==wizard_char[i+2]){
                        int front = Integer.parseInt(String.valueOf(wizard_char[i-2]));
                        int back = Integer.parseInt(String.valueOf(wizard_char[i+1]));
                        // False if the preceding column is larger than the following column
                        if (front>back){
                            return null;
                        }
                    }else {
                        int front = Integer.parseInt(String.valueOf(wizard_char[i-1]));
                        int back = Integer.parseInt(String.valueOf(wizard_char[i+2]));
                        if (front>back){
                            return null;
                        }
                    }
                }
            }
        }

        all1[0] = piece_word;
        all1[1] = wizard_word;

        return all1;
    }


    /**
     * It is used to find the leading chess piece and
     * input the remaining chess pieces into the leading
     * chess piece. It returns the position of other associated
     * chess pieces except the leading chess piece
     * Tianjian Yang
     * @ param piece the position of the leading chess piece
     * @ return the position of other associated chess pieces
     */
    static ArrayList<String> allotherpieceposition(String piece) {
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

}
