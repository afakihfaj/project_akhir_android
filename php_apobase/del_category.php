<?php

include_once('koneksi.php');

$category_id = $_GET['category_id'];

$getdata = mysqli_query($conn, "SELECT * FROM p_category WHERE category_id = '$category_id'");
$rows = mysqli_num_rows($getdata);

$delete = "DELETE FROM p_category WHERE category_id = '$category_id'";
$exedelete = mysqli_query($conn,$delete);

if ($rows > 0)
{
    if($exedelete)
    {
        $respone ['code']=1;
        $respone ['message'] = "Delete Success";
    }
    else
    {
        $respone ['code']=0;
        $respone ['message'] = "Delete Gagal";
    }

}
else
{
    $respone ['code']=0;
    $respone ['message'] = "Delete gagal, Data tidak ditemukan";
}


echo json_encode($respone);

?>