function validate()
{
 return (nameValidate(document.myForm.firstName.value) && 
         nameValidate(document.myForm.middleName.value) &&
         nameValidate(document.myForm.lastName.value) &&
         emailValidate(document.myForm.email.value) && 
         passwordValidate() && 
         hobbiesValidate()
         );
}

function nameValidate(name){
  console.log(name);
  var NameErr = document.getElementById('firstName_error');
  if( /^[a-zA-Z]*$/.test(name) )
  {
    NameErr.innerHTML = "";   
    return true; 
  } else {
    NameErr.innerHTML = "   *Name should not contain numbers and special characters!";
    document.myForm.firstName.focus() ;
    return false;
  }
}


function emailValidate(email){
 var EmailErr = document.getElementById('email_error');
 
 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))
 {
  EmailErr.innerHTML = "";
  return true;
 } else {
   EmailErr.innerHTML ="Invalid Email!";
   document.myForm.email.focus() ;
   return false;
 }
}

function passwordValidate(){
 var confirmPasswordError=document.getElementById('confirmPassword_error');
 if( document.myForm.password.value != document.myForm.confirmPassword.value )
 {
   confirmPasswordError.innerHTML=" * Passwords do not match. Please check!";
   return false;
 }
 else{
  confirmPasswordError.innerHTML="";
  return true;
 }
}


function hobbiesValidate(){
 var hobbyError = document.getElementById('hobbies_error');
 if( $('input:checkbox').is(':checked'))
 {
   hobbyError.innerHTML = "";
   return true;
 } else {
  hobbyError.innerHTML = "* Choose atleast one hobby !" ;
  return false;
  }
}

