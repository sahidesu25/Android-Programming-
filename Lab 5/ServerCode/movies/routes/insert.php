<?php

function insert($data)
{
	$conn=getDB();
	$id=$data['id'];
	$name=$data['name'];
	$description=$data['description'];
	$rating=$data['rating'];
	$url=$data['url'];
	$director=$data['director'];
	$stars=$data['stars'];
	
	
	
	    $sql="INSERT INTO Movies(id,rating,name,description,url,director,stars) VALUES('$id',$rating,'$name','$description','$url','$director','$stars')";
		//die('There was an error running the query [' .$conn->error . ']\n');
		if(!$result = $conn->query($sql)){
		die('There was an error running the query [' .$conn->error . ']\n');
	}
		//$stmt->execute();
		  $conn->close();
	
}
?>
