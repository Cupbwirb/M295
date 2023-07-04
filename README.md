# M295 Documentation

All Information to the M295 Project found here. 
It is about managing all the animals in an animal shelter.

## MySql Database

There are 2 Tables. One is the 'Tierart' table which
already has all the types of animals added to the table and the
other Table is the 'Tier' table which you can add new
animals, update existing animals or delete animals when they are no longer in
the animals shelter.

```sql
create database IF NOT EXISTS M295_Tierheim;
use M295_Tierheim;
```

#### `Tierarten`
```sql
create Table Tierarten(
artId int primary key auto_increment,
arten varchar(100)
);
```

#### `Tier`
```sql
Create Table Tier(
tierId int primary key auto_increment,
tierName varchar(100),
tierAlter int,
artId int,
FOREIGN KEY (artId) REFERENCES Tierarten(artId));
```

### `INSERTS`
```sql
-- mandatory
insert into Tierarten(arten)
values ('Hund'), ('Katze'), ('Vogel'), ('Reptil'), ('Fisch');

-- Test datas
insert into Tier(tiername, tierAlter, artId)
values('Priscilla', 18, 2), ('Lilo', 20, 3);
```

## Spring Boot REST API
It can be reached by `http://localhost:8080`
### Paths:
#### /tier
`GET`
- It will return a whole list of animals which are registered in the database.
- Responses: 200 OK

`POST`
- Adds a new animal.
- In Postman in "Body" set "raw" in "JSON" to add a new animal.
- Example (tierId is auto_increment): 
{
   "tierName":"Litchi",
   "tierAlter":5,
   "artId":1
   }
- Responses: 200 OK

#### /tier/{tierId}
`GET`
- It will return the animal with that tierId.
- Responses: 200 OK

`PUT`
- Updates an animal.
- In Postman in "Body" set "raw" in "JSON" to update an animal which you set the tierId in the path.
- Example: {
  "tierName":"Joshi",
  "tierAlter":"9",
  "artId":"2"
  }
- Responses: 200 OK

`DELETE`
- Deletes an animal by its tierId.
- Responses: 200 OK

#### /art
Here you can see all animal types which are allowed in the animals shelter.
###### Security
This `/art` endpoint is secured. To access this endpoint please use "Basic Auth".
- `Username: user`
- `Password: 12345`

# M294 Documentation
The "Index.html" you will find in: 
`src/main/resources/static/M294/Index.html`
<br>
Additional information you should know:
### `Update Animal`
Giving an Id is required to update an animal. You can also only update
1 or 2 Fields. It is not required to update all Fields, they can just stay empty.
### `Get Animal`
There is a `Get` Button which lists you every animal in the Database. Whenever you Post, Put  or Delete,
the List will automatically update.

### `Get Art`
The "Art" endpoint is secured. You have to log in first. 
- `Username: user`
- `Password: 12345`
<br>
After you have successfully logged in, all the animal types will be displayed.
You can logout and login again whenever you want.


