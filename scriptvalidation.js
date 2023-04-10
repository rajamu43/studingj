var nameError=document.getElementById("name-error");
var passwdError=document.getElementById("pwd-error");
var mobilenoError=document.getElementById("mobileno-error");
var mailError=document.getElementById("mail-error");
var regidError=document.getElementById("regid-error");
var genderError=document.getElementById("gender-error");
var ageError=document.getElementById("age-error");
var addressError=document.getElementById("address-error");
var cardError=document.getElementById("card-error");
var upiError=document.getElementById("upi-error");
var pinError=document.getElementById("pin-error");
var feesError=document.getElementById("fees-error");
var text;
	   function namevalidation(){
			var names=document.getElementById("name").value;
			let nameValid="^[A-Za-z]*$";
				if(names === 0 || names.trim() === ""){
					text='name cannot be empty';
					nameError.innerHTML = text;
					alert("Name cannot be empty");
					return false;
					}
					if(names.length < 3){
					 text='Name Should be greater than 3 characters';
					 nameError.innerHTML = text;
					 alert("Name should be greater than 3 characters");
					 return false;
					 }
					 if(!names.match(nameValid)){
					  text='Invalid name';
				      nameError.innerHTML= text;
				      alert("Invalid Name");
					  return false;
					 }
					  nameError.innerHTML='valid name';
					  return true;
				}
				function mobileNovalidation(){
					var mobileNo=document.getElementById("mobileno").value;
					let mobileNoValid="[6-9][0-9]{9}";
					  if(mobileNo === 0){
						  text='mobile number cannot be empty';
						  mobilenoError.innerHTML = text;
						  return false;
						}
					  if(mobileNo.length < 10 ||mobileNo.length>12){
						  var mn='Mobile no must be contain 10 digits';
						   mobilenoError.innerHTML = mn;
						   return false;
						}
					  if(!mobileNo.match(mobileNoValid)){
							var mon='Invalid mobile numer';
							mobilenoError.innerHTML=mon;
							return false;
						}
						 mobilenoError.innerHTML='valid mobile no';
						 return true;
					}
					function pwdvalid(){
						var pass=document.getElementById("pwd").value;
						  if(pass.length > 6){
							text='input should be more then 6 characters';
							 passwdError.innerHTML = text;
							 return false;
						  }
						  passwdError.innerHTML='valid credentials';
						  return true;
					}
					function mailvalidation(){
						var mail=document.getElementById("mail").value;
						let mailValid="^(.+)@(.+)$";
							if(mail === 0 || mail.trim() === ""){
								text='mail id cannot be empty';
								 mailError.innerHTML = text;
								 return false;
							 }
							 if(!mail.match(mailValid)){
								  text='Invalid mail numer';
								  mailError.innerHTML=text;
								  return false;
							 }
								mailError.innerHTML='valid mail id';
								return true;
						}
						function regidvalidation(){
					var regno=document.getElementById("id").value;
					let regNoValid="\\d{4}";
					  if(regno === 0){
						  text='Userid cannot be empty';
						  regidError.innerHTML = text;
						  return false;
						}
					  if(regno.length < 4 || regno.length > 4){
						   text='user id must be contain 4 digits';
						   regidError.innerHTML = text;
						   return false;
						}
					  if(!regno.match(regNoValid)){
							text='Invalid User id';
							regidError.innerHTML=text;
							return false;
						}
						 regidError.innerHTML='valid Patient id';
						 return true;
					}
					function gendervalidation(){
					var gender=document.getElementById("gender").value;
					let genderValid="^male$|^female$";
					  if(gender === 0 || gender.trim() === ""){
						  text='gender cannot be empty';
						  genderError.innerHTML = text;
						  return false;
						}
					  if(!gender.match(genderValid)){
							text='Invalid select option';
							genderError.innerHTML=text;
							return false;
						}
						 genderError.innerHTML='your selected valid option';
						 return true;
					}
					function agevalidation(){
					var age=document.getElementById("age").value;
					  if(age === 0){
						  text='Age is cannot be empty';
						  ageError.innerHTML = text;
						  return false;
						}
					  if(age.length>3){
						   text='Give age is correctly';
						   ageError.innerHTML = text;
						   return false;
						}
					//  if(!age.match(ageValid)){
							//text='Invalid Age';
							//ageError.innerHTML=text;
							//return false;
						//}
						 ageError.innerHTML='valid age';
						 return true;
					}
					function addressvalidation(){
					var address=document.getElementById("address").value;
					let addressValid="^[0-9a-zA-Z\\s,-]+$";
					  if(address === 0 || address.trim() === ""){
						  text='address cannot be empty';
						  addressError.innerHTML = text;
						  return false;
						}
					  if(!address.match(addressValid)){
							text='Invalid address';
							addressError.innerHTML=text;
							return false;
						}
						 addressError.innerHTML='valid address';
						 return true;
					}
					function cardNovalidation(){
					var cardNo=document.getElementById("cid").value;
					let cardNoValid="\\d{16}";
					  if(cardNo === 0){
						  text='card number cannot be empty';
						  cardError.innerHTML = text;
						  return false;
						}
					  if(cardNo.length < 16){
						   text='Card no must be contain 16 digits';
						   cardError.innerHTML = text;
						   return false;
						}
					  if(cardNo.length > 16){
						   text='Card no must be contain 16 digits'
						   cardError.innerHTML = text;
						   return false;
					}
					  if(!cardNo.match(cardNoValid)){
							text='Invalid mobile numer';
							cardError.innerHTML=text;
							return false;
						}
						 cardError.innerHTML='verified sucessfully';
						 return true;
					}
					function upiNovalidation(){
					var upiNo=document.getElementById("pid").value;
					let upiNoValid="\\d{10}";
					  if(upiNo === 0){
						  text='upi number cannot be empty';
						  upiError.innerHTML = text;
						  return false;
						}
					  if(upiNo.length < 10){
						   text='upi no must be contain 10 digits';
						   upiError.innerHTML = text;
						   return false;
						}
					  if(upiNo.length > 10){
						   text='upi no must be contain 10 digits';
						   upiError.innerHTML = text;
						   return false;
					}	
					  if(!upiNo.match(upiNoValid)){
							text='Invalid upi number';
							upiError.innerHTML=text;
							return false;
						}
						 upiError.innerHTML='verified sucessfully';
						 return true;
					}
						function pinNovalidation(){
					var pinNo=document.getElementById("pinno").value;
					let pinNoValid="\\d{4}";
					  if(pinNo === 0){
						  text='pin number cannot be empty';
						  pinError.innerHTML = text;
						  return false;
						}
					  if(pinNo.length < 4){
						   text='pin No must be contain 4 digits';
						   pinError.innerHTML = text;
						   return false;
						}
						if(pinNo.length > 4){
							text='pin No must be contain 4 digits';
							pinError.innerHTML=text;
							return false;
						}
					  if(!pinNo.match(pinNoValid)){
							text='Invalid pin number';
							pinError.innerHTML=text;
							return false;
						}
						 pinError.innerHTML='verified sucessfully';
						 return true;
					}
					function hospitalfeesvalidation(){
					var fees=document.getElementById("df").value;
					let feesValid="^[0-9]+$";
					  if(fees === 0){
						  text='fees cannot be empty';
						  feesError.innerHTML = text;
						  return false;
						}
					  if(!fees.match(feesValid)){
							text='Invalid amount';
							feesError.innerHTML=text;
							return false;
						}
						 feesError.innerHTML='valid amount';
						 return true;
}