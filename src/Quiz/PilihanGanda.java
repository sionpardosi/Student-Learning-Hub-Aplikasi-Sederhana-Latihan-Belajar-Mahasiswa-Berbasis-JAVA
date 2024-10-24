/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Quiz;

import java.io.*;
import java.util.ArrayList;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @sionPardosi
 * 
 */

public class PilihanGanda implements Soal {

    private String jawaban;
    private String Id;
    private String OptionA;
    private String OptionB;
    private String OptionC;
    private String OptionD;
    private String OptionE;
    private String Soal;

    public PilihanGanda() {
    }

    public PilihanGanda(String jawaban, String Id,String OptionA, String OptionB, String OptionC, String OptionD, String OptionE, String Soal) {
        this.jawaban = jawaban;
        this.Id = Id;
        this.OptionA = OptionA;
        this.OptionB = OptionB;
        this.OptionC = OptionC;
        this.OptionD = OptionD;
        this.OptionD = OptionE;
        this.Soal = Soal;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getSoal() {
        return Soal;
    }

    public void setSoal(String Soal) {
        this.Soal = Soal;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
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

    public String getOptionC() {
        return OptionC;
    }

    public void setOptionC(String OptionC) {
        this.OptionC = OptionC;
    }

    public String getOptionD() {
        return OptionD;
    }

    public void setOptionD(String OptionD) {
        this.OptionD = OptionD;
    }

    public String getOptionE() {
        return OptionE;
    }

    public void setOptionE(String OptionE) {
        this.OptionE = OptionE;
    }

    @Override
    public boolean checkJawaban(String input, String answer) throws IOException{
        if(input.equalsIgnoreCase(answer)) return true;
        return false;
    }


    @Override
    public String getPath() {
        return dataPath; //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<PilihanGanda> showSoal() throws IOException {
        FileReader fileInput = new FileReader(getPath());
        BufferedReader bufferInput = new BufferedReader(fileInput);

        ArrayList<PilihanGanda> pilihanGanda = new ArrayList<PilihanGanda>();

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

            if (stringToken.nextToken().equalsIgnoreCase("pilgan")) {
                PilihanGanda temp = new PilihanGanda();
                temp.setSoal(stringToken.nextToken());
                temp.setOptionA(stringToken.nextToken());
                temp.setOptionB(stringToken.nextToken());
                temp.setOptionC(stringToken.nextToken());
                temp.setOptionD(stringToken.nextToken());
                temp.setOptionE(stringToken.nextToken());
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
