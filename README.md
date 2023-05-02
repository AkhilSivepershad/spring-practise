# spring-practise
This project is intended as a warm up to spring-boot and using github for continuous development.


## There are 2 ways to run this app
### 1
Navigate to the src\main\docker folder and run the command docker-compose up\
This will generate the 3 images and run the application on docker.
### 2
Start the application in spring boot.\
Start running an activeMQ instance (locally or on docker)

## How to use activeMQ
Once you have activeMQ running, navigate to http://localhost:8161/admin/queues.jsp \
Ensure you have two queues running "input-queue" and "output-queue"\
Post to the "input-queue" using the following format\

