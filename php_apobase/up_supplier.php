<?php
require('koneksi.php');

$response = array();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $supplier_id = $_POST["supplier_id"];
    $username = $_POST["username"];
    $phone = $_POST["phone"];
    $description = $_POST["description"];
    $perintah = "UPDATE supplier SET name ='$name',phone ='$phone',description ='$description' WHERE supplier_id='$supplier_id'";
    $eksekusi = mysqli_query($conn, $perintah);
    $cek = mysqli_affected_rows($conn);

    if ($cek > 0) {
        $response["kode"] = 1;
        $response["pesan"] = "Data berhasil dihapus";
    } else {
        $response["kode"] = 0;
        $response["pesan"] = "Data gagal dihapus";
    }
} else {
    $response["kode"] = 0;
    $response["pesan"] = "Tidak ada post data";
}

echo json_encode($response);
mysqli_close($conn);
