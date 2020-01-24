# Java / Spring Boot Notebook Server
- Spring Boot Notebook Server that can execute python codes.

    
1. [Installation](#installation)
    - [Maven Installation](#maven-installation)
        - [GraalVM](#graalvm)
2. [Usage](#usage)
    - [API Usage](#api-usage)
        - [Api End-Point](#api-end-point)
        - [Interpreter request body](#interpreter-request-body)
        - [Interpreter response body](#interpreter-response-body)
    - [API Usage Example](#api-usage-example)
        - [Example 1 simple python print](#example-python-print)
    
# Installation 

Clone project into a local folder.

```$shell 
$ git clone https://github.com/Eroui/notbook-interpreter.git
```

## Maven Installation

### GraalVM 
GraalVM must be installed in order to be able to build the project. 
You can download graalVM from the [GraalVM homepage](https://www.graalvm.org/). 
Also you can follow the [Getting Started with GraalVM](https://www.graalvm.org/docs/getting-started/). 

Don't forget to export GraalVM directory to your JAVA_HOME.
```
$ export PATH="$GRAALVM_DIR/bin:$PATH"
```

You also need to make sure that python for GraalVM is install. You can use the Graal Updater :
```$shell
$ gu install python
```

You should be able to interact with the server in:
```
http://localhost:8080/
```

# Usage

## API Usage

### Api End-Point
The Interpreter API is available via http POST method at:
```
/execute
```

### Interpreter request body

The **/execute** interpreter End-Point accepts JSON as request body. 
The json object must have the following format

```json
{
  "code": "string",
  "sessionId": "string"
}
```

Here is a small description of the request body fields:
- code: the code to be interpreted, it must have the format:

```json
%language code
```

See example below.

- sessionId: is the id of the session we are using. this field is used to differentiate between users and also to allow
users to continue their interaction with the interpreter in the same execution context (example declare variable and reuse them).
Note that you may have same sessionId for different languages but the codes run in seperate contexts for different sessions. Also the sessionId is not mandatory in the first request and the API will provide you with a sessionId in case not specified but it must be used to remembre the previous declaration...



### Interpreter response body

The **/execute** returns a json object as response. The response have the following format:

```json
{
  "result": "string",
  "sessionId": "string"
}
```

- result: the output of the code interpretation or . error information
- sessionId: the sessionId used during the interpretation for future usage.



## Api Usage Example

Here are some example of requests and responses :


### Example python print

- Request Body :
```json
{
  "code": "%python print(1)”, 
  "sessionId": "mySessionId"
}
```

- Response Body:

```json
{
  "result": “1\n”,
  "sessionId": "mySessionId"
}
```

