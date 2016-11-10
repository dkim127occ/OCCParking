# OCCParking
An application to track and guide the user to empty parking space in Orange Coast College.

###Database tables
* One table per parking lot
    * INTEGER ID: each unique parking spot
    * TEXT type: type of the lot (staff, handicapped, metered, etc)
    * REAL X: GPS x-coordinate of the spot
    * REAL Y: GPS y-coordinate of the spot
    * INTEGER filled: 0 for empty, 1 for filled 
