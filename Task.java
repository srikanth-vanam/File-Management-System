import java.util.*;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

class FileHandler{
    String name;
    String content;
    long contentSize;
    
    String rootDirectory;

    public FileHandler(String rootDir){
        this.rootDirectory=rootDir;
    }


    // Getters and setters

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public long getSize() {
        return contentSize;
    }


    // This is used to Create a File in rootdirectory as well as any given directories.
    public void CreateFile(String dirName,String name, String content) {
        this.name = name;
        this.content = content;
        this.contentSize=content.length();

       File file=null;
        if(dirName.equals("NULL") || dirName.equals("null")){
            file = new File(rootDirectory+ name + ".txt");

        }
        else{
            System.out.println("inside else:");
            file = new File(rootDirectory+ dirName +"//"+ name + ".txt");
        }
        System.out.println(rootDirectory);
        try {
            if (file.createNewFile()) {
                System.out.println("File created successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e);
        }

        // This block of code is used to write the contents into the file
        try{
            FileWriter filew=null;
            if(dirName.equals("NULL") || dirName.equals("null")){
                filew = new FileWriter(rootDirectory+ name + ".txt");
    
            }
            else{
                filew = new FileWriter(rootDirectory+ dirName +"//"+ name + ".txt");
            }
            filew.write(content);
            filew.close();
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while writing to file");
        }
    }

    // This method is used to Update the old content with the new content
    public void updateContent(String directoryName,String fileName,String newContent) {
        this.contentSize = newContent.length();
        
        try{
            FileWriter file=null;
            if(directoryName.equals("NULL") || directoryName.equals("null")){
                file = new FileWriter(rootDirectory+ fileName + ".txt");
    
            }
            else{
                file = new FileWriter(rootDirectory+ directoryName +"//"+ fileName + ".txt");
            }
            
            file.write(content);
            file.close();
            System.out.println("content updated");
        }catch (IOException e) {
            e.printStackTrace();
            System.err.println(e);
        }
    }

    // This method is used to  Delete a file
    public void deleteFile(String  directoryName, String fileName) {
        File file=null;
        if(directoryName.equals("NULL") || directoryName.equals("null")){
            file = new File(rootDirectory+ fileName + ".txt");

        }
        else{
            file = new File(rootDirectory+ directoryName +"//"+ fileName + ".txt");
        }

        if (file.exists() && file.isFile()) {
            file.delete();
            System.out.println("File has been deleted successfuly.");
        } else {
            System.out.println("File '" + fileName + "' not found.");
        }
    }

    // This method is used to create Directory
    public void createDirectory(String dirName){
        File file = new File(rootDirectory +dirName);
        
            if (file.mkdirs()) {
                System.out.println("directory created successfully.");
                
            } else {
                System.out.println("directory already exists.");
            } 
    }

    //This method us used to delete directory
    public void deleteDirectory(String directoryName) {
        File directory = new File(rootDirectory +directoryName);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        file.delete();
                        System.out.println("Directory "+file.getAbsolutePath()+" is deleted ");
                    } else {
                        file.delete();
                        System.out.println("Directory "+file.getAbsolutePath()+" is deleted ");
                    }
                }
            }
            directory.delete();
            System.out.println("Directory "+directory.getAbsolutePath()+" is deleted ");
        } else {
            System.out.println("Directory '" + directoryName + "' not found.");
        }
    }


    //This method is used to move files from one directory to another directory
    public void changeDirectory(String sourceDirectory,String destinationDirectory,String fileName){
        File file=new File(rootDirectory+sourceDirectory+"//"+fileName+".txt");
        boolean ans=file.renameTo(new File(rootDirectory+destinationDirectory+"//"+fileName+".txt"));
        
        if(ans){
            System.out.println("Filed moved");
            System.out.println("Name: " + file.getName());
            System.out.println("Size: " + file.length());
            System.out.println("Created At: " + new Date(file.lastModified()));
        }
        else
            System.out.println("File cannot be moved ");
    }

    // This method is used to display the contents of the given directory
    public  void displayContentsOfDirectory(String directoryPath) {

        if(directoryPath.equals("null") || directoryPath.equals("NULL")){
            directoryPath=rootDirectory;
        }

        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        System.out.println("Directory: " + file.getName());
                    } else {
                        System.out.println("File: " + file.getName());
                    }
                }
            }
        } else {
            System.out.println("Directory does not exist.");
        }
    }

    // This method is used to display the file details
    public void displayFileInformation(String directoryName, String fileName) {
        File file=null;
        if(directoryName.equals("NULL") || directoryName.equals("null")){
            file = new File(rootDirectory+ fileName + ".txt");

        }
        else{
            file = new File(rootDirectory+ directoryName +"//"+ fileName + ".txt");
        }
        if (file.exists() && file.isFile()) {
            System.out.println("Name: " + file.getName());
            System.out.println("Size: " + file.length());
            System.out.println("Created At: " + getCreationDate(file)); 
            System.out.println("LastModified At: "+new Date(file.lastModified()));
        } else {
            System.out.println("File '" + fileName + "' not found.");
        }
    }
     // method to get the Created Date of a file
    private Date getCreationDate(File file) {
        try {
            Path filePath = file.toPath();
            BasicFileAttributes attributes = Files.readAttributes(filePath, BasicFileAttributes.class);
            return new Date(attributes.creationTime().toMillis());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /// This is the SEARCH METHOD 
    public List<String> searchFiles(String searchTerm) {
        List<String> matchedFiles = new ArrayList<>(); // This contains the files name with matching searchTerm in the fileName or file content

        File directory = new File(rootDirectory); 

        if (directory.exists() && directory.isDirectory()) {
            searchFilesInDirectory(directory, searchTerm, matchedFiles);
        } else {
            System.out.println("Directory does not exist.");
        }

        return matchedFiles;
    }
    // REcursive code for searching
    private void searchFilesInDirectory(File directory, String searchTerm, List<String> matchedFiles) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    searchFilesInDirectory(file, searchTerm, matchedFiles);
                } else {
                    if (fileMatchesSearchTerm(file, searchTerm)) {
                        matchedFiles.add(file.getName());// file.getAbsolutePath() is changed to file.getName();
                    }
                }
            }
        }
    }
    // Method to check the fileName or file contents contains the searchTerm or not
    private boolean fileMatchesSearchTerm(File file, String searchTerm) {
        if (file.getName().contains(searchTerm)) {
            return true;
        }

        try {
            Path filePath = Paths.get(file.getAbsolutePath());
            String content = new String(Files.readAllBytes(filePath));
            return content.contains(searchTerm);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }



}

class Task{
        String fileName;
        String directoryName;
        String content;
        String sourceDirectory;
        String destinationDirectory;
        String directoryPath;
        String searchPattern;

        String rootDirectoryy="C://java//"; /// The root directory where all files and folders are created

        FileHandler fm=new FileHandler(rootDirectoryy);
        
        Scanner sc=new Scanner(System.in);

        public void CreateFileMethod() {
            System.out.println("File creation method takes FileName, DirectoryName, content as Parameters:");
            System.out.println("If you want to create a file in the root directory, enter 'null' or 'NULL' as the Directory Name.");
            System.out.println("Enter the File Name and Directory Name:");
            fileName = sc.next();
            directoryName = sc.next();
            System.out.println("you have to enter the file content so");
            System.out.println("Enter the file content:");
            content = sc.nextLine();
            fm.CreateFile(directoryName, fileName, content);
        }
        

       public void deleteFileMethod(){

            System.out.println("File deletion method takes FileName, DirectoryName as Parameters:");
            System.out.println("If you want to delete a file in root directory then enter 'null' or 'NULL' as Directory Name:");
            System.out.println("Enter the File Name :");
            fileName=sc.next();
            System.out.println("Enter direcory Name:");
            directoryName=sc.next();
            
            
            fm.deleteFile(directoryName,fileName);
       }

       public void createDirectoryMethod(){
            System.out.println("This takes only directory Name as parameter:");
            System.out.println("Enter the Directory Name:");
            directoryName=sc.next();
            
            fm.createDirectory(directoryName);
       }


       public void deleteDirectoryMethod(){
            System.out.println("This takes only directory Name as parameter:");
            System.out.println("Enter the Directory Name:");
            directoryName=sc.next();
            
            fm.deleteDirectory(directoryName);
       }

       public void changeDirectoryMethod(){
            System.out.println("This takes Source, Destination directories and fileName as parameters:");
            System.out.println("Enter the SourceDirectory:");
            sourceDirectory=sc.next();
            System.out.println("Enter DestinationDirecory Name:");
            destinationDirectory=sc.next();
            System.out.println("Enter fileName:");
            fileName=sc.next();
            
            fm.changeDirectory(sourceDirectory, destinationDirectory, fileName);

       }

       public void updateContentMethod(){
            System.out.println("This method takes FileName, DirectoryName, NewContent as Parameters:");
            System.out.println("Enter new content");
            content=sc.nextLine();
            System.out.println();

            System.out.println("Enter the File Name :");
            fileName=sc.next();
            System.out.println("Enter direcory Name if the file is in root Directory then enter 'null' or 'NULL':");
            directoryName=sc.next();
            
            
            fm.updateContent(directoryName, fileName, content);
       }
       public void displayContentsMethod(){
            System.out.println("This takes directory name as the only  parameter:");
            System.out.println(" if directory is root Directory then enter null else Enter the directory name:");
            directoryPath=sc.next();
            
            
            fm.displayContentsOfDirectory(directoryPath);
       }

       public void displayFileInformationMethod(){
            System.out.println("This method takes FileName, DirectoryName as Parameters:");
            System.out.println("Enter the File Name :");
            fileName=sc.next();
            System.out.println("Enter direcory Name if the file is in root Directory then enter 'null' or 'NULL':");
            directoryName=sc.next();
           
            fm.displayFileInformation(directoryName, fileName);
       }

       public void searchFilesMethod(){
            System.out.println("This takes searchPattern as the only parameter");
            System.out.println("Enter the search Pattern:");
            searchPattern=sc.nextLine();
            
            List<String> ListofFiles=new ArrayList<String>();
            ListofFiles=fm.searchFiles(searchPattern);
            System.out.println(ListofFiles);
       }


    public static void main(String[] args) {
        Task fms=new Task();
       
        System.out.println(".....................Welcome to File Management System....................\n This creates file and folders in root directory here User has to enter root directory Manually\n\t Available Operations in this System are:");
        System.out.println("\t 1.File Creation\n \t 2.File Deletion\n \t 3.Directory creation\n \t 4.Directory Deletion\n \t 5.Change Files from One Directory to Other Directory\n \t 6.Display File Information\n \t 7. Search files\n \t 8.Update content \n \t 9.Display root directory");
        System.out.println("Rules to Remember .... \n 1.Enter FilesName without any extension \n 2.Enter Directories Name including the'//' in between ex:- 'Directory1//Directory2' but do not enter backslash '//' before the directory or after the directory names");
        System.out.println("Here root directory is:-'C://java//' ");
        try (Scanner sc = new Scanner(System.in)) {

            
            //fms.rootDirectoryy="C://java//"; //sc.next();

            int option=-1;
            do {
                System.out.println("\t 1.File Creation\n \t 2.File Deletion\n \t 3.Directory creation\n \t 4.Directory Deletion\n \t 5.Change Files from One Directory to Other Directory\n \t 6.Display File Information\n \t 7. Search files\n \t 8.Display root Directory\n \t 9.Update contents\n \t 10.Exit\n ");
                System.out.println("Now enter your option:");
                option=sc.nextInt();
                
                switch (option) {
                    case 1:
                        fms.CreateFileMethod();
                        break;
                    case 2:
                        fms.deleteFileMethod();
                        break;
                    case 3:
                        fms.createDirectoryMethod();
                        break;
                    case 4:
                        fms.deleteDirectoryMethod();
                        break;
                    case 5:
                        fms.changeDirectoryMethod();
                        break;
                    case 6:
                        fms.displayFileInformationMethod();
                        break;
                    case 7:
                        fms.searchFilesMethod();
                        break;
                    case 8:
                        fms.displayContentsMethod();
                        break;
                    case 9:
                        fms.updateContentMethod();
                        break;
                    case 10:
                        break;
                    default:
                        System.out.println("Enter valid Option");
                        break;
                }
            } while (option!=10);
        }
    }
}