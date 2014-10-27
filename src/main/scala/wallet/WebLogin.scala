package wallet
import javax.validation.constraints.NotNull
import org.springframework.data.annotation.Id;
class WebLogin {
 @Id 
 var loginid :String=_;
 @NotNull
 var url  : String=_;
 @NotNull
 var login : String=_;
 @NotNull
  var password : String="";
  var userid : String = _

	def setUserid(userid: String)
	{
		this.userid = userid
	}

	def getUserid(): String = userid   
 
 def getloginid():String={
      return this.loginid;
   }
   
   def setloginid(counter:Int)={
     this.loginid=("l"+counter);
     
   }
   def geturl():String={
     return this.url;
   }
   def seturl(url:String){
     this.url=url;
   }
   
    def getlogin():String={
     return this.login;
   }
   def setname(login:String){
     this.login=login;
   }
   
   def getpassword():String={
     return this.password;
   }
   def setpassword(password:String){
     this.password=password;
   }
}
