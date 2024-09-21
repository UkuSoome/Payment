#!/bin/bash


curl -v -X POST \
 http://localhost:8080/payment \
 -H "Content-Type: application/json" \
 -d '{
       "senderAccountId": "1",
       "receiverAccountId": "2",
       "amount": 100
     }'
