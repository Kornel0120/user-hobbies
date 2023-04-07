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

# New Endpoints:

/users/dequeueUsers : dequeue first users from the queue

/users/peekUsers : peek first users from the queue

/queue/enqueue/{string} : enqueue string to the queue

/queue/dequeue : dequeue first string from the queue

/queue/peek : peek first string from the queue

/queue/isEmpty : check if the queue is empty

/queue/size : get the size of the queue
