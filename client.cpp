/*
* Huyen Tran
* client.cpp program to connect to web server
*/
#include <arpa/inet.h>
#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#include <iostream>
#include <unistd.h>
#define PORT 8080

using namespace std;

int main(int argc, char **argv)
{
	int sockfd = 0, valread;
	struct sockaddr_in serv_addr;
	string name = "test-client"; //default
	char buffer[1024] = { 0 };
	int ch;
	bool n = false;

	//get name from user
	while((ch = getopt(argc, argv, "n:")) != -1){
    switch (ch){
      case 'n':
        name = strdup(optarg);
        n = true;
        break;
      default:
        printf("usage: ./client [-n name]\n");
        break;
    }
  }

	//combine name into our request
	string req = "GET https://localhost:8080/greeting?client_name=" +  name + "\r\n\r\n";
	char* request = new char[req.length()];
	strncpy(request, req.c_str(), req.length());

	//create socket with IPv4(domain), TCP (reliable type), IP (protocol)
	if ((sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
		cout << "Socket creation error" << endl;
		exit(1);
	}

	//server info
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_port = htons(PORT);

	// Convert IPv4 and IPv6 addresses from text to binary
	// form
	if (inet_pton(AF_INET, "127.0.0.1", &serv_addr.sin_addr)
		<= 0) {
		cout << "Invalid address/ Address not supported \n" << endl;
		exit(1);
	}

	//connect our socket to server address
	if (connect(sockfd, (struct sockaddr*)&serv_addr,
				sizeof(serv_addr))
		< 0) {
		cout << "Connection failed." << endl;
		exit(1);
	}

	//send request to server
	send(sockfd, request, strlen(request), 0);

	valread = read(sockfd, buffer, 1024);

  //did we read anything from server?
  if(valread < 0){
    cout << "unable to read from server!";
    exit(1);
  }
  else{
    //success!
    printf("[server]: %s\n", buffer);
  }

	//free mem
	free(request);

	return 0;
}
