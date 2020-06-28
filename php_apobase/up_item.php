<?php
require('koneksi.php');

$response = array();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $item_id = $_POST["item_id"];
    $barcode = $_POST["barcode"];
    $name = $_POST["name"];
    $category_id = $_POST["category_id"];
    $unit_id = $_POST["unit_id"];
    $category_id = $_POST["category_id"];
    $price = $_POST["price"];
    $berat = $_POST["berat"];
    $deskripsi = $_POST["deskripsi"];
    $stock = $_POST["stock"];
    $perintah = "UPDATE p_item SET barcode ='$barcode',name ='$name',category_id ='$category_id',unit_id ='$unit_id',berat ='$berat',deskripsi ='$deskripsi',stock ='$stock' WHERE item_id='$item_id'";
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
