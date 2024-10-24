/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Quiz;

import com.data.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 *
 * @sionPardosi
 *
 */
public class Quiz {

    ArrayList<PilihanGanda> pilihanGanda;
    ArrayList<TrueFalse> trueFalse;
    String keywords;
    private static final String daftarNilaiPath = "nilai.txt";
    private int nilai = 0;

    public ArrayList<PilihanGanda> getPilihanGanda() {
        return pilihanGanda;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setPilihanGanda(ArrayList<PilihanGanda> pilihanGanda) {
        this.pilihanGanda = pilihanGanda;
    }

    public ArrayList<TrueFalse> getTrueFalse() {
        return trueFalse;
    }

    public void setTrueFalse(ArrayList<TrueFalse> trueFalse) {
        this.trueFalse = trueFalse;
    }

    public int getNilai() {
        return nilai;
    }

    public String getPath() {
        return daftarNilaiPath;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }

    public void KUNCIJAWABAN(ArrayList<Mahasiswa> log) throws IOException {
        PilihanGanda pilgan = new PilihanGanda();
        TrueFalse trufal = new TrueFalse();

        pilihanGanda = pilgan.showSoal();
        trueFalse = trufal.showSoal();

        System.out.println("\n==================== Kunci Jawaban Soal ===================");

        for (PilihanGanda barang : pilihanGanda) {
            System.out.println(barang.getId() + ". " + barang.getJawaban());
        }
        for (TrueFalse barang : trueFalse) {
            System.out.println(barang.getId() + ". " + barang.getJawaban());

        }
    }

    public void tampilkanSoal(ArrayList<Mahasiswa> log) throws IOException {

        PilihanGanda pilgan = new PilihanGanda();
        TrueFalse trufal = new TrueFalse();

        pilihanGanda = pilgan.showSoal();
        trueFalse = trufal.showSoal();

        float jumlahsoal = pilihanGanda.size() + trueFalse.size();
        float hasil;

        Scanner terminalInput = new Scanner(System.in);

        String jawaban = new String();

        System.out.println("\n==================== SELAMAT MENGERJAKAN KUIS/SOAL ====================");
        System.out.println("                 Kejujuran adalah Kunci Keberhasilan!            ");
        System.out.println("-----------------------------------------------------------------------");

        for (PilihanGanda barang : pilihanGanda) {
            System.out.println(barang.getId() + ". " + barang.getSoal());
            System.out.println("a) " + barang.getOptionA());
            System.out.println("b) " + barang.getOptionB());
            System.out.println("c) " + barang.getOptionC());
            System.out.println("d) " + barang.getOptionD());
            System.out.println("e) " + barang.getOptionE());
            System.out.println("\nMasukkan jawaban anda : ");
            jawaban = terminalInput.next();
            if (pilgan.checkJawaban(jawaban, barang.getJawaban())) {
                nilai += 1;
            };
            System.out.println(); // Menambahkan baris kosong setelah jawaban
        }

        System.out.println("                 Soal True/False            ");
        System.out.println("------------------------------------------------");
        for (TrueFalse barang : trueFalse) {
            System.out.println(barang.getId() + ". " + barang.getSoal());
            System.out.println("a) " + barang.getOptionA());
            System.out.println("b) " + barang.getOptionB());
            System.out.println("\nMasukkan jawaban anda : ");
            jawaban = terminalInput.next();
            if (trufal.checkJawaban(jawaban, barang.getJawaban())) {
                nilai += 1;
            };
            System.out.println(); // Menambahkan baris kosong setelah jawaban
        }

        hasil = (nilai * 100 / jumlahsoal);

        System.out.println("Nilai Anda => " + hasil);
        for (PilihanGanda barang : pilihanGanda) {
            System.out.println(barang.getId() + ". " + barang.getJawaban());
        }
        for (TrueFalse barang : trueFalse) {
            System.out.println(barang.getId() + ". " + barang.getJawaban());

        }
        simpanNilai(log, hasil);
    }

    public void simpanNilai(ArrayList<Mahasiswa> log, float hasil) throws IOException {

        FileWriter fileOutput = new FileWriter(this.getPath(), true);
        BufferedWriter bufferOutput = new BufferedWriter(fileOutput);

        boolean isTambah = yaAtauTidak("Apakah akan ingin menambah data tersebut? ");

        for (Mahasiswa a : log) {
            this.setKeywords(a.getNim());
        }

        if (isTambah) {
            for (Mahasiswa a : log) {
                bufferOutput.write(a.getNama() + "," + a.getNim() + "," + a.getJurusan() + "," + hasil);
            }
            bufferOutput.newLine();
            bufferOutput.flush();
        }

        this.tampilkanNilai();
    }

    public void penggunaan() throws IOException {
        System.out.println("");
    }

    public void tampilkanNilai() throws IOException {
        boolean isExist;

        FileReader fileInput;
        BufferedReader bufferInput;

        try {
            fileInput = new FileReader(this.getPath());
            bufferInput = new BufferedReader(fileInput);
        } catch (Exception e) {
            System.err.println("Database Tidak ditemukan");
            System.err.println("Silahkan tambah data terlebih dulu");
            return;
        }

        System.out.println("\n==================== Daftar Mahasiswa dan Nilai ===================");

        System.out.println("\n| No |\tNama      |\tNIM           |\tJurusan    | \tNilai    |");
        System.out.println("-------------------------------------------------------------------");

        String data = bufferInput.readLine();
        int nomorData = 0;
        while (data != null) {
            nomorData++;
            StringTokenizer stringToken = new StringTokenizer(data, ",");

            System.out.printf("| %2d ", nomorData);
            System.out.printf("|\t%-8s  ", stringToken.nextToken());
            System.out.printf("|\t%-11s   ", stringToken.nextToken());
            System.out.printf("|\t%-12s   ", stringToken.nextToken());
            System.out.printf("| %3s  ", stringToken.nextToken());
            System.out.print("\n");

            data = bufferInput.readLine();
        }
    }

    public void saran() throws IOException {
        System.out.println("\n=================== TENTANG APLIKASI LATIHAN BELAJAR =================\n");
        
        //Representasi lokasi file yang akan dibaca
        File file = new File("SARAN.txt");

        //Menggunakan try with resource statement
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            //Untuk mengambil baris data yang ada pada File
            String barisData;

            //Menampilkan semua baris data didalam file contohFile.txt
            while ((barisData = br.readLine()) != null) {
                System.out.println(barisData);
            }

            //Digunakan untuk menangani kesalahan jika terjadi error
        } catch (FileNotFoundException ex1) {

            //Menangani kesalahan jika file tersebut tidak ditemukan
            System.out.println("File tidak ditemukan " + file.toString());

        } catch (Exception ex2) {
            //Menangani kesalahan jika file tersebut tidak dapat dibaca
            System.out.println("File tidak dapat dibaca " + file.toString());

        }
    }

    public void komentar() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n==================== Usulan-Pendapat-Komentar-Saran ====================");

        System.out.println("masukkan : ");
        String komentar = input.nextLine();
        Writer writer = new FileWriter("komentar.txt", true);
        BufferedWriter output = new BufferedWriter(writer);
        /*mengirim data ke file */
        output.write(komentar + " \n");
        System.out.println("Berhasil menambahkan data ke file dan Mengirimkan ke Admin");

        output.close();

    }

    public static boolean yaAtauTidak(String messages) {
        Scanner userInput = new Scanner(System.in);
        System.out.print("\n" + messages + " (y/n)? ");
        String pilihanUser = userInput.next();

        while (!pilihanUser.equalsIgnoreCase("y") && !pilihanUser.equalsIgnoreCase("n")) {
            System.out.println("Pilihan tidak valid. Harap masukkan 'y'(lanjutkan) atau 'n'(tidak).");
            System.out.println("\n" + messages + " y/n? ");
            pilihanUser = userInput.next();
        }

        return pilihanUser.equalsIgnoreCase("y");
    }
}
