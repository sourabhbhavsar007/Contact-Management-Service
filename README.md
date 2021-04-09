# Contact-Management-Service
This application facilitates users to manage contacts. New contacts can be added, existing can be fetched, filtered on some parameters.


=====Contact Management Service========

This application facilitates the users to manage the contacts. Contacts can be added and saved to the in-memory H2 Database, all contacts 
can be fetched, contacts can be searched using contactId, which is an unique identifier. Also, filters can be applied on zipcode,
and contacts matching those criteria can be fetched.

We also have implemented logging functionality and log the requests and responses/exceptions, 
since it is a cross-cutting concern across the application.


Features of the application along with their endpoints can be described as follows:

Base URL : http:\\localhost:8080/api/v1


1) GET /contacts 

This endpoint fetches all the contacts which are persisted in our in-memory H2 database.
It returns a list of Contact objects.




2) GET /contacts/{contactId}

This endpoint will facilitate to fetch contact whose contactId matches with the one given in the URI path.
If the contact with matching contactId is not found ,we return HttpStatus 404 Resource Not Found.




3) POST /contacts

This endpoint uses Http POST method and takes the Contact object passed in RequestBody and persists it in our
in-memory H2 database.

If the contact with same identifier is passed in RequestBody is present in the database, we throw an exception 
HttpStatus 409 Conflict and HttpStatus 400 Bad Request for any other exception.

Application will perform every time a new contact is added and also logs exceptions which occurred while processing the
request of adding new contact.



4) GET /contacts/filter/zipcode/{zipcode}

This endpoint is uses as filter on zipcode. This will work similar to LIKE clause in SQL.We pass a query/keyword and only records 
whose zipcode matches the given query are fetched.

For example : if we pass query as 42, records whose zipocde has 42 in it, will be fetched, like 425000, 428980, 435642




5) GET /contacts/filter/name/{name}

This endpoint is filter on full name. This will work similar to LIKE clause in SQL.
We pass a query/keyword and only records whose full name matches the given query are fetched.

For example : if we pass query as Jam, records whose full name has Jam in it, will be fetched, like James, Jamie, Jameson




