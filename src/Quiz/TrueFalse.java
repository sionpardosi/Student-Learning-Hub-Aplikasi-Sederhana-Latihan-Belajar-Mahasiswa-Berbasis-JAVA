    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Quiz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @sionPardosi
 * 
 */

public class TrueFalse implements Soal {

    private String jawaban;
    private String soal;
    private String OptionA;
    private String OptionB;
    private String Id;

    public TrueFalse() {
    }

    public TrueFalse(String jawaban, String soal, String OptionA, String OptionB, String Id) {
        this.jawaban = jawaban;
        this.soal = soal;
        this.OptionA = OptionA;
        this.OptionB = OptionB;
        this.Id = Id;
    }

    public String getOptionA() {
        return OptionA;
    }

    public void setOptionA(String OptionA) {
        this.OptionA = OptionA;
    }

    public String getOptionB() {
        return OptionB;
    }

    public void setOptionB(String OptionB) {
        this.OptionB = OptionB;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    @Override
    public boolean checkJawaban(String input, String answer) {
        if (input.equalsIgnoreCase(answer)) return true;
        return false;
    }

    @Override
    public String getPath() {
        return dataPath;
    }

    public ArrayList<TrueFalse> showSoal() throws IOException {
        FileReader fileInput = null;
        BufferedReader bufferInput = null;

        ArrayList<TrueFalse> pilihanGanda = new ArrayList<TrueFalse>();

        try {
            fileInput = new FileReader(getPath());
            bufferInput = new BufferedReader(fileInput);
        } catch (FileNotFoundException err) {
            System.err.println("Database Tidak ditemukan");
        }

        String data = bufferInput.readLine();

        int nomorData = 1;

        while (data != null) {

            StringTokenizer stringToken = new StringTokenizer(data, ",");

            if (stringToken.nextToken().equalsIgnoreCase("truefalse")) {
                TrueFalse temp = new TrueFalse();
                temp.setSoal(stringToken.nextToken());
                temp.setOptionA(stringToken.nextToken());
                temp.setOptionB(stringToken.nextToken());
                temp.setJawaban(stringToken.nextToken());
                temp.setId(Integer.toString(nomorData));
                pilihanGanda.add(temp);
            }

            data = bufferInput.readLine();

            nomorData++;
        }

        fileInput.close();
        bufferInput.close();
        return pilihanGanda;
    }

}
