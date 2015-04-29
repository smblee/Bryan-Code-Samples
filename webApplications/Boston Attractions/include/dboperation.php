<?php
include ('dbconn.php');
?>
<?php
insertToDB();
?>
<?php
function geoCoding(){
	$address = $_POST['address'];
	$geocodeURL = "https://maps.googleapis.com/maps/api/geocode/xml?";
	$address = "address=" . urlencode($address);
	$key = "key=AIzaSyD0_slxssx5eUZomrTvaHGb1JZBkmAivBE";
	$geocoderequest = "$geocodeURL$address" . "&" . $key;
	
	//die( "The url is >" . $geocoderequest . "<" );
	
	$xml= new SimpleXMLElement( file_get_contents( $geocoderequest ) );   		
	if($xml->status != 'OK') {
		$status = $xml->error_message;
		header( "Location: ../index.php?status=badAddress");
	}

	$placeRequestURL = "https://maps.googleapis.com/maps/api/place/details/xml?";
	$placeID = "placeid=" . $xml->place_id;
	$placedetailsrequest = "$placeRequestURL$placeID" . "&" . $key;
	$xml2 = new SimpleXMLElement( file_get_contents( $geocoderequest ) );
	$loc = getLocation($xml);
	
	return $loc;
}


Function getLocation($xml)
{
	 $latitude  = $xml->result->geometry->location->lat;
     $longitude = $xml->result->geometry->location->lng;
        
     $location = array("latitude" => $latitude, "longitude" => $longitude);
        
     return ($location);
}

Function insertToDB()
{
	$location = geoCoding();
	$userID = $_POST['userID'];
	$name = mysql_real_escape_string ( $_POST['name'] );
	$category = $_POST['category'];
	$address = $_POST['address'];
	$latitude = $location['latitude'];
	$longitude = $location['longitude'];
	$phone = $_POST['phone'];
	$url = $_POST['url'];
	$rating = $_POST['rating'];
	$price = $_POST['price'];
	$comment = mysql_real_escape_string ( $_POST['comment'] );

	
	$query = "INSERT INTO bestofbc (entered_by, insertion_date, name, category, address, latitude, longitude, phone, url, stars, price_range, comment) VALUES
('$userID', curdate(), '$name', '$category', '$address', $latitude, $longitude, '$phone', '$url', '$rating', '$price', '$comment');";

//	echo "$query <br>"; 


	$dbc= connectToDB( "csci2254" );
	$result = performQuery($dbc, $query);
	if (! $result )
	{
		header('location: /index.php?status=insertFailed');
	}
	else
	{	
		header('location: ../index.php?status=insertSuccess');	
	}
}
?>