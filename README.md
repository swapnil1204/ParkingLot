# ParkingLot


I learn about dependency injection...

how attendent class parking vehicle accordingly.

declare :  ParkingStrategy parkingStrategy;       and initialise in constructor.    (is called dependency injection)

then Attendent used that strategy to implement while parking . (So the method park() is invoked of perticular strategy like
ParkingLotWithMaximumCapacity,ParkingLotWithMaximumFreeSpace accordingly)



################ LEARNING ###############
Using comparator interface- Comparator interface is used to order the objects of user-defined class(In this case, parking lot). This interface is present in java.util package and contains 2 methods compare(Object obj1, Object obj2) and equals(Object element). Using comparator, we can sort the elements based on data members (In this case, CAPACITY and FREESPACE).


Internally the SORT method does call Compare method of the classes it is sorting. To compare two elements, it asks “Which is greater?” Compare method returns -1, 0 or 1 to say if it is less than, equal, or greater to the other. It uses this result to then determine if they should be swapped for its sort.
