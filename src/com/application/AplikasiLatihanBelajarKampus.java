package com.application;

import Quiz.*;
import com.data.Admin;
import com.data.Mahasiswa;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @sionPardosi
 *
 */
public class AplikasiLatihanBelajarKampus {

    static ArrayList<Mahasiswa> log;

    public static void main(String[] args) throws IOException {
        Scanner userInput = new Scanner(System.in);
        boolean lanjutkan = true;
        boolean login = false;
        int peran = 0;

        Quiz quio = new Quiz();
        Admin admm = new Admin();

        while (lanjutkan) {
            if (login) {
                if (peran == 1) {
                    System.out.println("\n========== MENU MAHASISWA ==========");
                    System.out.println("Selamat datang sebagai Mahasiswa!");
                    System.out.println("\n1. Soal/kuis");
                    System.out.println("2. Lihat Daftar Nilai");
                    System.out.println("3. lihat Kunci Jawaban");
                    System.out.println("4. Usulan");
                    System.out.println("5. Tentang Aplikasi");
                    System.out.println("6. Logout");
                    System.out.println("\nMasukkan Pilihan Anda : ");
                    int choice = userInput.nextInt();

                    switch (choice) {
                        case 1:
                            quio.tampilkanSoal(log);
                            break;
                        case 2:
                            quio.tampilkanNilai();
                            break;
                        case 3:
                            quio.KUNCIJAWABAN(log);
                            break;
                        case 4:
                            quio.komentar();
                                 break;
                        case 5:
                            quio.saran();
                            break;
                        case 6:
                            Mahasiswa mhs = new Mahasiswa();
                            login = mhs.logout();
                            break;
                    }

                    lanjutkan = yaAtauTidak("kembali");

                } else if (peran == 2) {
                    Admin adm = new Admin();
                    System.out.println("\n========== MENU ADMIN ==========");
                    System.out.println("Selamat datang sebagai Admin!");
                    System.out.println("1. Tambah Soal");
                    System.out.println("2. Ubah Soal");
                    System.out.println("3. Hapus Soal");
                    System.out.println("4. Data Mahasiswa dan Nilai");
                    System.out.println("5. Tampilkan komentar Mahasiswa");
                    System.out.println("6. LogOut");
                    System.out.print("\nMasukkan Pilihan Anda : ");
                    int pilihan = userInput.nextInt();
                    switch (pilihan) {
                        case 1:
                            adm.tambahSoal();
                            break;
                        case 2:
                            adm.editSoal();
                            break;
                        case 3:
                            adm.hapusSoal();
                            break;
                        case 4:
                            quio.tampilkanNilai();
                            break;
                        case 5:
                            adm.bacakomentar();
                            break;
                        case 6:
                            Mahasiswa mhs = new Mahasiswa();
                            login = mhs.logout();
                            break;

                    }
                    lanjutkan = yaAtauTidak("Kembali");
                } else {
                    System.out.println("no");
                }
            } else {

                System.out.println("\n==================== SELAMAT DATANG ====================");
                System.out.println("             APLIKASI LATIHAN BELAJAR KAMPUS");
                System.out.println("--------------------------------------------------------");
                System.out.println("Untuk memulai, pilih peran Anda di bawah ini:");
                System.out.println("1. Mahasiswa");
                System.out.println("2. Admin");
                System.out.print("Anda hanya dapat memasukkan '1' dan '2', Masukkan pilihan anda: ");
                peran = userInput.nextInt();

                switch (peran) {
                    case 1:
                        int pilihan;
                        Mahasiswa mhs = new Mahasiswa();
                        System.out.println("\n======================================================");
                        System.out.println("             SELAMAT DATANG DI MENU MAHASISWA             ");
                        System.out.println("======================================================");
                        System.out.println("1. Masuk");
                        System.out.println("2. Daftar");
                        System.out.print("Masukkan Pilihan anda: ");
                        pilihan = userInput.nextInt();
                        switch (pilihan) {
                            case 1:
                                System.out.println("\n========== MASUK ==========");
                                log = mhs.loginMahasiswa();
                                if (log.size() == 1) {
                                    login = true;
                                }
                                break;
                            case 2:
                                System.out.println("\n==== DAFTAR AKUN MAHASISWA ====");
                                mhs.register();
                                break;
                            default:
                                System.out.println("Maaf, kode inputan Anda tidak valid, anda hanya dapat memilih '1' dan '2'. Mohon coba lagi.");
                        }
                        break;
                    case 2:
                        Admin adm = new Admin();
                        System.out.println("\n======================================================");
                        System.out.println("             SELAMAT DATANG DI MENU ADMIN            ");
                        System.out.println("======================================================");
                        System.out.println("1. Masuk");
                        System.out.println("2. Daftar");
                        System.out.print("Masukkan pilihan anda: ");
                        pilihan = userInput.nextInt();
                        switch (pilihan) {
                            case 1:
                                System.out.println("\n==== MASUK ====");
                                ArrayList<Admin> log = adm.loginAdmin();
                                if (log.size() == 1) {
                                    login = true;
                                }
                                break;
                            case 2:
                                System.out.println("\n==== DAFTAR AKUN ADMIN ====");
                                adm.register();
                                break;
                            default:
                                System.out.println("Maaf, kode inputan Anda tidak valid, anda hanya dapat memilih '1' dan '2'. Mohon coba lagi.");
                        }
                        break;
                    default:
                        System.out.println("Maaf kode inputan anda salah");
                }

                if (!login) {
                    lanjutkan = yaAtauTidak("Anda Harus Login Kembali ke Halaman Utama!");
                }
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
