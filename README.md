# Java / Spring Boot Notebook Server
- Spring Boot Notebook Server that can execute python codes.

    
1. [Installation](#installation)
    - [GraalVM Installation](#graalvm)
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
$ git clone https://github.com/LMohamedPro/notebook--server.git
```

## GraalVM

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

- sessionId: the session id to for using the same context.

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
