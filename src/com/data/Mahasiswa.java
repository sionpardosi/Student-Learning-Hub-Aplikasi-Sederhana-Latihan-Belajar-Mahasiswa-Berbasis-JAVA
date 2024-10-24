/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @sionPardosi
 * 
 */

public class Mahasiswa extends User implements Auth {

    private String kelas;
    private String nim;
    private JenisProdi jurusan;

    /**
     *
     * @param kelas
     * @param nim
     * @param jurusan
     */
    public Mahasiswa(String kelas, String nim, JenisProdi jurusan) {
        this.kelas = kelas;
        this.nim = nim;
        this.jurusan = jurusan;
    }

    public Mahasiswa() {
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public JenisProdi getJurusan() {
        return jurusan;
    }

    public void setJurusan(JenisProdi jurusan) {
        this.jurusan = jurusan;
    }

    public ArrayList<Mahasiswa> loginMahasiswa() throws IOException {
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

        ArrayList<Mahasiswa> isExist = cari(username, password);

        return isExist;
    }

    public ArrayList<Mahasiswa> cari(String username, String password) throws IOException {
        FileReader fileInput = new FileReader("Mahasiswa.txt");
        BufferedReader bufferInput = new BufferedReader(fileInput);

        String data = bufferInput.readLine();
        ArrayList<Mahasiswa> bio = new ArrayList<Mahasiswa>();

        while (data != null) {

            StringTokenizer stringToken = new StringTokenizer(data, ",");

            if (stringToken.nextToken().equalsIgnoreCase(username) && stringToken.nextToken().equalsIgnoreCase(password)) {
                Mahasiswa mhs = new Mahasiswa();
                JenisKelamin jk;
                JenisProdi jp;

                mhs.setNama(stringToken.nextToken());
                mhs.setNim(stringToken.nextToken());
                jp = JenisProdi.valueOf(stringToken.nextToken());
                mhs.setJurusan(jp);
                jk = JenisKelamin.valueOf(stringToken.nextToken());
                mhs.setJenisKelamin(jk);

                bio.add(mhs);
            }

            data = bufferInput.readLine();
        }

        return bio;
    }

    @Override
    public boolean logout() {
        return false;
    }

    /**
     *
     * @throws IOException
     */
    @Override
    public void register() throws IOException {
        FileWriter fileOutput = new FileWriter("Mahasiswa.txt", true);
        try (BufferedWriter bufferOutput = new BufferedWriter(fileOutput)) {
            Mahasiswa mhs = new Mahasiswa();
            Scanner userInput = new Scanner(System.in);
            int pilihan;

            System.out.print("Masukkan Nama: ");
            mhs.setNama(userInput.nextLine());
            System.out.print("Masukkan NIM: ");
            mhs.setNim(userInput.nextLine());
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
                    mhs.setJenisKelamin(JenisKelamin.LAKILAKI);
                    break;
                case 2:
                    mhs.setJenisKelamin(JenisKelamin.PEREMPUAN);
                    break;
            }

            System.out.println("Jurusan: ");
            System.out.println("1. " + JenisProdi.D3TI);
            System.out.println("2. " + JenisProdi.D3TK);
            System.out.println("3. " + JenisProdi.D4TRPL);
            System.out.println("4. " + JenisProdi.S1IF);
            System.out.println("5. " + JenisProdi.S1SI);
            System.out.println("6. " + JenisProdi.S1TE);
            System.out.println("7. " + JenisProdi.S1MR);
            System.out.println("8. " + JenisProdi.S1BP);
            System.out.print("Masukkan Kode Jurusan: ");
            pilihan = userInput.nextInt();

            while (pilihan != 1 && pilihan != 2 && pilihan != 3 && pilihan != 4 && pilihan != 5 && pilihan != 6 && pilihan != 7 && pilihan != 8) {
                System.out.println("Jurusan: ");
                System.out.println("1. " + JenisProdi.D3TI);
                System.out.println("2. " + JenisProdi.D3TK);
                System.out.println("3. " + JenisProdi.D4TRPL);
                System.out.println("4. " + JenisProdi.S1IF);
                System.out.println("5. " + JenisProdi.S1SI);
                System.out.println("6. " + JenisProdi.S1TE);
                System.out.println("7. " + JenisProdi.S1MR);
                System.out.println("8. " + JenisProdi.S1BP);
                System.out.print("Masukkan Kode Jurusan: ");
                pilihan = userInput.nextInt();
            }

            switch (pilihan) {
                case 1:
                    mhs.setJurusan(JenisProdi.D3TI);
                    break;
                case 2:
                    mhs.setJurusan(JenisProdi.D3TK);
                    break;
                case 3:
                    mhs.setJurusan(JenisProdi.D4TRPL);
                    break;
                case 4:
                    mhs.setJurusan(JenisProdi.S1IF);
                    break;
                case 5:
                    mhs.setJurusan(JenisProdi.S1SI);
                    break;
                case 6:
                    mhs.setJurusan(JenisProdi.S1TE);
                    break;
                case 7:
                    mhs.setJurusan(JenisProdi.S1MR);
                    break;
                case 8:
                    mhs.setJurusan(JenisProdi.S1BP);
                    break;
            }

            System.out.print("Masukkan Nama Pengguna: ");
            mhs.setUsername(userInput.next());
            System.out.print("Masukkan Kata Sandi: ");
            mhs.setPassword(userInput.next());

            System.out.println("Nama Lengkap: " + mhs.getNama());
            System.out.println("NIM: " + mhs.getNim());
            System.out.println("Jurusan: " + mhs.getJurusan());
            System.out.println("Jenis Kelamin " + mhs.getJenisKelamin());
            System.out.println("Nama Pengguna: " + mhs.getUsername());
            System.out.println("Kata Sandi: " + mhs.getPassword());

            boolean isRegister = yaAtauTidak("Apakah anda yakin ingin menambah data tersebut");

            if (isRegister) {
                bufferOutput.write(mhs.getUsername() + "," + mhs.getPassword() + "," + mhs.getNama() + "," + mhs.getNim() + "," + mhs.getJurusan() + "," + mhs.getJenisKelamin());
                bufferOutput.newLine();
                bufferOutput.flush();
            }
        }
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
