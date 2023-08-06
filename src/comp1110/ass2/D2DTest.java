package comp1110.ass2;

//test for allotherpieceposition(a helper method)
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class D2DTest {
    static final String[] TESTPIECE = {"y322", "g262", "o231"};
    static final String[] TESTSTRING = {"Wg11g32i00p43", "Wy50b30p10", "Wg22b81","Wy24",
                                          "r001o302y040g330b121i000p151Wr22o13b21","r250W","b220i111W","r250o011y322g262b100i010p340W"};
    @Test
    public void allotherpiecepositionTest() {

        List<String> sln = IQStars.allotherpieceposition(TESTPIECE[0]);
        assertTrue(sln.contains("23"), "with y322, the other postion did not contain 23");
        assertTrue(sln.contains("13"), "with y322, the other postion did not contain 13");
        assertTrue(sln.contains("03"), "with y322, the other postion did not contain 03");
        List<String> sln1 = IQStars.allotherpieceposition(TESTPIECE[1]);
        assertTrue(sln1.contains("53"), "with g262, the other postion did not contain 53");
        assertTrue(sln1.contains("43"), "with g262, the other postion did not contain 43");
        assertTrue(sln1.contains("33"), "with g262, the other postion did not contain 33");
        List<String> sln2 = IQStars.allotherpieceposition(TESTPIECE[2]);
        assertTrue(sln2.contains("32"), "with o231, the other postion did not contain 53");
        assertTrue(sln2.contains("22"), "with o231, the other postion did not contain 43");
    }
    @Test
    public void allstringvalueTest(){
        for(int i =0;i<TESTSTRING.length;i++){
            String[][] all = IQStars.statestring(TESTSTRING[i]);
            if (all==null){
                continue;
            }
            String[] piece =all[0];
            String[] wizard =all[1];
            char[] words = TESTSTRING[i].toCharArray();
            int lenth = 0;
            for (int z =0;z<words.length;z++){

                if (words[z]=='W'){
                    lenth = z;
                }
            }
            if (piece.length!=0){
                for (int j =0;j<piece.length;j++){
                    String act = piece[j];
                    String exp = (TESTSTRING[i].substring(j*4,(j+1)*4));
                    assertTrue(act.equals(exp),"with "+TESTSTRING[i]+", the string did not contain "+piece[j]);
                }
            }
            if (wizard!=null){
                for (int j =0;j<wizard.length;j++){
                    String act = wizard[j];
                    String exp = (TESTSTRING[i].substring(lenth+j*3+1,lenth+(j+1)*3+1));
                    assertTrue(act.equals(exp),"with "+TESTSTRING[i]+", the string did not contain "+wizard[j]);
                }
            }
        }
    }
}