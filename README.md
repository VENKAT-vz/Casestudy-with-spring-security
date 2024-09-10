<center>Casestudy with Spring Secutity</center>
<pre>
-> Import the project 
      and change the server port and password in the properties 
      and run this project
      In postman:
            POST method with URL  "http://localhost:1133/users/addUser"
                  In the body, to register a customer
      <br>
              {
                "users":{
                    "firstname": "dharani",
                    "lastname": "priya",
                    "dateOfBirth": "2002-05-21",
                    "gender": "F",
                    "contactNumber": "7854269315",
                    "address": "62/34,voyage street",
                    "city": "Salem",
                    "state": "Tamilnadu",
                    "username": "dharani",
                    "emailid": "dharanip@gmail.com"
              },
                "password": "dharanipriya",
                "role":"Customer"
            }
      <br>
      Again in that do it for BankManager, the body is
       {
          "users":{
              "firstname": "vinith",
              "lastname": "kumar",
              "dateOfBirth": "2002-03-03",
              "gender": "M",
              "contactNumber": "9632841426",
              "address": "691/45,voyage street",
              "city": "Salem",
              "state": "Tamilnadu",
              "username": "vinithuser",
              "emailid": "vinith02@gmail.com"
          },
          "password": "vinithpl02",
          "role":"BankManager"
      }
      <br>
      Also, we need to add Admin, and for that
      POST method in Postman with url http://localhost:1133/registerAdmin
      with body,
            {
                "username": "Rajan",
                "emailid": "rajan@gmail.com",
                "password": "rajan002",
                "role": "Admin"
            }
      <br>
      Now that we have login credentials for Customer, BankManager, Admin, we need to ensure spring security by 
      modifying the existing <b>configure</b> method in <b>WebSecurityConfig</b> to this way
      <br>
      <I>updated config method in WebSecurityConfig</I>
      <br>
   @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
        .authorizeRequests()
    		.antMatchers("/users/addUser").permitAll() 
    		.antMatchers("/registerAdmin").permitAll() 
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/manager/**").hasRole("BANK_MANAGER")
            .antMatchers("/users/**").hasRole("CUSTOMER")
            .antMatchers("/login/**").hasRole("CUSTOMER")
            .anyRequest().authenticated()
        .and()
        .httpBasic() 
        .and()
        .formLogin().permitAll()
        .and()
        .logout().permitAll()
        .and()
        .csrf().disable();  
    }

<br> 
<hr>
By doing this, only the authorized users with respective roles can access particular methods. 
<br>
<b>Then from now on, we need to ensure we also use authorization in postman by selecting authorization ->basicauth and filling in the necessary credentials.
If we run it in the browser, then the login page will appear we should enter valid credentials to use that method.</b>
<br>
->There are three controllers
      ->UserController 
              ->The methods in UserController are addUser,addAccounts,login, searchAccounts(returns no.of accounts by the user),
                deposit,withdraw,transfer.
                <br>
              ->To access UserController methods the url starts with "http://localhost:1133/users", 
                after users extend with the method url in the UserController
              <br>
      ->AdminController
              ->The methods in AdminController are approveUseraccounts,showAllaccounts, showAlluser, deleteUserAccounts, searchUser.
                  <br>
              ->To access UserController methods the url starts with "http://localhost:1133/admin", 
                after users extend with the method url in the AdminController.
                 <br>
      ->ManagerController
              ->The methods in ManagerController are show unapproved bank accounts, 
                approve the accounts(sets status to 'active', showbankAccounts, searchAccounts, deletebankAccounts
                  <br>
              ->To access UserController methods the url starts with "http://localhost:1133/admin", 
                after users extend with the method url in the ManagerController.
             <br>
              <hr>
<b>Adding user</b>
        We need to use this format in body:
        {
          "users":{
              "firstname": "vinith",
              "lastname": "kumar",
              "dateOfBirth": "2002-03-03",
              "gender": "M",
              "contactNumber": "9632841426",
              "address": "691/45,voyage street",
              "city": "Salem",
              "state": "Tamilnadu",
              "username": "vinithuser",
              "emailid": "vinith02@gmail.com"
          },
          "password": "vinithpl02",
          "role":"Customer"
      }
<br>
  By using the above format, the method updates both users table and login table. The password will be stored in an encrypted way using Bcrypt.
  <br>
  Also, we only input the values of Aadhar and pan numbers while getting input for the account. So we need to use this format,
  <br>
  <b>Adding accounts</b>
  {
    "account": {
        "accountType": "Current",
        "balance": 4500.00,
        "branchName": "Coimbatore",
        "ifscCode": "65489631454",
        "username": "mah1user",
        "emailid":"mahraj@gmail.com"
    },
    "aadhaarNumber": "865231453678",
    "panNumber": "6412367962"
  }
  <hr>

  <b> **Note**</b>
  ->In the browser only the POST and GET methods will work, for PUT and DELETE we must use postman
    Also, in POST and GET methods if there is body then we should use POSTMAN.
  ->Plain text password in the database that is not Bcrypt encoded will not work. 
    Like, the records inserted into the login table directly with the password not encrypted will not work.
    We need to add user records and login records via Postman to make it work.
  
</pre>
