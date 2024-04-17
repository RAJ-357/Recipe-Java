

# Create USER
curl -X POST -H "Content-Type: application/json" -d '{"id":1, "name":"John Doe"}' http://localhost:8080/users/

#Read users
curl http://localhost:8080/users/

#Update user
curl -X PUT -H "Content-Type: application/json" -d '{"name":"Jane Doe"}' http://localhost:8080/users/1

#Delete user
curl -X DELETE http://localhost:8080/users/1
