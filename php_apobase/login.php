<?php

if($_SERVER['REQUEST_METHOD']=='POST'){

 $username= $_POST['username'];
 $password= $_POST['password'];

 require_once 'koneksi.php';

 $sql ="SELECT * FROM user WHERE username='$username'";
 $respone = mysqli_query($conn, $sql);

 $result = array();
 $result['login'] = array();

 if (mysqli_num_rows($respone) == 1) {

    $row = mysqli_fetch_assoc($respone);
    
    if (sha1($password) == $row['password'] ) {

        $index['name'] =$row['name'];
        $index['username']=$row['username'];

        array_push($result['login'], $index);

        $result['success'] = "1";
        $result['message'] = "success";
        echo json_encode($result);

        mysqli_close($conn);

    }else{
        $result['error'] = "0";
        $result['message'] = "error";
        echo json_encode($result);
    }
 }

}

?>