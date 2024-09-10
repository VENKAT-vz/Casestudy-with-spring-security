<center>Casestudy with Spring Secutity</center>
<pre>
-> Import and run this project
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
  Also, we only input the values of aadhar and pan numbers while getting input for the account. So we need to use this format,
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
</pre>
