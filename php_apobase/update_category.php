<?php

    include_once('koneksi.php');

    $category_id = $_GET['category_id'];
    $name = $_GET['name'];

    $getdata = mysqli_query($conn, "SELECT * FROM p_category WHERE category_id = '$category_id'");
    $rows = mysqli_num_rows($getdata);

    $respone = array();
    if ($rows > 0)
    {
        $query = "UPDATE p_category SET name='$name' WHERE category_id='$category_id'";
        $exequery = mysqli_query($conn,$query);

        if($exequery)
        {
            $respone ['code']=1;
            $respone ['message'] = "Update Success";
        }
        else
        {
            $respone ['code']=0;
            $respone ['message'] = "Update Gagal";
        }
    
    }
    else
    {
        $respone ['code']=0;
        $respone ['message'] = "Update gagal, Data tidak ditemukan";
    }
    

    echo json_encode($respone);

?>