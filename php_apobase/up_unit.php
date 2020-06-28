<?php
require('koneksi.php');

$response = array();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $unit_id = $_POST["unit_id"];
    $name = $_POST["name"];
    $perintah = "UPDATE p_unit SET name ='$name' WHERE unit_id='$unit_id'";
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
