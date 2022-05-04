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
#define PORT 8080

using namespace std;

int main(int argc, char const* argv[])
{
	int sock = 0, valread;
	struct sockaddr_in serv_addr;
	char* request = "GET https://localhost:8080/greeting?name=Bobby\r\n\r\n";
	char buffer[1024] = { 0 };
	if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
		cout << "Socket creation error" << endl;
		exit(1);
	}

	serv_addr.sin_family = AF_INET;
	serv_addr.sin_port = htons(PORT);

	// Convert IPv4 and IPv6 addresses from text to binary
	// form
	if (inet_pton(AF_INET, "127.0.0.1", &serv_addr.sin_addr)
		<= 0) {
		cout << "Invalid address/ Address not supported \n" << endl;
		exit(1);
	}

	if (connect(sock, (struct sockaddr*)&serv_addr,
				sizeof(serv_addr))
		< 0) {
		cout << "Connection failed." << endl;
		exit(1);
	}
	send(sock, request, strlen(request), 0);

	valread = read(sock, buffer, 1024);

  //did we read anything from server?
  if(valread < 0){
    cout << "unable to read from server!";
    exit(1);
  }
  else{
    //success!
    printf("Message from server: %s\n", buffer);
  }
	return 0;
}
