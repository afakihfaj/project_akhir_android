<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
    include 'koneksi.php';
    $username = $_POST['username'];
    $password = $_POST['password'];
    $Sql_Query = "SELECT * FROM user WHERE username='$username' and password='$password'";
    $check =mysqli_fetch_array(mysqli_query($conn, $Sql_Query));
    if(isset($check)){
        echo "Data Matched";
    }
    else{
        echo "invalid username or password";
    }
}

?>