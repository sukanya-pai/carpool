## Review Comments
Following are some of the review comments found:
- Usually for adding resources to database, POST Mapping is used as part of convention.
PUT is used for updating resources.
- For Deleting resources, DELETE (or POST) method is used. Here, Get is used which goes against general convention
  - Delete logic resulted in ConcurrentObjectModification exception - To fix this, break was added once the object was deleted to stop list modification and accessing it in for loop.
- Appropriate response messages and status codes has to be sent based on requirements.
  - While adding resource, we can send "Car added successfully with ID 1" instead of whole object
  - While deleting car, we can send "Car with ID 2 removed successfully" instead of no response.
- While loading objects from JSON, given logic used Pattern Matchers to get the data. This causes tight coupling and hard coding which forces developers to change the code logic if new attributes of cars are added.
  - Made changes to get the JSON objects as list without need for Pattern Matchers by making use of org.json library.
- While String comparison, we have to use .equals() method to compare contents of the variables accurately.
  - In get car for a given ID, there was == comparison which was not matching any of the ids and hence car details were not returned successfully.
  - Fixing it with .equals, solved the issue.
- While using switch statements, for different cases we have to use break, else the default case will be executed always.
  - Alternately, we can use a hashmap of color as key and RGB equivalent representation of the color as value to create a lookup datastructure.
  - Instead of switch, if we use hashmap.get(color), it should return the RGB value.
- It is always recommended to use getters and setters in Java Classes for data security.
- Proper exception throwing and handling mechanisms are not incorporated. What if we are trying to add duplicate cars? Or we are trying to delete cars that are not there?
- It is recommended to follow proper naming conventions for the classes and proper package structure 
- Regarding unit test cases:
  - While writing unit test cases, it is recommended to include the test class inside the same package in test/java folder to that of the class under test present in main/java folder
  - The name of the class should be "<ServiceClass>Test" as part of general convention 
  - The name of test methods should be meaningful and define what the purpose of the test case is.
- Proper variable naming conventions has to be followed in test and the source classes.
- Appropriate Java Doc and inline comments have to be used on the methods to ensure that code is more readable.
## Notes
- Created config package and added ApplicationConfig and CarPoolConfig classes to load the cars when the server is started instead of loading it in the CarPoolService constructor.
- Created controller package and CarPoolController.java for updating the controller file code.
- Created service package and CarPoolService.java
- Created exception package and within that 2 classes - CarAlreadyExistsException and CarNotFoundException
- Updated the APIs in the controller package by incorporating the ResponseEntity object to send responses accordingly.
- Added validators for checking if id exists or not during addition of cars and deletion and while getting the car details.
- Created mapper package and created ObjectToCarMapper class that has one function of converting object to Car. This class has been autowired to CarPoolService to demo the concept of dependency injection.
- Added Getters and setters in the Car class to access or update parameters.
- Added Java doc comments for all methods created newly.
- Created service package inside test/java folder and created CarPoolServiceTest class
  - Wrote success and failure test cases for add new car scenario.