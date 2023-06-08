# File-Management-System
There is no requirement of additional downloads and just run the code.
 Here's an outline of the design and implementation for the file management system.
 FileHandler class: This class will contain methods for file and directory operations.

Attributes:

rootDirectory: The root directory path where the file management system operates.
Methods:

createFile(directoryName, fileName, content): Creates a new file with the given name and content in the specified directory.
updateContent(directoryName, fileName, newContent): Updates the content of an existing file.
deleteFile(directoryName, fileName): Deletes an existing file.
createDirectory(directoryName): Creates a new directory.
deleteDirectory(directoryName): Deletes an existing directory and its contents recursively.
changeDirectory(sourceDirectory, destinationDirectory, fileName): Moves a file from one directory to another.
displayContentsOfDirectory(directoryPath): Displays the contents (files and directories) of a directory.
displayFileInformation(directoryName, fileName): Displays information about a file.
searchFiles(searchTerm): Searches for files matching the given search term and returns a list of matching file names.
Task class: This class contains methods that interact with the user and demonstrate the functionality of the file management system.

Methods:
createFileMethod(): Prompts the user to enter the file name, directory name, and content to create a new file.
updateContentMethod(): Prompts the user to enter the file name, directory name, and new content to update an existing file.
deleteFileMethod(): Prompts the user to enter the file name and directory name to delete a file.
createDirectoryMethod(): Prompts the user to enter the directory name to create a new directory.
deleteDirectoryMethod(): Prompts the user to enter the directory name to delete a directory.
changeDirectoryMethod(): Prompts the user to enter the source directory, destination directory, and file name to move a file.
displayContentsMethod(): Prompts the user to enter the directory name to display the contents of a directory.
displayFileInformationMethod(): Prompts the user to enter the file name and directory name to display file information.
searchFilesMethod(): Prompts the user to enter a search term to search for files and displays the matching file names.
Main method: This is the entry point of the program. It creates an instance of the Task class and calls the appropriate methods based on user input.
The time and space complexities for the methods mentioned in the file management system code:

createFile(directoryName, fileName, content):

Time Complexity: O(1)
Space Complexity: O(1)
updateContent(directoryName, fileName, newContent):

Time Complexity: O(1)
Space Complexity: O(1)
deleteFile(directoryName, fileName):

Time Complexity: O(1)
Space Complexity: O(1)
createDirectory(directoryName):

Time Complexity: O(1)
Space Complexity: O(1)
deleteDirectory(directoryName):

Time Complexity: O(N), where N is the total number of files and directories inside the directory being deleted. This is due to the need to traverse and delete each file and directory recursively.
Space Complexity: O(1)
changeDirectory(sourceDirectory, destinationDirectory, fileName):

Time Complexity: O(1)
Space Complexity: O(1)
displayContentsOfDirectory(directoryPath):

Time Complexity: O(N), where N is the number of files and directories inside the given directory. This is because we need to retrieve and display information about each file and directory.
Space Complexity: O(1)
displayFileInformation(directoryName, fileName):

Time Complexity: O(1)
Space Complexity: O(1)
searchFiles(searchTerm):

Time Complexity: O(N), where N is the total number of files in the file management system. In the worst case, we need to search through all files to find matches.
Space Complexity: O(1)
