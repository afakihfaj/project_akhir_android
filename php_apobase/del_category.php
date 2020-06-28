<?php
require('koneksi.php');

$response = array();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $category_id = $_POST["category_id"];

    $perintah = "DELETE FROM p_category where category_id = '$category_id'";
    $eksekusi = mysqli_query($conn, $perintah);
    $cek = mysqli_affected_rows($conn);

    if ($cek > 0) {
        $response["kode"] = 1;
        $response["pesan"] = "Data berhasil dihapus";
    } else {
        $response["kode"] = 0;
        $response["pesan"] = "Gagal menghapus data";
    }
} else {
    $response["kode"] = 0;
    $response["pesan"] = "Tidak ada post data";
}

echo json_encode($response);
mysqli_close($conn);
