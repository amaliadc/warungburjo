<?php

include 'main.php';

$conn = mysqli_connect($db_hostname, $db_username, $db_password, $db_database);

if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}



    $sql = "SELECT * FROM menu";

 $result = mysqli_query($conn, $sql) or die("Error in Selecting " . mysqli_error($conn));

   
   $rows = array();
while($r = mysqli_fetch_assoc($result)) {
    $rows[] = $r;
}
echo json_encode($rows);

mysqli_close($conn);
    
?>