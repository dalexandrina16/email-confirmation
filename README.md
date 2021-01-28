# email-confirmation

I used gmail as a mail service provider. 
When the user registers it is assigned the user role.
An email is being sent with a random string also being attached to the registred user and recorded in the database.
Next the user recieves this string as an url and when clicking on it it generates an api request that changes the user role to user_activated.
Now the user can see the secret page.
