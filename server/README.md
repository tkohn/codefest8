

Create jar with maven (Profiles: local-dev, server-dev)
mvn clean install -P local-dev // use
mvn clean install -P server-dev // use server db

Copy file from local maschine to server
scp ~/development/workspace/codefest8/server/target/codeFEST8-gamification-1.0-SNAPSHOT.jar vw@137.226.183.140:~/server

starting server / jar
java -jar ~/server/codeFEST8-gamification-1.0-SNAPSHOT.jar



REST calls
```
Alle User ausgeben:
GET http://137.226.183.140:8080/aix-cruise/api/v1/users/

GET /aix-cruise/api/v1/users/ HTTP/1.1
Host: 137.226.183.140:8080
Cache-Control: no-cache

[
    {
        "id": 1,
        "name": "Karl Heinz",
        "friends": null,
        "trips": [
            1,
            2,
            3
        ],
        "achievements": []
    }
]
```

GET /aix-cruise/api/v1/users/7 HTTP/1.1
Host: 137.226.183.140:8080
Cache-Control: no-cache