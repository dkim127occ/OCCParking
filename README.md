# OCCParking
An application to track and guide the user to empty parking space in Orange Coast College.

###Database tables
* One table per parking lot
    * INTEGER ID: three-digit number that represents each space
        * first digit - row number
        * last two digits - unique ID of the spot in that particular row 
    * TEXT type: type of the lot (staff, handicapped, metered, etc)
    * REAL X: GPS x-coordinate of the spot
    * REAL Y: GPS y-coordinate of the spot
    * INTEGER filled: 0 for empty, 1 for filled 
