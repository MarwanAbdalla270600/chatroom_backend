### Definieren der Implentationen der **User** Class methods:

1. **friendRequest()**:
Functionality: Sends request to a other registered user of the app.

- takes the username of the receiver as a parameter
- checking if other user is valid/exists and pending request is not already set to true
- if valid -> create new FriendRequestMessage with sender/receiver set and pending to "true"
- create new StatusMessage and set when request and to whom it was sent
- ? inform User that he has new friend request --> needs a field like pendingRequests?

Fragen:
// sollen die gesendeten requests auch in einer liste gespeichert werden?
// wie informieren wir die User, dass neue requests gibt?



2. **acceptFriendRequest()**:
Functionality: Accepts a new friend request and adds new friend to Friendlist.
- takes messageId from FriendRequestMessage as parameter
- check if in this FriendRequestMessage "pending" is on "true".
- add sender User to Friendslist of Receiver and add receiver to friendsList of sender
// eventuell notifications, dass es neue friends gibt



4. **declineFriendRequest()**: 
Functionality: Declines a friend request.
- takes messageId from FriendRequestMessage as parameter
- check if in this FriendRequestMessage "pending" is on "true"
- sets pending to "false"
//eventuell rückmeldung, dass friend abgelehnt wurde oder so, aber vlt auch gar nix



5. **deleteFriend()**:
Functionality: Deletes a friend from the friendList
- takes username as a parameter
- checks if username is in users current friendlist
- if true --> remove username from Set/list zB bei Set: friendList.remove(username);



5. **changeUsername()**:
Functionality: Changes the username of a User.
- use setter Method of Username in User and change name


6. **changePassword()**:
Functionality: Changes password of user.
- use setter Method for pw in user

