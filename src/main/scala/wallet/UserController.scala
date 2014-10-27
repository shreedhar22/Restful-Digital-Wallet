package wallet
import scala.collection.mutable.ArrayBuffer
import Array._
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import scala.collection.mutable.Set
import scala.collection.mutable.MultiMap
//import scala.collection.mutable.List

import java.util.concurrent.atomic.AtomicLong
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
//import java.util.HashMap;
import org.springframework.web.bind.annotation._
import javax.validation.Valid                          // import this for validation
import org.springframework.validation.BindingResult 
import org.springframework.context.annotation.Configuration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan


//Conditional Get packages
import org.springframework.http._
import org.springframework.http.ResponseEntity;
import javax.ws.rs.core.CacheControl
import javax.ws.rs.core.EntityTag
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.github.nscala_time.time.Imports._;


//SpringData MongoDB packages//
import org.springframework.data.mongodb.core.query.Criteria.where;

import org.springframework.data.mongodb.core.query.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core._
import com.mongodb.Mongo;
import java.util.List;

import org.springframework.data.mongodb.core.query.BasicQuery
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import javax.annotation.Resource
import org.springframework.transaction.annotation.Transactional
import org.springframework.data.mongodb.core.mapping.DBRef
import org.bson.types.ObjectId

//MongoDB Casbah packages
//import com.mongodb.casbah.Imports._
//import com.mongodb.casbah.MongoDB
//import  com.mongodb.casbah.Imports_.
//import  com.mongodb.casbah.commons.MongoDBObject

@Configuration
@EnableAutoConfiguration
@ComponentScan
@RestController
class UserController  {
 
         
       
       var  user=new User();
       var ucounter:Int=1;
	var icounter:Int=1;
	var wcounter:Int=1;
	var bcounter:Int=1;
	//var updated_at:DateTime=DateTime.now;
	var hm=new HashMap[String,User]();
        var hmID=new HashMap[String,Set[IdCard]]with MultiMap[String,IdCard];    // Stores IDcards 
        var hmWB=new HashMap[String,Set[WebLogin]]with MultiMap[String,WebLogin];  // Stores WebLogins
      var hmBA=new HashMap[String,Set[BankAccount]]with MultiMap[String,BankAccount];  // Stores BankAccounts
         
  val context = new AnnotationConfigApplicationContext(classOf[SpringMongoConfig])
  val mongoOps = context.getBean("mongoTemplate").asInstanceOf[MongoOperations]
         //val mongoTemplate:MongoTemplate = new MongoTemplate(new MongoClient("ds047950.mongolab.com:47950"), "digital_wallet", new UserCredentials("shreedhar_sjsu","root"));    
       //  val mongoClient = MongoClient("localhost", 27017);
      //val db1 = mongoClient("test");
     //db1.collection;
      
   //  val coll = db1("test1");
    // println(coll);


	@RequestMapping(value = Array("api/v1/user"), method = Array(RequestMethod.POST), consumes = Array("application/json"),headers=Array("content-type=application/json"))
	@ResponseBody
	def CreateUser(@Valid @RequestBody user:User,result:BindingResult):User={
	  
	       if (result.hasErrors()) {
                  println("Post param missing")
               throw new MissingFieldError(result.toString)
           }
	       else{
                     
		     this.user=user;
		     this.user.setuserid(ucounter);
                     var uid:String=this.user.getuserid();
	        
                   this.mongoOps.insert(this.user);
                
               //  var usr=new User();
               //  usr=u.get("userid"); 
              
		 
		   //  var u:User = this.hm(uid);
               	     ucounter=ucounter+1;
                     return this.user;
	       }
	}
	
            
      /*   @RequestMapping(value=Array("api/v1/users/{userid}"), method=Array(RequestMethod.GET))
	def ViewUsers(@PathVariable("userid")  userId:String):User={
		
                  var cursor=coll.find(MongoDBObject("userid"->userId));
                   var u=(cursor.next());
             // var usr=new User(); 
             //usr.setemail(u.get("email").toString)
                //var u:User =this.hm(userId);
                //u.getcreated_at();
           /*     var tag: String = Etag
		var cc: CacheControl = new CacheControl()
        	cc.setMaxAge(216)
        	var etag: EntityTag = new EntityTag(Integer.toString(u.hashCode()));
              //   var up:String=updated_at.toString
               println(etag);
             //  println(up);
        	var responseHeader: HttpHeaders = new HttpHeaders	
        	responseHeader.setCacheControl(cc.toString())
        	responseHeader.add("Etag", etag.getValue())
        	if(etag.getValue().equalsIgnoreCase(tag)){
        		 println("Not_Modified");
                    new ResponseEntity[String]( null, responseHeader, HttpStatus.NOT_MODIFIED )   
        	} else {
                        println("Modified");
        		new ResponseEntity[User]( u, responseHeader, HttpStatus.OK )  
        	}*/
		
		return u;
	}*/


	@RequestMapping(value=Array("api/v1/users/{userid}"), method=Array(RequestMethod.GET))
	def ViewUsers(@PathVariable("userid")  userId:String):User={
		
                 
                
                 var usr:User = this.mongoOps.findById(userId,classOf[User]); 
           /*     var tag: String = Etag
		var cc: CacheControl = new CacheControl()
        	cc.setMaxAge(216)
        	var etag: EntityTag = new EntityTag(Integer.toString(u.hashCode()));
              //   var up:String=updated_at.toString
               println(etag);
             //  println(up);
        	var responseHeader: HttpHeaders = new HttpHeaders	
        	responseHeader.setCacheControl(cc.toString())
        	responseHeader.add("Etag", etag.getValue())
        	if(etag.getValue().equalsIgnoreCase(tag)){
        		 println("Not_Modified");
                    new ResponseEntity[String]( null, responseHeader, HttpStatus.NOT_MODIFIED )   
        	} else {
                        println("Modified");
        		new ResponseEntity[User]( u, responseHeader, HttpStatus.OK )  
        	}*/
		
		return usr;
	} 

       @RequestMapping(value = Array("api/v1/users/{userid}"), method = Array(RequestMethod.PUT), consumes = Array("application/json"),headers=Array("content-type=application/json"))
	@ResponseBody
	def UpdateUser( @PathVariable("userid") userId:String,@RequestBody user: User):User= {
	  
        //   println(user.getname());
	 // println(user.getemailid());
	 // println(user.getpassword());

       var u: User=this.hm(userId);
      if(user.getemail()==""){
        u.setemail(u.getemail());
         println("NULL");
      }
      else{
        u.setemail(user.getemail());
      }
      if(user.getpassword==""){
        u.setpassword(u.getpassword());
      }
      else{
        u.setpassword(user.getpassword());
      }
      this.hm-=(userId);
      this.hm+=(userId->u);
   //   println(this.hm(u.getuserid()).getemail());
   //    updated_at=DateTime.now + 2.months
    //  var up:String=updated_at.toString
      //         println(up);
	  return u;
     }

   



 /////////////IDCard///////////

	
  @RequestMapping(value = Array("api/v1/users/{userid}/idcards"), method = Array(RequestMethod.POST), consumes = Array("application/json"),headers=Array("content-type=application/json"))
	@ResponseBody
	def CreateIdCard( @PathVariable("userid") userId:String, @Valid @RequestBody idcard:IdCard,result:BindingResult):IdCard={
		  if (result.hasErrors()) {
               throw new MissingFieldError(result.toString)
           }
	       else{  
          idcard.setcardid(icounter);
          icounter=icounter+1;
          idcard.setUserid(userId)
       	  this.mongoOps.insert(idcard)   
         return idcard;
	}
      }
  
  
	@RequestMapping(value=Array("api/v1/users/{userid}/idcards"), method=Array(RequestMethod.GET), produces = Array("application/json"), headers=Array("content-type=application/json"))
	def ListAllIdCard(@PathVariable("userid")  userId:String ):java.util.List[IdCard]={
		
	        
                 var queryCard: Query = new Query()
		queryCard.addCriteria(Criteria.where("userid").is(userId)) 
		var card = mongoOps.find(queryCard, classOf[IdCard])
                  println(card);
              // var icard:IdCard=mongoOps.findById(userId,classOf[IdCard])
	        return card;
     
	}
	
	@RequestMapping(value = Array("api/v1/users/{user_id}/idcards/{card_id}"), method = Array(RequestMethod.DELETE), consumes = Array("application/json"),headers=Array("content-type=application/json"))
	@ResponseBody
	def DeleteIdCard( @PathVariable("user_id") userId:String,@PathVariable("card_id") card_id:String)={
	  
	var queryCard: Query = new Query()
        queryCard.addCriteria(Criteria.where("_id").is(card_id))
	var card = mongoOps.findAndRemove(queryCard, classOf[IdCard])

       /* if(usercard == null)
			{
				throw new CardNotFoundException("Card with card_id "+card_id+" not found")
			}*/
	  
	}


	////////////WebLogin///////////////
	
	@RequestMapping(value = Array(" api/v1/users/{user_id}/weblogins"), method = Array(RequestMethod.POST), consumes = Array("application/json"),headers=Array("content-type=application/json"))
	@ResponseBody
	def CreateWebLogin( @PathVariable("user_id") userId:String, @Valid @RequestBody weblogin:WebLogin,result:BindingResult):WebLogin={    
        if (result.hasErrors()) {
               throw new MissingFieldError(result.toString)
           }
	       else{        
          weblogin.setUserid(userId)
           weblogin.setloginid(wcounter);
          wcounter=wcounter+1;
       	  this.mongoOps.insert(weblogin)
         
	  return weblogin;
	}
     }
  
    @RequestMapping(value=Array("api/v1/users/{user_id}/weblogins"), method=Array(RequestMethod.GET), produces = Array("application/json"), headers=Array("content-type=application/json"))
	def ListAllWebLogins(@PathVariable("user_id")  userId:String ):java.util.List[WebLogin]={
		
	        
	        var queryCard: Query = new Query()
		queryCard.addCriteria(Criteria.where("userid").is(userId)) 
		var wlogin = mongoOps.find(queryCard, classOf[WebLogin])
	        return wlogin;
     
	}
	
    @RequestMapping(value = Array("api/v1/users/{user_id}/weblogins/{login_id}"), method = Array(RequestMethod.DELETE), consumes = Array("application/json"),headers=Array("content-type=application/json"))
	@ResponseBody
	def DeleteWebLogin( @PathVariable("user_id") userId:String,@PathVariable("login_id") login_id:String)={
	  
	  var queryUrl: Query = new Query()
        queryUrl.addCriteria(Criteria.where("_id").is(login_id))
	var url = mongoOps.findAndRemove(queryUrl, classOf[WebLogin])
	}
  
  ////////////BankAccount/////////////////
  
  @RequestMapping(value = Array(" api/v1/users/{user_id}/bankaccounts"), method = Array(RequestMethod.POST), consumes = Array("application/json"),headers=Array("content-type=application/json"))
	@ResponseBody
	def CreateBankAccount( @PathVariable("user_id") userId:String, @Valid @RequestBody bankaccount:BankAccount,result:BindingResult):BankAccount={    
     
       if (result.hasErrors()) {
               throw new MissingFieldError(result.toString)
           }
	       else{  

                var routingno = bankaccount.getrouting_number()
         	var dotest: ApplicationTests = new ApplicationTests()
  	        var customer = dotest.testRoute(routingno)
                bankaccount.setaccount_name(customer)
                bankaccount.setUserid(userId)
                bankaccount.setba_id(bcounter);
       	        this.mongoOps.insert(bankaccount)
         
                bcounter=bcounter+1;
	    
                return bankaccount;
	}
  }

    @RequestMapping(value=Array("api/v1/users/{user_id}/bankaccounts"), method=Array(RequestMethod.GET), produces = Array("application/json"), headers=Array("content-type=application/json"))
	def ListAllBankAccounts(@PathVariable("user_id")  userId:String ):java.util.List[BankAccount]={
		
	     
                
	        var queryCard: Query = new Query()
		queryCard.addCriteria(Criteria.where("userid").is(userId)) 
		var bank = mongoOps.find(queryCard, classOf[BankAccount])
	        return bank;
     
	}
  
  
    @RequestMapping(value = Array("api/v1/users/{user_id}/bankaccounts/{bank_id}"), method = Array(RequestMethod.DELETE), consumes = Array("application/json"),
            headers=Array("content-type=application/json"))
	@ResponseBody
	def DeleteBankAccount( @PathVariable("user_id") userId:String,@PathVariable("bank_id") bank_id:String)={
	  
	  var queryBank: Query = new Query()
        queryBank.addCriteria(Criteria.where("_id").is(bank_id))
	var bank = mongoOps.findAndRemove(queryBank, classOf[BankAccount])
	}

//CASBAH Scala Driver for connectign to MongoDB //

}
