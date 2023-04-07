# Endpoints:

/users/ : Get all user

/users/ : Post user 

{
    "userId": 12,
    "firstName": "post",
    "lastName": "user",
    "age": 43,
    "gender": "FEMALE"
}

/users/hobbies : get all hobby

/users/{id}/hobbies : get spec hobbies

# New endpoints:

/users/queue/postUser: Queues the request into a Queue and a thread executes them in order.


