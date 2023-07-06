#Frontend Dockerfile

#Use Nginx image as the base image
FROM --platform=linux/amd64 nginx:latest

#install curl -used to debug
RUN apt-get update && apt-get install -y curl

#Remove the default Nginx configuration file
RUN rm /etc/nginx/conf.d/default.conf

#Copy the Nginx configuration file
COPY nginx.conf /etc/nginx/conf.d/default.conf

#Copy the React build to the working directory
COPY ./build /usr/share/nginx/html

#Expose port 80 for Nginx
EXPOSE 80