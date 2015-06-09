<?php



function getMoviesAboveRating($rating)
{
	
	$conn=getDB();
	
	if($stmt=$conn->prepare("SELECT id, name, description,rating,url
	FROM Movies WHERE round(rating,1) >=round(?,1)
	  order by name DESC")){
		
		  $stmt->bind_param("s",$rating);
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