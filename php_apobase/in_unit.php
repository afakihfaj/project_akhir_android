<?php
if ($_SERVER['REQUEST_METHOD'] =='POST'){

    $name = $_POST['name'];

    require_once 'koneksi.php';

    $sql = "INSERT INTO p_unit SET name ='$name'";
    
    if(mysqli_query($conn, $sql)) {
        $result["success"] = "1";
        $result["message"] ="success";

        echo json_encode($result);
        mysqli_close($conn);
    } else {
        $result["success"] = "0";
        $result["message"] ="error";

        echo json_encode($result);
        mysqli_close($conn);

    }
} 

?>