<?php
require('koneksi.php');

$response = array();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $username = $_POST["username"];
    $passwrod = $_POST["password"];
    $name = $_POST["name"];
    $address = $_POST["address"];
    $level = $_POST["level"];

    $perintah = "INSERT INTO user (username, password, name, address, level) VALUES ('$username','$passwrod','$name','$address','$level')";
    $eksekusi = mysqli_query($conn, $perintah);
    $cek = mysqli_affected_rows($conn);

    if ($cek > 0) {
        $response["kode"] = 1;
        $response["pesan"] = "Data berhasil disimpan";
    } else {
        $response["kode"] = 0;
        $response["pesan"] = "Gagal menyimpan data";
    }
} else {
    $response["kode"] = 0;
    $response["pesan"] = "Tidak ada post data";
}

echo json_encode($response);
mysqli_close($conn);
