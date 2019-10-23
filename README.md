# StudProgram
JavaFX program that tracks stud IDs and the defects associated with those studs.

This program is a real-world program that could be utilized by the quality department at Mercedes-Benz Vans in Ladson, SC. There are over 100 studs that go on each van – some automatically added by a robot, some manually added by an employee. Both processes can result in missing, broken, or off location studs that require rework/repair further down the assembly line. I made a program to help track the type of defect, when it happened, which stud, and what that stud is used for. There are also a few other features not mentioned.

Here is a breakdown of the program and interfaces:

<strong>Main Window / Plant Window

Main Window – Plant window that has 4 options
1) Find Stud
2) Show Defects
3) Show Studs
4) Done

Find Stud:</strong><br>
When find stud is clicked, a new window will popup that will ask for a stud ID. You can search or hit done to close the window.

<strong>A)</strong> If the stud is found the window will expand to include all the details of the stud object. It will also include a TableView table to show all findings of this stud. You can then “Add Findings” or “Edit Containment”. Containment is the actions we are doing in house to fix the defect.

When add findings is clicked, another window pops up with the stud Id, 3 radio buttons, a date calendar, and a text field for units affected. User MUST select a date and enter a positive number for units affected or the user will get an error alert. If input is all correct and “Add” button is clicked, the defect will be added to the defect array list and written to the defect csv file. Error alert given if there is an error adding to the file. Cancel simply shuts the window.

The table and values in Find Stud window are not updated immediately with an observable. I plan to implement this soon. Click search again with the same stud ID and the values will update.

When “Edit Containment” is clicked, a simple window will show up to type in a new containment. If submit is clicked, the containment for that stud will change. Also does not update immediately. Would also implement that and have that written to the file as well when I have more time.

<strong>B)</strong> If the stud is NOT found, a new button, “Add Stud” will become visible. When this button is clicked, a new window will pop up similar to Add Findings, that will allow a user to create a new stud. Clicking add stud will also close Find stud window to prevent users from adding the same stud multiple times. Stud will be created using the process selected by user and the name added. New stud object is added to stud array list and added to csv stud file.

<strong>Show Findings:</strong><br>
Simply a tableview that shows a table of all defect findings, sorted by date using a comparator. When a new defect is added, the new defect will be included if window is closed and reopened. Would like to implement an observable pattern soon for this as well.

<strong>Show Defects:</strong><br>
Essentially the same as show findings, except that all studs are shown, rather than all defects. New studs are added and ordered by studID.

<strong>Done:</strong><br>
Closes the window!
