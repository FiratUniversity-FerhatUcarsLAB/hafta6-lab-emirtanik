/**
 * Ad Soyad: Muhammed Emir Tanık
 * Öğrenci No: 250541089
 * Proje: Sinema Bilet Sistemi
 * Tarih:15.11.2025

 * KOdun bir çoğunu kendim yadım fakat çalıştırdığımda sürekli hata verdi 
 * Hataları düzeltmek için gptye attım
 */

import java.util.Scanner;

public class odev_2 {

    public static boolean Hafta_sonuMu(int gun) {
        return gun == 6 || gun == 7;
    }

    public static boolean matine(int saat) {
        return saat < 12;
    }

    public static double temel_hesap(int gun, int saat) {

        boolean haftasonu = Hafta_sonuMu(gun);
        boolean isMatine = matine(saat); // değişken adı metodla karışmasın diye isMatine yaptım
        if (haftasonu) {
            if (isMatine)
                return 55;
            else
                return 85;
        } else {
            if (isMatine)
                return 45;
            else
                return 65;
        }
    }

    public static double indirim_hesaplama(int yas, int meslek, int gun) {
        double indirim = 0;

        // --- YAŞA GÖRE İNDİRİM ---
        if (yas >= 65) {
            indirim = 0.30;
        } else if (yas < 12) {
            indirim = 0.25;
        }

        // --- ÖĞRENCİ İNDİRİMİ ---
        else if (meslek == 1) {
            if (gun >= 1 && gun <= 4) {
                indirim = 0.20;
            } else {
                indirim = 0.15;
            }
        }

        // öğretmen indirimi (yalnızca çarşamba)
        else if (meslek == 2 && gun == 3) {
            indirim = 0.35;
        }
        return indirim;
    }

    public static double ekstra_ucret(int tur) {
        switch (tur) {
            case 2:
                return 25; // 3D
            case 3:
                return 35; // IMAX
            case 4:
                return 50; // 4DX
            default:
                return 0; // 2D veya bilinmeyen
        }
    }

    // final fiyatını hesaplama
    // (düzeltme: metot film türü bilgisini de almalı, ayrıca parametre isimleri uyumlu)
    public static double toplami_hesapla(int gun, int saat, int yas, int meslek, int tur) {
        double temel = temel_hesap(gun, saat);
        double indirim = indirim_hesaplama(yas, meslek, gun);
        double ekstra = ekstra_ucret(tur);

        double indirimli = temel - (temel * indirim);
        double toplam = indirimli + ekstra;

        return toplam;
    }

    public static void generateTicketInfo(double fiyat) {
        System.out.println("=== BİLET BİLGİSİ ===");
        System.out.printf("Toplam Fiyat: %.2f TL\n", fiyat);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("-------BİLET SAYFAMIZA HOŞ GELDİNİZ-------");

        System.out.print("Gün (1-7): ");
        int gun = scanner.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = scanner.nextInt(); // saat tam sayı olmalı, double gerek yok

        System.out.print("Yaş: ");
        int yas = scanner.nextInt();

        System.out.print("Meslek (1= öğrenci 2= öğretmen 3=diğer): ");
        int meslek = scanner.nextInt();

        System.out.print("Film Türü (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
        int tur = scanner.nextInt();

        // toplami_hesapla parametre sırası: gun, saat, yas, meslek, tur
        double fiyat = toplami_hesapla(gun, saat, yas, meslek, tur);

        generateTicketInfo(fiyat);

        scanner.close();
    }
}
