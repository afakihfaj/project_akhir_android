<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $username = $_POST['username'];
    $password = $_POST['password'];
    $name = $_POST['name'];
    $address = $_POST['address'];
    $level = $_POST['level'];

    require_once 'koneksi.php';

    $sql = "INSERT INTO user (username, password, name, address, level) VALUES('$username', '$password', '$name', '$address', '$level')";

    if (mysqli_query($conn, $sql)) {
        $result["success"] = "1";
        $result["message"] = "success";

        echo json_encode($result);
        mysqli_close($conn);
    } else {
        $result["success"] = "0";
        $result["message"] = "error";

        echo json_encode($result);
        mysqli_close($conn);
    }
}
