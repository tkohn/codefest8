

Create jar with maven (Profiles: local-dev, server-dev)
mvn clean install -P local-dev // use
mvn clean install -P server-dev // use server db

Copy file from local maschine to server
scp ~/development/workspace/codefest8/server/target/codeFEST8-gamification-1.0-SNAPSHOT.jar vw@137.226.183.140:~/server

starting server / jar
java -jar ~/server/codeFEST8-gamification-1.0-SNAPSHOT.jar



REST calls
Alle User ausgeben
```
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

User mit einer bestimmten ID ausgeben
```
GET http://137.226.183.140:8080/aix-cruise/api/v1/users/1

GET /aix-cruise/api/v1/users/1 HTTP/1.1
Host: 137.226.183.140:8080
Cache-Control: no-cache

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

```

Alle Trips für einen bestimmten User ausgeben
```
GET http://137.226.183.140:8080/aix-cruise/api/v1/users/1/trips/

GET /aix-cruise/api/v1/users/1/trips/ HTTP/1.1
Host: 137.226.183.140:8080
Cache-Control: no-cache

[
    {
        "id": 1,
        "user": 1,
        "startTime": 1425712075000,
        "routeLength": 8126.891131655675
    },
    {
        "id": 2,
        "user": 1,
        "startTime": 1425725783000,
        "routeLength": 4851.818062987633
    },
    {
        "id": 3,
        "user": 1,
        "startTime": 1425726286000,
        "routeLength": 7535.008371465469
    }
]
```

Ein bestimmten Trip für einen bestimmten User ausgeben
```
http://137.226.183.140:8080/aix-cruise/api/v1/users/1/trips/1

GET /aix-cruise/api/v1/users/1/trips/1 HTTP/1.1
Host: 137.226.183.140:8080
Cache-Control: no-cache

{
    "id": 1,
    "user": 1,
    "startTime": 1425712075000,
    "routeLength": 8126.891131655675
}
```

Interessante Trip Daten für einen bestimmten Trip
```
GET http://137.226.183.140:8080/aix-cruise/api/v1/users/1/trips/1/data

GET /aix-cruise/api/v1/users/1/trips/1/data HTTP/1.1
Host: 137.226.183.140:8080
Cache-Control: no-cache

[
    [
        6.08639476,   /* long */
        50.78242198,  /* lat*/
        0,            /* gps_speed_kmh */
        0,            /* engine_load */
        0,            /* engine_rpm */
        0,            /* air_temperature */
        0,            /* fuel_level */
        0             /* kpl */
    ],
    [
        6.09679542,
        50.77796613,
        0,
        28.235294342041016,
        898.5,
        6,
        0,
        0
    ]
]
```
