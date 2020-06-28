<?php
    require("koneksi.php");
          $perintah = "SELECT * FROM supplier";
          $eksekusi = mysqli_query($conn, $perintah);
          $cek = mysqli_affected_rows($conn);

    if ($cek > 0)
    {
        $response["kode"] = 1;
        $response["pesan"] = "Data Tersedia";
        $response["data"] = array();

        while($ambil = mysqli_fetch_object($eksekusi)){
            $F['supplier_id'] = $ambil->supplier_id;
            $F['name'] = $ambil->name;
            $F['phone'] = $ambil->phone;
            $F['address'] = $ambil->address;
            $F['description'] = $ambil->description;

            array_push($response["data"], $F);
        }
    }
    else
    {
        $response["kode"] = 0;
        $response["pesan"] = "Data Tidak Tersedia";
    }
    echo json_encode($response);
    mysqli_close($conn);
?>