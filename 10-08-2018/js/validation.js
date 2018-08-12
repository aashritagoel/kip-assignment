function validate()
{
 return (nameValidate() &&
   emailValidate() && cnfEmailValidate() && genderValidate() && phoneNoValidate());
}

function nameValidate(){
  var NameErr = document.getElementById('firstName_error');
  if( document.myForm.firstName.value == "" )
  {
    NameErr.innerHTML = "Firstname cann't be left blank!";
    document.myForm.firstName.focus() ;
    return false;
  }
  else {
   NameErr.innerHTML = "";
 }
 return true;
}

function emailValidate(){
 var email = document.myForm.email.value;
 var EmailErr = document.getElementById('email_error');
 
 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm.email.value))
 {
  EmailErr.innerHTML = "";
  return true;
}
else {
 EmailErr.innerHTML ="Invalid Email!";
 document.myForm.email.focus() ;
 return false;
}
}

function cnfEmailValidate(){
 var confirmEmailError=document.getElementById('confirmEmail_error');
 if( document.myForm.email.value != document.myForm.confirmEmail.value )
 {

   confirmEmailError.innerHTML="Entered email-id doesn't match";
   return false;
 }
 else{
  confirmEmailError.innerHTML="";
}
return true;
}

function genderValidate(){

 var genderError = document.getElementById('gender_error');
 var maleRadioButton = document.myForm.maleRadio;
 var femaleRadioButton = document.myForm.femaleRadio;
 if ( ( maleRadioButton.checked == false ) && ( femaleRadioButton.checked == false ) )
 {
  genderError.innerHTML = "Select your Gender" ;
  return false;
}
else {
  genderError.innerHTML = "";
  
}
return true;

}

function phoneNoValidate(){
 var phn_error = document.getElementById('phoneNo_error');
 if( document.myForm.phoneNumber.value == "" ||
  isNaN( document.myForm.phoneNumber.value) ||
  document.myForm.phoneNumber.value.length != 10 )
 {

  phn_error.innerHTML = "Phone number must be 10 digits long." ;
  document.myForm.phoneNumber.focus() ;
  return false;
}
else {
 phn_error.innerHTML = "";
}

return (true);
}

