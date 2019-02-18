##	Mancala Game(Kalah)

#	Description 

	The assignment is implementing Kalah game(or mancala game).Mancala is playing with 2 players.
	Every player has their own pit to allowed to play and one House(Kalah). You can find the details of game on https://en.wikipedia.org/wiki/Kalah.
	Please note that the Wikipedia article explains 3 and 4-stone Kalah; but this game implemented as 6-stone here. 

#	Project frameworks	:
		Java 1.8
		Spring Boot
		Rest-Jersey
		Maven
		Test RestTemplate Spring

#	How to run the project. 
	
	Import the project to your favorite IDE. Update the project dependencies by maven and find the ChallengeApplication.java 
	then right click and run the project as Spring Boot app.It will start as a SpringBoot application.
	
#	Implementation
	
	Project made by 2 different Rest services which are creating the game and playing the game. 
	You can call these services by Postman or other Rest service caller programs.
	
	1- Creation of the Game:
		This service for initialize a new game structure and return the created new game id plus game the game url which linked to see the created game.
		
			Please find the service specifications.
		
			- End Point			:	http://<host>:<port>/games
			- HTTP Code			:	201 (Created)
			- Response Type		:	POST
			- Response Example	:	{"id": 1001,"uri": "http://localhost:8080/games/1001"}   
										
										*id is unique game number
										*uri is an end-point linked with created game.  
										
													
		2. Making Moves: 			
			The game must play with 2 player and provide multiple games. This service present to play the game with the players allowed pits. Players can play after creation of the game by the service mentioned above.
				
			Service Specifications.
		
			-End Point 			: 	http://<host>:<port>/games/{gameId}/pits/{pitId}
			-HTTP Code			:	200 (OK)
			-Response Type		:	Put
			-Response Example	:	{"id": 1002,"uri": "http://localhost:8080/games/1002","status":{
											    "1": "6",
											    "2": "0",
											    "3": "7",
											    "4": "7",
											    "5": "7",
											    "6": "7",
											    "7": "1",
											    "8": "7",
											    "9": "6",
											    "10": "6",
											    "11": "6",
											    "12": "6",
											    "13": "6",
											    "14": "0"
											}
										}
										
			-Logic : Player1 is the first player of the game and player1 has 1,2,3,4,5,6 pits and the 7th pit is player1's house(kalah).
			 Whereas, player2 has 8,9,10,11,12,13 pits and 14th pit is player2's house(kalah). The player who sow the last stone to his/her own House,
			 the player earn extra round.By this service players can play just by changing pitId in order to rules of Kalah.
			 Game ends when a player finish all stones in his/her pits. 
 			
 			
 			 
 			 