<?php 

require_once 'connect.php';

$key = $_POST['key'];

$name             = $_POST['name'];
$price            = $_POST['price'];
$description	  = $_POST['description'];
$picture          = $_POST['picture'];


if ( $key == "insert" ){

    $create_day_newformat = date('Y-m-d', strtotime($create_day));

    $query = "INSERT INTO products (name,price,description,picture)
    VALUES ('$name', '$price','$description', '$picture') ";

        if ( mysqli_query($conn, $query) ){

            if ($picture == null) {

                $finalPath = "/asm_rofood/product/food_logo.png"; 
                $result["value"] = "1";
                $result["message"] = "Success";
    
                echo json_encode($result);
                mysqli_close($conn);

            } else {

                $id = mysqli_insert_id($conn);
                $path = "product_picture/$id.jpeg";
                $finalPath = "/product/".$path;

                $insert_picture = "UPDATE products SET 	picture='$finalPath' WHERE id='$id' ";
            
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