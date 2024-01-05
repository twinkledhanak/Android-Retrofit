<?php

require_once 'dbconnect.php';
$db = new dbconnect();
$conn = $db->connect();

//if(isset($_POST['name']) && isset($_POST['age'])) // urldecode($_POST['name'])
//{

		//$name = urldecode($_POST["name"]);
		//$age = urldecode($_POST["age"]);
	   
if($_SERVER['REQUEST_METHOD']=='POST')
{
        $age = urldecode($_POST["age"]);
		$name =urldecode( $_POST["name"]);

		echo $name.'name is: ';
		echo $age.'age is: '; 

		if(isset($_POST["age"]) && isset($_POST["name"]))
		{
		
   
		$response = array();
		$stmt = $conn->prepare("INSERT INTO student(name,age) VALUES(?,?)");
		$stmt->bind_param("si",$name,$age);
		$result = $stmt->execute();

					if($result)
					{
						
						$response['result'] = "Inserted";
						$response['name'] = $name;
						$response['age'] = $age;

						echo json_encode($response);
					}

					else
					{
						$response['result'] = "Not getting inserted";
						$response['name'] = $name;
						$response['age'] = $age;

						echo json_encode($response);
					}
	    }

		else
		{
			$response['result'] = "Fields are empty";
			echo json_encode($response);
		}
}		
?>