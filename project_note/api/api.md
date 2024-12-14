---
title: MOOC API v1.0.0
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
highlight_theme: darkula
headingLevel: 2

---

<!-- Generator: Widdershins v4.0.1 -->

<h1 id="mooc-api">MOOC API v1.0.0</h1>

> Scroll down for code samples, example requests and responses. Select a language for code samples from the tabs above or the mobile navigation menu.

REST MOOC API

Base URLs:

* <a href="http://localhost:8080">http://localhost:8080</a>

<h1 id="mooc-api-teacher-api-controller">teacher-api-controller</h1>

## updateQuiz

<a id="opIdupdateQuiz"></a>

> Code samples

```shell
# You can also use wget
curl -X PUT http://localhost:8080/api/teacher/quizzes/{quizId} \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*'

```

```http
PUT http://localhost:8080/api/teacher/quizzes/{quizId} HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*

```

```javascript
const inputBody = '{
  "quizId": 0,
  "question": "string",
  "answer": "string"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/teacher/quizzes/{quizId}',
{
  method: 'PUT',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*'
}

result = RestClient.put 'http://localhost:8080/api/teacher/quizzes/{quizId}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*'
}

r = requests.put('http://localhost:8080/api/teacher/quizzes/{quizId}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('PUT','http://localhost:8080/api/teacher/quizzes/{quizId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/teacher/quizzes/{quizId}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("PUT");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("PUT", "http://localhost:8080/api/teacher/quizzes/{quizId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`PUT /api/teacher/quizzes/{quizId}`

> Body parameter

```json
{
  "quizId": 0,
  "question": "string",
  "answer": "string"
}
```

<h3 id="updatequiz-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|quizId|path|integer(int32)|true|none|
|body|body|[QuizDTO](#schemaquizdto)|true|none|

> Example responses

> 200 Response

<h3 id="updatequiz-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ApiResponseVoid](#schemaapiresponsevoid)|

<aside class="success">
This operation does not require authentication
</aside>

## deleteQuiz

<a id="opIddeleteQuiz"></a>

> Code samples

```shell
# You can also use wget
curl -X DELETE http://localhost:8080/api/teacher/quizzes/{quizId} \
  -H 'Accept: */*'

```

```http
DELETE http://localhost:8080/api/teacher/quizzes/{quizId} HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/teacher/quizzes/{quizId}',
{
  method: 'DELETE',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*'
}

result = RestClient.delete 'http://localhost:8080/api/teacher/quizzes/{quizId}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*'
}

r = requests.delete('http://localhost:8080/api/teacher/quizzes/{quizId}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('DELETE','http://localhost:8080/api/teacher/quizzes/{quizId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/teacher/quizzes/{quizId}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("DELETE");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("DELETE", "http://localhost:8080/api/teacher/quizzes/{quizId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`DELETE /api/teacher/quizzes/{quizId}`

<h3 id="deletequiz-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|quizId|path|integer(int32)|true|none|

> Example responses

> 200 Response

<h3 id="deletequiz-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ApiResponseVoid](#schemaapiresponsevoid)|

<aside class="success">
This operation does not require authentication
</aside>

## updateLecture

<a id="opIdupdateLecture"></a>

> Code samples

```shell
# You can also use wget
curl -X PUT http://localhost:8080/api/teacher/lectures/{lectureId} \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*'

```

```http
PUT http://localhost:8080/api/teacher/lectures/{lectureId} HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*

```

```javascript
const inputBody = '{
  "title": "string",
  "description": "string"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/teacher/lectures/{lectureId}',
{
  method: 'PUT',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*'
}

result = RestClient.put 'http://localhost:8080/api/teacher/lectures/{lectureId}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*'
}

r = requests.put('http://localhost:8080/api/teacher/lectures/{lectureId}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('PUT','http://localhost:8080/api/teacher/lectures/{lectureId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/teacher/lectures/{lectureId}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("PUT");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("PUT", "http://localhost:8080/api/teacher/lectures/{lectureId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`PUT /api/teacher/lectures/{lectureId}`

> Body parameter

```json
{
  "title": "string",
  "description": "string"
}
```

<h3 id="updatelecture-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|lectureId|path|integer(int32)|true|none|
|body|body|[LectureUpdateDTO](#schemalectureupdatedto)|true|none|

> Example responses

> 200 Response

<h3 id="updatelecture-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ApiResponseVoid](#schemaapiresponsevoid)|

<aside class="success">
This operation does not require authentication
</aside>

## deleteLecture

<a id="opIddeleteLecture"></a>

> Code samples

```shell
# You can also use wget
curl -X DELETE http://localhost:8080/api/teacher/lectures/{lectureId} \
  -H 'Accept: */*'

```

```http
DELETE http://localhost:8080/api/teacher/lectures/{lectureId} HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/teacher/lectures/{lectureId}',
{
  method: 'DELETE',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*'
}

result = RestClient.delete 'http://localhost:8080/api/teacher/lectures/{lectureId}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*'
}

r = requests.delete('http://localhost:8080/api/teacher/lectures/{lectureId}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('DELETE','http://localhost:8080/api/teacher/lectures/{lectureId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/teacher/lectures/{lectureId}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("DELETE");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("DELETE", "http://localhost:8080/api/teacher/lectures/{lectureId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`DELETE /api/teacher/lectures/{lectureId}`

<h3 id="deletelecture-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|lectureId|path|integer(int32)|true|none|

> Example responses

> 200 Response

<h3 id="deletelecture-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ApiResponseVoid](#schemaapiresponsevoid)|

<aside class="success">
This operation does not require authentication
</aside>

## updateCourse

<a id="opIdupdateCourse"></a>

> Code samples

```shell
# You can also use wget
curl -X PUT http://localhost:8080/api/teacher/courses/{courseId}?dto=title,string,description,string,subjectId,0,weeks,1,learningTime,1,language,string,isCreditBank,0,thumbnail,string \
  -H 'Accept: */*'

```

```http
PUT http://localhost:8080/api/teacher/courses/{courseId}?dto=title,string,description,string,subjectId,0,weeks,1,learningTime,1,language,string,isCreditBank,0,thumbnail,string HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/teacher/courses/{courseId}?dto=title,string,description,string,subjectId,0,weeks,1,learningTime,1,language,string,isCreditBank,0,thumbnail,string',
{
  method: 'PUT',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*'
}

result = RestClient.put 'http://localhost:8080/api/teacher/courses/{courseId}',
  params: {
  'dto' => '[CourseUpdateDTO](#schemacourseupdatedto)'
}, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*'
}

r = requests.put('http://localhost:8080/api/teacher/courses/{courseId}', params={
  'dto': {
  "title": "string",
  "description": "string",
  "subjectId": 0,
  "weeks": 1,
  "learningTime": 1,
  "language": "string",
  "isCreditBank": 0,
  "thumbnail": "string"
}
}, headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('PUT','http://localhost:8080/api/teacher/courses/{courseId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/teacher/courses/{courseId}?dto=title,string,description,string,subjectId,0,weeks,1,learningTime,1,language,string,isCreditBank,0,thumbnail,string");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("PUT");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("PUT", "http://localhost:8080/api/teacher/courses/{courseId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`PUT /api/teacher/courses/{courseId}`

<h3 id="updatecourse-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|courseId|path|integer(int32)|true|none|
|dto|query|[CourseUpdateDTO](#schemacourseupdatedto)|true|none|

> Example responses

> 200 Response

<h3 id="updatecourse-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ApiResponseVoid](#schemaapiresponsevoid)|

<aside class="success">
This operation does not require authentication
</aside>

## deleteCourse

<a id="opIddeleteCourse"></a>

> Code samples

```shell
# You can also use wget
curl -X DELETE http://localhost:8080/api/teacher/courses/{courseId} \
  -H 'Accept: */*'

```

```http
DELETE http://localhost:8080/api/teacher/courses/{courseId} HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/teacher/courses/{courseId}',
{
  method: 'DELETE',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*'
}

result = RestClient.delete 'http://localhost:8080/api/teacher/courses/{courseId}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*'
}

r = requests.delete('http://localhost:8080/api/teacher/courses/{courseId}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('DELETE','http://localhost:8080/api/teacher/courses/{courseId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/teacher/courses/{courseId}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("DELETE");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("DELETE", "http://localhost:8080/api/teacher/courses/{courseId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`DELETE /api/teacher/courses/{courseId}`

<h3 id="deletecourse-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|courseId|path|integer(int32)|true|none|

> Example responses

> 200 Response

<h3 id="deletecourse-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ApiResponseVoid](#schemaapiresponsevoid)|

<aside class="success">
This operation does not require authentication
</aside>

## updateContent

<a id="opIdupdateContent"></a>

> Code samples

```shell
# You can also use wget
curl -X PUT http://localhost:8080/api/teacher/contents/{contentId}?dto=title,string,description,string,type,string,file,string \
  -H 'Accept: */*'

```

```http
PUT http://localhost:8080/api/teacher/contents/{contentId}?dto=title,string,description,string,type,string,file,string HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/teacher/contents/{contentId}?dto=title,string,description,string,type,string,file,string',
{
  method: 'PUT',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*'
}

result = RestClient.put 'http://localhost:8080/api/teacher/contents/{contentId}',
  params: {
  'dto' => '[LectureContentUpdateDTO](#schemalecturecontentupdatedto)'
}, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*'
}

r = requests.put('http://localhost:8080/api/teacher/contents/{contentId}', params={
  'dto': {
  "title": "string",
  "description": "string",
  "type": "string",
  "file": "string"
}
}, headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('PUT','http://localhost:8080/api/teacher/contents/{contentId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/teacher/contents/{contentId}?dto=title,string,description,string,type,string,file,string");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("PUT");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("PUT", "http://localhost:8080/api/teacher/contents/{contentId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`PUT /api/teacher/contents/{contentId}`

<h3 id="updatecontent-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|contentId|path|integer(int32)|true|none|
|dto|query|[LectureContentUpdateDTO](#schemalecturecontentupdatedto)|true|none|

> Example responses

> 200 Response

<h3 id="updatecontent-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ApiResponseVoid](#schemaapiresponsevoid)|

<aside class="success">
This operation does not require authentication
</aside>

## deleteContent

<a id="opIddeleteContent"></a>

> Code samples

```shell
# You can also use wget
curl -X DELETE http://localhost:8080/api/teacher/contents/{contentId} \
  -H 'Accept: */*'

```

```http
DELETE http://localhost:8080/api/teacher/contents/{contentId} HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/teacher/contents/{contentId}',
{
  method: 'DELETE',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*'
}

result = RestClient.delete 'http://localhost:8080/api/teacher/contents/{contentId}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*'
}

r = requests.delete('http://localhost:8080/api/teacher/contents/{contentId}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('DELETE','http://localhost:8080/api/teacher/contents/{contentId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/teacher/contents/{contentId}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("DELETE");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("DELETE", "http://localhost:8080/api/teacher/contents/{contentId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`DELETE /api/teacher/contents/{contentId}`

<h3 id="deletecontent-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|contentId|path|integer(int32)|true|none|

> Example responses

> 200 Response

<h3 id="deletecontent-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ApiResponseVoid](#schemaapiresponsevoid)|

<aside class="success">
This operation does not require authentication
</aside>

## createQuizzes

<a id="opIdcreateQuizzes"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/teacher/lectures/{lectureId}/quizzes \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*'

```

```http
POST http://localhost:8080/api/teacher/lectures/{lectureId}/quizzes HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*

```

```javascript
const inputBody = '{
  "lectureId": 0,
  "quizzes": [
    {
      "quizId": 0,
      "question": "string",
      "answer": "string"
    }
  ]
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/teacher/lectures/{lectureId}/quizzes',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*'
}

result = RestClient.post 'http://localhost:8080/api/teacher/lectures/{lectureId}/quizzes',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*'
}

r = requests.post('http://localhost:8080/api/teacher/lectures/{lectureId}/quizzes', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/teacher/lectures/{lectureId}/quizzes', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/teacher/lectures/{lectureId}/quizzes");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/teacher/lectures/{lectureId}/quizzes", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/teacher/lectures/{lectureId}/quizzes`

> Body parameter

```json
{
  "lectureId": 0,
  "quizzes": [
    {
      "quizId": 0,
      "question": "string",
      "answer": "string"
    }
  ]
}
```

<h3 id="createquizzes-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|lectureId|path|integer(int32)|true|none|
|body|body|[QuizCreateDTO](#schemaquizcreatedto)|true|none|

> Example responses

> 200 Response

<h3 id="createquizzes-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ApiResponseVoid](#schemaapiresponsevoid)|

<aside class="success">
This operation does not require authentication
</aside>

## createContent

<a id="opIdcreateContent"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/teacher/lectures/{lectureId}/contents?dto=lectureId,0,title,string,description,string,type,string,file,string \
  -H 'Accept: */*'

```

```http
POST http://localhost:8080/api/teacher/lectures/{lectureId}/contents?dto=lectureId,0,title,string,description,string,type,string,file,string HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/teacher/lectures/{lectureId}/contents?dto=lectureId,0,title,string,description,string,type,string,file,string',
{
  method: 'POST',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*'
}

result = RestClient.post 'http://localhost:8080/api/teacher/lectures/{lectureId}/contents',
  params: {
  'dto' => '[LectureContentCreateDTO](#schemalecturecontentcreatedto)'
}, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*'
}

r = requests.post('http://localhost:8080/api/teacher/lectures/{lectureId}/contents', params={
  'dto': {
  "lectureId": 0,
  "title": "string",
  "description": "string",
  "type": "string",
  "file": "string"
}
}, headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/teacher/lectures/{lectureId}/contents', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/teacher/lectures/{lectureId}/contents?dto=lectureId,0,title,string,description,string,type,string,file,string");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/teacher/lectures/{lectureId}/contents", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/teacher/lectures/{lectureId}/contents`

<h3 id="createcontent-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|lectureId|path|integer(int32)|true|none|
|dto|query|[LectureContentCreateDTO](#schemalecturecontentcreatedto)|true|none|

> Example responses

> 200 Response

<h3 id="createcontent-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ApiResponseVoid](#schemaapiresponsevoid)|

<aside class="success">
This operation does not require authentication
</aside>

## createCourse

<a id="opIdcreateCourse"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/teacher/courses?courseDTO=title,string,subjectId,0,weeks,1,learningTime,1,language,string,description,string,isCreditBank,1,thumbnail,string \
  -H 'Accept: */*'

```

```http
POST http://localhost:8080/api/teacher/courses?courseDTO=title,string,subjectId,0,weeks,1,learningTime,1,language,string,description,string,isCreditBank,1,thumbnail,string HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/teacher/courses?courseDTO=title,string,subjectId,0,weeks,1,learningTime,1,language,string,description,string,isCreditBank,1,thumbnail,string',
{
  method: 'POST',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*'
}

result = RestClient.post 'http://localhost:8080/api/teacher/courses',
  params: {
  'courseDTO' => '[CourseCreateDTO](#schemacoursecreatedto)'
}, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*'
}

r = requests.post('http://localhost:8080/api/teacher/courses', params={
  'courseDTO': {
  "title": "string",
  "subjectId": 0,
  "weeks": 1,
  "learningTime": 1,
  "language": "string",
  "description": "string",
  "isCreditBank": 1,
  "thumbnail": "string"
}
}, headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/teacher/courses', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/teacher/courses?courseDTO=title,string,subjectId,0,weeks,1,learningTime,1,language,string,description,string,isCreditBank,1,thumbnail,string");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/teacher/courses", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/teacher/courses`

<h3 id="createcourse-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|courseDTO|query|[CourseCreateDTO](#schemacoursecreatedto)|true|none|

> Example responses

> 200 Response

<h3 id="createcourse-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="createcourse-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## createLecture

<a id="opIdcreateLecture"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/teacher/courses/{courseId}/lectures \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*'

```

```http
POST http://localhost:8080/api/teacher/courses/{courseId}/lectures HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*

```

```javascript
const inputBody = '{
  "title": "string",
  "description": "string",
  "courseId": 0
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/teacher/courses/{courseId}/lectures',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*'
}

result = RestClient.post 'http://localhost:8080/api/teacher/courses/{courseId}/lectures',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*'
}

r = requests.post('http://localhost:8080/api/teacher/courses/{courseId}/lectures', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/teacher/courses/{courseId}/lectures', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/teacher/courses/{courseId}/lectures");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/teacher/courses/{courseId}/lectures", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/teacher/courses/{courseId}/lectures`

> Body parameter

```json
{
  "title": "string",
  "description": "string",
  "courseId": 0
}
```

<h3 id="createlecture-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|courseId|path|integer(int32)|true|none|
|body|body|[LectureCreateDTO](#schemalecturecreatedto)|true|none|

> Example responses

> 200 Response

<h3 id="createlecture-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ApiResponseVoid](#schemaapiresponsevoid)|

<aside class="success">
This operation does not require authentication
</aside>

## createLectures

<a id="opIdcreateLectures"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/teacher/courses/{courseId}/lectures/batch \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*'

```

```http
POST http://localhost:8080/api/teacher/courses/{courseId}/lectures/batch HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*

```

```javascript
const inputBody = '[
  {
    "title": "string",
    "description": "string",
    "courseId": 0
  }
]';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/teacher/courses/{courseId}/lectures/batch',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*'
}

result = RestClient.post 'http://localhost:8080/api/teacher/courses/{courseId}/lectures/batch',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*'
}

r = requests.post('http://localhost:8080/api/teacher/courses/{courseId}/lectures/batch', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/teacher/courses/{courseId}/lectures/batch', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/teacher/courses/{courseId}/lectures/batch");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/teacher/courses/{courseId}/lectures/batch", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/teacher/courses/{courseId}/lectures/batch`

> Body parameter

```json
[
  {
    "title": "string",
    "description": "string",
    "courseId": 0
  }
]
```

<h3 id="createlectures-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|courseId|path|integer(int32)|true|none|
|body|body|[LectureCreateDTO](#schemalecturecreatedto)|true|none|

> Example responses

> 200 Response

<h3 id="createlectures-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="createlectures-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## downloadFile

<a id="opIddownloadFile"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/teacher/download?filePath=string \
  -H 'Accept: */*'

```

```http
GET http://localhost:8080/api/teacher/download?filePath=string HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/teacher/download?filePath=string',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*'
}

result = RestClient.get 'http://localhost:8080/api/teacher/download',
  params: {
  'filePath' => 'string'
}, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*'
}

r = requests.get('http://localhost:8080/api/teacher/download', params={
  'filePath': 'string'
}, headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/teacher/download', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/teacher/download?filePath=string");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/teacher/download", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/teacher/download`

<h3 id="downloadfile-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|filePath|query|string|true|none|

> Example responses

> 200 Response

<h3 id="downloadfile-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|string|

<aside class="success">
This operation does not require authentication
</aside>

<h1 id="mooc-api-learn-history-api-controller">learn-history-api-controller</h1>

## updateLearnHistory

<a id="opIdupdateLearnHistory"></a>

> Code samples

```shell
# You can also use wget
curl -X PUT http://localhost:8080/api/learn-history/{lectureContentId} \
  -H 'Accept: */*'

```

```http
PUT http://localhost:8080/api/learn-history/{lectureContentId} HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/learn-history/{lectureContentId}',
{
  method: 'PUT',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*'
}

result = RestClient.put 'http://localhost:8080/api/learn-history/{lectureContentId}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*'
}

r = requests.put('http://localhost:8080/api/learn-history/{lectureContentId}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('PUT','http://localhost:8080/api/learn-history/{lectureContentId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/learn-history/{lectureContentId}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("PUT");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("PUT", "http://localhost:8080/api/learn-history/{lectureContentId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`PUT /api/learn-history/{lectureContentId}`

<h3 id="updatelearnhistory-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|lectureContentId|path|integer(int32)|true|none|

> Example responses

> 200 Response

<h3 id="updatelearnhistory-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="updatelearnhistory-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## getProgressRate

<a id="opIdgetProgressRate"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/learn-history/{courseId} \
  -H 'Accept: */*'

```

```http
GET http://localhost:8080/api/learn-history/{courseId} HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/learn-history/{courseId}',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*'
}

result = RestClient.get 'http://localhost:8080/api/learn-history/{courseId}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*'
}

r = requests.get('http://localhost:8080/api/learn-history/{courseId}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/learn-history/{courseId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/learn-history/{courseId}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/learn-history/{courseId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/learn-history/{courseId}`

<h3 id="getprogressrate-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|courseId|path|integer(int32)|true|none|

> Example responses

> 200 Response

<h3 id="getprogressrate-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="getprogressrate-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

<h1 id="mooc-api-my-page-controller">my-page-controller</h1>

## deleteMember

<a id="opIddeleteMember"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/mypage/memberDelete?memberId=string \
  -H 'Accept: */*'

```

```http
POST http://localhost:8080/mypage/memberDelete?memberId=string HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*'
};

fetch('http://localhost:8080/mypage/memberDelete?memberId=string',
{
  method: 'POST',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*'
}

result = RestClient.post 'http://localhost:8080/mypage/memberDelete',
  params: {
  'memberId' => 'string'
}, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*'
}

r = requests.post('http://localhost:8080/mypage/memberDelete', params={
  'memberId': 'string'
}, headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/mypage/memberDelete', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/mypage/memberDelete?memberId=string");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/mypage/memberDelete", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /mypage/memberDelete`

<h3 id="deletemember-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|memberId|query|string|true|none|

> Example responses

> 200 Response

<h3 id="deletemember-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="deletemember-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» **additionalProperties**|object|false|none|none|

<aside class="success">
This operation does not require authentication
</aside>

<h1 id="mooc-api-login-controller">login-controller</h1>

## checkMemberId

<a id="opIdcheckMemberId"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/login/memberIdCheck?memberId=string \
  -H 'Accept: */*'

```

```http
POST http://localhost:8080/login/memberIdCheck?memberId=string HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*'
};

fetch('http://localhost:8080/login/memberIdCheck?memberId=string',
{
  method: 'POST',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*'
}

result = RestClient.post 'http://localhost:8080/login/memberIdCheck',
  params: {
  'memberId' => 'string'
}, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*'
}

r = requests.post('http://localhost:8080/login/memberIdCheck', params={
  'memberId': 'string'
}, headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/login/memberIdCheck', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/login/memberIdCheck?memberId=string");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/login/memberIdCheck", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /login/memberIdCheck`

<h3 id="checkmemberid-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|memberId|query|string|true|none|

> Example responses

> 200 Response

<h3 id="checkmemberid-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|string|

<aside class="success">
This operation does not require authentication
</aside>

## checkEmail

<a id="opIdcheckEmail"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/login/emailCheck?email=string \
  -H 'Accept: */*'

```

```http
POST http://localhost:8080/login/emailCheck?email=string HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*'
};

fetch('http://localhost:8080/login/emailCheck?email=string',
{
  method: 'POST',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*'
}

result = RestClient.post 'http://localhost:8080/login/emailCheck',
  params: {
  'email' => 'string'
}, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*'
}

r = requests.post('http://localhost:8080/login/emailCheck', params={
  'email': 'string'
}, headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/login/emailCheck', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/login/emailCheck?email=string");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/login/emailCheck", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /login/emailCheck`

<h3 id="checkemail-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|email|query|string|true|none|

> Example responses

> 200 Response

<h3 id="checkemail-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|string|

<aside class="success">
This operation does not require authentication
</aside>

<h1 id="mooc-api-course-enrollment-controller">course-enrollment-controller</h1>

## regist

<a id="opIdregist"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/courseEnrollment/regist/{courseId} \
  -H 'Accept: */*'

```

```http
POST http://localhost:8080/courseEnrollment/regist/{courseId} HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*'
};

fetch('http://localhost:8080/courseEnrollment/regist/{courseId}',
{
  method: 'POST',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*'
}

result = RestClient.post 'http://localhost:8080/courseEnrollment/regist/{courseId}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*'
}

r = requests.post('http://localhost:8080/courseEnrollment/regist/{courseId}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/courseEnrollment/regist/{courseId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/courseEnrollment/regist/{courseId}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/courseEnrollment/regist/{courseId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /courseEnrollment/regist/{courseId}`

<h3 id="regist-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|courseId|path|integer(int32)|true|none|

> Example responses

> 200 Response

<h3 id="regist-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="regist-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

## delete

<a id="opIddelete"></a>

> Code samples

```shell
# You can also use wget
curl -X DELETE http://localhost:8080/courseEnrollment/delete/{courseId} \
  -H 'Accept: */*'

```

```http
DELETE http://localhost:8080/courseEnrollment/delete/{courseId} HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*'
};

fetch('http://localhost:8080/courseEnrollment/delete/{courseId}',
{
  method: 'DELETE',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*'
}

result = RestClient.delete 'http://localhost:8080/courseEnrollment/delete/{courseId}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*'
}

r = requests.delete('http://localhost:8080/courseEnrollment/delete/{courseId}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('DELETE','http://localhost:8080/courseEnrollment/delete/{courseId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/courseEnrollment/delete/{courseId}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("DELETE");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("DELETE", "http://localhost:8080/courseEnrollment/delete/{courseId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`DELETE /courseEnrollment/delete/{courseId}`

<h3 id="delete-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|courseId|path|integer(int32)|true|none|

> Example responses

> 200 Response

<h3 id="delete-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="delete-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

<h1 id="mooc-api-common-api-controller">common-api-controller</h1>

## headerSubject

<a id="opIdheaderSubject"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/main/subjects \
  -H 'Accept: */*'

```

```http
GET http://localhost:8080/api/main/subjects HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*'
};

fetch('http://localhost:8080/api/main/subjects',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*'
}

result = RestClient.get 'http://localhost:8080/api/main/subjects',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*'
}

r = requests.get('http://localhost:8080/api/main/subjects', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/main/subjects', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/main/subjects");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/main/subjects", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/main/subjects`

> Example responses

> 200 Response

<h3 id="headersubject-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="headersubject-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

# Schemas

<h2 id="tocS_QuizDTO">QuizDTO</h2>
<!-- backwards compatibility -->
<a id="schemaquizdto"></a>
<a id="schema_QuizDTO"></a>
<a id="tocSquizdto"></a>
<a id="tocsquizdto"></a>

```json
{
  "quizId": 0,
  "question": "string",
  "answer": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|quizId|integer(int32)|false|none|none|
|question|string|true|none|none|
|answer|string|true|none|none|

<h2 id="tocS_ApiResponseVoid">ApiResponseVoid</h2>
<!-- backwards compatibility -->
<a id="schemaapiresponsevoid"></a>
<a id="schema_ApiResponseVoid"></a>
<a id="tocSapiresponsevoid"></a>
<a id="tocsapiresponsevoid"></a>

```json
{
  "success": true,
  "message": "string",
  "data": {}
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|success|boolean|false|none|none|
|message|string|false|none|none|
|data|object|false|none|none|

<h2 id="tocS_LectureUpdateDTO">LectureUpdateDTO</h2>
<!-- backwards compatibility -->
<a id="schemalectureupdatedto"></a>
<a id="schema_LectureUpdateDTO"></a>
<a id="tocSlectureupdatedto"></a>
<a id="tocslectureupdatedto"></a>

```json
{
  "title": "string",
  "description": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|title|string|true|none|none|
|description|string|true|none|none|

<h2 id="tocS_CourseUpdateDTO">CourseUpdateDTO</h2>
<!-- backwards compatibility -->
<a id="schemacourseupdatedto"></a>
<a id="schema_CourseUpdateDTO"></a>
<a id="tocScourseupdatedto"></a>
<a id="tocscourseupdatedto"></a>

```json
{
  "title": "string",
  "description": "string",
  "subjectId": 0,
  "weeks": 1,
  "learningTime": 1,
  "language": "string",
  "isCreditBank": 0,
  "thumbnail": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|title|string|true|none|none|
|description|string|false|none|none|
|subjectId|integer(int32)|true|none|none|
|weeks|integer(int32)|false|none|none|
|learningTime|integer(int32)|false|none|none|
|language|string|true|none|none|
|isCreditBank|integer(int32)|false|none|none|
|thumbnail|string(binary)|false|none|none|

<h2 id="tocS_LectureContentUpdateDTO">LectureContentUpdateDTO</h2>
<!-- backwards compatibility -->
<a id="schemalecturecontentupdatedto"></a>
<a id="schema_LectureContentUpdateDTO"></a>
<a id="tocSlecturecontentupdatedto"></a>
<a id="tocslecturecontentupdatedto"></a>

```json
{
  "title": "string",
  "description": "string",
  "type": "string",
  "file": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|title|string|true|none|none|
|description|string|false|none|none|
|type|string|false|none|none|
|file|string(binary)|false|none|none|

<h2 id="tocS_QuizCreateDTO">QuizCreateDTO</h2>
<!-- backwards compatibility -->
<a id="schemaquizcreatedto"></a>
<a id="schema_QuizCreateDTO"></a>
<a id="tocSquizcreatedto"></a>
<a id="tocsquizcreatedto"></a>

```json
{
  "lectureId": 0,
  "quizzes": [
    {
      "quizId": 0,
      "question": "string",
      "answer": "string"
    }
  ]
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|lectureId|integer(int32)|false|none|none|
|quizzes|[[QuizDTO](#schemaquizdto)]|false|none|none|

<h2 id="tocS_LectureContentCreateDTO">LectureContentCreateDTO</h2>
<!-- backwards compatibility -->
<a id="schemalecturecontentcreatedto"></a>
<a id="schema_LectureContentCreateDTO"></a>
<a id="tocSlecturecontentcreatedto"></a>
<a id="tocslecturecontentcreatedto"></a>

```json
{
  "lectureId": 0,
  "title": "string",
  "description": "string",
  "type": "string",
  "file": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|lectureId|integer(int32)|true|none|none|
|title|string|true|none|none|
|description|string|true|none|none|
|type|string|true|none|none|
|file|string(binary)|false|none|none|

<h2 id="tocS_CourseCreateDTO">CourseCreateDTO</h2>
<!-- backwards compatibility -->
<a id="schemacoursecreatedto"></a>
<a id="schema_CourseCreateDTO"></a>
<a id="tocScoursecreatedto"></a>
<a id="tocscoursecreatedto"></a>

```json
{
  "title": "string",
  "subjectId": 0,
  "weeks": 1,
  "learningTime": 1,
  "language": "string",
  "description": "string",
  "isCreditBank": 1,
  "thumbnail": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|title|string|true|none|none|
|subjectId|integer(int32)|true|none|none|
|weeks|integer(int32)|false|none|none|
|learningTime|integer(int32)|false|none|none|
|language|string|true|none|none|
|description|string|true|none|none|
|isCreditBank|integer(int32)|false|none|none|
|thumbnail|string(binary)|true|none|none|

<h2 id="tocS_LectureCreateDTO">LectureCreateDTO</h2>
<!-- backwards compatibility -->
<a id="schemalecturecreatedto"></a>
<a id="schema_LectureCreateDTO"></a>
<a id="tocSlecturecreatedto"></a>
<a id="tocslecturecreatedto"></a>

```json
{
  "title": "string",
  "description": "string",
  "courseId": 0
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|title|string|true|none|none|
|description|string|true|none|none|
|courseId|integer(int32)|true|none|none|

