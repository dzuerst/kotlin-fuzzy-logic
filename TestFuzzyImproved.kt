// untuk input dengan tipe data selain string
// gunakan library scanner
import java.util.Scanner;

fun main(){
    // untuk memanggil library inputan melalui objek scanner
    val reader = Scanner(System.`in`)

    print("Masukkan Suhu (F) :")
    var temperature = reader.nextDouble()

    print("Masukkan Tutupan Awan (%) : ")
    var cloud = reader.nextDouble()

    var y_freezing = 0.0
    var y_cool = 0.0
    var y_warm = 0.0
    var y_hot = 0.0

    // catatan penting
    // Variabel linguistik : variabel yang nilainya berupa kata contoh:
        // variabel linguistik                      : suhu
        // nilai nilai variabel linguistik suhu     : sangat dingin, dingin, panas, sangat panas

        // tiap-tiap nilai pada variabel linguistik memiliki nilai y (derajat keanggotaan) contoh

        /*
        var. linguistik : suhu
        nilai-nilai var : sangat dingin, dingin, panas, sangat panas

        y tiap-tiap nilai-nilai var
        sangat dingin : 1
        dingin        : 0
        panas         : 0
        sangat panas  : 0
        
        */





        

    
    // TAHAP 1 FUZZIFIKASI 
   /*Fuzzifikasi secara praktis adalah proses mencari nilai y pada tiap variabel linguistik dalam suatu variabel
     Fuzzyfication for temperature variable*/
    if (temperature <= 30){
        y_freezing = 1.0
        y_cool = 0.0
        y_warm = 0.0
        y_hot = 0.0
    }

    if (temperature > 30 && temperature < 50){
        y_freezing = (50 - temperature) / (50 - 30)
        y_cool = (temperature - 30) / (50-30)
        y_warm = 0.0
        y_hot = 0.0
    }
    
    if (temperature == 50.0) {
        y_freezing = 0.0
        y_cool = 1.0
        y_warm = 0.0
        y_hot = 0.0
    }
    
    if (temperature > 50 && temperature < 70){
        y_freezing = 0.0
        y_cool = (70 - temperature) / (70-50)
        y_warm = (temperature - 50) / (70-50)
        y_hot = 0.0
    }
    
    if (temperature == 70.0){
        y_freezing = 0.0
        y_cool = 0.0
        y_warm = 1.0
        y_hot = 0.0
    }

    if (temperature > 70 && temperature < 90) {
        y_freezing = 0.0
        y_cool = 0.0
        y_warm = (90 - temperature) / (90-70)
        y_hot = (temperature - 70) / (90-70)
    }
        

    if (temperature >= 90.0){
        y_freezing = 0.0
        y_cool = 0.0
        y_warm = 0.0
        y_hot = 1.0
    }

    println("Maka suhu dalam variabel linguistik, derajat keanggotaan adalah")
    println("Dingin : $y_freezing")
    println("Sejuk : $y_cool")
    println("Hangat : $y_warm")
    println("Panas : $y_hot")


    var y_sunny = 0.0
    var y_partly_cloudy = 0.0
    var y_overcast = 0.0

    // Fuzzyfication for cloud cover variable
    if (cloud <= 20){   
        y_sunny = 1.0
        y_partly_cloudy = 0.0
        y_overcast = 0.0
    }

    if (cloud > 20 && cloud < 40){
        y_sunny = (40 - cloud) / (40-20)
        y_overcast = 0.0
    }
  
    if (cloud > 20 && cloud < 50){
        y_partly_cloudy = (cloud - 20) / (50-20)
    }
  
    if (cloud == 50.0){
        y_sunny = 0.0
        y_partly_cloudy = 1.0
        y_overcast = 0.0
    }
  
    if (cloud > 50 && cloud < 80) {
        y_sunny = 0.0
        y_partly_cloudy = (80 - cloud) / (80-50)
    }
  
    if (cloud > 60 && cloud < 80){
        y_sunny = 0.0
        y_overcast = (cloud - 60) / (80-60)
    }
  
    if (cloud > 80) {
        y_sunny = 0.0
        y_partly_cloudy = 0.0
        y_overcast = 1.0
    }
  

    println("")
    println("Maka cloud cover dalam variabel linguistik, derajat keanggotaan adalah")
    println("Sunny : $y_sunny")
    println("Sebagian berawan : $y_partly_cloudy")
    println("Mendung : $y_overcast")


    // TAHAP 2
    // SISTEM INFERENSI DASAR

    // CATATAN
    // SUM(aturan) = SUM(variabel_linguistik_var_1) * SUM(var_linguistik_2) * SUM(var_linguistik_dst)
    // Dalam kasus ini terdapat 4 * 3 = 12 aturan

    var speed = mutableListOf<DoubleArray>()
// INFERENSI
fun inferenceSlow(temperature: Double, cloud: Double){
    if(temperature != 0.0){
        if (cloud != 0.0){
            // return nilai minimal
            var output: Double = Math.min(temperature, cloud)
            speed.add(doubleArrayOf(output,25.0))
        }
    }
}

fun inferenceFast(temperature: Double, cloud: Double){
    if(temperature != 0.0){
        if (cloud != 0.0){
            // return nilai minimal
            var output: Double = Math.min(temperature, cloud)
            speed.add(doubleArrayOf(output,75.0))
        }
    }
}



// ###### TAHAP 3 ATURAN DASAR #######
// # 12 ATURAN
// # UNTUK INFERENCE SLOW

// variabel linguistik suhu
val temperatures

// variabel linguistik cloud cover

inferenceSlow(y_freezing, y_sunny)
inferenceSlow(y_freezing, y_partly_cloudy)
inferenceSlow(y_freezing, y_overcast)

inferenceSlow(y_cool, y_sunny)
inferenceSlow(y_cool, y_partly_cloudy)
inferenceSlow(y_cool, y_overcast)

inferenceFast(y_warm, y_sunny)
inferenceFast(y_warm, y_partly_cloudy)
inferenceFast(y_warm, y_overcast)

inferenceFast(y_hot, y_sunny)
inferenceFast(y_hot, y_partly_cloudy)
inferenceFast(y_hot, y_overcast)

// println("Maka Speed adalah : $speed")

val range = 1..speed.size
val nestedRange = 1..(speed.size * 2)

// cek array size
// println(speed.size)
// println(speed.size * 2)

// 00 01
// 10 11
// 20 21  
// 30 31

print("Kecepatan : ")
var counter = 0
for(doubleArray in speed){
    for(element in doubleArray){
        if(counter < 1){
            print("[$element,")
            counter++
        } else {
            print(" $element], ")
            counter = 0
        }
    }
}

println()

// ###### TAHAP 4 DEFUZIFIKASI #####
// # len() mengembalikan panjang array

var perkalian = 0.0
var pembagian = 0.0
var perkalian_new = 0.0
var pembagian_new = 0.0


for(doubleArray in speed){
    perkalian = doubleArray[0] * doubleArray[1]
    pembagian = doubleArray[0]
    perkalian_new = perkalian_new + perkalian
    pembagian_new = pembagian_new + pembagian
}

// # rata-rata terbobot
val z = perkalian_new / pembagian_new
println("Rata rata terbobot $z")

}
