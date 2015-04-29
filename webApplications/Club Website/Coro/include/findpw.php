<?php
include ('dbconn.php')
?>
<!DOCTYPE html>
<html>
<head>
	<title>Find password action</title>
		<link rel="stylesheet" href="../css/myclub.css" />
</head>
<body>
<?php
	$email = $_POST['regEmail'];
	if (checkEmailExist($email))
	{
		resetPassword($email);
	} else {
		sendEmailNotFound();
	}
?>
</body>
</html>

<?php
Function checkEmailExist($regEmail)
{
	$dbc= connectToDB( "leeawg" );
	$q_emailCheck = "SELECT email FROM myclub WHERE email = '$regEmail';";
	$emailCheck_result = performQuery($dbc, $q_emailCheck);
	
	$emailCheck_extract = mysqli_fetch_array($emailCheck_result, MYSQLI_ASSOC);
	//print_r($emailCheck_extract);

	if  (mysqli_num_rows($emailCheck_result) == 0)
	{
		//echo "Email is not registered.";
		return false;
	}
	else
		//echo "Email found";
		return true;

	
}

Function sendEmailNotFound() //print error, add return link
{
	echo "Sorry, your email was not found. <br>";
	echo" <a href='http://cscilab.bc.edu/~leeawg/hw11/Coro/?join=clicked&findpw=Find+my+password'>Go back to the password reset page</a> ";
}

Function resetPassword($regEmail) //send email to the user
{
//set new password and update query.
	$newPassword = createNewPw();
	$newPasswordSecure = sha1($newPassword);
	$updateQuery = "UPDATE myclub SET password = '$newPasswordSecure' WHERE email = '$regEmail';";
	$dbc= connectToDB( "leeawg" );
	$result = performQuery($dbc, $updateQuery);
//send mail
	$to= $regEmail;
	$subject='Your password for Broro Club has been reset.';
	$body='Your password has been reset!
Your new password is: '. $newPassword. '
Please use your new password to login.';
	$headers = 'From: leeawg@bc.edu';
	if ( mail($to, $subject, $body, $headers) )
	{
		echo "Your password has been successfully reset! <br>";
		echo "Please check your email ". $regEmail . ". <br>";
		echo "<a href='http://cscilab.bc.edu/~leeawg/hw11/Coro/'>Go back to the main page</a>";
	}
	else
	{
		echo "Sorry. There was a problem. Please try again. <br>";
		echo"<a href='http://cscilab.bc.edu/~leeawg/hw11/Coro/?join=clicked&findpw=Find+my+password'>Go back to the password reset page</a> ";
	}
}

Function createNewPw(){
	$password="";
	$possible="23456789abcdefghjklmnpwrstuvwxyz";
	$password="";
	$length=8;
	for ($i=1; $i<=$length; $i++){
		$pick=rand(0, strlen($possible)-1);
		$passchar=substr($possible, $pick, 1);
		$password .= $passchar;
	}
	return $password;
}