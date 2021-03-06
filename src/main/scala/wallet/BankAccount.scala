package wallet
import javax.validation.constraints.NotNull
import org.springframework.data.annotation.Id;
class BankAccount {
    @Id
    var ba_id :String=_;
    var account_name :String=_;
    @NotNull
    var routing_number: String=_;
    @NotNull
    var account_number: String="";
    
    var userid : String = _

	def setUserid(userid: String)
	{
		this.userid = userid
	}

	def getUserid(): String = userid   
   
    def getba_id():String={
      return this.ba_id;
   }
   
   def setba_id(counter:Int)={
     this.ba_id=("b"+counter);
     
   }
   
   
   
   def getaccount_name():String={
     return this.account_name;
   }
   def setaccount_name(account_name:String){
     this.account_name=account_name;
   }
   
   def getrouting_number():String={
     return this.routing_number;
   }
   def setrouting_number(routing_number:String){
     this.routing_number=routing_number;
   }
   
   def getaccount_number():String={
     return this.account_number;
   }
   def setaccount_number(account_number:String){
     this.account_number=account_number;
   }
}
