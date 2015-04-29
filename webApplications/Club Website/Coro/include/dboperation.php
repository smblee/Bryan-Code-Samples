<?php
include ('dbconn.php')
?>
<!DOCTYPE html>
<html>
<head>
	<title>Check DB</title>
		<link rel="stylesheet" href="../css/myclub.css" />
</head>
<body>


<?php 


$join_password = $_POST['password'];
$join_passwordcheck = $_POST['passwordCheck'];
// First check if password retype is valid.
passwordCheck($join_password, $join_passwordcheck);



	?>
</body>
</html>
<?php
Function passwordCheck($original, $check)
{
	if ($original != $check) {	
	errorform('password');
	} 
	else
		emailcheck();
}

Function emailcheck()
{
	$dbc= connectToDB( "leeawg" );
	$join_name =  $_POST['name'];
	$join_email = $_POST['email'];
	$join_password = $_POST['password'];
	$join_securepw = sha1($join_password);
	$join_level = $_POST['level'];
	
	$q_emailCheck = "SELECT email FROM myclub WHERE email = '$join_email';";
	
	$emailCheck_result = performQuery($dbc, $q_emailCheck);
	$emailCheck_duplicate = mysqli_fetch_array($emailCheck_result, MYSQLI_ASSOC);

	if (mysqli_num_rows($emailCheck_result) == 0)
	{
		//echo "no duplicate :)";
		$query = "INSERT INTO myclub VALUES ( '$join_name', '$join_email', '$join_securepw', now(), '$join_level' )";
		insert($dbc, $query);
	}
	else
	{	
		//echo "There was a duplicate.";
		errorform('email');
	}	
}

Function insert($dbc, $query)
{
	$result = performQuery($dbc, $query);
	if (! $result )
	{
		//echo "<br> Registration failed";
		errorform('insert');
	}
	else
		//echo "<br> Insert success - $query";
		successform();
}

Function errorform($reason)
{
	Switch($reason) {
		Case "password":
			$msg =  "Your password did not match. Please try again.";
			break;
		Case "email":
			$msg = "You are already registered with the same email. Please try again.";
			break;
		Case "insert":
			$msg = "Database failed to insert to the system. Please check that you typed in all the fields.";
			break;
		Case "emptyFields":
			$msg = "You must fill in all the fields! Please try again.";
			break;
		default:
			$msg = "unexpected error.";
			break;

	}	
	echo "$msg";
	echo "<br>";
	echo "<a href='../index.php?join=clicked'> Go back to registration </a> ";

	
}

Function successform()
{
	echo "SUCCESS!! YOU ARE OFFICIALLY PART OF CORO <br>";
	echo" <a href='../index.php'>Go back the main page</a> ";
}
