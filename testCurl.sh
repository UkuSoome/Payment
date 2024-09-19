#!/bin/bash


curl -v -X POST \
 http://localhost:8080/payment \
 -H "Content-Type: application/json" \
 -d '{
       "senderAccountId": "35342",
       "receiverAccountId": "543543",
       "amount": 100
     }'
