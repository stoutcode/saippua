# Upload

For upload you need to insert path to your file in DropboxFunctions.js upload() -function on line 80:
query_str = 'token='+accesstoken+'&path=/home/stout/Documents/Code/saippua/demo2/task2/DropBoxClient/psyduck.png';
"/home/stout/Documents/Code/saippua/demo2/task2/DropBoxClient/psyduck.png" <- replace that

# Connect

For connect button you need app key to Connect_servlet.java:
String appKey = "applicationkey";

# Get token

For Get token -button you need to add app key and app secret to Token_servlet.java
String appKey = "applicationkey";
String appSecret = "applicationsecret";

# Misc

In case app does not work, update app key and app secret
