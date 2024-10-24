/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

import Quiz.PilihanGanda;
import Quiz.TrueFalse;
import static com.data.Mahasiswa.yaAtauTidak;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @sionPardosi
 *
 */
public class Admin extends User implements Auth {

    ArrayList<PilihanGanda> pilihanGanda;
    ArrayList<TrueFalse> trueFalse;

    public Admin() {
    }

    public ArrayList<Admin> loginAdmin() throws IOException {
        try {
            File file = new File("Mahasiswa.txt");
        } catch (Exception e) {
            System.out.println("Database tidak ditemukan");
            System.out.println("Silahkan register terlebih dahulu!");
            System.out.println(e);
        }

        Scanner userInput = new Scanner(System.in);

        String username;
        String password;

        System.out.print("Masukkan Nama Pengguna: ");
        username = userInput.next();
        System.out.print("Masukkan Kata Sandi: ");
        password = userInput.next();

        ArrayList<Admin> isExist = cari(username, password);

        return isExist;
    }

    public ArrayList<Admin> cari(String username, String password) throws IOException {
        FileReader fileInput = new FileReader("Admin.txt");
        BufferedReader bufferInput = new BufferedReader(fileInput);

        String data = bufferInput.readLine();
        boolean isExist = false;
        ArrayList<Admin> bio = new ArrayList<Admin>();

        while (data != null) {

            StringTokenizer stringToken = new StringTokenizer(data, ",");

            if (stringToken.nextToken().equalsIgnoreCase(username) && stringToken.nextToken().equalsIgnoreCase(password)) {
                Admin adm = new Admin();
                JenisKelamin jk;

                adm.setNama(stringToken.nextToken());
                jk = JenisKelamin.valueOf(stringToken.nextToken());
                adm.setJenisKelamin(jk);

                bio.add(adm);
            }

            data = bufferInput.readLine();
        }

        return bio;
    }

    public boolean login() throws IOException {
        try {
            File file = new File("Admin.txt");
        } catch (Exception e) {
            System.out.println("Database tidak ditemukan");
            System.out.println("Silahkan register terlebih dahulu!");
            System.out.println(e);
            return false;
        }

        Scanner userInput = new Scanner(System.in);

        String username;
        String password;

        System.out.print("Masukkan Nama Pengguna: ");
        username = userInput.next();
        System.out.print("Masukkan Kata Sandi: ");
        password = userInput.next();

        boolean isExist = cariDiDatabase(username, password);

        return isExist;
    }

    public static boolean cariDiDatabase(String username, String password) throws IOException {

        FileReader fileInput = new FileReader("Admin.txt");
        BufferedReader bufferInput = new BufferedReader(fileInput);

        String data = bufferInput.readLine();
        boolean isExist = false;

        while (data != null) {

            StringTokenizer stringToken = new StringTokenizer(data, ",");

            stringToken.nextToken();
            stringToken.nextToken();

            if (stringToken.nextToken().equalsIgnoreCase(username) && stringToken.nextToken().equalsIgnoreCase(password)) {
                isExist = true;
            }

            data = bufferInput.readLine();
        }

        return isExist;
    }

    @Override
    public void register() throws IOException {
        FileWriter fileOutput = new FileWriter("Admin.txt", true);
        BufferedWriter bufferOutput = new BufferedWriter(fileOutput);

        Admin adm = new Admin();
        Scanner userInput = new Scanner(System.in);
        int pilihan;

        System.out.print("Masukkan Nama Lengkap: ");
        adm.setNama(userInput.nextLine());
        System.out.println("Jenis Kelamin: ");
        System.out.println("1. " + JenisKelamin.LAKILAKI);
        System.out.println("2. " + JenisKelamin.PEREMPUAN);
        System.out.print("Masukkan kode Jenis Kelamin: ");
        pilihan = userInput.nextInt();

        while (pilihan != 1 && pilihan != 2) {
            System.out.println("Kode jenis kelamin salah!");
            System.out.println("1. " + JenisKelamin.LAKILAKI);
            System.out.println("2. " + JenisKelamin.PEREMPUAN);
            System.out.print("Masukkan kode Jenis Kelamin: ");
            pilihan = userInput.nextInt();
        }

        switch (pilihan) {
            case 1:
                adm.setJenisKelamin(JenisKelamin.LAKILAKI);
                break;
            case 2:
                adm.setJenisKelamin(JenisKelamin.PEREMPUAN);
                break;
        }

        System.out.print("Masukkan Nama Pengguna: ");
        adm.setUsername(userInput.next());
        System.out.print("Masukkan Kata Sandi: ");
        adm.setPassword(userInput.next());

        System.out.println("Nama: " + adm.getNama());
        System.out.println("Jenis Kelamin " + adm.getJenisKelamin());
        System.out.println("Nama Pengguna: " + adm.getUsername());
        System.out.println("Kata Sandi: " + adm.getPassword());

        boolean isRegister = yaAtauTidak("Apakah anda yakin ingin menambah data tersebut");

        if (isRegister) {
            bufferOutput.write(adm.getUsername() + "," + adm.getPassword() + "," + adm.getNama() + "," + adm.getJenisKelamin());
            bufferOutput.newLine();
            bufferOutput.flush();
        }

        bufferOutput.close();
    }

    @Override
    public boolean logout() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void tambahSoal() throws IOException {
        FileWriter fileOutput = new FileWriter("Soal.txt", true);
        BufferedWriter bufferedOutput = new BufferedWriter(fileOutput);
        Scanner userInput = new Scanner(System.in);
        int pilihan;
        System.out.println("\n==================== MENU TAMBAH SOAL ===================");
        System.out.println("\nPilih Jenis soal");
        System.out.println("1. Pilihan Berganda");
        System.out.println("2. True or False");
        System.out.print("\nMasukkan pilihan: ");
        pilihan = userInput.nextInt();
        userInput.nextLine();
        String tipeSoal;
        boolean isTambah;

        switch (pilihan) {
            case 1:
                tipeSoal = "pilgan";
                PilihanGanda pg = new PilihanGanda();
                System.out.println("\n==================== SOAL PILIHAN BERGANDA ===================");
                System.out.print("Masukkan Soal: ");
                pg.setSoal(userInput.nextLine());
                System.out.print("Masukkan Option A: ");
                pg.setOptionA(userInput.nextLine());
                System.out.print("Masukkan Option B: ");
                pg.setOptionB(userInput.nextLine());
                System.out.print("Masukkan Option C: ");
                pg.setOptionC(userInput.nextLine());
                System.out.print("Masukkan Option D: ");
                pg.setOptionD(userInput.nextLine());
                System.out.print("Masukkan Option E: ");
                pg.setOptionE(userInput.nextLine());
                System.out.print("Masukkan Jawaban: ");
                pg.setJawaban(userInput.nextLine());

                isTambah = yaAtauTidak("Apakah anda yakin ingin memasukkan data tersebut");
                if (isTambah) {
                    bufferedOutput.write(tipeSoal + "," + pg.getSoal() + "," + pg.getOptionA() + "," + pg.getOptionB() + "," + pg.getOptionC() + "," + pg.getOptionD() + "," + pg.getOptionE() + "," + pg.getJawaban());
                    bufferedOutput.newLine();
                    bufferedOutput.flush();
                    bufferedOutput.close();
                }
                break;
            case 2:
                tipeSoal = "truefalse";
                TrueFalse tf = new TrueFalse();
                System.out.println("\n==================== SOAL TRUE/FALSE ===================");
                System.out.print("Masukkan soal: ");
                tf.setSoal(userInput.nextLine());
                System.out.print("Masukkan Jawaban ( A (True) / B (False) ): ");
                tf.setJawaban(userInput.nextLine());
                isTambah = yaAtauTidak("Apakah anda yakin ingin memasukkan data tersebut");
                if (isTambah) {
                    bufferedOutput.write(tipeSoal + "," + tf.getSoal() + "," + "True,False" + "," + tf.getJawaban());
                    bufferedOutput.newLine();
                    bufferedOutput.flush();
                    bufferedOutput.close();
                }
                break;

        }
    }   

    public void editSoal() throws IOException {
        // DB Original
        File database = new File("SOAL.txt");
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferInput = new BufferedReader(fileInput);

        // DB Sementara
        File tempDB = new File("tempDB.txt");
        FileWriter fileOutput = new FileWriter(tempDB);
        BufferedWriter bufferOutput = new BufferedWriter(fileOutput);

        // tampilkan soal
//        tampilkanSoal();
        Scanner userInput = new Scanner(System.in);
        System.out.println("\n==================== MENU UBAH SOAL ===================");
        System.out.println("Masukkan Nomor soal yang akan di Ubah : ");
        int updateNum = userInput.nextInt();
        userInput.nextLine();

        // menampilkan data yang ingin diupdate
        String data = bufferInput.readLine();
        int entryCounts = 0;

        while (data != null) {
            entryCounts++;

            StringTokenizer st = new StringTokenizer(data, ",");

            if (updateNum == entryCounts) {
                System.out.println("Soal yang ingin anda update adalah: ");
                String jenisSoal = st.nextToken();
                if (jenisSoal.equalsIgnoreCase("pilgan")) {
                    System.out.println("Soal: ");
                    System.out.println(st.nextToken());
                    System.out.println("Option A: ");
                    System.out.println(st.nextToken());
                    System.out.println("Option B: ");
                    System.out.println(st.nextToken());
                    System.out.println("Option C: ");
                    System.out.println(st.nextToken());
                    System.out.println("Option D: ");
                    System.out.println(st.nextToken());
                    System.out.println("Option E: ");
                    System.out.println(st.nextToken());
                    System.out.println("Jawaban: ");
                    System.out.println(st.nextToken());

                    String[] fieldData = {"Soal", "Option A", "Option B", "Option C", "Option D", "Option E", "Jawaban"};
                    String[] tempData = new String[7];

                    // Refresh token
                    st = new StringTokenizer(data, ",");
                    String originalData = st.nextToken();

                    for (int i = 0; i < fieldData.length; i++) {
                        boolean isUpdate = yaAtauTidak("Apakah anda ingin merubah " + fieldData[i]);
                        originalData = st.nextToken();
                        if (isUpdate) {
                            System.out.print("Masukkan " + fieldData[i] + " baru: ");
                            tempData[i] = userInput.nextLine();
                        } else {
                            tempData[i] = originalData;
                        }
                    }

                    // tampilkan data baru ke layar 
                    System.out.println("\nData baru anda adalah ");
                    System.out.println("---------------------------------------");
                    System.out.println("Soal               : " + tempData[0]);
                    System.out.println("Option A            : " + tempData[1]);
                    System.out.println("Option B            : " + tempData[2]);
                    System.out.println("Option C              : " + tempData[3]);
                    System.out.println("Option D              : " + tempData[4]);
                    System.out.println("Option E              : " + tempData[5]);
                    System.out.println("Jawaban              : " + tempData[6]);

                    boolean isUpdate = yaAtauTidak("Apakah anda yakin ingin mungubah data tersebut");

                    if (isUpdate) {
                        String soal = tempData[0];
                        String optionA = tempData[1];
                        String optionB = tempData[2];
                        String optionC = tempData[3];
                        String optionD = tempData[4];
                        String optionE = tempData[5];
                        String jawaban = tempData[6];

                        bufferOutput.write("pilgan" + "," + soal + "," + optionA + "," + optionB + "," + optionC + "," + optionD + "," + optionE + "," + jawaban);
                    } else {
                        bufferOutput.write(data);
                    }

                } else if (jenisSoal.equalsIgnoreCase("truefalse")) {
                    System.out.println("Soal: ");
                    System.out.println(st.nextToken());
                    System.out.println("Option A: ");
                    System.out.println(st.nextToken());
                    System.out.println("Option B: ");
                    System.out.println(st.nextToken());
                    System.out.println("Jawaban: ");
                    System.out.println(st.nextToken());

                    String[] fieldData = {"Soal", "Option A", "Option B", "Jawaban"};
                    String[] tempData = new String[4];

                    // Refresh token
                    st = new StringTokenizer(data, ",");
                    String originalData = st.nextToken();

                    for (int i = 0; i < fieldData.length; i++) {
                        if (i != 1 && i != 2) {
                            boolean isUpdate = yaAtauTidak("Apakah anda ingin merubah " + fieldData[i]);
                            originalData = st.nextToken();
                            if (isUpdate) {
                                System.out.print("Masukkan " + fieldData[i] + " baru: ");
                                tempData[i] = userInput.nextLine();
                            } else {
                                tempData[i] = originalData;
                            }
                        }
                    }

                    // tampilkan data baru ke layar 
                    System.out.println("\nData baru anda adalah ");
                    System.out.println("---------------------------------------");
                    System.out.println("Soal               : " + tempData[0]);
                    System.out.println("Option A            : " + tempData[3]);

                    boolean isUpdate = yaAtauTidak("Apakah anda yakin ingin mungubah data tersebut");

                    if (isUpdate) {
                        String soal = tempData[0];
                        String jawaban = tempData[3];

                        bufferOutput.write("truefalse" + "," + soal + ",True,False," + jawaban);
                    } else {
                        bufferOutput.write(data);
                    }
                }
            } else {
                bufferOutput.write(data);
            }

            bufferOutput.newLine();

            data = bufferInput.readLine();
        }

        // menulis data ke file
        bufferOutput.flush();

        fileInput.close();
        fileOutput.close();
        bufferInput.close();
        bufferOutput.close();

        // delete original database
        database.delete();

        // rename file tempDB menjadi database
        boolean a = tempDB.renameTo(database);
        System.out.println(a);
    }

    public void bacakomentar() throws IOException {
        System.out.println("\n==================== MENU USULAN-PENDAPAT-KOMENTAR-SARAN ===================");
        //Representasi lokasi file yang akan dibaca
        File file = new File("komentar.txt");

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

    public void tampilkanSoal() throws IOException {

        PilihanGanda pilgan = new PilihanGanda();
        TrueFalse trufal = new TrueFalse();

        pilihanGanda = pilgan.showSoal();
        trueFalse = trufal.showSoal();

        for (PilihanGanda barang : pilihanGanda) {
            System.out.println("\n" + barang.getId() + ". " + barang.getSoal());
            System.out.println("a) " + barang.getOptionA());
            System.out.println("b) " + barang.getOptionB());
            System.out.println("c) " + barang.getOptionC());
            System.out.println("d) " + barang.getOptionD());
            System.out.println("e) " + barang.getOptionE());
            System.out.println("Jawaban => " + barang.getJawaban());
        }

        for (TrueFalse barang : trueFalse) {
            System.out.println("\n" + barang.getId() + ". " + barang.getSoal());
            System.out.println("a) " + barang.getOptionA());
            System.out.println("b) " + barang.getOptionB());
            System.out.println("Jawaban => " + barang.getJawaban());
        }
    }

    public void hapusSoal() throws IOException {
        File database = new File("SOAL.txt");
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferedInput = new BufferedReader(fileInput);

        // kita buat database sementara
        File tempDB = new File("tempDB.txt");
        FileWriter fileOutput = new FileWriter(tempDB);
        BufferedWriter bufferedOutput = new BufferedWriter(fileOutput);

        // kita ambil user input untuk mendelete data
        Scanner terminalInput = new Scanner(System.in);
        System.out.println("\n==================== MENU HAPUS SOAL ===================");
        System.out.print("\nMasukan Nomor soal yang akan diHapus: ");
        int deleteNum = terminalInput.nextInt();

        boolean isFound = false;
        int entryCounts = 0;

        String data = bufferedInput.readLine();

        while (data != null) {
            entryCounts++;
            boolean isDelete = false;

            StringTokenizer st = new StringTokenizer(data, ",");

            // tampilkan data yang ingin di hapus
            if (deleteNum == entryCounts) {
                String jenisSoal = st.nextToken();
                if (jenisSoal.equalsIgnoreCase("pilgan")) {
                    System.out.println("\nData yang ingin anda hapus adalah:");
                    System.out.println("-----------------------------------");
                    System.out.println("Soal       : " + st.nextToken());
                    System.out.println("Option A           : " + st.nextToken());
                    System.out.println("Option B          : " + st.nextToken());
                    System.out.println("Option C         : " + st.nextToken());
                    System.out.println("Option D            : " + st.nextToken());
                    System.out.println("Option E           : " + st.nextToken());
                    System.out.println("Jawaban           : " + st.nextToken());
                } else {
                    System.out.println("\nData yang ingin anda hapus adalah:");
                    System.out.println("-----------------------------------");
                    System.out.println("Soal       : " + st.nextToken());
                    System.out.println("Option A           : " + st.nextToken());
                    System.out.println("Option B        : " + st.nextToken());
                    System.out.println("Jawaban        : " + st.nextToken());
                }

                isDelete = yaAtauTidak("Apakah anda yakin akan menghapus?");
                isFound = true;
            }

            if (isDelete) {
                //skip pindahkan data dari original ke sementara
                System.out.println("Data berhasil dihapus");
            } else {
                // kita pindahkan data dari original ke sementara
                bufferedOutput.write(data);
                bufferedOutput.newLine();
            }
            data = bufferedInput.readLine();
        }

        if (!isFound) {
            System.err.println("Tidak ditemukan");
        }

        // menulis data ke file
        bufferedOutput.flush();
        fileInput.close();
        fileOutput.close();
        bufferedInput.close();
        bufferedOutput.close();

        // delete original file
        database.delete();
        // rename file sementara ke database
        tempDB.renameTo(database);
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
