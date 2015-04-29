	function validate() {
		var nameValid = validateName();
		var emailValid = validateEmail();
		var pwValid = validatePw();
		var pwchkValid = validatePwchk();
		var typeValid = validateType();
		if (nameValid && emailValid && pwValid && pwchkValid && typeValid)
			//header("Location: http://")
			return true;
		return false;
	}
	
	function validateAdmin() {
		var typeValid = validateType();
		var subjectValid = validateSubject();
		var messageValid = validateMessage();
		var adminpwValid = validateAdminpw();
		if (typeValid && subjectValid && messageValid && adminpwValid)
			return true;
		return false;
	}
	
	function validateSubject() {
		var subject = document.forms["myform"]["subject"].value;
		if (subject.length < 1) {
			var errorrpt = document.getElementById("subjecterror");
			errorrpt.innerHTML = "Please enter your subject.";
			return false;
		}
		var errorrpt = document.getElementById("subjecterror");
		errorrpt.innerHTML = "";
		return true;
	}
	
	function validateMessage() {
		var message = document.forms["myform"]["message"].value;
		if (message == " ") {
			var errorrpt = document.getElementById("messageerror");
			errorrpt.innerHTML = "Please enter your message.";
			return false;
		}
		var errorrpt = document.getElementById("messageerror");
		errorrpt.innerHTML = "";
		return true;
	}
	
	function validateAdminpw() {
		var adminpw = document.forms["myform"]["adminpw"].value;
		if (adminpw != 'Napoleon') {
			var errorrpt = document.getElementById("adminpwerror");
			errorrpt.innerHTML = "The password was incorrect.";
			return false;
		}
		var errorrpt = document.getElementById("adminpwerror");
		errorrpt.innerHTML = "";
		return true;
	}
	
	
	
	function validateName() {
		var name = document.forms["myform"]["name"].value;
		if (name.length < 1) {
			var errorrpt = document.getElementById("nameerror");
			errorrpt.innerHTML = "Please enter your name";
			return false;
		}
		var errorrpt = document.getElementById("nameerror");
		errorrpt.innerHTML = "";
		return true;
	}
	
	function validateEmail() {
		var email = document.forms["myform"]["email"].value;
		var emailRegex = /^\S+@\S+\.\S+$/;
		if (email.length < 1) {
			var errorrpt = document.getElementById("emailerror");
			errorrpt.innerHTML = "Please enter your email";
			return false;
		}		
		if (!emailRegex.test(email))
		{
			var errorrpt = document.getElementById("emailerror");
			errorrpt.innerHTML = "Please enter valid email";
			return false;
		}
		return true;
		
	}
	
	function validatePw() {
		var pw = document.forms["myform"]["password"].value;
		if (pw.length < 1) {
			var errorrpt = document.getElementById("pwerror");
			errorrpt.innerHTML = "Please enter your password.";
			return false;
		}
		if (pw.length < 5) {
			var errorrpt = document.getElementById("pwerror");
			errorrpt.innerHTML = "Your password is too short! The length must be greater than 5.";
			return false;
		}
		var errorrpt = document.getElementById("pwerror");
		errorrpt.innerHTML = "";
		return true;
	}

	function validatePwchk() {
		var pwchk = document.forms["myform"]["passwordCheck"].value;
		if (pwchk.length < 1) {
			var errorrpt = document.getElementById("pwchkerror");
			errorrpt.innerHTML = "Please enter your password check.";
			return false;
		}
		var errorrpt = document.getElementById("pwchkerror");
		errorrpt.innerHTML = "";
		return true;
	}
	
	function validateType() {
		var type = document.forms["myform"].level;
		var typeLength = type.length;
		for (var i=0; i < typeLength; i++) {
			if(type[i].checked){
				var errorrpt = document.getElementById("typeerror");
				errorrpt.innerHTML = "";
				return true;
			}
		}
		var errorrpt = document.getElementById("typeerror");
		errorrpt.innerHTML = "Please choose one of the options.";
		return false;
	}