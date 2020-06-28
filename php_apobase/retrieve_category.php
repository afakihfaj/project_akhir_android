<?php
require("koneksi.php");
$perintah = "SELECT * FROM p_category";
$eksekusi = mysqli_query($conn, $perintah);
$cek = mysqli_affected_rows($conn);

if ($cek > 0){
    $response["kode"] = 1;
    $response["pesan"] = "Data Tersedia";
    $response["data"] = array();

    while($ambil = mysqli_fetch_object($eksekusi)){
        $F['category_id'] = $ambil->category_id;
        $F['name'] = $ambil->name;
        // $F['created'] = $ambil->created;
        // $F['updated'] = $ambil->updated;

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