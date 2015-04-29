<?php include ('dbconn.php') ?>
<!DOCTYPE html>
<html lang="en">
<head>
     <meta charset="utf-8" />
     <title>Coro administrator page</title>
	 <script type = "text/javascript" src= "../validate.js"></script>
	 <link rel="stylesheet" href="../css/myclub.css">
	 <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Open+Sans">
</head>
<body>

	<h1 class = 'button' style = 'text-align: center' >Coro Admin Page </h1>
	<?php 
	createtable(); 
	displayMailSender();
	if (isset($_POST['submitted'])) {
		sendGroupMail();
	}
	?>

</body>
</html>



<?php
Function createtable() {
	$dbc = connectToDB("leeawg");
	$query = "SELECT name, mem_type, email, reg_date FROM myclub ORDER BY 2";
	$executeQuery = performQuery($dbc, $query);
?>
	<fieldset class='stitched'>

	<table class="TFtable">
	<tr>
		<th>Name</th>
		<th>Type</th>
		<th>Email</th>
		<th>Registration Date</th>
	</tr>
<?php	
	while ( @extract(mysqli_fetch_array($executeQuery, MYSQLI_ASSOC)) )
	{
		echo "<tr><td>$name</td>
		<td> $mem_type</td> 
		<td> $email</td>
		<td>$reg_date</td></tr>";
	}	
	?>
</table>
</fieldset>
<br>
<?php
}

Function displayMailSender(){
?>
	<fieldset class= 'join'>
	<legend class = input> Create group mail </legend>

	<form method = 'post' onsubmit = 'return validateAdmin();' name = 'myform'>
	To which membership type? <br>
	<input type = 'radio' name = 'level' value = 'Broro' /> Broro <br>
	<input type = 'radio' name = 'level' value = 'Freshrep' /> Freshrep <br>
	<input type = 'radio' name = 'level' value = 'Visitor' /> Visitor <br>
	<input type = 'radio' name = 'level' value = 'Lower' /> Lower <br>
	<div id="typeerror" class="error"></div>
	Subject: <input type = 'text' name = 'subject' /> 
	<div id="subjecterror" class="error"></div><br>
	Message: <textarea cols="40" rows='5' name='message'></textarea> <br>
 	<div id="messageerror" class="error"></div>
	Mail password: <input type = 'password' name = 'adminpw' /> <br>
	<div id="adminpwerror" class="error"></div>
	
	<input class = 'button' type = 'submit' name = 'submitted' value = 'Send the mail!' />
	</form>
	</fieldset>	
	

<?php
}

Function sendGroupMail()
{
	$dbc = connectToDB("leeawg");
	$selectedType = $_POST['level'];
	$query = "SELECT email FROM myclub WHERE mem_type='$selectedType';";
	$result = performQuery($dbc, $query);
		
	while ( $row = mysqli_fetch_array($result) )
	{
		$recipients[] = $row[0]; //extract the recipient mails from SQL result
	}
	$input_subj = $_POST['subject'];
	$input_msg = $_POST['message'];
	
	$imploded_recipients = implode(", ", $recipients); //Recipients are separated by comma from an array.
	//send mail
	$to= "$imploded_recipients";
	$subject="$input_subj";
	$body="$input_msg";
	$headers = "From: leeawg@bc.edu";
	if ( mail($to, $subject, $body, $headers) )
	{
		echo "
		<script type='text/javascript'>
		alert('Your group email has been sent!');   
		</script>"; //send a pop up message
	}
	else
	{
		echo "
		<script type='text/javascript'>
		alert('Sorry, your email was not sent.');   
		</script>";
	}
}
?>