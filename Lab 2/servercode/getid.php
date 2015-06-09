<?php



function  getMovieDetail($movieid)
{
	$conn=getDB();
	
	if($stmt=$conn->prepare("SELECT id, name, description,rating,url
	FROM Movies WHERE id=?
	  order by name DESC")){
		
		  $stmt->bind_param("s",$movieid);
		  $stmt->execute();
		  
	 
	    
		  
	$result=$stmt->get_result();
	$return_arr=array();
	while($row=$result->fetch_assoc())
	{
		array_push($return_arr,$row);
	}
	echo json_encode($return_arr);
	$conn->close();
}	
}
								 

?>