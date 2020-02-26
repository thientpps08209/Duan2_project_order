<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'connect.php';

$key = $_POST['key'];

if ( $key == "update" ){
    $id               = $_POST['id'];
    $name             = $_POST['name'];
    $price            = $_POST['price'];
    $description      = $_POST['description'];
    $picture          = $_POST['picture'];
  //$create_day       = $_POST['create_day'];
    
    $create_day =  date('Y-m-d', strtotime($create_day));

    $query = "UPDATE products SET 
    name='$name',  
    description='$description',
    price='$price',
    //create_day='$create_day',
    WHERE id='$id' ";

        if ( mysqli_query($conn, $query) ){

            if ($picture == null) {

                $result["value"] = "1";
                $result["message"] = "Success";
    
                echo json_encode($result);
                mysqli_close($conn);

            } else {

                $path = "product_picture/$id.jpeg";
                $finalPath = "/asm_rofood/product/".$path;

                $insert_picture = "UPDATE products SET picture='$finalPath' WHERE id='$id' ";
            
                if (mysqli_query($conn, $insert_picture)) {
            
                    if ( file_put_contents( $path, base64_decode($picture) ) ) {
                        
                        $result["value"] = "1";
                        $result["message"] = "Success!";
            
                        echo json_encode($result);
                        mysqli_close($conn);
            
                    } else {
                        
                        $response["value"] = "0";
                        $response["message"] = "Error! ".mysqli_error($conn);
                        echo json_encode($response);

                        mysqli_close($conn);
                    }

                }
            }

        } 
        else {
            $response["value"] = "0";
            $response["message"] = "Error! ".mysqli_error($conn);
            echo json_encode($response);

            mysqli_close($conn);
        }
}

?>