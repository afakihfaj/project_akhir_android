<?php
require("koneksi.php");
$perintah = "SELECT * FROM p_item";
$eksekusi = mysqli_query($conn, $perintah);
$cek = mysqli_affected_rows($conn);

if ($cek > 0){
    $response["kode"] = 1;
    $response["pesan"] = "Data Tersedia";
    $response["data"] = array();

    while($ambil = mysqli_fetch_object($eksekusi)){
        $F['item_id'] = $ambil->item_id;
        $F['barcode'] = $ambil->barcode;
        $F['name'] = $ambil->name;
        $F['supplier_id'] = $ambil->supplier_id;
        $F['unit_id'] = $ambil->$unit_id;
        $F['price'] = $ambil->price;
        $F['stock'] = $ambil->stock;

        array_push($response["data"], $F);
    }
}
else{
    $response["kode"] = 0;
    $response["pesan"] = "Data Tidak Tersedia";
}
echo json_encode($response);
mysqli_close($conn);
?>