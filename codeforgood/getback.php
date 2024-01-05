<?php

require_once 'dbconnect.php';
$db = new dbconnect();
$conn = $db->connect();


$response = array();
$detail = array();

$result = mysqli_query($conn,"SELECT name,age FROM student");

	if(mysqli_num_rows($result) > 0)
	{

				while($row = mysqli_fetch_array($result))
				{
					$detail["name"] = $row["name"];
					$detail["age"] = $row["age"];

					array_push($response,$detail);
					$fp = fopen('user.json','w');
					fwrite($fp, json_encode($response));
					fclose($fp);

					echo json_encode($response);
				}

	}			

?>