# WebSocket API Documentation

# Table of contents
1. [Protocol](#protocol)
   1. [STOMP Message Format](#stomp-message-format)
2. [Connecting to WebSocket](#connecting-to-websocket)
3. [Authentication](#authentication)
4. [Creating a new game](#creating-a-new-game)

# Protocol
This API uses the **STOMP** protocol over WebSocket for real-time communication.

There are many libraries available that simplify the use of the STOMP protocol in JavaScript. For example, you can use **SockJS**

### STOMP Message Format
In some examples, the body of the message is in the STOMP protocol format. The body of a STOMP frame ends with a **NULL octet**. In this document, we will use `^@` (control-@ in ASCII) to represent the NULL octet.

# Connecting to WebSocket
### Endpoint
`ws://localhost:8080/ws`

To start working with the WebSocket API, the client needs to connect to the WebSocket server at the above endpoint. After a successful connection, the client can send and receive messages.

# Authentication
To send any messages using our WebSocket API, you need to be authenticated.

When sending a message with the `CONNECT` type, you should include an `Authorization` header with the format `Bearer JWT`.

You can obtain this JWT token by making a REST request to the login endpoint, which can be found in our REST API documentation.

### Example message to connect
```plaintext
CONNECT
Authorization:Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJSb21hbjIiLCJleHAiOjE3MTkwNzYyOTYsImlhdCI6MTcxOTA3NTk5Nn0.V6ujB1C8B9BVcQIsdKE5rnM6_VIN-AIE7AzrCFG5WO7xjF8Y-z3j8JAntTRG4FuXvJT6xSnwvc2UL1KZRP9AS-wEyjcYR3dO0ZtjkKfF5kYJWPRMpdTfvXTcDXNLgHV4D6foObrAfBFyKcrRGPLns2xPGstxuYbdT3nAAYnKoOF_PgCsjZaClHpHZrC9PGbVoAa7GlDxn_m9ylNu3XZR18yJwmnZf20fpb3QbQFZKizQLg3AdaGOtplezfUDN29RqIheYpyw_vLaYshADn09pPmxgTpTuIxWauQnoi0QfU48rVQBNof8ubTmokFD9w37q3Dq2uX2_QrXR9XyP0mwDQ
accept-version:1.1,1.0
heart-beat:10000,10000

^@
```

### Example message of successful connection

```plaintext
CONNECTED
version:1.1
heart-beat:0,0
user-name:Roman

^@
```


# Creating a new game
### Destination
`/app/tables/{tableId}/create-game`

This destination is used to create a new game on a specified table.

Only the owner of the table has the permission to create a game.

### Parameters

- `tableId:` The ID of the table where the new game will be created.

### Example message to create a game

Destination: `/app/tables/zcOwdufL3PS1H45R5Lk2/create-game`

### Notifications
After a game is created, all participants of the table will receive a notification with the new game information at /user/{username}/queue/games.

### Example notification of a new game
```json
{
  "id": 1,
  "table": {
    "id": "table1",
    "name": "Table Name",
    "votingSystem": "{1}{2}{3}{4}{5}{6}",
    "owner": {
      "id": 1,
      "username": "ownerUsername"
    },
    "participants": [
      {
        "id": 2,
        "username": "participant1Username"
      }
    ]
  },
  "value": 10,
  "showdown": false
}
```