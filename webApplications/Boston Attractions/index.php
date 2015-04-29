<?php
include ('include/dbconn.php')
?>
<!doctype html>

<html lang="en">
<head>
  <meta charset="utf-16">

  <title>Best of BC</title>
  <meta name="description" content="Best of BC">
  <meta name="author" content="Seungmin Lee">
  <link rel="stylesheet" href="css/styles.css?v=1.0">
  <script type = "text/javascript" src= "include/validate.js"></script>
</head>
<body>
<?php

$op = isset($_GET['op']) ? $_GET['op'] : "";
switch ($op){
	case 'Add Attraction':
		displayInsertAttractionForm();
		break;
	case 'Search':
		displayHomePage($_GET['searchtxt']);
		break;
	default:
		displayHomePage("");
		break;
}



?>
</body>
</html>

<?php


function displayHomePage($string)
{
?>
	<fieldset>
	<legend> Options </legend>
	<form method = 'get'>
		<input type = 'text' name = 'searchtxt' /> 
		<input type = 'submit' name = 'op' value = 'Search' /> <br><br>
		<input type = 'submit' name = 'op' value = 'Add Attraction' class = 'btn'/><br>
	</form>
	<form method = 'get' action = 'cookieh.php'>
		<input type = 'hidden' name = 'clear' value = 'yes' />
		<input type = 'submit' name = 'op' value = 'Clear Hide Attraction' />
	</form>

	</fieldset>

	
<?php
	displayAttractions($string);
}

function displayAttractions($str)
{
	$dbc = connectToDB('csci2254');
	$color = "lightblue";
	//set searching variable that will be used in cookieh.php for headers.
	$searching = $str == '' ? 'false' : 'true';
	//if string is empty or not empty, query will change. (cookie doesn't exist here)
	$query = $str == '' ? "SELECT * FROM bestofbc" : 
	"SELECT * FROM bestofbc WHERE 
	(
	name LIKE '%$str%' OR 
	category LIKE '%$str%' OR 
	address LIKE '%$str%' OR 
	url LIKE '%$str%' 
	OR stars LIKE '%$str%' 
	OR comment LIKE '%$str%' 
	OR price_range LIKE '%$str%'
	)
	";

	// if search is empty AND cookie exists
	if ($str == '' && isset($_COOKIE['omit']) )
	{	
		$omitList = $_COOKIE['omit'];
		$query = $query." WHERE attraction_id NOT IN (".$omitList.")";
	}
	// if search exists AND cookie exists
	if ($str != '' && isset($_COOKIE['omit']) )
	{
		$omitList = $_COOKIE['omit'];
		$query = $query." AND attraction_id NOT IN (".$omitList.")";
	}
	
	$result = performQuery($dbc,$query);
	$rowsFound = mysqli_num_rows($result);
	echo "$rowsFound Attractions were found. <br>";
	$status = isset($_GET['status']) ? $_GET['status'] : "";
//display error statuses if there are any.	
	switch($status) {
		case 'badAddress':
			echo "<span class='error'>Your attraction was not added because you have entered an invalid address. </span>";
			break;
		case 'insertFailed':
			echo "<span class='error'>Your attraction was not added due to Database error. </span>";
			break;
		case 'insertSuccess':
			echo "<span class='success'>Your attraction was successfully added! </span>";
			break;
		default:
	}

	
	echo "<table>";
	echo "<tr>";
	echo "<th>Attraction</th>";
	echo "<th>Comment</th>";
	echo "</tr>";

	//Cookie testing purpose.
/*	if ( isset($_COOKIE['omit']) )
		print_r($_COOKIE['omit']);	*/

	//Extract all the values. The while loop will extract everything to variable corresponding to the table name.
		while (@extract(mysqli_fetch_array($result, MYSQLI_ASSOC))) {
			$color = $color == "lightblue" ? "lightgreen": "lightblue";
			echo "
			<tr style='background-color: $color'>
			<td> 
			<form method = 'get' action = 'cookieh.php'>
			<strong>$name</strong> $stars $price_range <br> 
			$url <br> 
			$address
			<input type = 'hidden' name = 'searching' value = '$searching' />
			<input type = 'hidden' name = 'searchedTerm' value = '$str' />
			<br>$str
			<input type = 'hidden' name = 'id' value = '$attraction_id' /> 
			<br><br><input type = 'submit' name = 'hide' value = 'Hide attraction' />
			</form>
			</td> 
			<td> $comment </td></tr>\n";
		}
	echo "</table>";
	disconnectFromDB($dbc,$result);
}

function displayInsertAttractionForm()
{
?>
	<h1>Best of BC</h1>
	<fieldset>
	<legend> Add an Attraction </legend>
	<form method = 'post' onsubmit = 'return validate();' name = 'myform' action = 'include/dboperation.php'>
		Name: <input type = 'text' name = 'name' />
		<span id="nameerror" class="error"></span> <br><br>
		Star Rating:
		<select name = 'rating'>
			<option value = 'default'> Please choose </option> 
			<option value = '*'> &#9733; </option> 
			<option value = '**'> &#9733;&#9733; </option>
			<option value = '***'> &#9733;&#9733;&#9733; </option>
			<option value = '****'> &#9733;&#9733;&#9733;&#9733; </option>
			<option value = '*****'> &#9733;&#9733;&#9733;&#9733;&#9733; </option>
		</select>	
		<span id="ratingerror" class="error"></span>
		<br><br>
		Price:
		<select name = 'price'>
			<option value = 'default'> Please choose </option> 
			<option value = '$'> $ </option> 
			<option value = '$$'> $$ </option>
			<option value = '$$$'> $$$ </option>
			<option value = '$$$$'> $$$$ </option>
			<option value = '$$$$$'> $$$$$ </option>
		</select>		
		<span id="priceerror" class="error"></span>		
		<br><br>
		Category: <input type = 'text' name = 'category' /> 
		<span id="categoryerror" class="error"></span> <br><br>
		Address: <input type = 'text' name = 'address' size = '55'/>
		<span id="addresserror" class="error"></span>		<br><br>
		Phone: <input type = 'text' name = 'phone' /> 
		<span id="phoneerror" class="error"></span> <br><br>
		URL: <input type = 'text' name = 'url' /> 
		<span id="urlerror" class="error"></span> <br><br>
		Your UserID: <input type = 'text' name = 'userID' /> 
		<span id="userIDerror" class="error"></span> <br><br>
		Comment:<br> <textarea name = 'comment' cols= '50' rows = '5'></textarea> 
		<span id="commenterror" class="error"></span> <br><br>
		<input type = 'submit' name = 'submitted' value = 'Add' /> <br>
		
	</form>
	</fieldset>


<?php
}