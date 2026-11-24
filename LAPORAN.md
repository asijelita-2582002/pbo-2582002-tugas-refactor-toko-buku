# LAPORAN TUGAS 11 – REFACTOR TOKO BUKU

**Nama:** Asi Jelita Tamba  
**NIM:** 2582002  
**Mata Kuliah:** Pemrograman Berorientasi Objek (PBO)  
**Judul Tugas:** Refactor Toko Buku Menggunakan Prinsip SOLID

## 1. Pendahuluan

Pada tugas ini dilakukan refactoring terhadap program Toko Buku yang sebelumnya dibuat dalam satu class, yaitu `TokoBukuKotor.java`. Program awal masih memiliki beberapa masalah dalam struktur kode karena banyak tanggung jawab dikerjakan dalam satu class.

Untuk memperbaiki struktur program, dilakukan pemisahan class dan interface berdasarkan tanggung jawab masing-masing. Refactoring ini menerapkan lima prinsip SOLID agar program lebih mudah dipahami, dikembangkan, dan dipelihara.

## 2. Penerapan Prinsip SOLID

### A. Single Responsibility Principle (SRP)

**Pengertian:**

Single Responsibility Principle merupakan prinsip yang menyatakan bahwa setiap class sebaiknya memiliki satu tanggung jawab utama.

**Masalah pada program awal:**

Pada program awal, beberapa tanggung jawab seperti mengelola data buku, menghitung harga, mencetak struk, menyimpan riwayat transaksi, dan mengirim notifikasi masih digabungkan dalam satu class. Hal ini membuat kode sulit dikelola apabila terjadi perubahan.

**Perbaikan yang dilakukan:**

Program dipisahkan menjadi beberapa class sesuai tanggung jawabnya, yaitu:

- `Buku.java` untuk menyimpan data dasar buku.
- `BukuCetak.java` untuk mengelola buku cetak.
- `BukuDigital.java` untuk mengelola buku digital.
- `BukuAudio.java` untuk mengelola buku audio.
- `Keranjang.java` untuk mengelola daftar buku dan menghitung total belanja.
- `PrinterKonsol.java` untuk mencetak struk.
- `FileRiwayat.java` untuk menangani penyimpanan riwayat transaksi.
- `EmailSender.java` untuk mengirim notifikasi.

Dengan pemisahan tersebut, setiap class memiliki tugas yang lebih jelas.

### B. Open/Closed Principle (OCP)

**Pengertian:**

Open/Closed Principle menyatakan bahwa sebuah program harus terbuka untuk pengembangan fitur baru, tetapi tertutup untuk perubahan kode yang sudah ada.

**Masalah pada program awal:**

Apabila terdapat metode pembayaran baru atau jenis buku baru, program awal memerlukan perubahan pada kode utama. Hal ini dapat membuat kode semakin kompleks.

**Perbaikan yang dilakukan:**

Dibuat interface `MetodeBayar.java` yang memiliki dua method, yaitu:

- `getNamaMetode()`
- `hitungTotalBayar(int totalBelanja)`

Interface tersebut diimplementasikan oleh beberapa class:

- `Tunai.java`
- `Transfer.java`
- `EWallet.java`

Dengan cara ini, metode pembayaran baru dapat ditambahkan dengan membuat class baru yang mengimplementasikan `MetodeBayar`, tanpa harus mengubah struktur utama program.

### C. Liskov Substitution Principle (LSP)

**Pengertian:**

Liskov Substitution Principle menyatakan bahwa objek dari subclass harus dapat menggantikan objek dari superclass tanpa menyebabkan kesalahan pada program.

**Masalah pada program awal:**

Pengelolaan berbagai jenis buku yang memiliki karakteristik berbeda dapat menyebabkan ketergantungan pada jenis objek tertentu. Jika perilaku subclass tidak sesuai dengan kontrak class induknya, program dapat mengalami masalah.

**Perbaikan yang dilakukan:**

Dibuat class induk abstrak `Buku.java` yang memiliki method:

- `hargaAkhir()`
- `keterangan()`

Kemudian dibuat tiga subclass, yaitu `BukuCetak`, `BukuDigital`, dan `BukuAudio`.

Setiap subclass mengimplementasikan method sesuai karakteristik bukunya masing-masing. Dengan demikian, ketiga jenis buku tetap dapat digunakan sebagai objek `Buku` dalam keranjang belanja.

### D. Interface Segregation Principle (ISP)

**Pengertian:**

Interface Segregation Principle menyatakan bahwa sebuah class tidak seharusnya dipaksa untuk mengimplementasikan method yang sebenarnya tidak dibutuhkan.

**Masalah pada program awal:**

Jika semua jenis buku menggunakan satu interface yang berisi seluruh kemampuan, buku yang tidak memiliki kemampuan tertentu tetap harus menyediakan implementasi method tersebut.

**Perbaikan yang dilakukan:**

Interface dipisahkan berdasarkan kemampuan objek:

- `BisaDikirim.java` digunakan untuk objek yang dapat dikirim secara fisik.
- `BisaDiunduh.java` digunakan untuk objek yang memiliki link unduhan.

Implementasinya:

- `BukuCetak` menggunakan `BisaDikirim`.
- `BukuDigital` menggunakan `BisaDiunduh`.
- `BukuAudio` menggunakan `BisaDiunduh`.

Dengan pemisahan tersebut, setiap jenis buku hanya menggunakan kemampuan yang memang dibutuhkan.

### E. Dependency Inversion Principle (DIP)

**Pengertian:**

Dependency Inversion Principle menyatakan bahwa class utama sebaiknya bergantung pada abstraksi, bukan langsung pada class implementasi tertentu.

**Masalah pada program awal:**

Jika program utama langsung membuat dan menggunakan class tertentu untuk mencetak struk, menyimpan riwayat, atau mengirim notifikasi, maka program menjadi bergantung pada implementasi tersebut.

**Perbaikan yang dilakukan:**

Dibuat beberapa interface sebagai abstraksi:

- `StrukPrinter.java`
- `PenyimpananRiwayat.java`
- `PengirimNotifikasi.java`

Interface tersebut diimplementasikan oleh:

- `PrinterKonsol.java`
- `FileRiwayat.java`
- `EmailSender.java`

Pada `TokoBukuSolid.java`, dependency diberikan melalui constructor (Dependency Injection). Dengan demikian, class utama bergantung pada interface, bukan langsung pada implementasi konkretnya.

## 3. Kesimpulan

Melalui refactoring ini, program Toko Buku berhasil disusun kembali dengan menerapkan lima prinsip SOLID. Pemisahan class dan interface membuat setiap bagian program memiliki tanggung jawab yang lebih jelas, mengurangi ketergantungan antarclass, serta mempermudah pengembangan fitur baru. Program juga berhasil dijalankan dengan hasil transaksi yang sesuai dengan output yang diharapkan.