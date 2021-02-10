# krungsri-okstartup

### environment
mysql database

### installation guide
`1.mvn eclipse:eclipse`

`2.import to eclipse ide`

`3.config application.properties`

  spring.datasource.url=jdbc:mysql://{your_host}:{your_port}/{your_database}\
  spring.datasource.username={username}\
  spring.datasource.password={password}\
  jwt.secret={set_your_secret_key}
  
`4.to run test in src/test/java -> right click RegisterGetInfoTest.java -> Run as -> Junit Test`

  ### RegisterGetInfoTest.java
  
    method [register_thenStatus200] - call rest api /register with username password and information\
    method [authenticateAndgetRegisterInfo] - call rest api /authenticate to get token and get register info with access token
    
 ### Restful api
 
  `1. /register`
  
  register user with parameter\
     -username\
     -password\
     -firstname\
     -lastname\
     -address\
     -email\
     -salary\
     -phone
 
  `2. /authenticate` 
  
  login with username and password
 
  `3. /getUserInfo` 
  
  get userinfo with authentication access token
