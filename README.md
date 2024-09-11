# Photouploader

## Project Overview

This project is a file upload Web applicaion based on Spring Boot. It was created to collect photos and videos from attendees of my wedding. The final storage location is assumed to be a NAS (Network Attached Storage). Therefore, when running the JAR file, you need to provide the path to the network drive along with the username and password.

## Usage
First, to run the application, execute the JAR file as follows. You need to specify the path for the files to be uploaded using the ```--location``` parameter:
```
java -jar photouploader.jar --location=\\\\mynas\\shared-folder\\
```

Once the JAR package is running, you can access the application through a web browser. By default, it is accessible at ```localhost:8080```. Upon accessing, you will be prompted to enter three parameters. These parameters are required to create a folder before uploading the files.