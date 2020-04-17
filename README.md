# Software-Engineering-II-Project

small springboot program run on java maven with derby DB

forward port to 9991

Available Services :

(the key for registering admin directly is : AdminKey00)
(.../register/admin)
Parameters:(
    UserName String,
    Email String,
    FirstName String,
    LastName String,
    Password1 String,
    Password2 String,
    Key String
)
	
(.../register/user)
Parameters:(
    UserName String,
    Email String,
    FirstName String,
    LastName String,
    Password1 String,
    Password2 String
)	
	
(.../login)
Parameters:(
	UserName/Email String,
	Password String
)

(.../logout)
No Parameters


(show all users in DB for admins only)
(.../list/user)
No Parameters
   
