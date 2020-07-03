<?php
require('koneksi.php');

$response = array();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $user_id = $_POST["user_id"];
    $username = $_POST["username"];
    $name = $_POST["name"];
    $address = $_POST["address"];
    $perintah = "UPDATE user SET username ='$username',name ='$name',address ='$address' WHERE user_id='$user_id'";
    $eksekusi = mysqli_query($conn, $perintah);
    $cek = mysqli_affected_rows($conn);

    if ($cek > 0) {
        $response["kode"] = 1;
        $response["pesan"] = "Data berhasil diupdate";
    } else {
        $response["kode"] = 0;
        $response["pesan"] = "Data gagal diupdate";
    }
} else {
    $response["kode"] = 0;
    $response["pesan"] = "Tidak ada post data";
}

echo json_encode($response);
mysqli_close($conn);
