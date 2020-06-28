<?php
require_once 'koneksi.php';
$response = array();
if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $barcode = $_POST['barcode'];
    $name = $_POST['name'];
    $category_id = $_POST['category_id'];
    $unit_id = $_POST['unit_id'];
    $price = $_POST['price'];
    $berat = $_POST['berat'];
    $deskripsi = $_POST['deskripsi'];
    $stock = $_POST['stock'];
    // $image = $_POST['image'];

    $perintah = "INSERT INTO p_item (barcode, name, category_id , unit_id, price, berat, deskripsi, stock) VALUES('$barcode', '$name', '$category_id', '$unit_id', '$price', '$berat', '$deskripsi', '$stock')";
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
