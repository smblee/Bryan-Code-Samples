<?php

//get user's searched term. If it doesn't exist, it will be ""
$searchedTerm = isset($_GET['searchedTerm']) ? $_GET['searchedTerm'] : "";

//if user was searching
if ( isset($_GET['searching']) )
{
	$locationHeader = $_GET['searching'] == 'true' ? 
	'Location: index.php?searchtxt='.$searchedTerm.'&op=Search' : //if user was searching, send him back to the search queue
	'Location: index.php'; //if not, send him back to just index.php
}



//if user came here to clear hide attraction
if ( isset($_GET['clear']) )
{
	setcookie('omit', null, -1);
	header('Location: index.php');
}
//if user came here to hide attraction
$id = $_GET['id'];
isset($_COOKIE['omit']) ? setcookie('omit', $_COOKIE['omit'].','.$id) : setcookie('omit', $id);
header($locationHeader);
?>