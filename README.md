# OCCParking
An application to track and guide the user to empty parking space in Orange Coast College.

##Purpose
This app aims to ease the process of finding an empty parking space.

##Data model
* One table per parking lot
    * the table will then be converted into a `ParkingLot` object
    * Each `ParkingLot` object will contain an array of multiple `ParkingSpace` objects

###Database fields for each parking space
* `INTEGER ID`: ID number of the parking space 
* `TEXT type`: type of the lot (staff, handicapped, metered, etc)
* `REAL latitude`: latitude coordinate of the parking space
* `REAL longitude`: longitude coordinate of the parking space
* `INTEGER filled`: 0 for empty, 1 for filled 

##Credits
Freddy Juarez  
Justin Kloppenburg  
Austin Kim
