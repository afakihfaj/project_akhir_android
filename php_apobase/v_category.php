<?php
include_once('koneksi.php');

$query = "SELECT * FROM p_category";
$result = mysqli_query($conn,$query);

$array = array();

while($baris = mysqli_fetch_assoc($result))
{
    $arraydata[]=$baris;
}

echo json_encode($arraydata);

?>