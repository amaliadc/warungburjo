<?php

include 'main.php';


$url = "php://input";
$jsonUrl = file_get_contents($url, false);
$input = json_decode($jsonUrl, true);

$nama = $input['nama'];
$alamat = $input['alamat'];
$telepon = $input['telepon'];

$conn = mysqli_connect($db_hostname, $db_username, $db_password, $db_database);

if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}



    $dataPemesan = "INSERT INTO pemesan (nama, alamat, telepon)
VALUES ('$nama', '$alamat', '$telepon')";

if (mysqli_query($conn, $dataPemesan)) {
    $last_id = mysqli_insert_id($conn);
    $status = "OK";
} else {
    echo "Error: " . $dataPemesan . "<br>" . mysqli_error($conn);
}

if ($status == "OK"){

for($i = 0; $i < count($input['pesanan']); $i++){

$idMakanan = $input['pesanan'][$i]['idMakanan'];
$jumlah = $input['pesanan'][$i]['jumlah'];

$dataPesananMakanan = "INSERT INTO pesanan (no_pesanan, idMakanan, jumlah)
VALUES ('$last_id', '$idMakanan', '$jumlah')";
      	mysqli_query($conn, $dataPesananMakanan);
      	}
      	$arr = array('status' => 'success');
	echo json_encode($arr);
}

else {
    echo "Error: " . $dataPesananMakanan . "<br>" . mysqli_error($conn);
}




mysqli_close($conn);
    
?>