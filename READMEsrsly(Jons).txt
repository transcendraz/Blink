Some small improvements 
writing it here since I don't know where all the relevant css is located

Make the buttons hoverable (change color when mousing over a button)
in the nav.css: 

a:hover {
    background-color: #ff9999;
} 

I made a logo!
https://imgur.com/a/9iR6YxN   lmk if its fine

html/css for it
<img src="https://i.imgur.com/gjMbF0s.png" id="logo" >
    #logo
    {
    	float:left;
    	padding-left:20px;
    	padding-right:10px;
    	width: 100px;
    	height: auto;
    }

side note: is there a specific screen size we will be basing the css off of? 
	   some of the css I am using will probably not work on other resolutions

made some changes to the chatroom css also so that when a lot of users are logged in,
the part that displays the ppl inside the room will overflow

attempted to add emojis to chat room...will have to figure out more about how to use the
actual api but i do have it connected to a api
