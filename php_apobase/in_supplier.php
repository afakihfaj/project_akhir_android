<?php
if ($_SERVER['REQUEST_METHOD'] =='POST'){

    $name = $_POST['name'];
    $phone = $_POST['phone'];
    $address = $_POST['address'];
    $description = $_POST['description'];

    require_once 'koneksi.php';

    $sql = "INSERT INTO supplier (name, phone, address, description) VALUES('$name', '$phone', '$address', '$description')";
    
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