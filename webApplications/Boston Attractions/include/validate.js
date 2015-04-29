function validate() {
	//var nameValid = validateName();
	var vars = ["name", "category", "address", "userID", "comment"];
	var selectVars = ["rating","price"];
	var validSelects = validateSelects(selectVars);
	var validURL = validateURL();
	var validInputs = validateInputs(vars);
	var validPhone = validatePhone();
	if(validPhone && validURL &&validInputs && validateSelects)
		return true;
	return false;
}

function validateInputs(vars)
{
	var counter = 0;
	for (i = 0; i < vars.length; ++i) {	
		var variable = vars[i];
		var errorname = variable + "error";
		var value = document.forms["myform"][variable].value;
		if (value.length < 1) {
			var errorrpt = document.getElementById(errorname);
			errorrpt.innerHTML = "No " + variable + " entered!";
			counter++;
		} else
		{
		var errorrpt = document.getElementById(errorname);
		errorrpt.innerHTML = "";
		}
	}
	if (counter == 0)
		return true;
	else
		return false;	
}

function validateSelects(vars)
{
	var counter = 0;
	for (i = 0; i < vars.length; i++)
	{
		var variable = vars[i];
		var errorname = variable + "error";
		var value = document.forms["myform"][variable].value;
		if (value == "default")
		{
			var errorrpt = document.getElementById(errorname);
			errorrpt.innerHTML = "No " + variable + " entered!";
			counter++;
		} else
		{
		var errorrpt = document.getElementById(errorname);
		errorrpt.innerHTML = "";
		}
	}
	if (counter == 0)
		return true;
	else
		return false;			
	}
	
function validatePhone() {
	var thephone = document.forms["myform"]["phone"].value;
	var phoneregex=/^\d{3}\-\d{3}\-\d{4}$/;
	if (!phoneregex.test(thephone)) {
		var errorrpt=document.getElementById("phoneerror");
		errorrpt.innerHTML = "Please enter your phone number " +
								"in the format XXX-XXX-XXXX";
		return false;
	} 
	var errorrpt=document.getElementById("phoneerror");
	errorrpt.innerHTML = "";
	return true;
}

function validateURL() {
	var theurl = document.forms["myform"]["url"].value;
	var urlregex=/((([A-Za-z]{3,9}:(?:\/\/)?)(?:[-;:&=\+\$,\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\+\$,\w]+@)[A-Za-z0-9.-]+)((?:\/[\+~%\/.\w-_]*)?\??(?:[-\+=&;%@.\w_]*)#?(?:[\w]*))?)/;
	if (!urlregex.test(theurl)) {
		var errorrpt=document.getElementById("urlerror");
		errorrpt.innerHTML = "Please enter URL in correct format";
		return false;
	} 
	var errorrpt=document.getElementById("urlerror");
	errorrpt.innerHTML = "";
	return true;
}
