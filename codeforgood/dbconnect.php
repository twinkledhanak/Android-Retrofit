<?php

class dbconnect
{
	private $conn;
	function _construct()
	{
		
	}

	function connect()
	{
		include_once dirname(_FILE_) . '/config.php';
		$this->conn = new mysqli(hostname,username,password,dbname);

		if(mysqli_connect_errno())
			echo 'Problem connecting to database';
		else
			echo 'Connected to database';

		return $this->conn;
	}
}


?>