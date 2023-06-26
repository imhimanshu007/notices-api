# notices-api
- install mysql server on machine on port 3306 with credentials
- username : root, password : root@123
- create database notices

# Run the jar file to start the application

## Post request to register user/admin
- Using Postman make a Post Request to http://localhost:8080/api/v1/auth/register
- Go to Body Choose RAW then JSON and then provide the details in below format

    { \
        "name" : "{Your Name}", \
        "username" : "{Your Username}", \
        "email" : "{Your Email}", \
        "password" : "{Your Password}", \
        "role" : "{either user or admin}" \
    }
- To register as User input user in the role and for admin input admin.
- On Successful Registration. "User Registered Successfully will be returned as Response"

## Post request to login user/admin

- Using Postman make a Post Request to http://localhost:8080/api/v1/auth/login
    - Your may login either using username or email
- Go to Body Choose RAW then JSON and then provide the details in below format

  { \
  "usernameOrEmail" : "{Your Username}", \
  "password" : "{Your password}" \
  }
- Once login is done JW Token will be received in response
    - Pass this token for every subsequent request by going to Postman
      - Choose Authorization -> Choose Bearer Token -> Then Copy the received token


## Post Request to Create Notices for others users to view
- Using Postman make a Post Request to http://localhost:8080/api/v1/createNotice
- Only Users with Admin access can create Notice, So first login with admin credentials
- Go to Body choose RAW then JSON and then provide the details in below format
  
    { \
  "title" : "Holidays Notice", \
  "description" : "Holidays schedule out for Semester End", \
  "content" : "Students are requested to get plan their trips as per the Holidays" \
  }
## Get Request to see all Notices
- Using Postman make a Get Request to http://localhost:8080/api/v1/getNotices
- Since it is a public api its access is open to everyone.
- List of Notices with latest date ordering will be displayed




