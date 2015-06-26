<?php

function getDB() {
	$dbhost="localhost";
	$dbuser="root";
	$dbpass="";
	$dbname="androidMovieDB";
	
	
	//Create DB Connection 
	
	$conn=new mysqli($dbhost,$dbuser,$dbpass,$dbname);
	if($conn->connect_error){
		die("Connection failed: " . $conn->connection_error . "\n");
	}
	
	return $conn;
}
?>