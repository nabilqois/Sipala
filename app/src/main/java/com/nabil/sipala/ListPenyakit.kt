package com.nabil.sipala

object ListPenyakit {
    val all: List<Penyakit> = listOf(
        Penyakit(
            "Tungkak Lambung",
            "Tukak lambung adalah luka yang muncul pada dinding lambung akibat terkikisnya lapisan dinding lambung. Luka ini juga berpotensi muncul pada dinding bagian pertama usus kecil (duodenum) serta kerongkongan (esofagus).",
            listOf<String>(
                "Infeksi Bakteri Helicobacter Pylori",
                "Penggunaan Obat Anti Inflamasi Non-steroid",
                "Kanker",
                "Merokok",
                "Konsumsi minuman keras"
            ),
            listOf<String>(
                "Membatasi konsumsi minuman keras.",
                "Berhenti merokok.",
                "Mengurangi konsumsi teh dan kopi.",
                "Mengonsumsi produk berbahan dasar susu, seperti keju.",
                "Menghindari konsumsi makanan pedas atau berlemak.",
                "Makan dengan porsi kecil, tapi lebih sering.",
                "Miliki berat badan yang sehat dan ideal.",
                "Bagi pengidap tukak lambung yang disebabkan oleh obat antiinflamasi non-steroid, sebaiknya diskusikan pada dokter mengenai alternatif obat atau penyesuaian dosis agar terhindar dari risiko tukak lambung."
            ),
            listOf<String>(
                "Menjaga kebersihan, misalnya sering mencuci tangan dan memastikan makanan benar-benar matang sebelum mengonsumsinya.",
                "Berhati-hati dalam penggunaan obat anti inflamasi non-steroid agar tidak berlebihan.",
                "Mengurangi atau berhenti merokok.",
                "Menghindari konsumsi minuman beralkohol."
            )
        ),

        Penyakit(
            "Gastroparesis",
            "Gastroparesis adalah kelainan di lambung yang ditandai dengan lambatnya pengosongan makanan dari lambung ke usus halus.",
            listOf<String>(
                "Diabetes melitus yang kondisi gula darahnya tidak terkontrol.",
                "Pasca operasi usus.",
                "Gangguan syaraf, seperti Parkinson dan multiple sclerosis.",
                "Diabetes.",
                "Operasi perut atau kerongkongan.",
                "Infeksi (seringnya disebabkan oleh virus).",
                "Konsumsi obat yang memperlambat proses pencernaan, seperti obat tidur.",
                "Efek samping pengobatan kanker, seperti radioterapi atau kemoterapi.",
                "Gangguan jaringan penghubung.",
                "Penyakit saraf, seperti Parkinson atau multiple sclerosis.",
                "Hipotiroidisme.",
                ),
            listOf<String>(
                "Mengubah menu makanan. Pengidap disarankan untuk mengonsumsi makanan yang lebih mudah dicerna.",
                "Penyuntikan Botulinum toxin pada katup yang menghubungkan lambung dengan usus kecil (pyloric sphincter), untuk melemahkan otot katup, sehingga dapat membuka lebih lama untuk pengosongan lambung.",
                "Obat-obatan. Ada dua jenis obat yang digunakan pada gastroparesis, yaitu obat-obatan untuk merangsang otot lambung dan obat-obatan untuk menekan mual dan muntah.",
                "Stimulasi listrik. Mengalirkan listrik melalui elektroda yang ditempelkan ke dinding lambung, dengan tujuan merangsang gerakan otot lambung. Namun, manfaat dari terapi ini masih perlu diteliti lebih jauh.",
                "Pembedahan. (Silahkan konsultasi pada dokter di rumah sakit terdekat)",
                ),
            listOf<String>(
                "Kondisi ini tidak bisa dicegah. Namun, ada beberapa tips yang bisa dilakukan untuk menurunkan risikonya, yaitu dengan menerapkan pola makan sehat, tepat waktu, dan tepat porsi."
            )
        ),

        Penyakit(
            "GERD",
            "GERD atau gastroesophageal reflux disease adalah suatu simtom atau perubahan mukosa yang diakibatkan oleh gangguan sistem saluran pencernaan, di mana asam lambung dan isi perut mengalir kembali ke kerongkongan (esofagus).",
            listOf<String>(
                "Mengkonsumsi makanan dan minuman dengan rasa yang kuat",
                "Pola makan yang tidak teratur",
                "Kebiasaan makan yang kurang baik",
                ),
            listOf<String>(
                "Anda disarankan untuk berkonsultasi dengan dokter untuk menentukan mana pilihan obat yang terbaik untuk Anda."
            ),
            listOf<String>(
                "Menurunkan berat badan",
                "Berhenti merokok",
                "Menghindari makan dalam porsi besar dan berat di malam hari",
                "Menghindari makanan pemicu seperti cokelat, kafein, dan alcohol",
                "Makan maksimal 3 jam sebelum tidur",
                "Tidak langsung berbaring setelah makan",
                "Meninggikan posisi kepala saat berbaring",
            )
        ),

        Penyakit(
            "Gastritis",
            "Gastritis atau yang sering kita kenal ada sakit maag adalah peradangan yang terjadi pada lapisan dinding lambung dalam kantung nasi.",
            listOf<String>(
                "Menggunakan obat-obatan tertentu, seperti obat kortikosteroid dan antiinflamasi nonsteroid.",
                "Mengonsumsi minuman beralkohol secara berlebihan",
                "Menderita penyakit tertentu seperti gagal ginjal, refluks empedu, dan infeksi virus atau bakteri seperti Helicobacter pylori",
                "Mengalami stres yang berat",
                "Mengidap penyakit autoimun yang menyebabkan sistem kekebalan tubuh menyerang dinding lambung",
                "Mengalami efek samping akibat prosedur operasi",
                ),
            listOf<String>(
                "Anda disarankan untuk berkonsultasi dengan dokter untuk menentukan mana pilihan obat yang terbaik untuk Anda."
            ),
            listOf<String>(
                "Dengan mencuci tangan dengan sabun dan air mengalir sebelum makan dan memasak, untuk mencegah penularan infeksi bakteri pylori",
                "Menghindari makanan asam, pedas, berlemak dan minyak berlebih",
                "Mengurangi konsumsi minuman berkafein atau beralkohol",
                "Mengolah dan mengendalikan stres dengan baik",
                "Menghindari konsumsi obat antiinflamasi nonsteroid berlebihan atau tanpa berkonsultasi dengan dokter dahulu.",
            )
        ),

        Penyakit(
            "Kanker Lambung",
            "*Stomach cancer* atau kanker lambung adalah pertumbuhan sel abnormal di lapisan lambung. Lambung manusia memiliki empat lapisan, yaitu mukosa, submukosa, muskularis, dan serosa. Umumnya, sel kanker tumbuh di lapisan mukosa kemudian menyebar ke lapisan lainnya.",
            listOf<String>(
                "Memiliki kebiasaan merokok.",
                "Berusia di atas 55 tahun.",
                "Jenis kelamin laki-laki.",
                "Pernah menjalani operasi lambung.",
                "Memiliki keluarga dengan riwayat kanker lambung.",
            ),
            listOf<String>(
                "Dilakukan Operasi. (Anda disarankan untuk berkonsultasi dengan dokter untuk penentuan tindakan.)"
            ),
            listOf<String>(
                "Rutin berolahraga minimal 30 menit per hari sebanyak 3-5 kali per minggu.",
                "Menjaga berat badan ideal.",
                "Berhenti merokok.",
                "Menjalani pola makan sehat, seperti mengonsumsi makanan tinggi serat dan menghindari makanan olahan.",
            )
        ),
    )
}