# Swagger Back End Demo by Shawn Change

## General
- GitHub: https://github.com/shawn11ZX/auto_general_backend
- AWS address: http://swagger-dev.us-west-2.elasticbeanstalk.com/, 
	e.g. http://swagger-dev.us-west-2.elasticbeanstalk.com/tasks/validateBrackets?input=aaa

## Build
- cd to project directory
- ./gradlew build

## Code Navagation

### package: au.com.autogeneral.swagger.todo
REST API for /todo/**

### package: au.com.autogeneral.swagger.tasks
REST API for /tasks/**

### package: au.com.autogeneral.swagger.error
Exception defination and interception to generate HTTP error codes.

### package: au.com.autogeneral.swagger.bean
JSON objects definition

## Complains
The examples in the [test description web page](https://join.autogeneral.com.au/swagger-ui/?url=/swagger.json#/) has errors.  

To be more specific, according to the example, **todo id** should be of type Integer:
```json
{  
  "id": 42,  
  "text": "Uulwi ifis halahs gag erh'ongg w'ssh.",  
  "isCompleted": false,  
  "createdAt": "2017-10-13T01:50:58.735Z"  
}
```

according to the integration test, **todo id** should be of type string:
```json
 "expected": {
  "id": "...",
  "text": "Uulwi ifis halahs gag erh'ongg w'ssh.",
  "isCompleted": false,
  "createdAt": "..."
},
``` 