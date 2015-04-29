
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Coro Club Page</title>
	<link rel="stylesheet" href="css/myclub.css">
	<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Open+Sans">
	<script type = "text/javascript" src= "validate.js"></script>
</head>
<body>
<?php
if(isset($_GET['join'])) {
	if (isset($_GET['findpw'])) {
		displayfindpwform();
	} else {
	displayjoinform();
	}
} else {
	displaymain();
}

?>
 
</body>
</html>

<?php
Function displaymain() {
?>
	<form method="get">
		<fieldset class=stitched> 
			<h1>Welcome to College Road Club!!</h1>
		</fieldset>
		<br>
		<fieldset class = 'joinform'>
		<p class=h_paragraph>
		<h2 class=h_headline> Useful Links </h2>
		<ul>
		<li> 4.0er definition: <a href= "http://www.urbandictionary.com/define.php?term=4.0er"> Click here </a> </li>
		<li> Map of BC: <a href= "http://www.bc.edu/a-z/maps/s-chestnuthill/_jcr_content/content/bcimage.img.png/1418676330793.png"> Click here </a> </li>
		<li> Reslife Twitter: <a href= "https://www.twitter.com/bc_reslife"> Click here </a> </li>
		<li> BC Portal: <a href= "http://portal.bc.edu/"> Click here </a> </li>
		<li> BC Foodpro: <a href= "http://foodpro.bc.edu/foodpro/location.asp"> Click here </a> </li>
		</ul>
		</fieldset>
		<fieldset class = 'picturedisplay'>
		<p class=h_paragraph>
		<p class='centeredImage'><img class='img' src="img/coro.jpg" alt="coro image" /> </p>
		</fieldset>		
		
		<fieldset class = 'weatherdisplay'>
		<p class=h_paragraph>
		
		
		<?php

		$weatherloc = "http://w1.weather.gov/xml/current_obs/KBOS.xml";	
		$xml_city = new SimpleXMLElement(file_get_contents($weatherloc) );
		$location = $xml_city-> location;

		$observed_time = $xml_city -> observation_time;
		$weather_icon = "http://forecast.weather.gov/images/wtf/small/" . $xml_city -> icon_url_name;
		$temperature = $xml_city -> temperature_string;
		$weather = $xml_city -> weather;
		$wind = $xml_city -> wind_string;

		echo "<h2 class=h_headline> Boston Weather</h2><br>";
		//echo "<h2>$location </h2>";
		echo "$weather ";

		echo "<img src='$weather_icon' alt = 'weather icon' style = 'float:left' /> <br><br>";
		echo "<ul>";
		echo "<li>$observed_time</li>";
		echo "<li>Temperature: $temperature</li> ";
		echo "<li>Wind: $wind</li>";
		echo "</ul>";

		?>
		</fieldset>		
		
		 
		<br>
		<fieldset class=join>
		
			<input type = 'hidden' name ='join' value ='clicked'/>
			<input type = 'submit' value ='Click here to join!' class = 'button' /> <br>
		</fieldset>
			<div style="text-align: center;">
			<input type = 'submit' value = 'Find my password' class = 'button2' name = 'findpw' />
			</div>
			
</form>
<?php	
}

Function displayjoinform() {
?>
	<form method = "post" action= "include/dboperation.php" onsubmit = "return validate();" name ="myform">

		<fieldset class=joinform>
		<h1> registration form </h1>
		Name: <input type = 'text' name = 'name' class = 'input' /> (ex. John Smith)  
		<div id="nameerror" class="error"></div>
		Email:  <input type = 'text' name = 'email' class = 'input' /> (ex. abc@bc.edu)
		<div id="emailerror" class="error"></div>		
		Password:<input type = 'password' name = 'password' class ='input' /><br>
		<div id="pwerror" class="error"></div>
		PW check:<input type = 'password' name = 'passwordCheck' class ='input' /><br>
		<div id="pwchkerror" class="error"></div>
		What are you: <br> 
		<input type = 'radio' name = 'level' value = 'Broro' class ='input' /> Part of the Broro<br>
		<input type = 'radio' name = 'level' value = 'Freshrep' class ='input' /> Broro Freshrep <br>
		<input type = 'radio' name = 'level' value = 'Visitor' class ='input' /> Visitor<br>
		<input type = 'radio' name = 'level' value = 'Lower' class ='input' /> I'm from Lower<br>
		<div id="typeerror" class="error"></div>
		<input type= 'hidden' name = 'joined' value = 'true'/>
		<br>
		<input type= 'submit' value = 'Join!' class= 'button2' />
		</fieldset>
		
	</form>
		
<?php
}

Function displayfindpwform() {
?>
	<form method = 'post' action = 'include/findpw.php'>
	<fieldset class=joinform>
	<legend class = input> Reset my password!  </legend>
		<div style="text-align: center;">
		Registered email: <input type = 'text' name = 'regEmail' class = 'input' /> <br><br>
		<input type = 'submit' value = 'Reset!' class = 'button2' />
		</div>
	</fieldset>
	</form>
<?php
}
?>


