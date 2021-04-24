# Overview

You are going to build in Java a Binary Heap (BHEAP). Specifically, you will code a minimum binary heap (BHEAP) with the numerically smallest priority number at the root of the tree. We will a couple of the heap operations efficient (faster than O(N)) with the help of a hashmap. Let's call this ADT a "HashHeap". This is a data structure that would support, for example, a Least Frequently Used caching policy in various operating system functions (like caching pages from virtual memory).

In the text (and class examples) the heaps have contained an integer (the priority). This is for clarity of the examples... and the array containing the heap elements was array of integer. In this example, on order to allow hashing access to the heap elements, we will have the heap array contain pointers to dynamically allocated cells. One of the fields in a cell will be a priority number, to be used to structure the heap. The other field(s) in a cell object will be data for the application (cache pages, for example). For this assignment, the particular data is not relevant, so we use a structure similar to the cells from the previous two assignments. See the code template for details.

**_Caching with LFU Page Replacement_**

Caching is a well proven strategy for speeding up many computing tasks. When data is stored in some slower stoage medium (like a disk or the cloud), we can retrieve data from that slow medium the first time we need it, and then save it into some faster memory (like on-chip RAM) to use for subsequent accesses. From time to time altered data needs to be written from the cache back to the slower storage. When the cache fills (it has limited size) and we need to retrieve more information from slow storage, we must decide what part of the cache to send back to make room for the newly retrieved data.

There are many strategies for replacing portions of a cache when we need to. Least Recently Used (LRU) is one common approace... choose the cache frame that was used the farthest back in time and replace it with the new page data. This recognizes "locality", that when we reference variables we often re-reference them in a near time frame. The reasoning behind LRU is that we find the cache frame that is inactive the longest, assuming that the timeframe in which the code using the data in that frame is most likely to have passed. A slighty altered FIFO Queue will keep track of this for us. When a cache frame is referenced, we find it in the queue, and move it to the head of the queue. When we need to replace a cache frame, we take out the one at the end of the queue.

Another replacement strategy is called Least Frequently Used (LFU). Here, we find the cache frame that has not been referenced very many times and replace that one. Cache frames that are referenced heavily will stay in the cache. To do this we have to keep a counter on each cache frame, and bump up that counter each time the program uses memory in the cache. We can use a minimum binary heap to efficiently decide which cache frame has the smallest use counter (and so has been referenced least frequently) when we need to replace a frame. This is the sort of thing we are supporting in this assignment.

**_How does a HashMap help?_**

You can think of your "cache" in this assignment as a collection of cells that are dynamically allocated in your program. The cells contain some representation of the memory data and some supporting information to make the cache and LFU policy work. The memory data is not important to use here, so we will simply use a hexadecimal string (representing a memory address... such as perhaps the beginning address of a memory block) as the entire data field. Then we have two other data structures containing pointers to these cells: a MinBinHeap where the cell pointers are stored in the heap array; and a HashMap that pairs up the hexadecimal "address" for a cell with a pointer to the cell for that address. As such, the HashMap will use String as key, and cell object as value. We will use the HashMap from the Java collections library, but you will write your own minimum binary heap code as part of this assignment (following the template below).

**_The Cache_LFU structure_**

The Cache_LFU then will then be an object containing both a HashMap and a MinBinHeap (MBH). It will have an interface of operations that it responds to, the main one being "refer". The refer operation takes a String parameter, the address of a memoory block needed by the application code using the cache. For example, consider the call

   cache.refer("AA88");

This indicates the page for address AA88 is needed from the cache, and several things might be the case:

  -- the frame might be in the cache, so it can be used as is and we
     must bump up the frequency counter by 1 to reflect the new access to that frame

  -- the frame might NOT be in the cache, so it must be added to the cache and
     its frequency counter set to 1 to record this first access.
     Since we need to add a frame to the cache, we have 2 posibilities:

       -- if the cache is not full, there is room to just create a new frame, add
          it to the cache and record the frequency counter as 1

       -- if the cache IS full, then we must take something out to make room for
          the new frame; we do this by asking the heap which frame is least frequently
          used and remove that one; we then have room to make the new frame with its
          frequency counter set to 1

What does it mean to "add a frame to the cache" ? There are several steps. First, we make a new cache frame object and set its fields to hold the String address and the frequency counter 1. We then must "put" an entry for the (address, frame object) pair into the HashMap. This gives us O(1) accress to the frame object if we know the address String. Then we have to put that frame object into the MnBinHeap as well... and with the frequency count field being used as the priority to guide where the cell ends up in the heap array. Note that since the frequency counter is 1 when we add a new frame to the heap, it will percolate up towards the root of the heap tree... towards slot 1 in the heap array. Other frames that have been referened more will be lower down in the heap.

Now, what happens when we "refer" to an address and the frame is already in the cache? We know a frame is in the cache, because we do a "get" on the HashMap and find an entry for the (address, frame object) there. In this case, there is no need to make any new frames, but we do need to record that we have another reference to the frame as the address. So we follow the pointer to the frame object and increase it frequency count field by 1. This means its no longer (possibly) in the proper place in the heap array. So we also mush do an heap.incElt(frame_object) operation on the heap and move the frame object to its new proper location.

So, here is how the HashMap helps. We do not have to do a O(N) search throught the heap array to find where the frame object parameter is located (at what array subscript). Rather we have the pointer to the frame object from the HashMap (a O(1) "search") and we have a field in the frame object that tells which array subscript is lives at in the heap array. So we can directly addresss the heap array at that element, and do the bubbling down required by the incElt operation.

Note that this means we must keep the "slot" field in the frame object updated properly whenever we move a cell around in the heap array.

**_What are we learning here?_**

Keeping the "slot" field of each frame object updated is a cheap (small constant) amount of work every time we swap array elements. We are happy to do that work to change the element search on incElt from O(N) to O(1). We win!

Think on the incElt operation. In order to bump up the priority of some item in the MBH we first need to find the item we are interested in. In the array representation of the MBH, this means searching every item in the array, from slot 0 up. This means in worst case, O(N) for N items in the MBH.

Using a HashMap to save pointers to all cells in the MBH means we can cut the cost of the incElt to O(log N). Here's how: we must rearrange the MBH because some cache frame is being referenced, and so the frequency counter (priority) must be bumped up. We have the address of the cache frame (or some unique ID for it, here a hex string) so we access the HashMap and get back a pointer to the cell in the MBH in O(1). Once we have that pointer we have the subscript where we can find it in the MBH array in O(1).

We go there via array access, and do the increase-key operation on the cell ( O(log N) , including increment priority field). As we move cells around in the MBH array, we must remember to update the subscript information in those cells telling where they live in the MBH array.

The payoff:
We will need this sort of HashMap support to make graph manupulation algorithm perform efficiently when we build a graph data structure in Assn 5. We will need to find components of a graph in O(1) time instead of searching through a list of N elements. In this HashHeap assignment, the HashMap helps us keep a O(N) operation down to O(log N)... which is good to do, but not a killer if we have to do the O(N) search instead. In the graph applications, the HashMap will help up have O(N) and O(N log N) operations instead of O(N^2) or worse. This then becomes crucial rather than just nice. Overview

You are going to build in Java a Binary Heap (BHEAP). Specifically, you will code a minimum binary heap (BHEAP) with the numerically smallest priority number at the root of the tree. We will a couple of the heap operations efficient (faster than O(N)) with the help of a hashmap. Let's call this ADT a "HashHeap". This is a data structure that would support, for example, a Least Frequently Used caching policy in various operating system functions (like caching pages from virtual memory).

In the text (and class examples) the heaps have contained an integer (the priority). This is for clarity of the examples... and the array containing the heap elements was array of integer. In this example, on order to allow hashing access to the heap elements, we will have the heap array contain pointers to dynamically allocated cells. One of the fields in a cell will be a priority number, to be used to structure the heap. The other field(s) in a cell object will be data for the application (cache pages, for example). For this assignment, the particular data is not relevant, so we use a structure similar to the cells from the previous two assignments. See the code template for details.
Caching with LFU Page Replacement

Caching is a well proven strategy for speeding up many computing tasks. When data is stored in some slower stoage medium (like a disk or the cloud), we can retrieve data from that slow medium the first time we need it, and then save it into some faster memory (like on-chip RAM) to use for subsequent accesses. From time to time altered data needs to be written from the cache back to the slower storage. When the cache fills (it has limited size) and we need to retrieve more information from slow storage, we must decide what part of the cache to send back to make room for the newly retrieved data.

There are many strategies for replacing portions of a cache when we need to. Least Recently Used (LRU) is one common approace... choose the cache frame that was used the farthest back in time and replace it with the new page data. This recognizes "locality", that when we reference variables we often re-reference them in a near time frame. The reasoning behind LRU is that we find the cache frame that is inactive the longest, assuming that the timeframe in which the code using the data in that frame is most likely to have passed. A slighty altered FIFO Queue will keep track of this for us. When a cache frame is referenced, we find it in the queue, and move it to the head of the queue. When we need to replace a cache frame, we take out the one at the end of the queue.

Another replacement strategy is called Least Frequently Used (LFU). Here, we find the cache frame that has not been referenced very many times and replace that one. Cache frames that are referenced heavily will stay in the cache. To do this we have to keep a counter on each cache frame, and bump up that counter each time the program uses memory in the cache. We can use a minimum binary heap to efficiently decide which cache frame has the smallest use counter (and so has been referenced least frequently) when we need to replace a frame. This is the sort of thing we are supporting in this assignment.
How does a HashMap help?

You can think of your "cache" in this assignment as a collection of cells that are dynamically allocated in your program. The cells contain some representation of the memory data and some supporting information to make the cache and LFU policy work. The memory data is not important to use here, so we will simply use a hexadecimal string (representing a memory address... such as perhaps the beginning address of a memory block) as the entire data field. Then we have two other data structures containing pointers to these cells: a MinBinHeap where the cell pointers are stored in the heap array; and a HashMap that pairs up the hexadecimal "address" for a cell with a pointer to the cell for that address. As such, the HashMap will use String as key, and cell object as value. We will use the HashMap from the Java collections library, but you will write your own minimum binary heap code as part of this assignment (following the template below).
The Cache_LFU structure

The Cache_LFU then will then be an object containing both a HashMap and a MinBinHeap (MBH). It will have an interface of operations that it responds to, the main one being "refer". The refer operation takes a String parameter, the address of a memoory block needed by the application code using the cache. For example, consider the call

   cache.refer("AA88");

This indicates the page for address AA88 is needed from the cache, and several things might be the case:

  -- the frame might be in the cache, so it can be used as is and we
     must bump up the frequency counter by 1 to reflect the new access to that frame

  -- the frame might NOT be in the cache, so it must be added to the cache and
     its frequency counter set to 1 to record this first access.
     Since we need to add a frame to the cache, we have 2 posibilities:

       -- if the cache is not full, there is room to just create a new frame, add
          it to the cache and record the frequency counter as 1

       -- if the cache IS full, then we must take something out to make room for
          the new frame; we do this by asking the heap which frame is least frequently
          used and remove that one; we then have room to make the new frame with its
          frequency counter set to 1

What does it mean to "add a frame to the cache" ? There are several steps. First, we make a new cache frame object and set its fields to hold the String address and the frequency counter 1. We then must "put" an entry for the (address, frame object) pair into the HashMap. This gives us O(1) accress to the frame object if we know the address String. Then we have to put that frame object into the MnBinHeap as well... and with the frequency count field being used as the priority to guide where the cell ends up in the heap array. Note that since the frequency counter is 1 when we add a new frame to the heap, it will percolate up towards the root of the heap tree... towards slot 1 in the heap array. Other frames that have been referened more will be lower down in the heap.

Now, what happens when we "refer" to an address and the frame is already in the cache? We know a frame is in the cache, because we do a "get" on the HashMap and find an entry for the (address, frame object) there. In this case, there is no need to make any new frames, but we do need to record that we have another reference to the frame as the address. So we follow the pointer to the frame object and increase it frequency count field by 1. This means its no longer (possibly) in the proper place in the heap array. So we also mush do an heap.incElt(frame_object) operation on the heap and move the frame object to its new proper location.

So, here is how the HashMap helps. We do not have to do a O(N) search throught the heap array to find where the frame object parameter is located (at what array subscript). Rather we have the pointer to the frame object from the HashMap (a O(1) "search") and we have a field in the frame object that tells which array subscript is lives at in the heap array. So we can directly addresss the heap array at that element, and do the bubbling down required by the incElt operation.

Note that this means we must keep the "slot" field in the frame object updated properly whenever we move a cell around in the heap array.
What are we learning here?

Keeping the "slot" field of each frame object updated is a cheap (small constant) amount of work every time we swap array elements. We are happy to do that work to change the element search on incElt from O(N) to O(1). We win!

Think on the incElt operation. In order to bump up the priority of some item in the MBH we first need to find the item we are interested in. In the array representation of the MBH, this means searching every item in the array, from slot 0 up. This means in worst case, O(N) for N items in the MBH.

Using a HashMap to save pointers to all cells in the MBH means we can cut the cost of the incElt to O(log N). Here's how: we must rearrange the MBH because some cache frame is being referenced, and so the frequency counter (priority) must be bumped up. We have the address of the cache frame (or some unique ID for it, here a hex string) so we access the HashMap and get back a pointer to the cell in the MBH in O(1). Once we have that pointer we have the subscript where we can find it in the MBH array in O(1).

We go there via array access, and do the increase-key operation on the cell ( O(log N) , including increment priority field). As we move cells around in the MBH array, we must remember to update the subscript information in those cells telling where they live in the MBH array.

The payoff:
We will need this sort of HashMap support to make graph manupulation algorithm perform efficiently when we build a graph data structure in Assn 5. We will need to find components of a graph in O(1) time instead of searching through a list of N elements. In this HashHeap assignment, the HashMap helps us keep a O(N) operation down to O(log N)... which is good to do, but not a killer if we have to do the O(N) search instead. In the graph applications, the HashMap will help up have O(N) and O(N log N) operations instead of O(N^2) or worse. This then becomes crucial rather than just nice.

**_Hopefully helpful diagram_**
[!Link to Diagram](https://github.com/ckong6953/COMP410/blob/main/ckong727_assignment4/hashheap.png)
