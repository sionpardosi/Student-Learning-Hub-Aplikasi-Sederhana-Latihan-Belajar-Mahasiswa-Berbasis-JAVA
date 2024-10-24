/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Quiz;

import java.io.*;

/**
 *
 * @sionPardosi
 * 
 */

public interface Soal {

    public static final String dataPath = "SOAL.txt";

    public boolean checkJawaban(String input, String answer) throws IOException;
    
    abstract String getPath();
    
}
